import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.junit.Test;

import com.ekunt.Util.HibernateUtil;
import com.ekunt.entity.Student;

public class HibernateTest {

	@Test
	public void saveTest(){
		Student s1 = new Student("古月哥欠",35);
		Student s2 = new Student("纠结伦",45);
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(s1);
		session.save(s2);
		session.getTransaction().commit();
		HibernateUtil.closeSession(session);
	}
	
	/**
	 * 乐观锁：见实体类@Version
	 * 
	 */
	@Test
	public void testOptimisticLock() {
		Session session1 = HibernateUtil.getSession();
		Session session2 = HibernateUtil.getSession();
		session1.beginTransaction();
		Student s1 = (Student) session1.load(Student.class, 1);
		

		session2.beginTransaction();
		Student s2 = (Student) session2.load(Student.class, 1);
		
		s1.setAge(18);
		s2.setAge(20);

		session1.getTransaction().commit();
		System.out.println(s1.getVersion());

		session2.getTransaction().commit(); //此时报错
		System.out.println(s2.getVersion());

		session1.close();
		session2.close();
	}
	
	
}
