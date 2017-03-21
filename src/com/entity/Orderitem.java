package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Orderitem {
	@Id
	@GeneratedValue
	private int id;
	//小计
	private int amount;
	//商品数量
	private int count;
	//多个订单条目对应一个订单
	@ManyToOne
	@JoinColumn(name="oid")
	private Order order;
	//一个订单条目对应一本书
	@OneToOne
	@JoinColumn(name="bid")
	private Book book;


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	
	
	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}


	@Override
	public String toString() {
		return "Orderitem [id=" + id + ", amount=" + amount + ", count="
				+ count + "]";
	}
}
