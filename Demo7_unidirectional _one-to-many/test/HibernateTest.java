import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.ekunt.Util.HibernateUtil;
import com.ekunt.entity.Grade;
import com.ekunt.entity.Student;

/**
 * 单向一对多关系关系（班级--->学生）
 * 建立关联关系后，可以方便的从一个对象导航到另一个对象
 * 注意关联的方向
 */
public class HibernateTest {

	/**
	 * 保存操作。
	 * 创建班级1，学生1、2， 把学生1、2都加入班级1。
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
	
	/**
	 * 查询操作。
	 * 查询班级1的所有学生信息.
	 * 
	 * 单向一对多的关系中，
	 * 只能通过一来查询多，不能通过多来查询一，
	 * 即只能通过班级来查询学生，不能通过学生来查询班级。
	 */
	@Test
	public void getTest(){
		Session session = HibernateUtil.getSession();
		Grade grade = (Grade) session.get(Grade.class, 1);
		System.out.println(grade);
		
		Set<Student> students = grade.getStudents();
		for(Student s : students) {
			System.out.println(s);
		}
		
		HibernateUtil.closeSession(session);
		
	}
	
	/**
	 * 更新操作。
	 * 创建班级2，把编号为1的学生从班级1改为班级2。
	 */
	@Test
	public void updateTest(){
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		
		Student student = (Student) session.get(Student.class, 1);
		
		Grade grade = new Grade("计算机2班","通信工程");
		Set<Student> students = new HashSet<Student>();
		students.add(student);
		grade.setStudents(students);
		session.save(grade);
		
		session.getTransaction().commit();
		HibernateUtil.closeSession(session);
		
	}
	
	/**
	 * 删除操作。
	 * 删除学生2
	 */
	@Test
	public void deleteTest(){
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		Student student = (Student) session.get(Student.class, 2);
		session.delete(student);
		session.getTransaction().commit();
		HibernateUtil.closeSession(session);
		
	}
}
