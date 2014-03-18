package test;

import dao.hibernate.UserDaoHibernate;
import dao.interfaces.UserDao;
import pojo.User;

public class DAOTest {

	public static void main(String[] args){
		UserDao userDao = new UserDaoHibernate();
		User oneUser = userDao.findById(1);
		System.out.println(oneUser.getName());
		
	}
	
}
