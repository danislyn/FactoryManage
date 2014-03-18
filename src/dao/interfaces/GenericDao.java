package dao.interfaces;

import java.io.Serializable;
import java.util.ArrayList;

public interface GenericDao<T, PK extends Serializable> {

	/**
	 * ��������ȡ����
	 * @param id ����
	 * @return T �Ҳ���ʱ����null
	 */
	public T findById(PK id);
	
	
	/**
	 * ȡ���������ж���
	 * @return ArrayList
	 */
	public ArrayList<T> findAll();
	
	
	/**
	 * ��һ���������󣬲���������
	 * @param entity ��������
	 * @return PK ����
	 */
	public PK save(T entity);
	
	
	/**
	 * ����һ�����������Ҳ���ʱ��Ϊ��һ������
	 * @param entity ��������
	 * @return boolean
	 */
	public boolean update(T entity);
	
	
	/**
	 * ɾ��һ����������
	 * @param entity ��������
	 * @return boolean
	 */
	public boolean delete(T entity);
	
	
	/**
	 * ��������ɾ��һ������
	 * @param id ����
	 * @return boolean
	 */
	public boolean delete(PK id);
	
}
