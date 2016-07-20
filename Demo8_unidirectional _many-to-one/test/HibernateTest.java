import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.ekunt.Util.HibernateUtil;
import com.ekunt.entity.Grade;
import com.ekunt.entity.Student;

/**
 * 单向多对一关系关系（学生--->班级）
 * 注意关联的方向
 */
public class HibernateTest {

	/**
	 * 保存操作。
	 * 创建班级1，学生1、2， 把学生1、2都加入班级1。
	 */
	@Test
	public void saveTest(){
		Grade g = new Grade("JAVA一班","JavaSE");
		Student s1 = new Student("郭敬明",35);
		Student s2 = new Student("纠结伦",45);
		s1.setGrade(g);
		s2.setGrade(g);
		
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(g);
		session.save(s1);
		session.save(s2);
		session.getTransaction().commit();
		HibernateUtil.closeSession(session);
	}
	
	
}
