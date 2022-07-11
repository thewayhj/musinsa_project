package com.musinsa.homework.thewayhj.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Description;
import javax.persistence.*;

/**
 * @since       2022.07.07
 * @author      thewayhj
 * @description CategorySub
 **/
@Data
@NoArgsConstructor
@Entity
@Table(name = "CATEGORY_SUB")
@Description("서브 카테고리")
public class CategorySub {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT NOT NULL COMMENT STRINGDECODE('메인 카테고리 ID')")
    private int categoryId;

    @Column(columnDefinition = "VARCHAR(20) COMMENT STRINGDECODE('메인 카테고리명')")
    private String categoryName;
}
