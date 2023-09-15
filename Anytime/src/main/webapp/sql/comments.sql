select * from comments;
select * from post;
select * from member;

drop table comments cascade constraints purge;

CREATE TABLE COMMENTS (
    COMMENTS_NUM NUMBER NOT NULL PRIMARY KEY,
    POST_NUM NUMBER NOT NULL,
    NUM NUMBER NOT NULL, 
    CONTENT VARCHAR2(200) NOT NULL,
    LIKE_COUNT NUMBER(4) NOT NULL,
    COMMENTS_DATE DATE NOT NULL,
    RE_REF NUMBER(10) NOT NULL, 							-- 원문은 자신 글번호, 댓글이면 원문 글번호
    RE_LEV NUMBER(10) NOT NULL CHECK(RE_LEV IN (0,1,2)), 	-- 원문이면 0 대댓글이면 1, 대대댓글이면 2
    RE_SEQ NUMBER(10) NOT NULL, 							-- 원문이면 0, 1레벨이면 1레벨 시퀀스 + 1
    REPORT_COUNT NUMBER(3) NOT NULL,
    FOREIGN KEY (POST_NUM) REFERENCES POST(POST_NUM) ON DELETE CASCADE,
    FOREIGN KEY (NUM) REFERENCES MEMBER(NUM)
);

drop sequence comments_seq;
create sequence comments_seq;

insert into comments (COMMENTS_NUM, POST_NUM, NUM, CONTENT, LIKE_COUNT, COMMENTS_DATE, RE_REF, RE_LEV, RE_SEQ, REPORT_COUNT)
values (comments_seq.nextval, 76, 1, '첫번째 댓글', 0, sysdate, comments_seq.nextval, 0, 0, 0);

insert into comments (COMMENTS_NUM, POST_NUM, NUM, CONTENT, LIKE_COUNT, COMMENTS_DATE, RE_REF, RE_LEV, RE_SEQ, REPORT_COUNT)
values (comments_seq.nextval, 76, 1, '두번째 댓글', 0, sysdate, comments_seq.nextval, 0, 0, 0);

insert into comments (COMMENTS_NUM, POST_NUM, NUM, CONTENT, LIKE_COUNT, COMMENTS_DATE, RE_REF, RE_LEV, RE_SEQ, REPORT_COUNT)
values (comments_seq.nextval, 76, 1, '첫번째의 대댓글', 0, sysdate, 1, 1, 1, 0);

insert into comments (COMMENTS_NUM, POST_NUM, NUM, CONTENT, LIKE_COUNT, COMMENTS_DATE, RE_REF, RE_LEV, RE_SEQ, REPORT_COUNT)
values (comments_seq.nextval, 76, 3, '대대댓글은 어떻게해야지?', 0, sysdate, 1, 2, 2, 0);

select COMMENTS_NUM, c.num, content, comments_date, re_lev, re_seq, re_ref
from comments c join member m
on c.num = m.num 
where post_num = 76 
order by re_ref asc , re_seq asc;

SELECT 
    c.COMMENTS_NUM,
    c.NUM,
    c.CONTENT,
    c.LIKE_COUNT,
    c.COMMENTS_DATE,
    c.RE_LEV,
    c.RE_SEQ,
    c.RE_REF,
    m.NICKNAME
FROM 
    COMMENTS c
JOIN 
    MEMBER m ON c.NUM = m.NUM
WHERE 
    c.POST_NUM = 76
ORDER BY 
    c.RE_REF ASC, c.RE_SEQ ASC;

delete COMMENTS
where comments_num =6;
