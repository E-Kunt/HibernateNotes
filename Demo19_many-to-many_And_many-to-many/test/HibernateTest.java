import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.ekunt.Util.HibernateUtil;
import com.ekunt.entity.Grade;
import com.ekunt.entity.Student;

public class HibernateTest {

	/**
	 * 测试多对多双向关联
	 */
	@Test
	public void saveTest(){
		Student s1 = new Student("张三",18);
		Student s2 = new Student("李四",20);
		Set<Student> students = new HashSet<Student>();
		students.add(s1);
		students.add(s2);
		Grade grade = new Grade("计算机1班","大班：计算机1班，小班：软件2班");
		grade.setStudents(students);
		
		Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();
		session.save(grade);
		session.save(s1);
		session.save(s2);
		t.commit();
		HibernateUtil.closeSession(session);
		
	}
}
