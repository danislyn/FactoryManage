package dao.hibernate;

import hibernate.util.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import pojo.Procedure;
import pojo.Product;
import dao.interfaces.ProcedureDao;
import dao.interfaces.ProductDao;

public class ProcedureDaoHibernate extends GenericDaoHibernate<Procedure, Integer> implements ProcedureDao {

	@Override
	public List<Procedure> getProceduresByProduct(Integer productId) {
		ProductDao productDao = new ProductDaoHibernate();
		Product product = productDao.findById(productId);
		
		List<Procedure> result = new ArrayList<Procedure>();
		try {
			Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			
			try {
				Criteria criteria = session.createCriteria(Procedure.class);
				criteria.add(Restrictions.eq("product", product));
				result = criteria.list();
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
