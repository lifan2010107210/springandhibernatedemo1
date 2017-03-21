package test;





import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cache.QueryKey;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.entity.User;

public class UserTest {
	@Test
	public void test1(){
		Configuration config=new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
	
		User user =new User();
		user.setName("abc");
		user.setAge(11);
		session.save(user);
		transaction.commit();
		
		session.close();
		sessionFactory.close();
	}
	@Test
	public void update(){
		Configuration config=new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
	
		User user =new User();
		user.setId(1);
		user.setEmail("ssdf@cc.com");
		
		session.update(user);
		
		
		transaction.commit();
		
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void delete(){
		Configuration config=new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
	
		User user =new User();
		user.setId(2);
		session.delete(user);
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	
	@Test
	public void test(){
		 int i = 0 , j ;

         for(j=0;j<5;j++){

               i=i++; System.out.println("i="+i);
          }
	}
	
	@Test
	public void get(){
		Configuration config=new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		User user=(User)session.get(User.class, 3);
		user.setAge(30);//持久数据自动同步
		
		//直接从session 缓 存中直接获取 ，而不是再次发起sql 请求
		User user1=(User)session.get(User.class, 3);
		System.out.println(user);
		System.out.println(user1);
		transaction.commit();
		
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void load(){
		Configuration config=new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		User user=(User)session.load(User.class, 3);
		
		System.out.println(user);
		transaction.commit();
		
		session.close();
		sessionFactory.close();
	}
	
	
	@Test
	public void gethql(){
		Configuration config=new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		String hql="select u from User u";//全表查询
		Query query=session.createQuery(hql);
		List<User> list=query.list();
		System.out.print(list);
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	
	@Test
	public void get2(){
		Configuration config=new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		//dasdf
		String hql="select u from User u where u.name= :name";
		Query query=session.createQuery(hql);
		query.setString("name", "abc");
		List<User> list =query.list();
		System.out.print(list);
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void get3(){
		Configuration config=new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		String hql="select u from User u where u.name= ?";
		Query query=session.createQuery(hql);
		query.setString(0, "abc");
		List<User> list =query.list();
		System.out.print(list);
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void get4(){
		Configuration config=new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		String hql = "update User set age=:age where name=:name";
		Query query= session.createQuery(hql);
		query.setInteger("age", 10);
		query.setString("name", "abc");
		int affectRow=query.executeUpdate();
		System.out.print(affectRow);
		
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void get5(){
		Configuration config=new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		String hql ="delete from User where name=:name";
		Query query=session.createQuery(hql);
		query.setString("name", "abc");
		int affectRow=query.executeUpdate();
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	@Test
	public void get6(){
		Configuration config=new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		String hql="select u from User u where u.name= :name";
		Query query=session.createQuery(hql);
		query.setString("name", "abc");
		User user=(User)query.uniqueResult();
		System.out.print(user);
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	@Test
	public void get7(){
		Configuration config=new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		String hql="select u from User u ";
		Query query=session.createQuery(hql);
		int currentPage=2;
		int pageSize=2;
		//查询某页
		int startRow=(currentPage-1)*pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		List<User> userlist =query.list();
		System.out.print(userlist);
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	//用count来查询总记录数
	@Test
	public void get8(){
		Configuration config=new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		String hql= "select count(*) from User";
		Query query= session.createQuery(hql);
		Long count=(Long)query.uniqueResult();
		if(count >0){
			System.out.println("有相关记录");
		}
		System.out.println(count);
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	@Test
	public void get9(){
		Configuration config=new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		String hql= "select count(*) from User where age >:age";
		Query query= session.createQuery(hql);
		query.setInteger("age", 10);
		Long count=(Long)query.uniqueResult();
		if(count >0){
			System.out.println("有相关记录");
		}
		System.out.println(count);
		//limit
		hql="from User where age > :age";
		query=session.createQuery(hql);
		query.setInteger("age", 10);
		int currentPage=2;
		int pageSize=2;
		int startRow =(currentPage-1)*pageSize;
		long pageCount=(count+pageSize-1)/pageSize;
		
		System.out.println("总页数"+pageCount);
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void get10(){
		Configuration config=new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		String hql="select distinct name from User";
		Query query=session.createQuery(hql);
		List<String> list =query.list();
		
		System.out.println(list.get(2).length());
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void get11(){
		Configuration config=new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		String hql="select age,count(*) from User group by age";
		Query query=session.createQuery(hql);
		List<Object[]> list=query.list();
		for (Object[] o : list) {
			System.out.println("年龄"+o[0]);
			System.out.println("数量"+o[0]);
		}
		System.out.println(list);
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void get12(){
		Configuration config=new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		String hql="select age,count(*) from User group by age having count(*)>1";
		Query query=session.createQuery(hql);
		List<Object[]> list=query.list();
		for (Object[] o : list) {
			System.out.println("年龄"+o[0]);
			System.out.println("数量"+o[1]);
		}
		System.out.println(list);
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	
}
