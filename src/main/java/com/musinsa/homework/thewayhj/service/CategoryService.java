package com.musinsa.homework.thewayhj.service;

import com.musinsa.homework.thewayhj.dto.DeleteCategoryDto;
import com.musinsa.homework.thewayhj.dto.GetCategoryDto;
import com.musinsa.homework.thewayhj.dto.ModifyCategoryDto;
import com.musinsa.homework.thewayhj.dto.SaveCategoryDto;
import com.musinsa.homework.thewayhj.entity.*;
import com.musinsa.homework.thewayhj.exception.BadRequestException;
import com.musinsa.homework.thewayhj.exception.ClientErrorException;
import com.musinsa.homework.thewayhj.exception.ConflictException;
import com.musinsa.homework.thewayhj.exception.ForbiddenException;
import com.musinsa.homework.thewayhj.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @since       2022.07.07
 * @author      thewayhj
 * @description Category Service
 **/
@Slf4j
@Service
public class CategoryService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CategoryMainRepository categoryMainRepository;

    @Autowired
    private CategorySubRepository categorySubRepository;

    @Autowired
    private CategoryMappingRepository categoryMappingRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<GetCategoryDto.Response.MainCategoryList> getMainCategoryList() {
        return categoryMainRepository.findAll().stream().map(
                list -> modelMapper.map(
                        list, GetCategoryDto.Response.MainCategoryList.class)
                ).collect(Collectors.toList());
    }

    public List<GetCategoryDto.Response.SubCategoryList> getSubCategoryList(String categoryId) {
        if(BooleanUtils.isFalse(categoryId.matches("-?\\d+")))
            throw new BadRequestException(HttpStatus.BAD_REQUEST);

        List<CategoryMapping> categoryMappingList = categoryMappingRepository.findByCategoryId(Integer.parseInt(categoryId));

        return categorySubRepository.findByCategoryIdIn(categoryMappingList.stream()
                                                                            .map(CategoryMapping::getSubCategoryId)
                                                                            .collect(Collectors.toList())
                ).stream()
                .map(list -> modelMapper.map(list, GetCategoryDto.Response.SubCategoryList.class))
                .collect(Collectors.toList());
    }

    public List<GetCategoryDto.Response.ItemCategoryList> getItemList(String categoryId, String subCategoryId) {
        if(BooleanUtils.isFalse(categoryId.matches("-?\\d+")) ||
                (StringUtils.isNotEmpty(subCategoryId) && BooleanUtils.isFalse(subCategoryId.matches("-?\\d+"))))
            throw new BadRequestException(HttpStatus.BAD_REQUEST);

        if(StringUtils.isEmpty(subCategoryId)) {
            List<CategoryMapping> categoryMappingList = categoryMappingRepository.findByCategoryId(Integer.parseInt(categoryId));
            return itemRepository.findByCategoryIdIn(categoryMappingList.stream()
                                                                        .map(CategoryMapping::getSubCategoryId)
                                                                        .collect(Collectors.toList())
                    ).stream()
                    .map(list -> modelMapper.map(list, GetCategoryDto.Response.ItemCategoryList.class))
                    .collect(Collectors.toList());
        }
        return itemRepository.findByCategoryId(Integer.parseInt(subCategoryId)).stream().map(
                list -> modelMapper.map(
                        list, GetCategoryDto.Response.ItemCategoryList.class)
                ).collect(Collectors.toList());
    }

    public SaveCategoryDto.Response.MainCategory mainSave(SaveCategoryDto.Request.MainCategory categoryDto) {
        CategoryMain categoryMain = new CategoryMain();

        if(ObjectUtils.isEmpty(categoryDto.getMainCategoryName()))
            throw new BadRequestException(HttpStatus.BAD_REQUEST);

        categoryMain.setCategoryName(categoryDto.getMainCategoryName());
        if (!ObjectUtils.isEmpty(categoryMainRepository.findByCategoryName(categoryMain.getCategoryName())))
            throw new ConflictException(HttpStatus.CONFLICT);

        return modelMapper.map(categoryMainRepository.save(categoryMain), SaveCategoryDto.Response.MainCategory.class);
    }

    public SaveCategoryDto.Response.SubCategory subCategory(SaveCategoryDto.Request.SubCategory categoryDto) {
        if(ObjectUtils.isEmpty(categoryDto.getSubCategoryName()))
            throw new BadRequestException(HttpStatus.BAD_REQUEST);

        if(ObjectUtils.isEmpty(categoryMainRepository.findById(categoryDto.getMainCategoryId())))
            throw new BadRequestException(HttpStatus.BAD_REQUEST);

        CategorySub categorySub = new CategorySub();
        CategoryMapping categoryMapping = new CategoryMapping();
        categorySub.setCategoryName(categoryDto.getSubCategoryName());

        categoryMapping.setCategoryId(categoryDto.getMainCategoryId());
        categoryMapping.setSubCategoryId(categorySub.getCategoryId());
        categoryMappingRepository.save(categoryMapping);

        return modelMapper.map(categorySubRepository.save(categorySub), SaveCategoryDto.Response.SubCategory.class);
    }

    public SaveCategoryDto.Response.ItemCategory itemSave(SaveCategoryDto.Request.ItemSave categoryDto) {
        if(categoryDto.getCategoryId() < 0 || StringUtils.isEmpty(categoryDto.getItemName()) ||
                StringUtils.isEmpty(categoryDto.getBrandName()) || categoryDto.getPrice().intValue() < 0 )
            throw new BadRequestException(HttpStatus.BAD_REQUEST);

        Item item = new Item();
        item.setCategoryId(categoryDto.getCategoryId());
        item.setItemName(categoryDto.getItemName());
        item.setDescription(categoryDto.getDescription());
        item.setPrice(categoryDto.getPrice());
        item.setBrandName(categoryDto.getBrandName());

        return modelMapper.map(itemRepository.save(item), SaveCategoryDto.Response.ItemCategory.class);
    }

    public ModifyCategoryDto.Response.MainCategory mainModify(ModifyCategoryDto.Request.MainModify categoryDto) {
        if(BooleanUtils.isFalse(String.valueOf(categoryDto.getCategoryId()).matches("-?\\d+")))
            throw new BadRequestException(HttpStatus.BAD_REQUEST);

        if(ObjectUtils.isEmpty(categoryMainRepository.findById(categoryDto.getCategoryId())))
            throw new BadRequestException(HttpStatus.BAD_REQUEST);

        CategoryMain categoryMain = new CategoryMain();
        categoryMain.setCategoryId(categoryDto.getCategoryId());
        categoryMain.setCategoryName(categoryDto.getCategoryName());
        categoryMainRepository.save(categoryMain);

        return modelMapper.map(categoryMainRepository.save(categoryMain), ModifyCategoryDto.Response.MainCategory.class);
    }

    public ModifyCategoryDto.Response.SubCategory subModify(ModifyCategoryDto.Request.SubModify categoryDto) {
        if(BooleanUtils.isFalse(String.valueOf(categoryDto.getCategoryId()).matches("-?\\d+")) ||
                BooleanUtils.isFalse(String.valueOf(categoryDto.getSubCategoryId()).matches("-?\\d+")))
            throw new BadRequestException(HttpStatus.BAD_REQUEST);

        if(ObjectUtils.isEmpty(categorySubRepository.findById(categoryDto.getSubCategoryId())))
            throw new BadRequestException(HttpStatus.BAD_REQUEST);

        CategoryMapping categoryMapping = categoryMappingRepository.findByCategoryIdAndSubCategoryId(categoryDto.getCategoryId(), categoryDto.getSubCategoryId());
        if(categoryMapping.getMappingNo() > 0 && (categoryDto.getCategoryId() != categoryDto.getChangeCategoryId())) {
            categoryMapping.setCategoryId(categoryDto.getChangeCategoryId());
            categoryMapping.setSubCategoryId(categoryDto.getSubCategoryId());
            categoryMappingRepository.save(categoryMapping);
        }

        CategorySub categorySub = new CategorySub();
        categorySub.setCategoryId(categoryDto.getSubCategoryId());
        categorySub.setCategoryName(categoryDto.getCategoryName());
        return modelMapper.map(categorySubRepository.save(categorySub), ModifyCategoryDto.Response.SubCategory.class);
    }

    public ModifyCategoryDto.Response.ItemCategory itemModify(ModifyCategoryDto.Request.ItemModify categoryDto) {
        if(BooleanUtils.isFalse(String.valueOf(categoryDto.getItemNo()).matches("-?\\d+")) ||
                BooleanUtils.isFalse(String.valueOf(categoryDto.getCategoryId()).matches("-?\\d+")) ||
                BooleanUtils.isFalse(String.valueOf(categoryDto.getPrice()).matches("-?\\d+")))
            throw new BadRequestException(HttpStatus.BAD_REQUEST);

        if(ObjectUtils.isEmpty(itemRepository.findById(categoryDto.getItemNo())))
            throw new BadRequestException(HttpStatus.BAD_REQUEST);

        Item item = new Item();
        item.setItemNo(categoryDto.getItemNo());
        item.setCategoryId(categoryDto.getCategoryId());
        item.setItemName(categoryDto.getItemName());
        item.setDescription(categoryDto.getDescription());
        item.setPrice(categoryDto.getPrice());
        item.setBrandName(categoryDto.getBrandName());

        return modelMapper.map(itemRepository.save(item), ModifyCategoryDto.Response.ItemCategory.class);
    }

    public DeleteCategoryDto.Response.MainCategory mainDelete(DeleteCategoryDto.Request.MainDelete categoryDto) {
        Optional<Member> member = memberRepository.findById(categoryDto.getMemberNo());
        if(ObjectUtils.isEmpty(member))
            throw new BadRequestException(HttpStatus.BAD_REQUEST);

        if(BooleanUtils.isFalse(member.get().getMemberIsAdmin()))
            throw new ForbiddenException(HttpStatus.FORBIDDEN);

        if(categoryMappingRepository.findByCategoryId(categoryDto.getCategoryId()).stream().count() > 0)
            throw new ClientErrorException(HttpStatus.PRECONDITION_FAILED);

        CategoryMain categoryMain = new CategoryMain();
        categoryMain.setCategoryId(categoryDto.getCategoryId());
        categoryMainRepository.delete(categoryMain);

        return modelMapper.map(categoryMain, DeleteCategoryDto.Response.MainCategory.class);
    }

    public DeleteCategoryDto.Response.SubCategory subDelete(DeleteCategoryDto.Request.SubDelete categoryDto) {
        Optional<Member> member = memberRepository.findById(categoryDto.getMemberNo());
        if(ObjectUtils.isEmpty(member))
            throw new BadRequestException(HttpStatus.BAD_REQUEST);

        if(BooleanUtils.isFalse(member.get().getMemberIsAdmin()))
            throw new ForbiddenException(HttpStatus.FORBIDDEN);

        if(itemRepository.findByCategoryId(categoryDto.getCategoryId()).stream().count() > 0)
            throw new ClientErrorException(HttpStatus.PRECONDITION_FAILED);

        categoryMappingRepository.deleteCategoryMappingByCategoryId(categoryDto.getCategoryId());

        CategorySub categorySub = new CategorySub();
        categorySub.setCategoryId(categoryDto.getCategoryId());
        categorySubRepository.delete(categorySub);

        return modelMapper.map(categorySub, DeleteCategoryDto.Response.SubCategory.class);
    }

    public DeleteCategoryDto.Response.ItemCategory itemDelete(DeleteCategoryDto.Request.ItemDelete categoryDto) {
        Optional<Member> member = memberRepository.findById(categoryDto.getMemberNo());
        if(ObjectUtils.isEmpty(member))
            throw new BadRequestException(HttpStatus.BAD_REQUEST);

        if(BooleanUtils.isFalse(member.get().getMemberIsAdmin()))
            throw new ForbiddenException(HttpStatus.FORBIDDEN);

        Item item = new Item();
        item.setItemNo(categoryDto.getItemNo());
        itemRepository.delete(item);
        return modelMapper.map(item, DeleteCategoryDto.Response.ItemCategory.class);
    }
}
