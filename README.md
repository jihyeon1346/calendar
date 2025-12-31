# API 명세서
## 1. 일정 생성
Method : POST
EndPoint : /api/calendars


RequestBody

json
{
    "tilte" : "계획짜기",
    "description" : "26년 계획짜기",
    "userName" : "홍길동",
    "password" : "1234"
}
Response (201 Created)
json
{
    "id" : 1,
    "tilte" : "계획짜기",
    "description" : "26년 계획짜기",
    "userName" : "홍길동",
    "createdAt": "2025-12-31T12:00:00",
    "modifiedAt": "2025-12-31T12:00:00"
}

## 2. 전체 일정 조회
Method : GET
EndPoint : /api/calendars

Response

json
[
    {
        "id": 2,
        "title": "과제하기",
        "description": "코드작성",
        "userName": "홍길동",
        "createdAt": "2025-12-31T14:20:00",
        "modifiedAt": "2025-12-31T15:30:00"
    },
    {
        "id" : 1,
        "tilte" : "계획짜기",
        "description" : "26년 계획짜기",
        "userName" : "홍길동",
        "createdAt": "2025-12-31T12:00:00",
        "modifiedAt": "2025-12-31T12:00:00"
    }
]

## 3. 선택 일정 조회
Method : GET
EndPoint : /api/calendars{id}
Path Variable : id(고유 식별자)

Response (200 OK)
 
json
{
   "id" : 1,
   "tilte" : "계획짜기",
   "description" : "26년 계획짜기",
   "userName" : "홍길동",
   "createdAt": "2025-12-31T12:00:00",
   "modifiedAt": "2025-12-31T12:00:00"
}

## 4. 일정 수정
Method : PUT
EndPoint : /api/calendars{id}
Path Variable : id(고유 식별자)

json
Request Body   
{
    "title": "계획짜기 (수정)",
    "userName": "김철수",
    "password": "1234"
}

Response (200 OK)

json
{
    "id" : 1,
    "tilte" : "계획짜기 (수정)",
    "description" : "26년 계획짜기",
    "userName" : "홍길동",
    "createdAt": "2025-12-31T12:00:00",
    "modifiedAt": "2025-12-31T12:00:00"
}

## 5. 일정 삭제

Method : DELETE
EndPoint : /api/calendars{id}
Path Variable : id(고유 식별자)

Request Body

json
{
    "password": "1234"
}

Response (204 No Content)

## 에러응답

400 Bad Request
json
{
    "message": "필수 내용이 누락되었습니다."
}

404 Not Found
json
{
    "message": "해당 일정을 찾을 수 없습니다."
}



##ERD
       calendars
id (pk)         BIGINT
title           VARCHAR
description     VARCHAR
userName        VARCHAR
password        VARCHAR
createdAt       DATETIME
modifiedAt      DATETIME