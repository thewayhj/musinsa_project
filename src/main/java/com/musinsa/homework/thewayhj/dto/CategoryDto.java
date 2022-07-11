package com.musinsa.homework.thewayhj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

public class CategoryDto {
	public static class Request {
		@Data
		@Builder
		public static class mainSave {
			@ApiModelProperty(example = "신발", notes = "메인 카테고리 Name")
			private String mainCategoryName;
		}

		@Data
		@Builder
		public static class subSave {
			@ApiModelProperty(example = "1", notes = "메인 카테고리 Id")
			private Integer mainCategoryId;

			@ApiModelProperty(example = "민소매 티셔츠", notes = "서브 카테고리 Name")
			private String subCategoryName;
		}

		@Data
		@Builder
		public static class mainModify {
			@ApiModelProperty(example = "1", notes = "메인 카테고리 Id")
			private Integer categoryId;

			@ApiModelProperty(example = "패션 소품", notes = "메인 카테고리명")
			private String categoryName;
		}

		@Data
		@Builder
		public static class subModify {
			@ApiModelProperty(example = "1", notes = "서브 카테고리 Id")
			private Integer categoryId;

			@ApiModelProperty(example = "셔츠/블라우스", notes = "서브 카테고리 Name")
			private String categoryName;
		}

		@Data
		@Builder
		public static class itemModify {
			@ApiModelProperty(example = "1", notes = "서브 카테고리 ID")
			private Integer categoryId;

			@ApiModelProperty(example = "크루 넥 반팔 티셔츠", notes = "ITEM명")
			private String itemName;

			@ApiModelProperty(example = "무신사 단독 판매 상품", notes = "ITEM 설명")
			private String description;

			@ApiModelProperty(example = "11900", notes = "ITEM 가격")
			private BigInteger price;

			@ApiModelProperty(example = "무신사 스탠타드", notes = "브랜드명")
			private String brandName;
		}
	}

	public static class Response {
		@Data
		@Builder
		public static class subCategory {
			@ApiModelProperty(example = "1", notes = "서브 카테고리 Id")
			private Integer subCategoryId;

			@ApiModelProperty(example = "", notes = "서브 카테고리 Name")
			private String subCategoryName;
		}
	}
}
