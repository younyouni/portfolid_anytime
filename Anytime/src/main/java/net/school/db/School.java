package net.school.db;

public class School {
   private int school_num;
   private String school_name;
   private String school_email;
   private int total_count;

public int getTotal_count() {
	return total_count;
}

public void setTotal_count(int total_count) {
	this.total_count = total_count;
}

public int getSchool_num() {
      return school_num;
   }

   public void setSchool_num(int school_num) {
      this.school_num = school_num;
   }

   public String getSchool_name() {
      return school_name;
   }

   public void setSchool_name(String school_name) {
      this.school_name = school_name;
   }

   public String getSchool_email() {
      return school_email;
   }

   public void setSchool_email(String school_email) {
      this.school_email = school_email;
   }

}
