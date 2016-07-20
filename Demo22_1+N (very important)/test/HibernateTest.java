import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import com.ekunt.Util.HibernateUtil;
import com.ekunt.entity.Group;
import com.ekunt.entity.Student;

/**
 * 演示Hibernate的1+N问题：
 * fetch=FetchType.eager配置下，
 * 取某对象（Student）的自身属性，仍会导致其关联对象(Group)一并查询，
 * 本来只需要发1条SQL语句即可完成，结果却发了1+N条SQL语句，影响性能。
 * 
 * 解决方法：
 * 1.fetch = FetchType.lazy , 开始只发关于本表的SQL语句，即等用到关联对象时，再发关联表的SQL语句。
 * 2.在关联对象类上标注@BatchSize(size=x) , 设置管理对象查询时一次性查询多少条记录， 使转为为 1+n/x问题。（不推荐）
 * 3.join fetch , 如 使用"from Student s left join fetch s.group g"， 进行表连接查询，此时就发1条SQL语句。
 * 4.使用QBC查询，默认效果与3相同。
 * 
 * @author E-Kunt
 *
 */
public class HibernateTest {

	@Test
	public void saveTest(){
		Group g1 = new Group("JAVA","JavaSE");
		Group g2 = new Group("ASP.NET","ASP.NET3.5");
		Student s1 = new Student("古月哥欠",35);
		Student s2 = new Student("纠结伦",45);
		s1.setGroup(g1);
		s2.setGroup(g2);
		
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(g1);
		session.save(g2);
		session.save(s1);
		session.save(s2);
		session.getTransaction().commit();
		HibernateUtil.closeSession(session);
	}
	
	@Test
	public void queryTest() {
		Session session = HibernateUtil.getSession();
		List<Student> students = session.createQuery("from Student").list();
		for(Student s : students) {
			System.out.println(s.getId() + "-" + s.getName());
		}
	}
	
	/**
	 * 1+N问题解决方法3
	 */
	@Test
	public void joinFetchTest() {
		Session session = HibernateUtil.getSession();
		List<Student> students = session.createQuery("from Student s left join fetch s.group g").list();
		for(Student s : students) {
			System.out.println(s.getId() + "-" + s.getName());
		}
	}
	
	/**
	 * 1+N问题解决方法4
	 */
	@Test
	public void QBCTest() {
		Session session = HibernateUtil.getSession();
		List<Student> students = session.createCriteria(Student.class).list();
		for(Student s : students) {
			System.out.println(s.getId() + "-" + s.getName());
		}
	}
	
	
}
