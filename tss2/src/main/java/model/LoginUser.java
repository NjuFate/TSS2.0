package model;

public class LoginUser {
	private long id;
	private String psw;
	private String account;
	private String hAccount;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String gethAccount() {
		return hAccount;
	}
	public void sethAccount(String hAccount) {
		this.hAccount = hAccount;
	}
	
	

}
