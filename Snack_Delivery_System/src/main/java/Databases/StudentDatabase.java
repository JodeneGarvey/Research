package Databases;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Item.Student;

public class StudentDatabase extends SQLProvider<Student> {
	
	private static final String Table_Name = "Students";
	
	@Override
	protected void createDatabase() {
		try {
			stat = con.createStatement();
			if(stat.execute("Create Table if not exsisted " + Table_Name +  " (id INTEGER(7) PRIMARY KEY, name varchar(40), location varchar(50) )")) {
				
			}else {
				System.out.println("Table Created");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Student> viewAll() {
		List<Student> Attributes = new ArrayList<Student>();
		try {
			stat = con.createStatement();
			String sql = "SELECT * FROM " +Table_Name;
			
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				Student attribute = new Student();
				attribute.setId(res.getInt(1));
				attribute.setName(res.getString(2));
				attribute.setLocation(res.getString(3));
				
				Attributes.add(attribute);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return Attributes;
	}

	@Override
	public Student show(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int Update(Student Entity, int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMultiple(int[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Add(Student Entity) {
		String sql = "INSERT INTO "+Table_Name+ " (id, name, location) values (?, ?, ?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, Entity.getId());
			st.setString(2, Entity.getName());
			st.setString(3, Entity.getLocation());
			return st.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
