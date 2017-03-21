package com.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Order;
import com.entity.Orderitem;

//默认的id命名规则, 类名(首字母小写)
@Service
public class OrderService {
	@Resource
	private HibernateTemplate hibernateTemplate;

	@Transactional(rollbackFor=Exception.class)
	public void addOrder() throws Exception{
		//创建order对象
		Order order = new Order();
		order.setAmount("555");
		order.setOrderCode("asdfsfd");
		//创建orderitem对象
		Orderitem orderitem1 = new Orderitem();
		orderitem1.setAmount(123);
		orderitem1.setCount(1);
		Orderitem orderitem2 = new Orderitem();
		orderitem2.setAmount(222);
		orderitem2.setCount(2);
		//设置关联关系, set方法
		orderitem1.setOrder(order);
		orderitem2.setOrder(order);
		hibernateTemplate.save(order);
//		System.out.println(1/0);
		if(true){
			throw new Exception("模拟异常...");
		}
		hibernateTemplate.save(orderitem1);
		hibernateTemplate.save(orderitem2);
	}

	@Override
	public String toString() {
		return "OrderService [hibernateTemplate=" + hibernateTemplate + "]";
	}
	
}
