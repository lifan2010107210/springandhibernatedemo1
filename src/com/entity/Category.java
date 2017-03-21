package com.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Category{
	@Id
	@GeneratedValue
    private int id;

    private String cname;

    @OneToMany(mappedBy="parent",fetch=FetchType.EAGER)
    private List<Category> catlist;

    @ManyToOne
	@JoinColumn(name="pid")
    private Category parent;

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

	public List<Category> getCatlist() {
		return catlist;
	}

	public void setCatlist(List<Category> catlist) {
		this.catlist = catlist;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}


}