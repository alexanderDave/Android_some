package daxigua.Bean;

public class userBean {
	
	//all point in db
	private int id;				//可缺省	
	private String uid;			
	private String upswd;
	private String upath;		//可缺省
	private int ugrade;			// -1===充值状态？ 0===一般状态 1===管理员
	private int ustatus;		// 0===不可用 1===可用	
	
	//generate getter and setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUpswd() {
		return upswd;
	}
	public void setUpswd(String upswd) {
		this.upswd = upswd;
	}
	public String getUpath() {
		return upath;
	}
	public void setUpath(String upath) {
		this.upath = upath;
	}
	public int getUgrade() {
		return ugrade;
	}
	public void setUgrade(int ugrade) {
		this.ugrade = ugrade;
	}
	public int getUstatus() {
		return ustatus;
	}
	public void setUstatus(int ustatus) {
		this.ustatus = ustatus;
	}
	

	
	
	
}
