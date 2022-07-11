package com.musinsa.homework.thewayhj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;

public class GetCategoryDto {
	public static class Request {
	}

	@Data
	public static class Response {
		public static MainCategoryList mainCategoryList;
		public static SubCategoryList subCategoryList;
		public static ItemCategoryList itemCategoryList;

		@Data
		@NoArgsConstructor
		public static class MainCategoryList {
			@ApiModelProperty("메인 카테고리 Id")
			private Integer categoryId;

			@ApiModelProperty("메인 카테고리 Name")
			private String categoryName;
		}

		@Data
		@NoArgsConstructor
		public static class SubCategoryList {
			@ApiModelProperty("서브 카테고리 Id")
			private Integer categoryId;

			@ApiModelProperty("서브 카테고리 Name")
			private String categoryName;
		}

		@Data
		@NoArgsConstructor
		public static class ItemCategoryList {
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
