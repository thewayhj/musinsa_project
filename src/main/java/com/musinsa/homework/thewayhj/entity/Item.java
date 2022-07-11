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
 * @description Item
 **/

@Data
@NoArgsConstructor
@Entity
@Table(name = "ITEM")
@Description("ITEM 정보")
public class Item {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT NOT NULL COMMENT STRINGDECODE('ITEM NUMBER')")
    private int itemNo;

    @NotNull
    @Column(columnDefinition = "INT NOT NULL COMMENT STRINGDECODE('서브 카테고리 ID')")
    private int categoryId;

    @NotNull
    @Column(columnDefinition = "VARCHAR(200) NOT NULL COMMENT STRINGDECODE('ITEM명')")
    private String itemName;

    @Column(columnDefinition = "VARCHAR(1000) COMMENT STRINGDECODE('ITEM 설명')")
    private String description;

    @NotNull
    @Column(columnDefinition = "BIGINTEGER NOT NULL COMMENT STRINGDECODE('ITEM 가격')")
    private BigInteger price;

    @NotNull
    @Column(columnDefinition = "VARCHAR(200) NOT NULL COMMENT STRINGDECODE('브랜드명')")
    private String brandName;
}
