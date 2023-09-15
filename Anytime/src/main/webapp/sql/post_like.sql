-- 게시물 공감테이블
drop table post_like cascade constraints purge;

select * from post_like;

CREATE TABLE POST_LIKE (
    POST_LIKE_NUM NUMBER NOT NULL PRIMARY KEY,
    POST_NUM NUMBER NOT NULL REFERENCES POST(POST_NUM),
    NUM NUMBER NOT NULL REFERENCES MEMBER(NUM)
);