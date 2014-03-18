package hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {

	private static final SessionFactory sessionFactory;
	
	static
	{
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable e) {
			/*
			 * 需要捕获Throwable对象，
			 * 否则捕获不到Error及其子类，以及NoClassDefFoundError类型的错误
			 */
			throw new ExceptionInInitializerError(e);
		}
	}
	
	private HibernateSessionFactoryUtil(){}
	
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
}
