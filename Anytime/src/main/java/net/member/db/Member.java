package net.member.db;

public class Member {

	private int num;
	private String userid;
	private String nickname;
	private String password;
	private String email;
	private int school_num;
	private String school_check;
	private String address_num;
	private String address1;
	private String address2;
	private String phone_num;
	private int board_admin;
	private int account_status;
	private int admission_year;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSchool_num() {
		return school_num;
	}

	public void setSchool_num(int school_num) {
		this.school_num = school_num;
	}

	public String getSchool_check() {
		return school_check;
	}

	public void setSchool_check(String school_check) {
		this.school_check = school_check;
	}

	public String getAddress_num() {
		return address_num;
	}

	public void setAddress_num(String address_num) {
		this.address_num = address_num;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public int getBoard_admin() {
		return board_admin;
	}

	public void setBoard_admin(int board_admin) {
		this.board_admin = board_admin;
	}

	public int getAccount_status() {
		return account_status;
	}

	public void setAccount_status(int account_status) {
		this.account_status = account_status;
	}

	public int getAdmission_year() {
		return admission_year;
	}

	public void setAdmission_year(int admission_year) {
		this.admission_year = admission_year;
	}

}