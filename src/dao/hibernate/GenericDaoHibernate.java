package dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.interfaces.GenericDao;
import hibernate.util.HibernateSessionFactoryUtil;

public abstract class GenericDaoHibernate<T, PK extends Serializable> implements GenericDao<T, PK> {

	private Class<T> clazz;
	
	public GenericDaoHibernate()
	{
		//反射获取T.class，实参类型
		clazz = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Override
	public T findById(PK id)
	{
		T entity = null;
		
		try {
			Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			
			try {
				entity = (T) session.get(clazz, id);
				tx.commit();
				
			} catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		return entity;
	}
	
	@Override
	public ArrayList<T> findAll()
	{
		ArrayList<T> result = new ArrayList<T>();
		
		try {
			Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			
			try {
				Query query = session.createQuery("from " + clazz.getName());
				result = new ArrayList<T>(query.list());
				tx.commit();
				
			} catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public PK save(T entity)
	{
		//boolean result = false;
		PK result = null;
		
		try {
			Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			
			try {
				result = (PK) session.save(entity);
				//result = true;
				tx.commit();
				
			} catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;
	}
	
	@Override
	public boolean update(T entity)
	{
		boolean result = false;
		
		try {
			Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			
			try {			
				session.saveOrUpdate(entity);
				result = true;
				tx.commit();
				
			} catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;
	}
	
	@Override
	public boolean delete(T entity)
	{
		boolean result = false;
		
		try {
			Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			
			try {
				session.delete(entity);
				result = true;
				tx.commit();
				
			} catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;
	}
	
	@Override
	/**
	 * 此法不好，暂时这样
	 */
	public boolean delete(PK id)
	{
		boolean result = false;
		
		try {
			T entity = findById(id);
			
			if(entity!=null && delete(entity)==true)
				result = true;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}
