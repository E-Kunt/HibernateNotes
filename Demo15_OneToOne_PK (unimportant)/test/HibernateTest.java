import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class HibernateTest {

	/**
	 * 一对一的主键关系，
	 * 注解：
	 * @OneToOne
	 * @PrimaryKeyJoinColumn
	 * 
	 * xml：
	 * one-to-one标签  reference=tue
	 * 同时主键 gernater 改为 foreign ，再加一个parm标签
	 * 
	 */
	@Test
	public void test() {
		new SchemaExport(new Configuration().configure()).create(true,true);
	}

	
}
