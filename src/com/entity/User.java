package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity//标识你这个实体类是需要映射的一个实体类
@Table(name="user1")//当表名和类名不一致的情况下, 去手动指定映射的表名
public class User {
	@Id//标识这个属性是一个主键对应的属性
	@GeneratedValue//标识主键是一个自增主键
	private int id;
	@Column(name="username")//当字段名和属性名不一致的情况下, 去手动指定映射的字段名
	private String name;
	
	private int age;
	
	private String email;
	@Transient//把这个属性排除在数据库的映射之外
	private int version;
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age
				+ ", email=" + email + ", version=" + version + "]";
	}

	
}
