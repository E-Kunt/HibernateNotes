package com.ekunt.Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	private static Session session;
	
	/**
	 * 创建Configuration对象，读取hibernate.cfg.xml文件，完成初始化
	 */
	static{
		Configuration cfg = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
		sessionFactory = cfg.buildSessionFactory(serviceRegistry);
	}

	/**
	 * 获取SessionFactory
	 * @return SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	/**
	 * 获取Session
	 * @return Session
	 */
	public static Session getSession() {
		session = sessionFactory.openSession();
		return session;
	}
	
	/**
	 * 关闭Session
	 */
	public static void closeSession(Session session) {
		if(session != null) {
			session.close();
			session = null;
		}
	}
	
	

}
