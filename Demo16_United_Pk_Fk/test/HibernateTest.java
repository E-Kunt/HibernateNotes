import javax.persistence.JoinColumns;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class HibernateTest {

	/**
	 * 联合主键的外键关系：@JoinColumns
	 * 
	 */
	@Test
	public void test() {
		new SchemaExport(new Configuration().configure()).create(true,true);
	}

	
}
