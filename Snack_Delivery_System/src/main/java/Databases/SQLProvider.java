package Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class SQLProvider<D> {

	protected Connection con = null;
	protected Statement stat = null;
	protected ResultSet res = null;
	
	private static final String Driver = "org.sqlite.JBDC";
	
	
	public SQLProvider() {
		try {
			Class.forName(Driver).newInstance();
			
			String url = "jbdc:sqlite:John's_Shop.sqlite";
			con = DriverManager.getConnection(url);
			
			createDatabase();
			
		}catch(SQLException e) {
			System.out.println("Cannot Connect to Database");
		}catch(ClassNotFoundException e) {
			System.out.println("Cannot load Database");
		}catch(NullPointerException e) {
			System.out.println("Cannot find Database");
		}catch(IllegalAccessException e) {
			System.out.println("Unauthorized Access");
		}catch(InstantiationException e) {
			System.out.println("Cannot instantiate Database");
		}
	}
	
	
	abstract protected void createDatabase();
	
	abstract public List<D> viewAll();
	
	abstract public D show(int id);
	
	abstract public int Update(D Entity, int id);
	
	abstract public int Delete(int id);
	
	abstract public int deleteMultiple(int[] ids);
	
	abstract public int Add(D Entity);
	
	
}