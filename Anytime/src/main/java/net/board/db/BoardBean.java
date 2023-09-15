package net.board.db;

public class BoardBean {
	private int board_num;
	private int school_num;
	private int num;
	private int type; // 1 : 기본 게시판 2 : 단체 게시판 3 : 학과 게시판 4 : 커스텀 게시판 
	private String name;
	private String content;
	private int anonymous; // 0 : 닉네임 1 : 익명 
	private String approval;
	private String purpose;

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public int getSchool_num() {
		return school_num;
	}

	public void setSchool_num(int school_num) {
		this.school_num = school_num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(int anonymous) {
		this.anonymous = anonymous;
	}

}