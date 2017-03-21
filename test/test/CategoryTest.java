package test;



import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.entity.Category;
import com.entity.Category1;

public class CategoryTest {
	//1. 加载spring的配置文件HibernateTemplate.class
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	//获取hibernateTemplate 这个bean
	HibernateTemplate hibernateTemplate= context.getBean(HibernateTemplate.class);


	@Test
	public void testFIndAll(){
		List<Category1> list = hibernateTemplate.find("from Category1");
		System.out.println(list);
		for(Category1 cat1 : list){
			System.out.println(cat1.getCatList());
		}
	}

	@Test
	public void testFIndAll1(){
		List<Category> list = hibernateTemplate.find("from Category where parent=null");
		for (Category cat1 : list) {
			System.out.println(cat1.getCname());
			for(Category cat2 : cat1.getCatlist()){
				System.out.println(cat2.getCname());
			}
			System.out.println("--------------");
		}
	}
	
	





}
