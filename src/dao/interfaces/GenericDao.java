package dao.interfaces;

import java.io.Serializable;
import java.util.ArrayList;

public interface GenericDao<T, PK extends Serializable> {

	/**
	 * 根据主键取对象
	 * @param id 主键
	 * @return T 找不到时返回null
	 */
	public T findById(PK id);
	
	
	/**
	 * 取出表中所有对象
	 * @return ArrayList
	 */
	public ArrayList<T> findAll();
	
	
	/**
	 * 存一个完整对象，并返回主键
	 * @param entity 完整对象
	 * @return PK 主键
	 */
	public PK save(T entity);
	
	
	/**
	 * 更新一个对象，主键找不到时改为存一个对象
	 * @param entity 完整对象
	 * @return boolean
	 */
	public boolean update(T entity);
	
	
	/**
	 * 删除一个完整对象
	 * @param entity 完整对象
	 * @return boolean
	 */
	public boolean delete(T entity);
	
	
	/**
	 * 根据主键删除一个对象
	 * @param id 主键
	 * @return boolean
	 */
	public boolean delete(PK id);
	
}
