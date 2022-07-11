package com.musinsa.homework.thewayhj.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Description;
import javax.persistence.*;
import java.math.BigInteger;

/**
 * @since       2022.07.07
 * @author      thewayhj
 * @description CategoryMain
 **/

@Data
@NoArgsConstructor
@Entity
@Table(name = "ITME")
@Description("ITEM 정보")
public class Item {
    @Id
    @NotNull
    @GeneratedValue
    @Column(columnDefinition = "INT NOT NULL COMMENT STRINGDECODE('ITEM NUMBER')")
    private int itemNo;

    @NotNull
    @Column(columnDefinition = "INT COMMENT STRINGDECODE('서브 카테고리 ID')")
    private String categoryId;

    @NotNull
    @Column(columnDefinition = "VARCHAR(200) COMMENT STRINGDECODE('ITEM명')")
    private String itemName;

    @NotNull
    @Column(columnDefinition = "VARCHAR(1000) COMMENT STRINGDECODE('ITEM 설명')")
    private String description;

    @NotNull
    @Column(columnDefinition = "BIGINT COMMENT STRINGDECODE('ITEM 가격')")
    private BigInteger price;

    @NotNull
    @Column(columnDefinition = "VARCHAR(200) COMMENT STRINGDECODE('브랜드명')")
    private String brandName;
}
