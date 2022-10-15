# seb39_pre_023
백엔드 API 문서 : https://ska40806.gitbook.io/api-docs/

백엔드 DB 스키마 : https://dbdiagram.io/d/63156bb00911f91ba533fcbb

## 📚 Intro
- 배포링크:
- 테스트 계정
<br/>id: Iddddddd1
<br/>password: password
<br/>

![stack-overflow-wordmark](https://user-images.githubusercontent.com/102936206/189313291-02e6b174-2ef8-43a8-a938-56ed440c752c.svg)

## 👨‍👩‍👧‍ 팀원소개
장원용(팀장, BE)|남충현(FE)|문도연(FE)
-|-|-
<a href="https://github.com/wonyong92">@wonyong92</a>|<a href="https://github.com/davidnam0514">@davidnam0514</a>|<a href="https://github.com/Moondoyeon">@Moondoyeon</a>

## Demo
회원가입|로그인|
-|-
![회원가입](https://user-images.githubusercontent.com/102936206/189317749-776cf8ef-80e3-48a5-bbcc-34a7ad13f9a5.png)|![로그인](https://user-images.githubusercontent.com/102936206/189317782-d8365441-1573-4047-8c28-f63b355469fe.png)
로그아웃|
![로그아웃](https://user-images.githubusercontent.com/102936206/189317936-32c99556-dc89-4257-b447-1544a673a384.png)|
글목록페이지|글상세페이지
![글목록페이지](https://user-images.githubusercontent.com/102936206/189318044-b56213c2-e7e7-4bf8-a5e7-78a118a8a7de.png)|![글상세](https://user-images.githubusercontent.com/102936206/189382386-98fa900e-71a5-4ff1-898d-3c966c510cd3.png)
글작성페이지|글수정페이지
![글작성페이지](https://user-images.githubusercontent.com/102936206/189318169-9c21738a-4f6d-40d6-960f-a6e5bb6372c1.png)|![질문수정](https://user-images.githubusercontent.com/102936206/189381091-e599bd87-4e34-41a1-afbb-5ef0a9c7e086.png)
검색결과페이지|답변작성/수정/삭제
![검색결과페이지](https://user-images.githubusercontent.com/102936206/189318287-97ab0e67-010a-4e3f-b6b7-8f5cfd6077ee.png)|![답변생성조회수정삭제](https://user-images.githubusercontent.com/102936206/189381223-2f7ce3e1-054c-49d2-ab7b-3039c5f919fe.png)
마이페이지-profile|마이페이지-settings
![마이페이지-profile](https://user-images.githubusercontent.com/102936206/189381259-a7da8f33-6e25-4080-af4b-63e687e905dd.png)|![마이페이지-setting](https://user-images.githubusercontent.com/102936206/189381288-d7beb73e-40e2-435b-91c6-7141ffd8e500.png)


## Skills
### Front-end
<img src="https://user-images.githubusercontent.com/52682603/138834243-fb74d81e-e90d-4c6a-8793-05df588f59ab.png" style="width:150px;"></img>
<img src="https://camo.githubusercontent.com/9eb91892d3969439e38151d9985cc1709f2bfd6062c4ff9e3518f355d4457cd6/68747470733a2f2f6e6f7469636f6e2d7374617469632e74616d6d6f6c6f2e636f6d2f6467676763726b78712f696d6167652f75706c6f61642f76313536373734393631342f6e6f7469636f6e2f7a6764617870616966356f6a6564756f6e7967622e706e67" style="width:150px;"></img>
<img src="https://camo.githubusercontent.com/ea2326599fe1ad74f07f5c2dd97ccdbd296e825d0ddf3f9fff2c417260a190fe/68747470733a2f2f6e6f7469636f6e2d7374617469632e74616d6d6f6c6f2e636f6d2f6467676763726b78712f696d6167652f75706c6f61642f76313536383835313531382f6e6f7469636f6e2f6c776a336872397631796f6865696d74776331772e706e67" style="width:150px;"></img>

### Back-end


## Infrastructure

## Directory Structure
### 프론트엔드
```
frontend
├── package-lock.json
├── package.json
└── src
    ├── action
    ├── components ─────────────┐
    ├── lib.                    ├──AnswerItem
    ├── pages ───┐              ├──Answers
    ├── reducer  ├── MyPage     ├──AnswerVote
    └── store    ├── Question   ├──ChangePassword
                 ├── Search     ├──DeleteModal
                 └── Sign       ├──DeleteProfile
                                ├──EditProfile
                                ├──HeaderModal
                                ├──MyAnswerListitem
                                ├──MyButton
                                ├──MyContent
                                ├──MyFooter
                                ├──MyHeader
                                ├──MyInfo
                                ├──MylistItem
                                ├──MyProfile
                                ├──MyProfileImg
                                ├──MyQuestionListItem
                                ├──MySettings
                                ├──Nav
                                ├──PostAnswer
                                ├──PostBodyTextarea
                                ├──PsotList
                                ├──PostVote
                                ├──RequireAuth
                                ├──SearchListItem
                                ├──SearchQuestion
                                ├──Tags
                                └──Widget

```

### 백엔드
```
src
    ├── main
    │   ├── java
    │   │   └── com.team23.PreProject
    │	│		        │
    │   │      	        │   
    │   │               ├── PreProjectApplication.java
    │   │               │
    │   │               ├── Config
    │   │               │   └── WebConfig.java
    │	│	          	│
    │   │               │─ profile
    │   │               │   ├── controller
    │   │               │   ├── entity
    │   │               │   ├── dto
    │   │               │   ├── mapper
    │   │               │   ├── service
    │   │               │   └── repository
    │   │               │	
    │   │               │─ user
    │   │               │   ├── controller
    │   │               │   ├── entity
    │   │               │   ├── dto
    │   │               │   ├── mapper
    │   │               │   ├── service
    │   │               │   └── repository
    │   │               │
    │   │               │─ comment
    │   │               │   ├── controller
    │   │               │   ├── entity
    │   │               │   ├── dto
    │   │               │   ├── mapper
    │   │               │   ├── service
    │   │               │   └── repository
    │   │               │
    │   │               │─ tag
    │   │               │   ├── controller
    │   │               │   ├── entity
    │   │               │   ├── dto
    │   │               │   ├── mapper
    │   │               │   ├── service
    │   │               │   └── repository
    │   │               │
    │   │               │─ post
    │   │               │   ├── controller
    │   │               │   ├── entity
    │   │               │   ├── dto
    │   │               │   ├── mapper
    │   │               │   ├── service
    │   │               │   └── repository
    │   │               │
    │   │               │─ post_vote
    │   │               │   ├── controller
    │   │               │   ├── entity
    │   │               │   ├── dto
    │   │               │   ├── mapper
    │   │               │   ├── service
    │   │               │   └── repository
    │   │               │
    │   │               │─ answer
    │   │               │   ├── controller
    │   │               │   ├── entity
    │   │               │   ├── dto
    │   │               │   ├── mapper
    │   │               │   ├── service
    │   │               │   └── repository
    │   │               │
    │   │               │─ answer_vote
    │   │               │   ├── controller
    │   │               │   ├── entity
    │   │               │   ├── dto
    │   │               │   ├── mapper
    │   │               │   ├── service
    │   │               │   └── repository
    │   │               │
    │   └── resources
    │		├── schema.sql // not empty
    │		├── data.sql // not empty
    │           └── application.properties
```

### Getting Started
#### 프론트엔드
```
# development
$ cd frontend
$ npm install
$ npm run start
```
#### 백엔드
```
./gradlew build
java -jar build/libs/coco-*-SNAPSHOT.jar
```
