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
			 * ��Ҫ����Throwable����
			 * ���򲶻񲻵�Error�������࣬�Լ�NoClassDefFoundError���͵Ĵ���
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
