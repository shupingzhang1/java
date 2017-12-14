package javabeen;
/*
 * Admin been  管理员
 * @author zsp
 * 11.22
 * */
public class admin {
	private int adminId;   //管理员id（自增 主健）
	private String userId;  //教工（外键）
	private String name;  //姓名
	private int sex;      //性别（0：男 1：女
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	

}
