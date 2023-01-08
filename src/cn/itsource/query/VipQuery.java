package cn.itsource.query;

public class VipQuery {
	
	private String name;
	private Integer age;
	
	@Override
	public String toString() {
		return "VipQuery [name=" + name + ", age=" + age + "]";
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
	public void setAge(Integer age) {
		this.age = age;
	}
	public VipQuery(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	public VipQuery() {
		super();
	}

}
