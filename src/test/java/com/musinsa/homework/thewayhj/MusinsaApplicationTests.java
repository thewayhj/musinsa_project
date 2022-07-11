package com.musinsa.homework.thewayhj;

import com.musinsa.homework.thewayhj.dto.DeleteCategoryDto;
import com.musinsa.homework.thewayhj.dto.ModifyCategoryDto;
import com.musinsa.homework.thewayhj.dto.SaveCategoryDto;
import com.musinsa.homework.thewayhj.exception.BadRequestException;
import com.musinsa.homework.thewayhj.exception.ClientErrorException;
import com.musinsa.homework.thewayhj.exception.ConflictException;
import com.musinsa.homework.thewayhj.exception.ForbiddenException;
import com.musinsa.homework.thewayhj.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ContextConfiguration
class MusinsaApplicationTests {

	@Autowired
	private CategoryService categoryService;
	@Test
	void musinsaTestCase() {
		Exception exception;
		String actualMessage;

		// READ
			/**
			 * @Description : Main Category 전체 조회
			 * @Return : List<GetCategoryDto.Response.MainCategoryList>
			 */
			System.out.println("[INFO] getMainCategoryList : " +categoryService.getMainCategoryList());

			/**
			 * @Description : Sub Category 전체 조회
			 * @Return : List<GetCategoryDto.Response.SubCategoryList>
			 */
			System.out.println("[INFO] getSubCategoryList(1) : " +categoryService.getSubCategoryList("1"));

			/**
			 * @Description : Sub Category 전체 조회 (존재하지 않는 catecoryId로 조회.)
			 * @Return : List<GetCategoryDto.Response.SubCategoryList>
			 */
			System.out.println("[INFO] getSubCategoryList(100) : " +categoryService.getSubCategoryList("100"));

			/**
			 * @Description : Sub Category 전체 조회 (잘못된 정보로 요청.)
			 * @Exception : Bad Request
			 */
			exception = assertThrows(BadRequestException.class, () -> {
				categoryService.getSubCategoryList("스니커즈");
			});
			actualMessage = exception.toString();
			assertTrue(actualMessage.contains("BadRequestException"));
			System.out.println("[ERROR] : getSubCategoryList BadRequestException error (categoryId is Not Number)");


			/**
			 * @Description : item 전체 조회
			 * @Return : List<GetCategoryDto.Response.SubCategoryList>
			 */
			System.out.println("[INFO] getItemList(1, '') : " +categoryService.getItemList("1", ""));

			/**
			 * @Description : item 전체 조회 (잘못된 정보로 요청.)
			 * @Exception : Bad Request
			 */
			exception = assertThrows(BadRequestException.class, () -> {
				categoryService.getItemList("아우터", "");
			});
			actualMessage = exception.toString();
			assertTrue(actualMessage.contains("BadRequestException"));
			System.out.println("[ERROR] : getItemList BadRequestException error (categoryId or subCategoryId is not Number)");

			/**
			 * @Description : subCategory에 해당하는 item 조회 (
			 * @Return : List<GetCategoryDto.Response.SubCategoryList>
			 */
			System.out.println("[INFO] getItemList(1, 1) : " +categoryService.getItemList("1", "1"));

			/**
			 * @Description : 존재하지 않는 subCategory 정보로 item 조회 (
			 * @Return : List<GetCategoryDto.Response.SubCategoryList>
			 */
			System.out.println("[INFO] getItemList(1, 100) : " +categoryService.getItemList("1", "100"));

			/**
			 * @Description : Sub Category 전체 조회 (잘못된 정보로 요청.)
			 * @Exception : Bad Request
			 */
			exception = assertThrows(BadRequestException.class, () -> {
				categoryService.getItemList("1", "신발");
			});
			actualMessage = exception.toString();
			assertTrue(actualMessage.contains("BadRequestException"));
			System.out.println("[ERROR] : getItemList BadRequestException error (categoryId or subCategoryId is not Number)");

		// CREATE
			/**
			 * @Description : Main Category 저장
			 * @Return : SaveCategoryDto.Response.MainCategory
			 */
			SaveCategoryDto.Request.MainCategory mainCategory = new SaveCategoryDto.Request.MainCategory();
			mainCategory.setMainCategoryName("스포츠/레저");
			System.out.println("[INFO] mainSave : " +categoryService.mainSave(mainCategory));

			/**
			 * @Description : Main Category 저장 (잘못된 정보로 요청.)
			 * @Return : Bad Request
			 */
			mainCategory.setMainCategoryName("");
			exception = assertThrows(BadRequestException.class, () -> {
				categoryService.mainSave(mainCategory);
			});
			actualMessage = exception.toString();
			assertTrue(actualMessage.contains("BadRequestException"));
			System.out.println("[ERROR] : mainSave BadRequestException error (Not Exists Main CategoryName)");

			/**
			 * @Description : Main Category 저장 (table에 이미 존재하는 카테고리 이름으로 저장 요청.)
			 * @Return : CONFLICT
			 */
			mainCategory.setMainCategoryName("신발");
			exception = assertThrows(ConflictException.class, () -> {
				categoryService.mainSave(mainCategory);
			});
			actualMessage = exception.toString();
			assertTrue(actualMessage.contains("ConflictException"));
			System.out.println("[ERROR] : mainSave ConflictException error");

			/**
			 * @Description : Sub Category 저장
			 * @Return : SaveCategoryDto.Response.SubCategory
			 */
			SaveCategoryDto.Request.SubCategory subCategory = new SaveCategoryDto.Request.SubCategory();
			subCategory.setMainCategoryId(1);
			subCategory.setSubCategoryName("민소매 티셔츠");
			System.out.println("[INFO] subCategory : " +categoryService.subCategory(subCategory));

			/**
			 * @Description : Sub Category 저장 (main 카테고리 id 정보 없음.)
			 * @Return : Bad Request
			 */
			subCategory.setMainCategoryId(100);
			subCategory.setSubCategoryName("민소매 티셔츠");
			exception = assertThrows(BadRequestException.class, () -> {
				categoryService.subCategory(subCategory);
			});
			actualMessage = exception.toString();
			assertTrue(actualMessage.contains("BadRequestException"));
			System.out.println("[ERROR] : subCategory BadRequestException error (Not Exists Main CategoryId)");

			/**
			 * @Description : Sub Category 저장 (sub 카테고리 이름 없음.)
			 * @Return : Bad Request
			 */
			subCategory.setMainCategoryId(1);
			subCategory.setSubCategoryName("");
			exception = assertThrows(BadRequestException.class, () -> {
				categoryService.subCategory(subCategory);
			});
			actualMessage = exception.toString();
			assertTrue(actualMessage.contains("BadRequestException"));
			System.out.println("[ERROR] : subCategory BadRequestException error (Not Exists Sub CategoryName)");

			/**
			 * @Description : Item 정보 저장
			 * @Return : SaveCategoryDto.Response.ItemCategory
			 */
			SaveCategoryDto.Request.ItemSave itemSave = new SaveCategoryDto.Request.ItemSave();
			itemSave.setCategoryId(1);
			itemSave.setItemName("아디다스 티셔츠");
			itemSave.setDescription("땀 흡수 잘되는 아디다스 티셔츠");
			itemSave.setPrice(BigInteger.valueOf(18900));
			itemSave.setBrandName("아디다스");
			System.out.println("[INFO] getMainCategoryList : " +categoryService.itemSave(itemSave));


			/**
			 * @Description : Item 정보 저장 (필수 데이터 정보 없음.)
			 * @Return : Bad Request
			 */
			itemSave.setItemName("");
			itemSave.setBrandName("");
			exception = assertThrows(BadRequestException.class, () -> {
				categoryService.itemSave(itemSave);
			});
			actualMessage = exception.toString();
			assertTrue(actualMessage.contains("BadRequestException"));
			System.out.println("[ERROR] : itemSave BadRequestException error (No required data)");

		// UPDATE
			/**
			 * @Description : Main Category 수정
			 * @Return : ModifyCategoryDto.Response.MainCategory
			 */
			ModifyCategoryDto.Request.MainModify mainModify = new ModifyCategoryDto.Request.MainModify();
			mainModify.setCategoryId(1);
			mainModify.setCategoryName("상의_수정");
			System.out.println("[INFO] mainModify : " +categoryService.mainModify(mainModify));

			/**
			 * @Description : Main Category 수정 (테이블에 없는 id로 데이터 수정 시도.)
			 * @Return : Bad Request
			 */
			mainModify.setCategoryId(100);
			exception = assertThrows(BadRequestException.class, () -> {
				categoryService.mainModify(mainModify);
			});
			actualMessage = exception.toString();
			assertTrue(actualMessage.contains("BadRequestException"));
			System.out.println("[ERROR] : mainModify BadRequestException error (Not Exists Main CategroyId)");


			/**
			 * @Description : Sub Category 수정
			 * @Return : ModifyCategoryDto.Response.SubCategory
			 */
			ModifyCategoryDto.Request.SubModify subModify = new ModifyCategoryDto.Request.SubModify();
			subModify.setCategoryId(1);
			subModify.setChangeCategoryId(3);
			subModify.setSubCategoryId(2);
			subModify.setCategoryName("긴소매 티셔츠_수정");
			System.out.println("[INFO] subModify : " +categoryService.subModify(subModify));

			/**
			 * @Description : Sub Category 수정 (테이블에 없는 id로 데이터 수정 시도.)
			 * @Return : Bad Request
			 */
			subModify.setSubCategoryId(100);
			exception = assertThrows(BadRequestException.class, () -> {
				categoryService.subModify(subModify);
			});
			actualMessage = exception.toString();
			assertTrue(actualMessage.contains("BadRequestException"));
			System.out.println("[ERROR] : subModify BadRequestException error (Not Exists Sub CategroyId)");

			/**
			 * @Description : Item 정보 수정
			 * @Return : ModifyCategoryDto.Response.SubCategory
			 */
			ModifyCategoryDto.Request.ItemModify itemModify = new ModifyCategoryDto.Request.ItemModify();
			itemModify.setItemNo(1);
			itemModify.setCategoryId(2);
			itemModify.setItemName("트위치 로고 스탠다드 후드 집업");
			itemModify.setDescription("무신사 한정판매 상품");
			itemModify.setPrice(BigInteger.valueOf(55300));
			itemModify.setBrandName("리");
			subModify.setCategoryName("긴소매 티셔츠_수정");
			System.out.println("[INFO] itemModify : " +categoryService.itemModify(itemModify));

			/**
			 * @Description : Item 정보 수정 (테이블에 없는 id로 데이터 수정 시도.)
			 * @Return : Bad Request
			 */
			itemModify.setItemNo(100);
			exception = assertThrows(BadRequestException.class, () -> {
				categoryService.itemModify(itemModify);
			});
			actualMessage = exception.toString();
			assertTrue(actualMessage.contains("BadRequestException"));
			System.out.println("[ERROR] : itemModify BadRequestException error (Not Exists Item No)");

		// DELETE
			/**
			 * @Description : Main Category 삭제
			 * @Return : DeleteCategoryDto.Response.MainCategory
			 */
			DeleteCategoryDto.Request.MainDelete mainDelete = new DeleteCategoryDto.Request.MainDelete();
			mainDelete.setMemberNo(1);
			mainDelete.setCategoryId(6);
			System.out.println("[INFO] mainDelete : " +categoryService.mainDelete(mainDelete));

			/**
			 * @Description : Main Category 삭제 (회원의 정보 없음.)
			 * @Return : Bad Request
			 */
			mainDelete.setMemberNo(100);
			exception = assertThrows(BadRequestException.class, () -> {
				categoryService.mainDelete(mainDelete);
			});
			actualMessage = exception.toString();
			assertTrue(actualMessage.contains("BadRequestException"));
			System.out.println("[ERROR] : mainDelete BadRequestException error (Not Exists Member)");

			/**
			 * @Description : Main Category 삭제 (해당 회원에게 삭제 권한 없음.)
			 * @Return : FORBIDDEN
			 */
			mainDelete.setMemberNo(2);
			exception = assertThrows(ForbiddenException.class, () -> {
				categoryService.mainDelete(mainDelete);
			});
			actualMessage = exception.toString();
			assertTrue(actualMessage.contains("ForbiddenException"));
			System.out.println("[ERROR] : mainDelete ForbiddenException error (No delete Permission)");

			/**
			 * @Description : Main Category 삭제 (선결 조건 실패 - 하위 카테고리 메뉴 존재.)
			 * @Return : PRECONDITION_FAILED
			 */
			mainDelete.setMemberNo(1);
			mainDelete.setCategoryId(1);
			exception = assertThrows(ClientErrorException.class, () -> {
				categoryService.mainDelete(mainDelete);
			});
			actualMessage = exception.toString();
			assertTrue(actualMessage.contains("ClientErrorException"));
			System.out.println("[ERROR] : mainDelete ClientErrorException error (Prerequisite failed)");

			/**
			 * @Description : Sub Category 삭제
			 * @Return : DeleteCategoryDto.Response.SubCategory
			 */
			DeleteCategoryDto.Request.SubDelete subDelete = new DeleteCategoryDto.Request.SubDelete();
			subDelete.setMemberNo(1);
			subDelete.setCategoryId(10);
			System.out.println("[INFO] subDelete : " +categoryService.subDelete(subDelete));

			/**
			 * @Description : Sub Category 삭제 (회원의 정보 없음.)
			 * @Return : Bad Request
			 */
			subDelete.setMemberNo(100);
			exception = assertThrows(BadRequestException.class, () -> {
				categoryService.subDelete(subDelete);
			});
			actualMessage = exception.toString();
			assertTrue(actualMessage.contains("BadRequestException"));
			System.out.println("[ERROR] : subDelete BadRequestException error (Not Exists Member)");

			/**
			 * @Description : Sub Category 삭제 (해당 회원에게 삭제 권한 없음.)
			 * @Return : FORBIDDEN
			 */
			subDelete.setMemberNo(2);
			exception = assertThrows(ForbiddenException.class, () -> {
				categoryService.subDelete(subDelete);
			});
			actualMessage = exception.toString();
			assertTrue(actualMessage.contains("ForbiddenException"));
			System.out.println("[ERROR] : subDelete ForbiddenException error (No delete Permission)");

			/**
			 * @Description : Sub Category 삭제 (선결 조건 실패 - 하위 카테고리 메뉴 존재.)
			 * @Return : PRECONDITION_FAILED
			 */
			subDelete.setMemberNo(1);
			subDelete.setCategoryId(1);
			exception = assertThrows(ClientErrorException.class, () -> {
				categoryService.subDelete(subDelete);
			});
			actualMessage = exception.toString();
			assertTrue(actualMessage.contains("ClientErrorException"));
			System.out.println("[ERROR] : subDelete ClientErrorException error (Prerequisite failed)");

			/**
			 * @Description : Item 정보 삭제
			 * @Return : DeleteCategoryDto.Response.SubCategory
			 */
			DeleteCategoryDto.Request.ItemDelete itemDelete = new DeleteCategoryDto.Request.ItemDelete();
			itemDelete.setMemberNo(1);
			itemDelete.setItemNo(10);
			System.out.println("[INFO] subDelete : " +categoryService.itemDelete(itemDelete));

			/**
			 * @Description : Item 정보 삭제 (회원의 정보 없음.)
			 * @Return : Bad Request
			 */
			itemDelete.setMemberNo(100);
			exception = assertThrows(BadRequestException.class, () -> {
				categoryService.itemDelete(itemDelete);
			});
			actualMessage = exception.toString();
			assertTrue(actualMessage.contains("BadRequestException"));
			System.out.println("[ERROR] : subDelete BadRequestException error (Not Exists Member)");

			/**
			 * @Description : Item 정보 삭제 (해당 회원에게 삭제 권한 없음.)
			 * @Return : FORBIDDEN
			 */
			itemDelete.setMemberNo(2);
			exception = assertThrows(ForbiddenException.class, () -> {
				categoryService.itemDelete(itemDelete);
			});
			actualMessage = exception.toString();
			assertTrue(actualMessage.contains("ForbiddenException"));
			System.out.println("[ERROR] : subDelete ForbiddenException error (No delete Permission)");
	}
}
