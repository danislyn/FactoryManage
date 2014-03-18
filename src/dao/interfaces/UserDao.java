package dao.interfaces;

import pojo.User;

public interface UserDao extends GenericDao<User, Integer> {

	public User loginCheck(String username, String password);
	
}
