drop table photo cascade constraints purge;

select * from photo;

CREATE TABLE PHOTO (
    PHOTO_NUM NUMBER NOT NULL PRIMARY KEY,
    POST_NUM NUMBER NOT NULL REFERENCES POST(POST_NUM),
    PATH VARCHAR2(300) NOT NULL
);

delete from photo;

