package po;


public class User implements BaseId{
	private Integer id;// 编号
	private String account;// 账号
	private String name; // 名字
	private Integer age; // 年龄
	private String psw;
	private String major; // 主修方向
	
	
	public User() {
		// TODO Auto-generated constructor stub
		
	}
	
	public User(model.User user){
		this.id = user.getId();
		this.account = user.getAccount();
		this.name = user.getName();
		this.age = user.getAge();
		this.major = user.toString(user.getMajor());

		
	}
	
	

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	
	
	
	
	
	
	
}
