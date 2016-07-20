import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;
import com.ekunt.model.Student;

public class HibernateTest {
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	@Before
	public void before() {
		Configuration cfg = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties())
				.buildServiceRegistry();
		sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}

	/**
	 * 演示对象3种状态
	 * transient、persistent、detached
	 * 
	 * 说明：缓存中有没有该对象，指内存中session保存的哈希表map中有没有该对象的引用。
	 */
	@Test
	public void test() {
		Student s = new Student("E-Kunt", new Date(), "SGU");
		//此时，s处于transient状态（临时状态）。无id值(id设为自动增长时)。内存中有，缓存中没有，数据库中没有（该对象）。
		System.out.println("transient: id = " + s.getId());
		
		session.save(s);
		//此时，s处于persistent状态（持久化状态）。有id值。内存中有，缓存中有，数据库中有（该对象）。
		System.out.println("persistent: id = " + s.getId());
		
		transaction.commit();
		session.close();
		//此时，s处于detached状态（脱管状态）。有id值。内存中有，缓存中没有，数据库中有（该对象）。
		System.out.println("detached: id = " + s.getId());
		
		sessionFactory.close();
	}

}
