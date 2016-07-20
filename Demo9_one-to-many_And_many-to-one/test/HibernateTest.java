import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.ekunt.Util.HibernateUtil;
import com.ekunt.entity.Grade;
import com.ekunt.entity.Student;

/**
 * 双向关系（班级<--->学生）
 * 
 * 若用注解，@OneToMany(必须设置mappedby) @ManyToOne 
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
		
		//设置双向关联关系,在班级（一方）设置控制反转属性inverse=true后，一方不再维和关联关系，不会再发出多余的update语句，提高性能
		grade.setStudents(students);
		s1.setGrade(grade);
		s2.setGrade(grade);
		
		Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();
		
		//在班级（一方）设置级联属性cascade不为none后,可以在对应权限操作,如save中，不用再手动save学生（多方），Hibernate会自动级联save。
		//同理，学生（多方）也可设置cascade属性。
	
		//session.save(grade);
		session.save(s1);
		session.save(s2);
		
		t.commit();
		HibernateUtil.closeSession(session);
		
	}
	
	/**
	 * 查询操作。
	 * 配置双向关联后，通过查询某一方数据后，可以直接导航查询到另一方的相关数据。不再有方向限制。
	 */
	@Test
	public void getStudentFromGradeTest(){
		Session session = HibernateUtil.getSession();
		Grade grade = (Grade) session.get(Grade.class, 1);
		Set<Student> students = grade.getStudents();
		for(Student s : students) {
			System.out.println(s);
		}
		HibernateUtil.closeSession(session);
	}
	
	/**
	 * 查询操作。
	 * 配置双向关联后，通过查询某一方数据后，可以直接导航查询到另一方的相关数据。不再有方向限制。
	 */
	@Test
	public void getGradeFromStudentTest(){
		Session session = HibernateUtil.getSession();
		Student s = (Student)session.get(Student.class, 2);
		Grade g = s.getGrade();
		System.out.println(g);
		HibernateUtil.closeSession(session);
	}
}
