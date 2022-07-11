package com.musinsa.homework.thewayhj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;

public class SaveCategoryDto {
	public static class Request {
		@Data
		public static class MainCategory {
			@ApiModelProperty(example = "신발", notes = "메인 카테고리 Name", required = true)
			private String mainCategoryName;
		}

		@Data
		public static class SubCategory {
			@ApiModelProperty(example = "1", notes = "메인 카테고리 Id", required = true)
			private Integer mainCategoryId;

			@ApiModelProperty(example = "민소매 티셔츠", notes = "서브 카테고리 Name", required = true)
			private String subCategoryName;
		}

		@Data
		public static class ItemSave {
			@ApiModelProperty(example = "1", notes = "서브 카테고리 ID", required = true)
			private Integer categoryId;

			@ApiModelProperty(example = "크루 넥 반팔 티셔츠", notes = "ITEM명", required = true)
			private String itemName;

			@ApiModelProperty(example = "무신사 단독 판매 상품", notes = "ITEM 설명")
			private String description;

			@ApiModelProperty(example = "11900", notes = "ITEM 가격", required = true)
			private BigInteger price;

			@ApiModelProperty(example = "무신사 스탠타드", notes = "브랜드명", required = true)
			private String brandName;
		}
	}

	public static class Response {
		public static MainCategory mainCategory;
		public static SubCategory subCategory;
		public static ItemCategory itemCategory;

		@Data
		@NoArgsConstructor
		public static class MainCategory {
			@ApiModelProperty(example = "6", notes = "메인 카테고리 id")
			private String categoryId;

			@ApiModelProperty(example = "패션 소품", notes = "메인 카테고리명")
			private String categoryName;
		}

		@Data
		@NoArgsConstructor
		public static class SubCategory {
			@ApiModelProperty(example = "1", notes = "서브 카테고리 Id")
			private Integer subCategoryId;

			@ApiModelProperty(example = "", notes = "서브 카테고리 Name")
			private String subCategoryName;
		}

		@Data
		@NoArgsConstructor
		public static class ItemCategory {
			@ApiModelProperty("item no (index)")
			private Integer itemNo;

			@ApiModelProperty("item명")
			private String itemName;

			@ApiModelProperty("item 설명")
			private String description;

			@ApiModelProperty("item 가격")
			private BigInteger price;

			@ApiModelProperty("브랜드명")
			private String brandName;
		}
	}
}
