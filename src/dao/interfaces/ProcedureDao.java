package dao.interfaces;

import java.util.List;

import pojo.Procedure;

public interface ProcedureDao extends GenericDao<Procedure, Integer> {

	public List<Procedure> getProceduresByProduct(Integer productId);
	
}
