package model;

public class Student {
	private int id;
	private String name;
	private String email;
	private int age;
	private String mobile;
	
	public Student() {
		
	}
	
	public Student( String name, String email, int age, String mobile) {
		super();
		
		this.name = name;
		this.email = email;
		this.age = age;
		this.mobile = mobile;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public int getAge() {
		return age;
	}

	public String getMobile() {
		return mobile;
	}
	
	
	
	

}
