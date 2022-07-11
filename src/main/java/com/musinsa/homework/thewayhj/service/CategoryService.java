package com.musinsa.homework.thewayhj.service;

import com.musinsa.homework.thewayhj.Dto.CategoryDto;
import com.musinsa.homework.thewayhj.entity.CategoryMain;
import com.musinsa.homework.thewayhj.entity.CategoryMapping;
import com.musinsa.homework.thewayhj.entity.CategorySub;
import com.musinsa.homework.thewayhj.entity.ResultForm;
import com.musinsa.homework.thewayhj.repository.CategoryMainRepository;
import com.musinsa.homework.thewayhj.repository.CategoryMappingRepository;
import com.musinsa.homework.thewayhj.repository.CategorySubRepository;
import com.musinsa.homework.thewayhj.util.MessageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * @since       2022.07.07
 * @author      thewayhj
 * @description Category Service
 **/
@Slf4j
@Service
public class CategoryService {

    @Autowired
    private CategoryMainRepository categoryMainRepository;

    @Autowired
    private CategorySubRepository categorySubRepository;

    @Autowired
    private CategoryMappingRepository categoryMappingRepository;

    public Object getMainCategory() {
        List<Object> result = new ArrayList<>();
        List<CategoryMain> categoryMainList;

        try {
            categoryMainList = categoryMainRepository.findAll();
        } catch (Exception e) {
            return MessageHelper.getException(HttpStatus.BAD_REQUEST);
        }
        result.add(ResultForm.builder().code(HttpStatus.OK.value()).message(HttpStatus.OK.name()).build());
        result.add(categoryMainList);
        return result;
    }

    public Object get(int categoryId) {
        List<Object> result = new ArrayList<>();
        List<CategorySub> categorySubList = new ArrayList<>();
        List<CategoryMapping> categoryMappingList;

        categoryMappingList = categoryMappingRepository.findByCategoryId(categoryId);
        if(categoryMappingList.isEmpty())
            return MessageHelper.getException(HttpStatus.NO_CONTENT);

        for (int i=0; i <categoryMappingList.size(); i++) {
            categorySubList.add(categorySubRepository.findByCategoryId(categoryMappingList.get(i).getSubCategoryId()));
        }

        result.add(ResultForm.builder().code(HttpStatus.OK.value()).message(HttpStatus.OK.name()).build());
        result.add(categorySubList);
        return result;
    }

    public Object mainSave(CategoryDto.Request.mainSave categoryDto) {
        CategoryMain categoryMain = new CategoryMain();

        if(ObjectUtils.isEmpty(categoryDto.getMainCategoryName()))
            return MessageHelper.getException(HttpStatus.BAD_REQUEST);

        categoryMain.setCategoryName(categoryDto.getMainCategoryName());
        if (!ObjectUtils.isEmpty(categoryMainRepository.findByCategoryName(categoryMain.getCategoryName())))
            return MessageHelper.getException(HttpStatus.CONFLICT);

        categoryMainRepository.save(categoryMain);
        return categoryMain;
    }

    public Object subSave(CategoryDto.Request.subSave categoryDto) {
        if(ObjectUtils.isEmpty(categoryDto.getSubCategoryName()))
            return MessageHelper.getException(HttpStatus.BAD_REQUEST);

        if(ObjectUtils.isEmpty(categoryMainRepository.findById(categoryDto.getMainCategoryId())))
            return MessageHelper.getException(HttpStatus.BAD_REQUEST);

        CategorySub categorySub = new CategorySub();
        CategoryMapping categoryMapping = new CategoryMapping();
        categorySub.setCategoryName(categoryDto.getSubCategoryName());
        categorySubRepository.save(categorySub);

        categoryMapping.setCategoryId(categoryDto.getMainCategoryId());
        categoryMapping.setSubCategoryId(categorySub.getCategoryId());
        categoryMappingRepository.save(categoryMapping);

        return categorySub;
    }

    public Object mainModify(CategoryDto.Request.mainModify categoryDto) {
        CategoryMain categoryMain = new CategoryMain();
        if(ObjectUtils.isEmpty(categoryMainRepository.findById(categoryDto.getCategoryId())))
            return MessageHelper.getException(HttpStatus.BAD_REQUEST);

        categoryMain.setCategoryId(categoryDto.getCategoryId());
        categoryMain.setCategoryName(categoryDto.getCategoryName());
        categoryMainRepository.save(categoryMain);

        return categoryMain;
    }

    public Object subModify(CategoryDto.Request.subModify categoryDto) {
        CategorySub categorySub = new CategorySub();
        if(ObjectUtils.isEmpty(categorySubRepository.findById(categoryDto.getCategoryId())))
            return MessageHelper.getException(HttpStatus.BAD_REQUEST);

        categorySub.setCategoryId(categoryDto.getCategoryId());
        categorySub.setCategoryName(categoryDto.getCategoryName());
        categorySubRepository.save(categorySub);

        return categorySub;
    }

    public Object itemModify(CategoryDto.Request.itemModify categoryDto) {
        return null;
    }
}
