import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ekunt.model.Course;
import com.ekunt.model.Score;
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
	public void testSave() {
		Course course = new Course();
		course.setName("java");
		Student student = new Student();
		student.setName("Eric");
		Score score = new Score();
		score.setPoint(100);
		
		score.setCourse(course);
		score.setStudent(student);
		
		session.save(course);
		session.save(student);
		session.save(score);
		
	}
	
	@Test
	public void testQuery() {
		Student s1 = (Student)session.load(Student.class, 3);
		for(Course c1 : s1.getCourses()) {
			System.out.println(c1.getName());
		}
		
		Course c2 = (Course)session.load(Course.class, 3);
		for(Student s2 : c2.getStudents()) {
			System.out.println(s2.getName());
		}
		
		Score s3 = (Score) session.load(Score.class, 4);
		System.out.println(s3.getStudent().getName());
		System.out.println(s3.getCourse().getName());
		System.out.println(s3.getPoint());
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
