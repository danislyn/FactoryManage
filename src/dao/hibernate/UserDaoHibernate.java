package dao.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.interfaces.UserDao;
import hibernate.util.HibernateSessionFactoryUtil;
import pojo.User;

public class UserDaoHibernate extends GenericDaoHibernate<User, Integer> implements UserDao {

	@Override
	public User loginCheck(String username, String password) {
		User result = null;
		try {
			Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			
			try {
				Criteria criteria = session.createCriteria(User.class);
				criteria.add(Restrictions.eq("username", username));
				criteria.add(Restrictions.eq("password", password));
				
				result = (User) criteria.uniqueResult();
				tx.commit();
				
			} catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
