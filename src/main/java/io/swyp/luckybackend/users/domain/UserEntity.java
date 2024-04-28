package io.swyp.luckybackend.users.domain;

import io.swyp.luckybackend.luckyDays.domain.LcAlarmEntity;
import io.swyp.luckybackend.luckyDays.domain.LcDayCycleEntity;
import io.swyp.luckybackend.luckyDays.domain.LcDayDtlEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "LC_USER")
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "USER_NO")
    private Long userNo;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "GENDER")
    @Nullable
    private char gender;

    @Column(name = "AGE_GROUP")
    @Nullable
    private int ageGroup;

    @Column(name = "BIRTH_YEAR")
    @Nullable
    private int birthYear;

    @Column(name = "PRF_ICON_NO")
    private int profileIconNo;

    @Builder.Default
    @OneToMany(mappedBy = "LC_DAY_CYCL")
    private List<LcDayCycleEntity> cycles = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "LC_DAY_DTL")
    private List<LcDayDtlEntity> dtls = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "LC_ALARM")
    private List<LcAlarmEntity> alarms = new ArrayList<>();


    @Builder
    public UserEntity(Long userNo, String nickname, String email, char gender, int ageGroup, int birthYear, int profileIconNo) {
        this.userNo = userNo;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.ageGroup = ageGroup;
        this.birthYear = birthYear;
        this.profileIconNo = profileIconNo;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changeEmail(String email) {
        this.email = email;
    }
}
