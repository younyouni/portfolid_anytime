drop table scrap cascade constraints purge;

select * from scrap;

CREATE TABLE SCRAP (
    SCRAP_NUM NUMBER(10) NOT NULL PRIMARY KEY,
    NUM NUMBER NOT NULL REFERENCES MEMBER(NUM),
    POST_NUM NUMBER NOT NULL REFERENCES POST(POST_NUM)
);