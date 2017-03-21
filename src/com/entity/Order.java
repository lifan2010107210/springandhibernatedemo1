package com.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="order1")
public class Order {
	@Id
	@GeneratedValue
	private int id;
	
	private String orderCode;
	
	private String amount;
	//一个订单拥有多个订单条目
	//mappedBy 属性是表示关联字段由对方去维护   fetch  获取方式
	@OneToMany(mappedBy="order",fetch=FetchType.EAGER)
	private List<Orderitem> orderitems;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public List<Orderitem> getOrderitems() {
		return orderitems;
	}

	public void setOrderitems(List<Orderitem> orderitems) {
		this.orderitems = orderitems;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderCode=" + orderCode + ", amount="
				+ amount + "]";
	}
}
