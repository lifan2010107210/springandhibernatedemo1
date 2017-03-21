package test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Test;

import com.entity.User;

public class UserTest2 {

	@Test
	public void testHQLSelectAll(){
		//1. 加载hibernate配置文件
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		//2. 获取sessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory();
		//3. 从sessionFactory中获取session, 相当于Connection
		Session session = sessionFactory.openSession();
		//4. 增删改查
		//4.1 开启事务, 增删改的操作都是必须开启事务
		Transaction transaction = session.beginTransaction();
		//4.2 session去操作数据库
		
		String hql = "from User where name=:name";//
		Query query = session.createQuery(hql);
		query.setString("name", "sdf");
//		List<User> userlist = query.list();
//		User user = userlist.get(0);
		//查询的记录<=1条 , 如果>1条 那么就会抛出异常
		User user = (User)query.uniqueResult();
		System.out.println(user);
		
		//4.3 事务提交
		transaction.commit();
		//5. 资源回收
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void testHQLPage(){
		//1. 加载hibernate配置文件
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		//2. 获取sessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory();
		//3. 从sessionFactory中获取session, 相当于Connection
		Session session = sessionFactory.openSession();
		//4. 增删改查
		//4.1 开启事务, 增删改的操作都是必须开启事务
		Transaction transaction = session.beginTransaction();
		//4.2 session去操作数据库
		
		String hql = "from User where age > :age";//
		Query query = session.createQuery(hql);
		query.setInteger("age", 10);
		int currentPage = 2;//当前第2页
		int pageSize = 2;//每页2条
		int startRow = (currentPage-1)*pageSize;
		
		query.setFirstResult(startRow);//从第3条开始
		query.setMaxResults(pageSize);//每页2条
		//executeUpdate支持删除和更新操作
		List<User> userlist = query.list();
		System.out.println(userlist);
		
		//4.3 事务提交
		transaction.commit();
		//5. 资源回收
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void testHQLCount(){
		//1. 加载hibernate配置文件
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		//2. 获取sessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory();
		//3. 从sessionFactory中获取session, 相当于Connection
		Session session = sessionFactory.openSession();
		//4. 增删改查
		//4.1 开启事务, 增删改的操作都是必须开启事务
		Transaction transaction = session.beginTransaction();
		//4.2 session去操作数据库
		
		//count
		String hql = "select count(*) from User where age > :age";// 
		Query query = session.createQuery(hql);
		query.setInteger("age", 10);
		Long count = (Long)query.uniqueResult();//记录总数
		if(count > 0){
			System.out.println("有相关记录..");
		}
		System.out.println(count);
		
		//limit
		hql = "from User where age > :age";//
		query = session.createQuery(hql);
		query.setInteger("age", 10);
		int currentPage = 2;//当前第2页
		int pageSize = 2;//每页2条
		int startRow = (currentPage-1)*pageSize;
		
		long pageCount = count % pageSize == 0 ? count/pageSize : count/pageSize+1;
		System.out.println("总页数:"+pageCount);
		
		
		query.setFirstResult(startRow);//从第3条开始
		query.setMaxResults(pageSize);//每页2条
		//executeUpdate支持删除和更新操作
		List<User> userlist = query.list();
		System.out.println(userlist);
		
		//4.3 事务提交
		transaction.commit();
		//5. 资源回收
		session.close();
		sessionFactory.close();
	}
	
	
	@Test
	public void testHQLDistinct(){
		//1. 加载hibernate配置文件
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		//2. 获取sessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory();
		//3. 从sessionFactory中获取session, 相当于Connection
		Session session = sessionFactory.openSession();
		//4. 增删改查
		//4.1 开启事务, 增删改的操作都是必须开启事务
		Transaction transaction = session.beginTransaction();
		//4.2 session去操作数据库
		
		//count
		String hql = "select distinct name from User";// 
		Query query = session.createQuery(hql);
		List<String> list = query.list();
		System.out.println(list.get(0).length());
		
		//4.3 事务提交
		transaction.commit();
		//5. 资源回收
		session.close();
		sessionFactory.close();
	}
	
	
	Configuration config = new Configuration().configure("hibernate.cfg.xml");
	//2. 获取sessionFactory
	SessionFactory sessionFactory = config.buildSessionFactory();
	//3. 从sessionFactory中获取session, 相当于Connection
	Session session = sessionFactory.openSession();
	
	@After
	public void after(){
		//5. 资源回收
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void testHQLGroupBy(){
		//group by
		String hql = "SELECT age,count(*) FROM User group by age having count(*)>1";// 
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		for(Object[] oa : list){
			System.out.println("年龄:"+oa[0]);
			System.out.println("数量:"+oa[1]);
		}
		System.out.println(list);
	}
}
