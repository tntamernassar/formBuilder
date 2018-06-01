

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class FormList
 */
@WebServlet("/formList")
public class FormList extends HttpServlet {
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/form_builder?useUnicode=yes&characterEncoding=UTF-8";
	static final String USER = "root";
	static final String PASS = "";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormList() {
        super();
        
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	

    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HTMLTable T = new HTMLTable(5,90);
    	T.buildHeader("Form Id","Form Name","#Submissions","Submit Page","Submition Page");
    	for(Form f : Form.getForms())
    		T.addRow(f);
    	request.setAttribute("table", T.toString());
		RequestDispatcher view = request.getRequestDispatcher("forumList.jsp");  
        view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	

	
}
