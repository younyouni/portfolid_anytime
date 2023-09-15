drop table board cascade constraints purge;

select * from board;

CREATE TABLE BOARD (
    BOARD_NUM NUMBER NOT NULL PRIMARY KEY,
    SCHOOL_NUM NUMBER NOT NULL REFERENCES SCHOOL(SCHOOL_NUM),
    NUM NUMBER NOT NULL REFERENCES MEMBER(NUM),
    TYPE NUMBER(1) NOT NULL,
    NAME VARCHAR2(100) NOT NULL,
    CONTENT VARCHAR2(4000),
    ANONYMOUS NUMBER(1) NOT NULL,
    APPROVAL VARCHAR2(1) NOT NULL,
    CONSTRAINT CK_BOARD_ANONYMOUS CHECK (ANONYMOUS IN (0, 1))
);

SELECT * FROM (	SELECT B.BOARD_NUM, B.NAME AS BOARD_NAME, P.SUBJECT, P.POST_DATE
FROM BOARD B 
LEFT JOIN POST P ON B.BOARD_NUM = P.BOARD_NUM 
WHERE B.SCHOOL_NUM = 1 AND B.BOARD_NUM = 1 AND B.TYPE = 1 AND B.APPROVAL = 'Y'
ORDER BY P.POST_DATE DESC
 ) 																		
WHERE ROWNUM <= 4;

create sequence board_seq;
drop sequence board_seq;

/* 230811_금_조상운 BOARD테이블에 PURPOSE 컬럼 추가완료 */
ALTER TABLE BOARD
ADD PURPOSE VARCHAR2(4000);

INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 1, 1, 1, '한국대 자유게시판', '한국대학교 자유게시판 설명입니다요플레', 0, 'Y');

INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 1, 1, 1, '한국대 비밀게시판(익명)', '한국대학교 비밀게시판 설명입니다요구르트', 1, 'Y');

INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 2, 4, 1, '고구마대 자유게시판', '고구마대학교 자유게시판 설명입니다요를레히', 0, 'Y');

INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 2, 4, 1, '고구마대 비밀게시판(익명)', '고구마대학교 비밀게시판 설명입니다요소수', 1, 'Y');

INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 3, 3, 1, '감자대 자유게시판', '감자대학교 자유게시판 설명입니다요다', 0, 'Y');

INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 3, 3, 1, '감자대 비밀게시판(익명)', '감자대학교 비밀게시판 설명입니다요요', 1, 'Y');

INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 1, 1, 1, '한국대 정보게시판(승인중)', '한국대학교 정보게시판 설명입니다요', 0, 'N');

/* 타입 테스트 용 */
INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 1, 1, 2, '한국대 총학생회', '한국대 총학생회 설명', 0, 'Y');

INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 1, 1, 3, '한국대 애국학과', '한국대 총학생회 설명', 0, 'Y');

INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 2, 2, 2, '고구마대 총학생회', '고구마대 총학생회 설명', 0, 'Y');

INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 2, 2, 3, '고구마대 호박고구마학과', '고구마대 호박고구마학과 설명', 0, 'Y');

INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 3, 3, 2, '감자대 총학생회', '감자대 총학생회 설명', 0, 'Y');

INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 3, 3, 3, '감자대 프링글스학과', '감자대 프링글스학과 설명', 0, 'Y');

INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 1, 1, 4, '한국여행가자', '사실 집에갈래', 0, 'Y');

INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 2, 2, 4, '고구마깡냠냠', '감자깡이 더 맛있지', 0, 'Y');

INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 3, 3, 4, '감자감자왕감자', '정말정말좋아요', 0, 'Y');

/* 보드리스트 갯수 8개 출력 후 옆으로 넘어가는지 확인*/

INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 1, 1, 1, '보드리스트 테스트1', '1', 0, 'Y');

INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 1, 1, 1, '보드리스트 테스트2', '2', 0, 'Y');

INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 1, 1, 1, '보드리스트 테스트3', '3', 0, 'Y');

INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 1, 1, 1, '보드리스트 테스트4', '4', 0, 'Y');

INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 1, 1, 1, '보드리스트 테스트5', '5', 0, 'Y');

INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 1, 1, 1, '보드리스트 테스트6', '6', 0, 'Y');

INSERT INTO BOARD (BOARD_NUM, SCHOOL_NUM, NUM, TYPE, NAME, CONTENT, ANONYMOUS, APPROVAL)
values(board_seq.nextval, 1, 1, 1, '보드리스트 테스트7', '7', 0, 'Y');

