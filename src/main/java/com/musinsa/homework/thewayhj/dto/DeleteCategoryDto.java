package com.musinsa.homework.thewayhj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DeleteCategoryDto {
	public static class Request {
		@Data
		public static class MainDelete {
			@ApiModelProperty(example = "1", notes = "회원 No")
			private Integer memberNo;

			@ApiModelProperty(example = "1", notes = "메인 카테고리 Id")
			private Integer categoryId;
		}

		@Data
		public static class SubDelete {
			@ApiModelProperty(example = "1", notes = "회원 No")
			private Integer memberNo;

			@ApiModelProperty(example = "1", notes = "서브 카테고리 Id")
			private Integer categoryId;
		}

		@Data
		public static class ItemDelete {
			@ApiModelProperty(example = "1", notes = "회원 No")
			private Integer memberNo;

			@ApiModelProperty(example = "1", notes = "item no (index)")
			private Integer itemNo;
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
			private Integer categoryId;
		}

		@Data
		@NoArgsConstructor
		public static class SubCategory {
			@ApiModelProperty(example = "1", notes = "서브 카테고리 Id")
			private Integer subCategoryId;
		}

		@Data
		@NoArgsConstructor
		public static class ItemCategory {
			@ApiModelProperty("item no (index)")
			private Integer itemNo;
		}
	}
}
