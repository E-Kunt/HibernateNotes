import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.Date;

import org.hibernate.Hibernate;
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
	public void saveBlob() throws Exception {
		Student s = new Student(1,"E-Kunt",new Date(),"sgu");
		InputStream input = new FileInputStream(new File("pic/pic.gif"));
		Blob pic = Hibernate.getLobCreator(session).createBlob(input,input.available());
		s.setPic(pic);
		session.save(s);
	}
	
	@Test
	public void getBlob() throws Exception {
		Student s = (Student) session.get(Student.class, 1);
		Blob pic = s.getPic();
		InputStream input = pic.getBinaryStream();
		OutputStream output = new FileOutputStream(new File("pic/blob2.gif"));
		byte[] buff = new byte[input.available()];
		input.read(buff);
		output.write(buff);
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
