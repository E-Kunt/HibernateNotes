import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ekunt.model.Category;

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
		Category c1 = new Category("分类1");
		Category c11 = new Category("分类1.1");
		Category c12 = new Category("分类1.2");
		Category c121 = new Category("分类1.2.1");
		Category c122 = new Category("分类1.2.2");
		Category c13 = new Category("分类1.3");
		
		c1.getChildren().add(c11);
		c1.getChildren().add(c12);
		c1.getChildren().add(c13);
		c12.getChildren().add(c121);
		c12.getChildren().add(c122);
		
		c121.setParent(c12);
		c122.setParent(c12);
		c11.setParent(c1);
		c12.setParent(c1);
		c13.setParent(c1);
		
		session.save(c1);
	}

	public void printTree(Category category, int level) {
		String preStr = "";
		for(int i = 0; i < level; i++) {
			preStr += "----";
		}
		System.out.println(preStr + category.getName());
		for(Category c : category.getChildren()) {
			printTree(c,level+1);
		}
	}
	
	@Test
	public void testTree() {
		Category c = (Category) session.get(Category.class, 1);
		printTree(c,0);
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
