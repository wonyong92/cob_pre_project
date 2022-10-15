package com.team23.PreProject.profile.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team23.PreProject.member.entity.member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@NoArgsConstructor
@Entity
@AllArgsConstructor
@Getter
@Setter
public class profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROFILE_ID")
    Integer profileId;
//    @OneToOne(orphanRemoval = true)
//    @JoinColumn(name="MEMBER_ID")
//    @JsonIgnore
//    member member = new member();
//
//    public void setMember(member member)
//    {
//        this.member = member;
//    }

    @Column(name="STUB_REPUTATION")
    Integer stubReputation = 0;

    @Column(name = "STUB_REACHED" )
    Integer stubReached = 0;

    @Column(name = "ABOUT")
    String about = "hello world";

    @Column(name = "SIGN_IN_DATE")
    ZonedDateTime signInDate = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

    @Column(name="LAST_VISIT")
    ZonedDateTime lastVisit = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

    @Column(name="LOCATION")
    String location="location";

    @Column(name = "DISPLAYNAME")
    String displayname = "displayname";
    @Column(name = "ANSWERS")
    Integer answers = 0;

}
