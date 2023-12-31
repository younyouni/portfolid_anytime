-- 게시물테이블
drop table post cascade constraints purge;

select * from post;

CREATE TABLE POST (
    POST_NUM NUMBER NOT NULL PRIMARY KEY,
    BOARD_NUM NUMBER NOT NULL,
    NUM NUMBER NOT NULL, 
    SUBJECT VARCHAR2(200) NOT NULL,
    CONTENT CLOB NOT NULL,
    FILE_COUNT NUMBER(1),
    POST_DATE DATE NOT NULL, 
    LIKE_COUNT NUMBER(10) NOT NULL,
    SCRAP_COUNT NUMBER(10) NOT NULL,
    REPORT_COUNT NUMBER(3) NOT NULL,
    FOREIGN KEY (BOARD_NUM) REFERENCES BOARD(BOARD_NUM),
    FOREIGN KEY (NUM) REFERENCES MEMBER(NUM) 
);

create sequence post_seq;
drop sequence post_seq;

SELECT TO_CHAR(post_date, 'MM-DD HH24:MI') 
FROM post;
        
        
/* 게시물 추가 내역 한국대 1번보드 1~21번 1번작성자 게시물 */       
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '1.순서/1.보드/1.작성자/한국대', '작성내용 테스트1', 1, SYSDATE, 0, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '2.순서/1.보드/1.작성자/한국대', '작성내용 테스트2', 2, SYSDATE, 1, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '3.순서/1.보드/1.작성자/한국대', '작성내용 테스트3', 3, SYSDATE, 9, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '4.순서/1.보드/1.작성자/한국대', '작성내용 테스트4', 1, SYSDATE, 10, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '5.순서/1.보드/1.작성자/한국대', '작성내용 테스트5', 1, SYSDATE, 11, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '6.순서/1.보드/1.작성자/한국대', '작성내용 테스트6', 1, SYSDATE, 12, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '7.순서/1.보드/1.작성자/한국대', '작성내용 테스트7', 1, SYSDATE, 13, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '8.순서/1.보드/1.작성자/한국대', '작성내용 테스트8', 1, SYSDATE, 14, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '9.순서/1.보드/1.작성자/한국대', '작성내용 테스트9', 1, SYSDATE, 15, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '10.순서/1.보드/1.작성자/한국대', '작성내용 테스트10', 1, SYSDATE, 16, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '11.순서/1.보드/1.작성자/한국대', '작성내용 테스트11', 1, SYSDATE, 17, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '12.순서/1.보드/1.작성자/한국대', '작성내용 테스트12', 1, SYSDATE, 18, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '13.순서/1.보드/1.작성자/한국대', '작성내용 테스트13', 1, SYSDATE, 19, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '14.순서/1.보드/1.작성자/한국대', '작성내용 테스트14', 1, SYSDATE, 20, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '15.순서/1.보드/1.작성자/한국대', '작성내용 테스트15', 1, SYSDATE, 99, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '16.순서/1.보드/1.작성자/한국대', '작성내용 테스트16', 1, SYSDATE, 100, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '17.순서/1.보드/1.작성자/한국대', '작성내용 테스트17', 1, SYSDATE, 101, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '18.순서/1.보드/1.작성자/한국대', '작성내용 테스트18', 1, SYSDATE, 200, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '19.순서/1.보드/1.작성자/한국대', '작성내용 테스트19', 1, SYSDATE, 300, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '20.순서/1.보드/1.작성자/한국대', '작성내용 테스트20', 1, SYSDATE, 400, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '21.순서/1.보드/1.작성자/한국대', '작성내용 테스트21', 1, SYSDATE, 500, 0, 0);

/* 게시물 추가 내역 한국대 2번보드 22~26번 게시물 */
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 2, 1, '22.순서/2.보드/1.작성자/한국대', '작성내용 테스트22', 1, SYSDATE, 10, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 2, 1, '23.순서/2.보드/1.작성자/한국대', '작성내용 테스트23', 2, SYSDATE, 11, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 2, 1, '24.순서/2.보드/1.작성자/한국대', '작성내용 테스트24', 3, SYSDATE, 12, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 2, 1, '25.순서/2.보드/1.작성자/한국대', '작성내용 테스트25', 1, SYSDATE, 13, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 2, 1, '26.순서/2.보드/1.작성자/한국대', '작성내용 테스트26', 1, SYSDATE, 14, 0, 0);

/* 게시물 추가 내역 고구마대 3번보드 27~31번 게시물 */
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 3, 7, '27.순서/3.보드/7.작성자/한국대', '작성내용 테스트27', 1, SYSDATE, 1, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 3, 7, '28.순서/3.보드/7.작성자/한국대', '작성내용 테스트28', 2, SYSDATE, 10, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 3, 7, '29.순서/3.보드/7.작성자/한국대', '작성내용 테스트29', 3, SYSDATE, 100, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 3, 7, '30.순서/3.보드/7.작성자/한국대', '작성내용 테스트30', 1, SYSDATE, 200, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 3, 7, '31.순서/3.보드/7.작성자/한국대', '작성내용 테스트31', 1, SYSDATE, 300, 0, 0);

