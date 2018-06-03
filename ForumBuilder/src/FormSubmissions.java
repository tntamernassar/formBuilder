

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TypesHandler.Structure;

/**
 * Servlet implementation class FormSubmissions
 */
@WebServlet("/FormSubmissions")
public class FormSubmissions extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static LinkedList<Submission> submissions;
    private static Structure structure;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormSubmissions() {
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
		submissions = Submission.getSubmissions(Integer.parseInt(fid));
		
	
		for(Submission s : submissions)
			s.getSubmissions(structure);
		
		HTMLTable T = new HTMLTable(structure.structure.size(),90);
		
		T.buildHeader(structure.getTypesHeader());
		for(Submission s : submissions) 
			T.addRow(s);

	
		request.setAttribute("table", T.toString());
		request.setAttribute("formName", structure.father.getName());
		RequestDispatcher view = request.getRequestDispatcher("Submissions.jsp");  
        view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
