package test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.activation.FileDataSource;
import javax.naming.Context;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.entity.Author;
import com.entity.Book;
import com.entity.Order;
import com.entity.Orderitem;
import com.service.OrderService;

public class RelationTest {
	
	ApplicationContext context= new ClassPathXmlApplicationContext("applicationContext.xml");
	
	
	HibernateTemplate hibernateTemplate = context.getBean(HibernateTemplate.class);
	
	
	@Test
	public void testOnetoOne(){
		Orderitem item = hibernateTemplate.get(Orderitem.class, 1);
		System.out.println(item);
		/*System.out.println(item.getBook());*/
	}
	
	@Test
	public void testOnetoOne2(){
		//使用两条sql 分别查询 2 个对象的数据
		List<Orderitem> items = hibernateTemplate.find("from Orderitem");
		System.out.println(items);
		System.out.println(items.get(0).getBook());
	}
	
	@Test
	public void testSave(){
		/*//新增orderitem 关联 book
		Orderitem orderitem=new Orderitem();
		orderitem.setAmount(150);
		orderitem.setCount(3);
		Book book=new Book();
		book.setBname("think in java");
		book.setPrice(50);
		
		orderitem.setBook(book);
		//先保存子对象 ，因为主对象有引用到子对象的id
		hibernateTemplate.save(book);
		hibernateTemplate.save(orderitem);*/
		
	
	}
	@Test
	public void testManytoOne(){
		Orderitem item = hibernateTemplate.get(Orderitem.class, 3);
		System.out.println(item);
		System.out.println("========"+item.getBook());
		System.out.println(item.getOrder());
		
		
		
		//hibernateTemplate.get(entityName, id)
	}
	
	
	
	@Test
	public void testOneToMany(){
		Order order = hibernateTemplate.get(Order.class, 1);
		System.out.println(order);
		System.out.println(order.getOrderitems());
	}
	
	@Test
	public void testManyToMany(){
		Author author = hibernateTemplate.get(Author.class, 1);
		System.out.println(author.getId());
		System.out.println(author.getAname());
		System.out.println(author.getBooks());
	}
	
	@Test
	public void testManyToMany2(){
		Book book = hibernateTemplate.get(Book.class, 2);
		System.out.println(book);
		System.out.println(book.getAuthors());
	/*	System.out.println(author.getAname());
		System.out.println(author.getBooks());*/
	}
	@Test
	public void testSetAndList(){
		Set<String> set = new HashSet<>();
		set.add("a");
		set.add("b");
		List<String> list = new ArrayList<>(set);
		System.out.println(list.get(0));
		System.out.println(list.get(1));
	}
	@Test
	public void testAddOrder(){
		Order order =new Order();
		order.setAmount("345");
		order.setOrderCode("134123");
		
		Orderitem orderitem1=new Orderitem();
		orderitem1.setAmount(123);
		orderitem1.setCount(1);
		Orderitem orderitem2=new Orderitem();
		orderitem2.setAmount(1234);
		orderitem2.setCount(12);
		
	}
	
	
	@Test 
	public void testReflect() throws IllegalArgumentException, IllegalAccessException{
		
		HibernateTemplate	hibernateTemplate=new HibernateTemplate();
		OrderService orderService = new OrderService();
		Class clazz=OrderService.class;
		Field[] fields=clazz.getDeclaredFields();
		Field templateField=fields[0];
		
		templateField.setAccessible(true);
		System.out.println(templateField.getName());
		//利用反射对orderService中的hibernateTemplate属性进行赋值
		templateField.set(orderService, hibernateTemplate);
		System.out.println(orderService);
	
	
	}
	
	@Test
	public void testAddOrder1(){
		OrderService service = (OrderService)context.getBean("orderService");
		try {
			service.addOrder();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
