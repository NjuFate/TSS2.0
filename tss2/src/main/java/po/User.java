package po;


public class User implements BaseId{
	private Long id;// 编号
	private String account;// 账号
	private String hAccount;//环信帐号
	private String psw;
	private String name; // 名字
	private Integer age; // 年龄
	private String major; // 主修方向
	
	private String iconurl;
	private String nickName;//昵称
	
    private String email;
    private String qq;
    private String grade;
    private String tel;
    
    private String educational_ID;
    private String educational_Psw;
	
	public User() {
		// TODO Auto-generated constructor stub
		
	}
	
	public User(model.User user){
		this.id = user.getId();
		this.account = user.getAccount();
		this.hAccount = user.gethAccount();
		this.name = user.getName();
		this.age = user.getAge();
		this.major = user.toString(user.getMajor());
		this.psw = user.getPsw();
		this.email = user.getEmail();
		this.qq = user.getQq();
		this.tel = user.getTel();
		this.educational_ID = user.getEducational_ID();
		this.educational_Psw = user.getEducational_Psw();
		this.grade = user.getGrade();
        this.iconurl = user.getIconurl();
        this.nickName = user.getNickName();
		
	}
	
	
	
	
	

	public String gethAccount() {
		return hAccount;
	}

	public void sethAccount(String hAccount) {
		this.hAccount = hAccount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEducational_ID() {
		return educational_ID;
	}

	public void setEducational_ID(String educationl_ID) {
		this.educational_ID = educationl_ID;
	}

	public String getEducational_Psw() {
		return educational_Psw;
	}

	public void setEducational_Psw(String education_Psw) {
		this.educational_Psw = education_Psw;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public Long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getIconurl() {
		return iconurl;
	}

	public void setIconurl(String iconurl) {
		this.iconurl = iconurl;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	
	
	
	
	
	
	
}
