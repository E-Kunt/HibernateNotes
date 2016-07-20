import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

import com.ekunt.model.Student;

/**
 * 测试使用openSession和getCurrentSession方法生成Session对象的区别
 * @author E-Kunt
 *
 */
public class SessionTest {
	
	/**
	 * 区别1：
	 * 使用openSession()时，每次都生成新的session对象。
	 * 使用getCurrentSession()时，每次都是使用现有的同一个单例对象。
	 */
	@Test
	public void testSession() {
		Configuration cfg = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		Session session1 = sessionFactory.openSession();
		Session session2 = sessionFactory.openSession();
		System.out.println("同个Session对象吗(使用openSession)？" + (session1==session2) ); //false
		
		
		Session session3 = sessionFactory.getCurrentSession();
		Session session4 = sessionFactory.getCurrentSession();
		System.out.println("同个Session对象吗(使用getCurrentSession)？" + (session3==session4) ); //true
	}
	
	/**
	 * 区别2：
	 * 使用openSession()时，事务提交/回滚后，需要手动调用close方法关闭session，否则多次之后会导致连接池溢出。
	 * 每次都创建新的Connection对象。
	 */
	@Test
	public void openSessionMustClose() {
		Configuration cfg = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		Session session1 = sessionFactory.openSession();
		Student std = new Student(1,"E-Kunt",new Date(),"sgu");
		Transaction transaction = session1.beginTransaction();
		session1.doWork(new Work() {
			@Override
			public void execute(Connection conn) throws SQLException {
				System.out.println("session1使用的Connection对象哈希码：" + conn.hashCode());
			}
		});
		session1.save(std);
		transaction.commit();
		//session1.close(); //openSession必须手动关闭，此处为了演示区别才注释掉
		
		
		Session session2 = sessionFactory.openSession();
		std = new Student(2,"Eric",new Date(),"sw");
		transaction = session2.beginTransaction();
		session2.doWork(new Work() {
			@Override
			public void execute(Connection conn) throws SQLException {
				System.out.println("session2使用的Connection对象哈希码：" + conn.hashCode());
			}
		});
		session2.save(std);
		transaction.commit();
		//session2.close(); //openSession必须手动关闭，此处为了演示区别才注释掉
		
 	}
	
	/**
	 * 区别2：
	 * 使用getCurrentSession()时，事务提交/回滚后，会自动关闭session。
	 * 使用同一个Connection对象，第一次用完会自动释放，第二次会再重复使用
	 */
	@Test
	public void getCurrentSessionWillAutoClose() {
		Configuration cfg = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		Session session1 = sessionFactory.getCurrentSession();
		Student std = new Student(1,"E-Kunt",new Date(),"sgu");
		Transaction transaction = session1.beginTransaction();
		session1.doWork(new Work() {
			@Override
			public void execute(Connection conn) throws SQLException {
				System.out.println("session1使用的Connection对象哈希码：" + conn.hashCode());
			}
		});
		session1.save(std);
		transaction.commit();
		
		
		Session session2 = sessionFactory.getCurrentSession();
		std = new Student(2,"Eric",new Date(),"sw");
		transaction = session2.beginTransaction();
		session2.doWork(new Work() {
			@Override
			public void execute(Connection conn) throws SQLException {
				System.out.println("session2使用的Connection对象哈希码：" + conn.hashCode());
			}
		});
		session2.save(std);
		transaction.commit();
		
 	}

}
