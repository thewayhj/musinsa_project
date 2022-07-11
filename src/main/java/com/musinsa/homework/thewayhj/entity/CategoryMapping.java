package com.musinsa.homework.thewayhj.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Description;
import javax.persistence.*;

/**
 * @since       2022.07.07
 * @author      thewayhj
 * @description CategoryMain
 **/

@Data
@NoArgsConstructor
@Entity
@Table(name = "CATEGORY_MAPPING")
@Description("카테고리 매핑 정보")
public class CategoryMapping {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT NOT NULL COMMENT STRINGDECODE('mapping table number')")
    private int mappingNo;

    @NotNull
    @Column(columnDefinition = "INT NOT NULL COMMENT STRINGDECODE('메인 카테고리 ID')")
    private int categoryId;

    @NotNull
    @Column(columnDefinition = "INT NOT NULL COMMENT STRINGDECODE('서브 카테고리 ID')")
    private int subCategoryId;
}
