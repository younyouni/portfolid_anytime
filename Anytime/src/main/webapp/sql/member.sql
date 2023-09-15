drop table member cascade constraints purge;
drop sequence member_seq;

CREATE TABLE MEMBER (
  NUM NUMBER PRIMARY KEY,
  SCHOOL_NUM NUMBER NOT NULL,
  USERID VARCHAR2(50) NOT NULL,
  NICKNAME VARCHAR2(50) NOT NULL,
  PASSWORD VARCHAR2(100) NOT NULL,
  EMAIL VARCHAR2(100) NOT NULL,
  SCHOOL_CHECK VARCHAR2(1) NOT NULL,
  ADDRESS_NUM VARCHAR2(10),
  ADDRESS1 VARCHAR2(200),
  ADDRESS2 VARCHAR2(200),
  PHONE_NUM VARCHAR2(20) NOT NULL,
  BOARD_ADMIN NUMBER(5) NOT NULL, /*일반 유저 : 0 , 게시판 관리자 : 1, 학교 관리자 : 2, 전체 관리자 : 3 */
  ACCOUNT_STATUS NUMBER(1) NOT NULL,
  ADMISSION_YEAR NUMBER(4) NOT NULL,
  CONSTRAINT FK_MEMBER_SCHOOL FOREIGN KEY (SCHOOL_NUM) REFERENCES SCHOOL (SCHOOL_NUM)
);
create sequence member_seq;


select * from member;
select * from board;

INSERT INTO MEMBER (
  NUM, USERID, NICKNAME, PASSWORD, EMAIL, SCHOOL_NUM, SCHOOL_CHECK, ADDRESS_NUM, ADDRESS1, ADDRESS2, PHONE_NUM, BOARD_ADMIN, ACCOUNT_STATUS, ADMISSION_YEAR
)
VALUES (
 member_seq.NEXTVAL, 'dingdong1234', '띵똥', 'qwerasdf123', 'dingdong@naver.com', 1, 'Y', '12345', '서울시 중구', '1동 1호', '010-123-4567', 1, 1, 2018
);

INSERT INTO MEMBER (
  NUM, USERID, NICKNAME, PASSWORD, EMAIL, SCHOOL_NUM, SCHOOL_CHECK, ADDRESS_NUM, ADDRESS1, ADDRESS2, PHONE_NUM, BOARD_ADMIN, ACCOUNT_STATUS, ADMISSION_YEAR
)
VALUES (
 member_seq.NEXTVAL, 'ddungie4321', '뚱인데여', 'pa$$w0rd', 'ddungi@gmail.com', 2, 'N', '56789', '경기도 수원', '2동 2호', '010-987-6543', 0, 1, 2019
);

INSERT INTO MEMBER (
  NUM, USERID, NICKNAME, PASSWORD, EMAIL, SCHOOL_NUM, SCHOOL_CHECK, ADDRESS_NUM, ADDRESS1, ADDRESS2, PHONE_NUM, BOARD_ADMIN, ACCOUNT_STATUS, ADMISSION_YEAR
)
VALUES (
  member_seq.NEXTVAL, 'chajipop123', '차지팝', 'password321$', 'chajipop@naver.com', 3, 'Y', '98765', '서울시 성동구', '3동 3호', '010-456-7890', 1, 1, 2020
);


select school_num, count(*) cnt
from member
group by school_num;


select school_name,  cnt 
from SCHOOL join(select school_num, count(*) cnt
				from member
				group by school_num) b				
on school.school_num = b.school_num; 

delete from member where userid = 'ddungie4321';
