package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.enums.State;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length=50, unique=true)
    private String email;

    @Column(name="social_id", length=50, nullable=false, unique=true)
    String social_id;

    @Column(name="social_type", nullable=false)
    String social_type;

    @Column(name = "name", length=5, nullable=false)
    private String name;

    @Column(name = "gender", nullable=false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth_date", nullable=false)
    LocalDateTime birth_date;

    @Column(name = "address", length = 50, nullable=false)
    String address;

    @Column(name = "phone_number", length=20)
    String phone_number;

    @Column(name = "point", nullable=false)
    @Builder.Default
    int point = 0;

    @Column(name = "state", nullable=false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    State state = State.ACTIVE;

    @Column(name = "deleted_at")
    LocalDateTime deleted_at;
}
