package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Category2 {
@Id
@GeneratedValue
	private int  id;
	private String cname;
	@ManyToOne
	@JoinColumn(name="pid")
	private Category1 category1;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Category1 getCategory1() {
		return category1;
	}
	public void setCategory1(Category1 category1) {
		this.category1 = category1;
	}
	
}
