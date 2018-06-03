

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Synthesizer;

import TypesHandler.Structure;
import TypesHandler.Type;

/**
 * Servlet implementation class SubmitForm
 */
@WebServlet("/SubmitForm")
public class SubmitForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private static Structure structure;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String fid = request.getParameter("fid");
		if(fid == null)
			return;
		
		 
		structure = Structure.getStructure(Integer.parseInt(fid));
		request.setAttribute("formName", structure.father.getName());
		request.setAttribute("fid",fid);
		request.setAttribute("structure", structure.getStructure(""));
		RequestDispatcher view = request.getRequestDispatcher("Submit_form.jsp");  
        view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
			if(action.equals("update")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			String newVal = request.getParameter("val");
			for(Type t:structure.structure) {
				
				if(t.getId() == id) {
					t.setResult(newVal);
					break;
				}
				
			}
			
		}else if(action.equals("submit")) {
			
			for(Type t:structure.structure) {
				if(t.getResult() == null) {
					response.setContentType("text/plain");  
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write("es");  
					response.getWriter().close();
					return;
				}
			}
			
			int id = structure.father.prepareSubmission();
			
			
			for(Type t:structure.structure) {
				t.setSubmissionId(id);
				t.submitComponent(structure.father.getId());
			}
			
			structure.father.increaseSN();
			structure.restart();
			
		
			
			response.setContentType("text/plain");  
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("");  
			response.getWriter().close();
			
		}
		
		
		 
		
	}

}
