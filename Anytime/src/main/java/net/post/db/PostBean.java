package net.post.db;

public class PostBean {
	private int    post_num;    	//게시글 글번호
	private int    board_num;   	//게시판 번호
	private int	   num;   			//유저 번호
	private String subject;			//글 제목
	private String content;			//글 내용
	private int file_count;   		//첨부될 파일의 이름
	private String post_date; 		//글 작성 시간
	private int    like_count; 		//좋아요 수
	private int    scrap_count;		//스크랩 수
	private int report_count; 		//신고 수
	private int comment_count;		//코멘트 수
	private String  nickname;		//유저 번호로 닉네임 매칭
	private String boardname;		//게시판 번호로 게시판이름 매칭
	
	public String getBoardname() {
		return boardname;
	}
	public void setBoardname(String boardname) {
		this.boardname = boardname;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public int getPost_num() {
		return post_num;
	}
	public void setPost_num(int post_num) {
		this.post_num = post_num;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getFile_count() {
		return file_count;
	}
	public void setFile_count(int file_count) {
		this.file_count = file_count;
	}
	public String getPost_date() {
		return post_date;
	}
	public void setPost_date(String post_date) {
		this.post_date = post_date;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	public int getScrap_count() {
		return scrap_count;
	}
	public void setScrap_count(int scrap_count) {
		this.scrap_count = scrap_count;
	}
	public int getReport_count() {
		return report_count;
	}
	public void setReport_count(int report_count) {
		this.report_count = report_count;
	}
	public int getComment_count() {
		return comment_count;
	}
	public void setComment_count(int comment_Count) {
		this.comment_count = comment_Count;
	}
	

}
