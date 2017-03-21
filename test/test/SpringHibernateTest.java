package test;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.dialect.IngresDialect;
import org.hibernate.hql.ast.tree.FromClause;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.entity.User;

public class SpringHibernateTest {

	@Test
	public void test1(){
		//1. 加载spring的配置文件
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
		//2. 获取hibernateTemplate这个bean
		HibernateTemplate hibernateTemplate = context.getBean(HibernateTemplate.class);
		
		User user = new User();
		user.setAge(20);
		user.setName("qqq");
		user.setEmail("sadf@sadf.com");
		hibernateTemplate.save(user);
	}
	
	
	
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	HibernateTemplate hibernateTemplate = context.getBean(HibernateTemplate.class);
	@Test
	public void test2(){
		/*String hql ="from User ";
		List<User> list=hibernateTemplate.find(hql);
		System.out.print(list);*/
	
		
		List<User> list=hibernateTemplate.find("from User");
		System.out.print(list);
	}
	@Test
	public void test3(){
		/*String hql ="from User where name=?";
		List<User> list=hibernateTemplate.find(hql,"abc");
		System.out.print(list);*/
	
		List<User>list=hibernateTemplate.find("from User where name=?","abc");
		System.out.print(list);
	
	}
	@Test
	public void test4(){
		/*String hql ="update User set email=? where id=?";
		int affectRow=hibernateTemplate.bulkUpdate(hql,"www.qq.com" ,5);
		System.out.print(affectRow);*/
		
		hibernateTemplate.bulkUpdate("update User set email=? where id=?","www.qq.com",5);
		
	}
	
	@Test
	public void test5(){
		/*String hql ="delete from User where id=?";
		int affectRow=hibernateTemplate.bulkUpdate(hql ,1);
		System.out.print(affectRow);*/
	hibernateTemplate.bulkUpdate("delete from User where id=?",1);
	
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public  void testPage(){
		final String hql ="from User";
		List<User> userlist=hibernateTemplate.executeFind(
					new HibernateCallback<List<User>>() {
						@Override
						public List<User> doInHibernate(org.hibernate.Session arg0)
								throws HibernateException, SQLException {
							// TODO Auto-generated method stub
							Query query=arg0.createQuery(hql);
							query.setFirstResult(2);
							query.setMaxResults(2);
							return query.list();
						}
					});
		System .out.print(userlist);
	}
	
}

