import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ekunt.model.Husband;
import com.ekunt.model.Wife;

public class HibernateTest {

	private Session session;
	/**
	 * 一对一的外键关系， 注解： 单向时，直接@OneToOne。 双向时，设置mappedby（一般必设）。mappedby=关联类中的此类的属性。
	 * 
	 * xml： 单向时，设置many-to-one标签的unique=true。
	 * 双向时，一方设成many-to-one，对方设成one-to-one,设置property-ref（一般必设）。
	 * 
	 * mappedby和property-ref 的作用相同，使某一方管理外键关系，故只生成1个外键关系，否则将生成2个外键关系
	 */
	@Test
	public void test() {
		new SchemaExport(new Configuration().configure()).create(true, true);
	}

	@Before
	public void before() {
		// 1.创建配置对象
		Configuration cfg = new Configuration().configure();
		// 2.创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties())
				.buildServiceRegistry();
		// 3.创建会话工厂对象
		SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		// 4.创建会话
		session = sessionFactory.openSession();
		// 5.开启事务（Hibernate默认不会自动提交，所以不使用事务手动提交的话，数据并不会进入数据库）
		session.beginTransaction();
		
	}

	@After
	public void after() {
		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void testSave() {
		Husband h = new Husband();
		h.setId(2);
		h.setName("gg");
		session.update(h);
	}
}
