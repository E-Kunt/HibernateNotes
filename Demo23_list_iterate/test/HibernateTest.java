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
 * 演示Query中的list()和iterate()方法的区别(只是为了应付面试)
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
	 * list()方法:
	 * 1.一次性取所有字段。
	 * 2.同个session中，第二次调用list()，仍会从数据库取数据。
	 */
	@Test
	public void listTest() {
		List<Teacher> teachers = session.createQuery("from Teacher").list();
		for(Teacher t : teachers) {
			System.out.println(t.getId() + "-" + t.getName());
		}
		List<Teacher> teachers2 = session.createQuery("from Teacher").list();
		for(Teacher t : teachers2) {
			System.out.println(t.getId() + "-" + t.getName());
		}
	}
	
	/**
	 * iterate()方法:
	 * 1.先取对象的id（主键），等要用到对象的时候，再通过id从数据库去取。
	 * 2.同个session中，第二次调用iterate()，首先从session级缓存去取。
	 */
	@Test
	public void iterateTest() {
		Iterator<Teacher> teachers = session.createQuery("from Teacher").iterate();
		while(teachers.hasNext()){
			Teacher t = (Teacher) teachers.next();
			System.out.println(t.getId() + "-" + t.getName());
		}
		Iterator<Teacher> teachers2 = session.createQuery("from Teacher").iterate();
		while(teachers2.hasNext()){
			Teacher t = (Teacher) teachers2.next();
			System.out.println(t.getId() + "-" + t.getName());
		}
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
