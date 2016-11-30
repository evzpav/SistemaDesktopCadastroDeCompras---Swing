package br.com.solvus.model;

import java.sql.SQLException;
import java.util.List;



public interface DAO<T> {

	
	public T findById(Integer id) throws SQLException;
	public void save(T object) throws SQLException;
	public void deleteById(Integer id) throws SQLException;
	public List<T> list() throws SQLException;
	public void update(T object) throws SQLException;
	


}
