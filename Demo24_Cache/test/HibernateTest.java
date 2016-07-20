import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ekunt.model.Teacher;

/**
 * 演示缓存
 * @author E-Kunt
 *
 */
public class HibernateTest {
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	@Before
	public void before() {
		//1.创建配置对象
		Configuration cfg = new Configuration().configure();
		//2.创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
		//3.创建会话工厂对象
		sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		//4.创建会话
		session = sessionFactory.openSession();
		//5.开启事务（Hibernate默认不会自动提交，所以不使用事务手动提交的话，数据并不会进入数据库）
		transaction = session.beginTransaction();
	}

	@Test
	public void saveTest() {
		Teacher t1 = new Teacher("E-Kunt",new Date(),30000,"sw");
		Teacher t2 = new Teacher("Ekunt",new Date(),40000,"sg");
		Teacher t3 = new Teacher("Eric",new Date(),50000,"zh");
		//4.保存对象进数据库
		session.save(t1);
		session.save(t2);
		session.save(t3);
	}
	

	/**
	 * 演示二级缓存，sessionFactory级别的缓存，可以跨越session存在。
	 * 
	 * 在不同个Session中，连续查询同个对象2次，
	 * 不用二级缓存，发2次SQL，
	 * 使用二级缓存，只发1次SQL。
	 * 
	 * 适用情况：1.经常访问 2.不经常改动 3.数量有限 （如：用户权限，部门体系）
	 * 
	 * load()、iterate() 默认使用二级缓存。
	 * list() 默认只往二级缓存存数据，查询时不使用二级缓存（除非打开查询缓存,调用Query的setCachable(true)）。
	 * 
	 */
	@Test
	public void SecondCacheTest() {
		Teacher t = (Teacher) session.load(Teacher.class, 1);
		System.out.println(t.getId() + "-" + t.getName());
		transaction.commit();
		session.close();
		
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		Teacher t1 = (Teacher) session.load(Teacher.class, 1);
		System.out.println(t1.getId() + "-" + t1.getName());
		
	}
	
	
	/**
	 * 演示一级缓存，session级别的缓存。
	 * 在同个Session中，连续查询同个对象2次，只发1次SQL
	 */
	@Test
	public void firstCacheTest() {
		Teacher t = (Teacher) session.load(Teacher.class, 1);
		System.out.println(t.getId() + "-" + t.getName());
		
		Teacher t1 = (Teacher) session.load(Teacher.class, 1);
		System.out.println(t1.getId() + "-" + t1.getName());
		
	}

	@After
	public void after() {
		//5.提交事务
		transaction.commit();
		//6.关闭会话
		session.close();
		//7.关闭会话工厂
		sessionFactory.close();
	}
}