/* 게시물 추가 내역 고구마대 4번보드 32~36번 게시물 */
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 4, 7, '32.순서/4.보드/7.작성자/한국대', '작성내용 테스트32', 1, SYSDATE, 1, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 4, 7, '33.순서/4.보드/7.작성자/한국대', '작성내용 테스트33', 2, SYSDATE, 10, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 4, 7, '34.순서/4.보드/7.작성자/한국대', '작성내용 테스트34', 3, SYSDATE, 100, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 4, 7, '35.순서/4.보드/7.작성자/한국대', '작성내용 테스트35', 1, SYSDATE, 200, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 4, 7, '36.순서/4.보드/7.작성자/한국대', '작성내용 테스트36', 1, SYSDATE, 300, 0, 0);

/* 게시물 추가 내역 감자대 5번보드 37~41번 게시물 */
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 5, 3, '37.순서/5.보드/3.작성자/한국대', '작성내용 테스트37', 1, SYSDATE, 1, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 5, 3, '38.순서/5.보드/3.작성자/한국대', '작성내용 테스트38', 2, SYSDATE, 10, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 5, 3, '39.순서/5.보드/3.작성자/한국대', '작성내용 테스트39', 3, SYSDATE, 100, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 5, 3, '40.순서/5.보드/3.작성자/한국대', '작성내용 테스트40', 1, SYSDATE, 200, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 5, 3, '41.순서/5.보드/3.작성자/한국대', '작성내용 테스트41', 1, SYSDATE, 300, 0, 0);

/* 게시물 추가 내역 감자대 6번보드 42~46번 게시물 */
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 6, 3, '42.순서/6.보드/3.작성자/한국대', '작성내용 테스트42', 1, SYSDATE, 1, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 6, 3, '43.순서/6.보드/3.작성자/한국대', '작성내용 테스트43', 2, SYSDATE, 10, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 6, 3, '44.순서/6.보드/3.작성자/한국대', '작성내용 테스트44', 3, SYSDATE, 100, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 6, 3, '45.순서/6.보드/3.작성자/한국대', '작성내용 테스트45', 1, SYSDATE, 200, 0, 0);
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 6, 3, '46.순서/6.보드/3.작성자/한국대', '작성내용 테스트46', 1, SYSDATE, 300, 0, 0);

/* 게시물 추가 내역 한국대 1번보드 47~48번 게시물 - 삭제된 사용자 "알수없음" 작동 확인 게시물*/
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 2, '47.순서/1.보드/탈퇴회원/한국대', '작성내용 테스트47', 1, SYSDATE, 100, 0, 0);

INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 21, '48.순서/1.보드/탈퇴회원/한국대', '작성내용 테스트48', 1, SYSDATE, 100, 0, 0);


/* 게시물 추가 내역 한국대 1번보드 49~52번 게시물 - 베스트게시판 페이지네이션 작동 확인 게시물*/
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '49.순서/1.보드/탈퇴회원/한국대', '작성내용 테스트49', 1, SYSDATE, 100, 0, 0);

INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '50.순서/1.보드/1.작성자/한국대', '작성내용 테스트50', 1, SYSDATE, 100, 0, 0);

INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '51.순서/1.보드/1.작성자/한국대', '작성내용 테스트51', 1, SYSDATE, 100, 0, 0);

INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '52.순서/1.보드/1.작성자/한국대', '작성내용 테스트52', 1, SYSDATE, 100, 0, 0);

/* 게시물 추가 내역 한국대 1번보드 53~56번 게시물 - 베스트게시판 페이지네이션 작동 확인 게시물 상운 추가*/
INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '53.순서/1.보드/1.작성자/한국대', '작성내용 테스트53', 1, SYSDATE, 10, 0, 0);

INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '54.순서/1.보드/1.작성자/한국대', '작성내용 테스트54', 1, SYSDATE, 12, 0, 0);

INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '55.순서/1.보드/1.작성자/한국대', '작성내용 테스트55', 1, SYSDATE, 102, 0, 0);

INSERT INTO POST (POST_NUM, BOARD_NUM, NUM, SUBJECT, CONTENT, FILE_COUNT, POST_DATE, LIKE_COUNT, SCRAP_COUNT, REPORT_COUNT)
values(post_seq.nextval, 1, 1, '56.순서/1.보드/1.작성자/한국대', '작성내용 테스트56', 1, SYSDATE, 110, 0, 0);

