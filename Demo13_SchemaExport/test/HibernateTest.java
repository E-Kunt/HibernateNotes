import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class HibernateTest {

	/**
	 * 删除hbm2ddl.auto配置
	 * 手动控制建表
	 * 
	 */
	@Test
	public void test() {
		new SchemaExport(new Configuration().configure()).create(true,true);
	}


}
