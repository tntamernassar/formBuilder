
import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TypesHandler.Structure;

@WebServlet("/FormSubmissions")
public class FormSubmissions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static LinkedList<Submission> submissions;
	private static Structure structure;

	public FormSubmissions() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fid = request.getParameter("fid"); // 'form id'
		if (fid == null)
			return;
		// get 'fid' structure
		structure = Structure.getStructure(Integer.parseInt(fid));
		// get 'fid' submissions
		submissions = Submission.getSubmissions(Integer.parseInt(fid));
		// get 'fid' submissions data
		for (Submission s : submissions)
			s.getSubmissions(structure);
		// create HTML table from the executed subbmissions
		HTMLTable T = new HTMLTable(structure.structure.size());
		// build 'T' table header
		T.buildHeader(structure.getTypesHeader());
		// add rows to the table
		for (Submission s : submissions)
			T.addRow(s);
		// send the table structure via request
		request.setAttribute("table", T.toString());
		// send 'fid' form name
		request.setAttribute("formName", structure.father.getName());
		// optimize JSP GUI
		RequestDispatcher view = request.getRequestDispatcher("Submissions.jsp");
		// send parameters
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
