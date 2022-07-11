package com.musinsa.homework.thewayhj.controller;

import com.musinsa.homework.thewayhj.dto.DeleteCategoryDto;
import com.musinsa.homework.thewayhj.dto.GetCategoryDto;
import com.musinsa.homework.thewayhj.dto.ModifyCategoryDto;
import com.musinsa.homework.thewayhj.dto.SaveCategoryDto;
import com.musinsa.homework.thewayhj.service.CategoryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * @since       2022.07.07
 * @author      thewayhj
 * @description Category Controller
 **/
@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * Main Category 전체 조회
	 * @return List<GetCategoryDto.Response.MainCategoryList>
	 */
	@ApiOperation(value = "Main Category 전체 조회", notes = "전체 카테고리 정보 조회")
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "ERROR")
	})
	@GetMapping(value = "/main")
	public List<GetCategoryDto.Response.MainCategoryList> getCategoryList() {
		return Optional.ofNullable(categoryService.getMainCategoryList())
				.orElseThrow(()-> new RuntimeException());
	}

	/**
	 * Sub Category 조회
	 * @param categoryId 카테고리 ID
	 * @return List<GetCategoryDto.Response.SubCategoryList>
	 */
	@ApiOperation(value = "Sub Category 조회", notes = "상위 카테고리 ID 정보를 받아 해당 카테고리의 하위 모든 카테고리를 조회.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "categoryId", required = true, example = "1", value = "메인 카테고리 id", paramType = "path")
	})
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 500, message = "ERROR")
	})
	@GetMapping(value = "/main/{categoryId}")
	public List<GetCategoryDto.Response.SubCategoryList> getSubCategoryList(@PathVariable(name = "categoryId") String categoryId) {
		return Optional.ofNullable(categoryService.getSubCategoryList(categoryId))
				.orElseThrow(()-> new RuntimeException());
	}

	/**
	 * Item 정보 조회
	 * @param categoryId 카테고리 ID
	 * @return List<GetCategoryDto.Response.ItemCategoryList>
	 */
	@ApiOperation(value = "Item 정보 조회", notes = "메인 카테고리 ID / 서브 카테고리 ID 정보를 받아 해당 서브 카테고리의 하위 모든 Item을 조회.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "categoryId", required = true,  example = "1", value = "메인 카테고리명 id", paramType = "path"),
			@ApiImplicitParam(name = "subCategoryId", example = "1", value = "서브 카테고리명 id", paramType = "path")
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "ERROR")
	})
	@GetMapping(value = {"/main/{categoryId}/sub", "/main/{categoryId}/sub/{subCategoryId}"})
	public List<GetCategoryDto.Response.ItemCategoryList> getItemList(@PathVariable(name = "categoryId") String categoryId, @PathVariable(name = "subCategoryId", required = false) String subCategoryId) {
		return Optional.ofNullable(categoryService.getItemList(categoryId, subCategoryId))
				.orElseThrow(()-> new RuntimeException());
	}

	/**
	 * Main Category 정보 저장
	 * @return SaveCategoryDto.Response.MainCategory
	 */
	@ApiOperation(value = "Main Category 정보 저장", notes = "메인 카테고리 정보 저장.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mainCategoryName", required = true, example = "패션 소품", value = "메인 카테고리명 (메인 카테고리 등록 시)")
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request - 필수 데이터 없음. / 조회된 정보 없음."),
			@ApiResponse(code = 500, message = "ERROR")
	})
	@PostMapping(value = "/main")
	public SaveCategoryDto.Response.MainCategory saveMainCategory(SaveCategoryDto.Request.MainCategory categoryDto) {
		return Optional.ofNullable(categoryService.mainSave(categoryDto))
				.orElseThrow(() -> new RuntimeException());
	}

	/**
	 * Sub Category 정보 저장
	 * @return SaveCategoryDto.Response.SubCategory
	 */
	@ApiOperation(value = "Sub Category 정보 저장", notes = "서브 카테고리 정보 저장.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mainCategoryId", required = true, value = "메인 카테고리 id", type = "int"),
			@ApiImplicitParam(name = "subCategoryName", required = true, example = "상의 티셔츠", value = "서브 카테고리명")
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request - 필수 데이터 없음. / 조회된 정보 없음."),
			@ApiResponse(code = 500, message = "ERROR")
	})
	@PostMapping(value = "/sub")
	public SaveCategoryDto.Response.SubCategory saveSubCategory(SaveCategoryDto.Request.SubCategory categoryDto) {
		return Optional.ofNullable(categoryService.subCategory(categoryDto))
				.orElseThrow(() -> new RuntimeException());
	}

	/**
	 * item 정보 저장
	 * @return SaveCategoryDto.Response.ItemCategory
	 */
	@ApiOperation(value = "item 정보 저장", notes = "아이템 정보 저장.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "itemName", required = true, example = "나이키 dry fit", value = "item명"),
			@ApiImplicitParam(name = "description", example = "땀이 금방 마르는 티셔츠 입니다.", value = "item 설명"),
			@ApiImplicitParam(name = "price", required = true, example = "17900", value = "item 가격", type = "int"),
			@ApiImplicitParam(name = "brandName", required = true, example = "나이키", value = "브랜드명")
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request - 필수 데이터 없음. / 조회된 정보 없음."),
			@ApiResponse(code = 500, message = "ERROR")
	})
	@PostMapping(value = "/item")
	public SaveCategoryDto.Response.ItemCategory saveItem(SaveCategoryDto.Request.ItemSave categoryDto) {
		return Optional.ofNullable(categoryService.itemSave(categoryDto))
				.orElseThrow(() -> new RuntimeException());
	}

	/**
	 * Main Category 정보 수정
	 * @return ModifyCategoryDto.Response.MainCategory
	 */
	@ApiOperation(value = "Main Category 정보 수정", notes = "메인 카테고리 정보 수정.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "categoryId", required = true, example = "3", value = "메인 카테고리 id", type = "int"),
			@ApiImplicitParam(name = "categoryName", example = "패션 소품", value = "메인 카테고리명")
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request - 필수 데이터 없음. / 조회된 정보 없음."),
			@ApiResponse(code = 500, message = "ERROR")
	})
	@PutMapping(value = "/main")
	public ModifyCategoryDto.Response.MainCategory modifyMainCategory(ModifyCategoryDto.Request.MainModify categoryDto) {
		return Optional.ofNullable(categoryService.mainModify(categoryDto))
				.orElseThrow(() -> new RuntimeException());
	}

	/**
	 * Sub Category 정보 수정
	 * @return ModifyCategoryDto.Response.SubCategory
	 */
	@ApiOperation(value = "Sub Category 정보 수정", notes = "서브 카테고리 정보 수정.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "categoryId", required = true, example = "5", value = "메인 카테고리 id", type = "int"),
			@ApiImplicitParam(name = "changeCategoryId", example = "1", value = "변경 할 메인 카테고리 id", type = "int"),
			@ApiImplicitParam(name = "subCategoryId", required = true, example = "5", value = "서브 카테고리 id", type = "int"),
			@ApiImplicitParam(name = "categoryName", example = "숏 팬츠", value = "서브 카테고리명")
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request - 필수 데이터 없음. / 조회된 정보 없음."),
			@ApiResponse(code = 500, message = "ERROR")
	})
	@PutMapping(value = "/sub")
	public ModifyCategoryDto.Response.SubCategory modifySubCategory(ModifyCategoryDto.Request.SubModify categoryDto) {
		return Optional.ofNullable(categoryService.subModify(categoryDto))
				.orElseThrow(() -> new RuntimeException());
	}

	/**
	 * Item 정보 수정
	 * @return ModifyCategoryDto.Response.ItemCategory
	 */
	@ApiOperation(value = "Item 정보 수정", notes = "아이템 정보 수정.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "itemNo", required = true, example = "5", value = "Item No"),
			@ApiImplicitParam(name = "categoryId", example = "1", value = "서브 카테고리 ID"),
			@ApiImplicitParam(name = "itemName", example = "나이키 티셔츠", value = "ITEM명"),
			@ApiImplicitParam(name = "description", example = "나이키 정품 한정 판매", value = "ITEM 설명"),
			@ApiImplicitParam(name = "price", example = "15900", value = "ITEM 가격"),
			@ApiImplicitParam(name = "brandName", example = "나이키", value = "브랜드명")
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request - 필수 데이터 없음. / 조회된 정보 없음."),
			@ApiResponse(code = 500, message = "ERROR")
	})
	@PutMapping(value = "/item")
	public ModifyCategoryDto.Response.ItemCategory modifyItemCategory(ModifyCategoryDto.Request.ItemModify categoryDto) {
		return Optional.ofNullable(categoryService.itemModify(categoryDto))
				.orElseThrow(() -> new RuntimeException());
	}

	/**
	 * main 카테고리 정보 삭제
	 * @return DeleteCategoryDto.Response.MainCategory
	 */
	@ApiOperation(value = "main 카테고리 정보 삭제", notes = "메인 카테고리 정보 삭제.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "categoryId", required = true, example = "5", value = "main 카테고리 id", type = "int"),
			@ApiImplicitParam(name = "memberNo", required = true, example = "5", value = "회원 No", type = "int")
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request - 필수 데이터 없음. / 조회된 정보 없음."),
			@ApiResponse(code = 500, message = "ERROR")
	})
	@DeleteMapping(value = "/main")
	public DeleteCategoryDto.Response.MainCategory deleteMainCategory(DeleteCategoryDto.Request.MainDelete categoryDto) {
		return Optional.ofNullable(categoryService.mainDelete(categoryDto))
				.orElseThrow(() -> new RuntimeException());
	}

	/**
	 * sub 카테고리 정보 삭제
	 * @return DeleteCategoryDto.Response.SubCategory
	 */
	@ApiOperation(value = "sub 카테고리 정보 삭제", notes = "서브 카테고리 정보 삭제.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "categoryId", required = true, example = "1", value = "서브 카테고리 Id", type = "int"),
			@ApiImplicitParam(name = "memberNo", required = true, example = "5", value = "회원 No", type = "int")
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request - 필수 데이터 없음. / 조회된 정보 없음."),
			@ApiResponse(code = 500, message = "ERROR")
	})
	@DeleteMapping(value = "/sub")
	public DeleteCategoryDto.Response.SubCategory deleteMainCategory(DeleteCategoryDto.Request.SubDelete categoryDto) {
		return Optional.ofNullable(categoryService.subDelete(categoryDto))
				.orElseThrow(() -> new RuntimeException());
	}

	/**
	 * item 정보 삭제
	 * @return DeleteCategoryDto.Response.ItemCategory
	 */
	@ApiOperation(value = "item 정보 삭제", notes = "아이템 정보 삭제.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "itemNo", required = true, example = "1", value = "Item No", type = "int"),
			@ApiImplicitParam(name = "memberNo", required = true, example = "5", value = "회원 No", type = "int")
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request - 필수 데이터 없음. / 조회된 정보 없음."),
			@ApiResponse(code = 500, message = "ERROR")
	})
	@DeleteMapping(value = "/item")
	public DeleteCategoryDto.Response.ItemCategory deleteMainCategory(DeleteCategoryDto.Request.ItemDelete categoryDto) {
		return Optional.ofNullable(categoryService.itemDelete(categoryDto))
				.orElseThrow(() -> new RuntimeException());
	}
}
