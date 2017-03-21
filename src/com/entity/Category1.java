package com.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Category1 {
@Id
@GeneratedValue
	private int id;
	private String cname;
	@OneToMany(mappedBy="category1" ,fetch=FetchType.EAGER)
	private List<Category2> catList;

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

	public List<Category2> getCatList() {
		return catList;
	}

	public void setCatList(List<Category2> catList) {
		this.catList = catList;
	}
	
	
}