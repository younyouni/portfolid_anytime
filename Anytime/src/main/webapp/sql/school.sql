drop table school cascade constraints purge;

select * from school;

CREATE TABLE SCHOOL (
  SCHOOL_NUM NUMBER PRIMARY KEY,
  SCHOOL_NAME VARCHAR2(100) NOT NULL,
  DOMAIN_EMAIL VARCHAR2(100) NOT NULL
);

INSERT INTO SCHOOL (SCHOOL_NUM, SCHOOL_NAME, DOMAIN_EMAIL)
VALUES (1, '한국대학교', '@koreauni.com');

INSERT INTO SCHOOL (SCHOOL_NUM, SCHOOL_NAME, DOMAIN_EMAIL)
VALUES (2, '고구마대학교', '@sweetpotatouni.com');

INSERT INTO SCHOOL (SCHOOL_NUM, SCHOOL_NAME, DOMAIN_EMAIL)
VALUES (3, '감자대학교', '@potatouni.com');

INSERT INTO SCHOOL (SCHOOL_NUM, SCHOOL_NAME, DOMAIN_EMAIL)
VALUES (4, '네이버대학교', '@naver.com');

create sequence school_seq;			/*생성만함, 안씀 -옥- */
drop sequence school_seq;

SELECT
  s.SCHOOL_NAME,
  COUNT(m.NUM) AS Total_Students
FROM
  SCHOOL s
LEFT JOIN
  MEMBER m ON s.SCHOOL_NUM = m.SCHOOL_NUM
GROUP BY
  s.SCHOOL_NAME;
=======
select s.* 
from school s 
where s.school_num = (	select school_num 
from member 
where userid = 'dingdong1234');

select s.* from school s 
where s.school_num = (	select school_num 
from member where userid = 'dingdong1234' );
>>>>>>> branch 'main' of https://github.com/younyouni/anytime.git




select *  
from(select rownum rnum, j.* 
		from(select post.POST_NUM, post.BOARD_NUM, post.NUM, post.SUBJECT, post.CONTENT, post.FILE_COUNT, TO_CHAR(post.post_date, 'MM-DD HH24:MI') post_date, post.LIKE_COUNT, post.SCRAP_COUNT, post.REPORT_COUNT, nvl(cnt,0) as COMMENT_COUNT, board.school_num 
			from post 
				left outer join (select POST_NUM, count(*) cnt 
 			from COMMENTS 
 			group by POST_NUM) sub_C 
 			on post.post_num = sub_C.post_num 
 				join board on post.board_num = board.board_num 
 				WHERE board.school_num = 1 AND post.like_count >= 10 
 			order by post.post_num desc) j 
		where rownum <= 10  
		) 
	where rnum>=1 and rnum <= 10;
