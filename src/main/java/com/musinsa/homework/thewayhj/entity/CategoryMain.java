package com.musinsa.homework.thewayhj.entity;

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
@Table(name = "categoryMain")
@Description("메인 카테고리")
public class CategoryMain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CATEGORY_ID", columnDefinition = "INT NOT NULL COMMENT STRINGDECODE('메인 카테고리 ID')")
    private int categoryId;

    @Column(name="CATEGORY_NAME", columnDefinition = "VARCHAR(20) COMMENT STRINGDECODE('메인 카테고리명')")
    private String categoryName;
}
