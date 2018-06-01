import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import TypesHandler.Structure;
import TypesHandler.Type;

public class Submission implements Rowable{

	static final String DB_URL = "jdbc:mysql://localhost:3306/form_builder?useUnicode=yes&characterEncoding=UTF-8";
	static final String USER = "root";
	static final String PASS = "";
	
	private LinkedList<Type> types;
	
	private int id;
	
	public Submission(int id) {
		this.id = id;
		this.types = new LinkedList<Type>();
	}
	
	
	public void addType(Type t) {
		types.add(t);
	}
	
	public int getId() {
		return id;
	}
	
	
	public void getSubmissions(Structure structure) {
		Connection conn = null;
		Statement stmt = null;
		   try{
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
		      stmt = (Statement) conn.createStatement();
		     
		      String sql = "SELECT * FROM submitions WHERE subid="+id+" ORDER BY id ASC";
		      ResultSet rs = (ResultSet) ((java.sql.Statement) stmt).executeQuery(sql);
		      while(rs.next()){
		        String val = rs.getString("value");
		        int qid = rs.getInt("qid");
		        Type t = structure.getType(qid);
		        t.setResult(val);
		        addType(t);
		      }

		      rs.close();
		   }catch(SQLException se){
			   System.out.println("JDBC BUG!!!");
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
	}
	
	
	public static LinkedList<Submission> getSubmissions(int fid){
		LinkedList<Submission> res = new LinkedList<Submission>();
		
		Connection conn = null;
		Statement stmt = null;
		   try{
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
		      stmt = (Statement) conn.createStatement();

		      String sql = "SELECT * FROM submissions WHERE form_id="+fid+" ORDER BY id ASC";
		      ResultSet rs = (ResultSet) ((java.sql.Statement) stmt).executeQuery(sql);
		      while(rs.next()){
		        int id = rs.getInt("id");
		        Submission sub = new Submission(id);
		        res.addFirst(sub);
		      }

		      rs.close();
		   }catch(SQLException se){
			   System.out.println("JDBC BUG!!!");
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		
		return res;
	}


	
	
	@Override
	public String generateRow() {
		String res = "<tr class=\"t_object\">";
		
		for(Type t : types) 
			res = res + "<td>"+t.getDisplay()+"</td>\n";
		
		
		return res+"</tr>";
	}


	@Override
	public Object getPrimaryValue() {
		return id;
	}
	
}
