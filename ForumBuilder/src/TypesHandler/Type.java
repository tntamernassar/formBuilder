package TypesHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

abstract public class Type {
	
	public static final int Text = 0;
	public static final int Color = 1;
	public static final int Date = 2;
	public static final int Email = 3;
	public static final int Telephone = 4;
	public static final int Number = 5;
	public static final int Paragraph = 6;
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/form_builder?useUnicode=yes&characterEncoding=UTF-8";
	static final String USER = "root";
	static final String PASS = "";
	
	protected String labelName;
	protected int id;
	protected int order;
	protected String result;
	protected int submissionId;
	
	public Type(int id,String labelName) {
		this.labelName = labelName;
		this.id = id;
		result=null;
	}
	
	
	public void insertType(int form_id) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		   try{
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
		      
		      String sql = "INSERT INTO components VALUES('',"+getType()+","+order+",?,"+form_id+")";
		      
		      stmt = (PreparedStatement) conn.prepareStatement(sql,
                      Statement.RETURN_GENERATED_KEYS);

		      stmt.setString(1, labelName);
		      int affectedRows = stmt.executeUpdate();
		      
		      
		      if (affectedRows == 0) {
		            throw new SQLException("Creating forms failed, no rows affected.");
		      }
		      
		      try (ResultSet generatedKeys = (ResultSet) stmt.getGeneratedKeys()) {
		            if (!generatedKeys.next()) 
		            	 throw new SQLException("Creating form failed, no ID obtained.");      
		      }
		     
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
	
	abstract public String getHTML(String property);
	
	abstract public int getType();
	
	public String getDisplay() {
		return result;
	}
	
	public void setOrder(int order) {
		this.order = order;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public void setSubmissionId(int submissionId) {
		this.submissionId = submissionId;
	}
	
	public int getId() {
		return id;
	}
	
	public String getResult() {
		return result;
	}

	
	
	public void submitComponent(int fid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		   try{
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
		      
		      String sql = "INSERT INTO submitions VALUES('',"+fid+",?,"+id+","+submissionId+")";
		      
		      stmt = (PreparedStatement) conn.prepareStatement(sql,
                      Statement.RETURN_GENERATED_KEYS);
		      
		      stmt.setString(1, result);

		      int affectedRows = stmt.executeUpdate();
		      
		      
		      if (affectedRows == 0) {
		            throw new SQLException("Submit answer failed, no rows affected.");
		      }
		      
		      try (ResultSet generatedKeys = (ResultSet) stmt.getGeneratedKeys()) {
		            if (!generatedKeys.next()) 
		            	 throw new SQLException("Submit answer failed, no ID obtained.");      
		      }
		     
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

}
