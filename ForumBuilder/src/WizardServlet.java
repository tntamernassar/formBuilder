

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TypesHandler.*;


/**
 * Servlet implementation class WizardServlet
 */
@WebServlet("/WizardServlet")
public class WizardServlet extends HttpServlet implements Servlet {
	
	private static final long serialVersionUID = 1L;
	private static Structure structure = new Structure();
	private static int id=1;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WizardServlet() {
    	super();
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("form",structure.getStructure("disabled"));
	
		RequestDispatcher view = request.getRequestDispatcher("Wizard.jsp");  
        view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		synchronized (structure.structure) {
			
			String newHTML;
			
			String action = request.getParameter("action");
			
			if(action.equals("publish")) {
				
				if(structure.isEmpty())
				{
					 response.setContentType("text/plain");  
					 response.setCharacterEncoding("UTF-8");
					 response.getWriter().write("es");  
					 response.getWriter().close();
					 return;
				}
			
				String formName = request.getParameter("fname");
				
				Form f = new Form(-1,formName);
				f.setId(f.insert());
				
				if(f.getId() == -1)
					throw new RuntimeException("Adding form failed , id return : -1");
				
				structure.publish(f.getId());
				structure.restart();
				
				
			}else if(action.equals("remove")) {
				
				int id = Integer.parseInt(request.getParameter("id"));
				structure.delete(id);	
				
			
			}else if(action.equals("add")){
			
				String lname = request.getParameter("lname");
		
				int choise = Integer.parseInt(request.getParameter("choise"));
		
				Type t =null;
				
				switch(choise) {
				case 0://Text input
					t= new TypeText(id,lname);
					break;
				case 1://Color input
					t = new TypeColor(id,lname);
					break;
				case 2://Date input
					t = new TypeDate(id,lname);
					break;
				case 3://Email input
					t = new TypeEmail(id,lname);
					break;
				case 4://Telephone input
					t = new TypeTel(id,lname);
					break;
				case 5:
					t = new TypeNumber(id,lname);
					break;
					
					default: return;
				}
				
				
				
				structure.add(t);
				increaseKey();
	
			}
			
			   newHTML  = structure.getStructure("disabled");
			   response.setContentType("text/plain");  
			   response.setCharacterEncoding("UTF-8");
			   response.getWriter().write(newHTML);  
			   response.getWriter().close();
		}
	}
	

	public synchronized void increaseKey() {
		id++;
	}
	
	

}
