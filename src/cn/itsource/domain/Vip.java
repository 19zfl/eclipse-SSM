package cn.itsource.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Vip {

	private Long id;
	private String name;
	private Integer age;
	private Boolean sex;
    private Double salary;
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthDay;
	private Date createDate = new Date();
	
	public Vip() {
		super();
	}
	public Vip(Long id, String name, Integer age, Boolean sex, Double salary, Date birthDay, Date createDate) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.salary = salary;
		this.birthDay = birthDay;
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Vip [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + ", salary=" + salary
				+ ", birthDay=" + birthDay + ", createDate=" + createDate + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Boolean getSex() {
		return sex;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
