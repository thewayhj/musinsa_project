package com.musinsa.homework.thewayhj.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Description;
import javax.persistence.*;

/**
 * @since       2022.07.07
 * @author      thewayhj
 * @description Item
 **/

@Data
@NoArgsConstructor
@Entity
@Table(name = "MEMBER")
@Description("회원 정보")
public class Member {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT NOT NULL COMMENT STRINGDECODE('회원 번호')")
    private int memberNo;

    @NotNull
    @Column(columnDefinition = "VARCHAR(200) NOT NULL COMMENT STRINGDECODE('회원 ID')")
    private String memberId;

    @NotNull
    @Column(columnDefinition = "VARCHAR(100) NOT NULL COMMENT STRINGDECODE('회원명')")
    private String memberName;

    @Column(columnDefinition = "VARCHAR(100) COMMENT STRINGDECODE('닉네임')")
    private String nickname;

    @Column(columnDefinition = "TINYINT COMMENT STRINGDECODE('관리자 여부')")
    private Boolean memberIsAdmin;
}
