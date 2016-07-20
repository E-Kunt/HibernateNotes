import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ekunt.model.Student;
/**
 * 测试Hibernate的单表的 增save()、删delete()、改update()、查get()/load()功能
 * *****get()和load()的区别***
 * 1.在不考虑缓存情况下，get调用后直接发出sql语句，返回实体对象。
 *   load调用后，不会立即发出sql语句，只返回代理对象，保存了实体对象的id，当使用该对象非主键属性时，才真正发出sql语句，所以若提前关闭了session，则会报错。
 * 2.数据不存在时，get方法返回null，load方法抛出异常org.hibernate.ObjectNotFoundException
 * 
 * @author E-Kunt
 *
 */
public class HibernateTest {
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	@Before
	public void before() {
		Configuration cfg = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
		sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	

	@After
	public void after() {
		transaction.commit();
		session.close();
		sessionFactory.close();
	}

	@Test
	public void saveTest() {
		Student s = new Student(1,"E-Kunt",new Date(),"sw");
		session.save(s);
	}
	
	@Test
	public void getTest() {
		Student s = (Student) session.get(Student.class, 1);
		System.out.println(s);
		System.out.println("s的类型：" + s.getClass().getName());
	}
	
	@Test
	public void loadTest() {
		Student s = (Student) session.load(Student.class, 1);
		System.out.println(s);
		System.out.println("s的类型：" + s.getClass().getName());
	}
	
	/**
	 * 
	 * 默认配置下，会更新所有字段，效率较低。（常用）
	 */
	@Test
	public void updateTest1() {
		Student s = (Student) session.get(Student.class, 1);
		s.setName("Eric1");
		session.update(s);
	}
	
	/**
	 * 默认配置下，会更新所有字段，效率较低。
	 * 
	 * 只要拥有id,update方法就可以执行。执行后会把对象的状态改为persistent
	 * 
	 */
	@Test
	public void updateTest2() {
		Student s = new Student();
		s.setId(1);
		s.setName("Eric2");
		session.update(s);
	}
	
	/**
	 * 默认配置下，会更新所有字段，效率较低。
	 * 
	 * 当对象处于persistent状态时，直接修改对象的属性值，
	 * commit时，Hibernate会自动再次检查缓存与数据库的数据差异，有差异时会自动发update语句。
	 */
	@Test
	public void updateTest3() {
		Student s = (Student) session.get(Student.class, 1);
		s.setName("Eric3");
	}
	
	/**
	 * 默认配置下，只更新改变的字段，但会发select语句，效率一般。
	 * 
	 * 使用merge()方法来更新，主要用途在与跨session更新。
	 */
	@Test
	public void updateTest4() {
		Student s = (Student) session.get(Student.class, 1);
		s.setName("Eric4");
		session.merge(s);
	}
	
	/**
	 * 默认配置下，只更新改变的字段，效率最高。
	 * 使用hql语句来更新。（推荐使用）
	 */
	@Test
	public void updateTest5() {
		String hql = "update Student s set s.name = 'Eric5' where s.id = 1 ";
		session.createQuery(hql).executeUpdate();
	}
	
	/**
	 * 对象在delete之后会变为transient状态.
	 * delete只有在对象拥有id的情况下才会执行。
	 * 就算对象只拥有id，而没有其他数据，仍然可以执行delete.
	 */
	@Test
	public void deleteTest() {
		Student s = (Student) session.get(Student.class, 1);
		session.delete(s);
	}
	
	/**
	 * 对新增的transeint对象,是save
	 * 对修改后的detached对象,是update
	 */
	@Test
	public void saveOrUpdateTest() {
		Student s = new Student(3,"E-Kunt",new Date(),"sw");
		session.saveOrUpdate(s); //save
		transaction.commit();
		session.close();
		
		session = sessionFactory.openSession();
		s.setName("stdname");
		transaction = session.beginTransaction();
		session.saveOrUpdate(s); //update
	}
	
	/**
	 * 不用clear时，第二次查询直接从缓存取，所以只发1条select语句。
	 * 用了clear之后，缓存被清除，所以会发2条select语句。
	 */
	@Test
	public void clearTest() {
		Student s1 = (Student) session.load(Student.class, 1);
		System.out.println(s1);
		
		session.clear();
		
		Student s2 = (Student) session.load(Student.class, 1);
		System.out.println(s2);
	}
	
	/**
	 * 不用flush时，连续多次更改属性值，只发1条update语句。
	 * 在2条属性值更新中间插入flush之后，强制同步缓存和数据库的数据，所以会发2条update语句。
	 */
	@Test
	public void flushTest() {
		Student s = (Student) session.get(Student.class, 1);
		s.setName("flush1");
		session.flush();
		s.setName("flush2");
		session.update(s);
	}

}
