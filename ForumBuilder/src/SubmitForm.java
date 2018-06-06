
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

@WebServlet("/SubmitForm")
public class SubmitForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Structure structure;

	public SubmitForm() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fid = request.getParameter("fid"); // 'form id'
		if (fid == null)
			return;
		// get 'fid' structure
		try{
			structure = Structure.getStructure(Integer.parseInt(fid));
		}catch(Exception e) {
			return;
		}
		if(structure == null)
			return;
		// set request data
		request.setAttribute("formName", structure.father.getName());
		request.setAttribute("fid", fid);
		request.setAttribute("structure", structure.getStructure(""));
		// set JSP GUI
		RequestDispatcher view = request.getRequestDispatcher("Submit_form.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");// action requested

		if (action.equals("update")) {// update data result
			// get component id
			int id = Integer.parseInt(request.getParameter("id"));
			// get new component data
			String newVal = request.getParameter("val");
			// update structure
			structure.update(id, newVal);

		} else if (action.equals("submit")) {// submit form

			// check if the submitted structure is legal (not containing any empty results)
			if (!structure.isLegal()) {
				// write response error
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("es");
				response.getWriter().close();
				return;
			}

			// prepare to submit form data
			int id = structure.father.prepareSubmission();
			// submit each component
			for (Type t : structure.structure) {
				t.setSubmissionId(id);
				t.submitComponent(structure.father.getId());
			}
			// increase form submissions number
			structure.father.increaseSN();
			// restart the structure to the next submission
			structure.restart();
			// send success message
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("");
			response.getWriter().close();

		}

	}

}
