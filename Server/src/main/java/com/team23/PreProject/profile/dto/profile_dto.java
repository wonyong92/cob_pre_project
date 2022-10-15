package com.team23.PreProject.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.ZoneId;
import java.time.ZonedDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class profile_dto {
    Integer profileId;
    String about = "hello world";
    Integer stubReputation = 0;
    Integer stubReached = 0;

    ZonedDateTime signInDate = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));


    ZonedDateTime lastVisit = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));


    String location = "location";


    String displayname = "displayname";

    Integer answers = 100;
}