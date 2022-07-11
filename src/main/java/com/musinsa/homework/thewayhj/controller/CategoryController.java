package com.musinsa.homework.thewayhj.controller;

import com.musinsa.homework.thewayhj.dto.CategoryDto;
import com.musinsa.homework.thewayhj.service.CategoryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
	 * @return Object
	 */
	@ApiOperation(value = "Main Category 전체 조회", notes = "전체 카테고리 정보 조회")
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found - 조회된 카테고리 데이터 없음."),
	})
	@GetMapping(value = "/main")
	public Object getCategoryAll() {
		return Optional.ofNullable(categoryService.getMainCategory())
				.orElseThrow(()-> new RuntimeException());
	}

	/**
	 * Sub Category 조회
	 * @param categoryId 카테고리 ID
	 * @return Object
	 */
	@ApiOperation(value = "Sub Category 조회", notes = "상위 카테고리 ID 정보를 받아 해당 카테고리의 하위 모든 카테고리를 조회.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "categoryId", required = true, example = "스니커즈", value = "메인 카테고리 id", paramType = "path")
	})
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 204, message = "조회된 카테고리 데이터 없음.")
	})
	@GetMapping(value = "/main/{categoryId}")
	public Object getCategory(@PathVariable int categoryId) {
		return Optional.ofNullable(categoryService.get(categoryId))
				.orElseThrow(()-> new RuntimeException());
	}

	/**
	 * Item 정보 조회
	 * @param categoryId 카테고리 ID
	 * @return Object
	 */
	@ApiOperation(value = "Item 정보 조회", notes = "서브 카테고리 ID 정보를 받아 해당 서브 카테고리의 하위 모든 Item을 조회.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "categoryId", required = true,  example = "스니커즈", value = "서브 카테고리명 id")
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 204, message = "조회된 카테고리 데이터 없음.")
	})
	@GetMapping(value = "/sub/{categoryId}{itemId}")
	public Object getItem(@PathVariable int categoryId) {
		// TODO
		return Optional.ofNullable(categoryService.get(categoryId))
				.orElseThrow(()-> new RuntimeException());
	}

	/**
	 * Main Category 정보 저장
	 * @return Object
	 */
	@ApiOperation(value = "Main Category 정보 저장", notes = "메인 카테고리 정보 저장.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mainCategoryName", example = "스니커즈", value = "메인 카테고리명 (메인 카테고리 등록 시)")
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request - 필수 데이터 없음. / 조회된 정보 없음.")
	})
	@PostMapping(value = "/main")
	public Object saveMainCategory(CategoryDto.Request.mainSave categoryDto) {
		return Optional.ofNullable(categoryService.mainSave(categoryDto))
				.orElseThrow(() -> new RuntimeException());
	}

	/**
	 * Sub Category 정보 저장
	 * @return Object
	 */
	@ApiOperation(value = "Sub Category 정보 저장", notes = "서브 카테고리 정보 저장.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mainCategoryId", value = "메인 카테고리 id"),
			@ApiImplicitParam(name = "subCategoryName", example = "상의 티셔츠", value = "서브 카테고리명")
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request - 필수 데이터 없음. / 조회된 정보 없음.")
	})
	@PostMapping(value = "/sub")
	public Object saveSubCategory(CategoryDto.Request.subSave categoryDto) {
		return Optional.ofNullable(categoryService.subSave(categoryDto))
				.orElseThrow(() -> new RuntimeException());
	}

	/**
	 * Main Category 정보 수정
	 * @return Object
	 */
	@ApiOperation(value = "Main Category 정보 수정", notes = "메인 카테고리 정보 수정.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "categoryId", required = true, example = "3", value = "메인 카테고리 id"),
			@ApiImplicitParam(name = "categoryName", example = "패션 소품", value = "메인 카테고리명")
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request - 필수 데이터 없음. / 조회된 정보 없음.")
	})
	@PutMapping(value = "/main")
	public Object modifyMainCategory(CategoryDto.Request.mainModify categoryDto) {
		return Optional.ofNullable(categoryService.mainModify(categoryDto))
				.orElseThrow(() -> new RuntimeException());
	}

	/**
	 * Sub Category 정보 수정
	 * @return Object
	 */
	@ApiOperation(value = "Sub Category 정보 수정", notes = "서브 카테고리 정보 수정.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "categoryId", required = true, example = "5", value = "서브 카테고리 id"),
			@ApiImplicitParam(name = "categoryName", example = "숏 팬츠", value = "서브 카테고리명")
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request - 필수 데이터 없음. / 조회된 정보 없음.")
	})
	@PutMapping(value = "/sub")
	public Object modifySubCategory(CategoryDto.Request.subModify categoryDto) {
		return Optional.ofNullable(categoryService.subModify(categoryDto))
				.orElseThrow(() -> new RuntimeException());
	}

	/**
	 * Item 정보 수정
	 * @return Object
	 */
	@ApiOperation(value = "Item 정보 수정", notes = "아이템 정보 수정.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "categoryId", required = true, example = "5", value = "Item 카테고리 id"),
			@ApiImplicitParam(name = "categoryName", example = "숏 팬츠", value = "Item 카테고리명"),
			@ApiImplicitParam(name = "categoryName", example = "숏 팬츠", value = "Item 카테고리명"),
			@ApiImplicitParam(name = "categoryName", example = "숏 팬츠", value = "Item 카테고리명"),
			@ApiImplicitParam(name = "categoryName", example = "숏 팬츠", value = "Item 카테고리명")
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request - 필수 데이터 없음. / 조회된 정보 없음.")
	})
	@PutMapping(value = "/item")
	public Object modifyItemCategory(CategoryDto.Request.itemModify categoryDto) {
		// TODO
		return Optional.ofNullable(categoryService.itemModify(categoryDto))
				.orElseThrow(() -> new RuntimeException());
	}

}
