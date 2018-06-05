
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TypesHandler.*;

@WebServlet("/WizardServlet")
public class WizardServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1L;
	private static Structure structure = new Structure();
	private static int id = 1;

	public WizardServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get current registered structure
		request.setAttribute("form", structure.getStructure("disabled"));
		// set JSP GUI
		RequestDispatcher view = request.getRequestDispatcher("Wizard.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		synchronized (structure.structure) {// synchronized structure for AJAX lags

			String newHTML;

			String action = request.getParameter("action");

			if (action.equals("publish")) {// publish current structure

				if (structure.isEmpty()) {
					// send error message back to AJAX
					response.setContentType("text/plain");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write("es");
					response.getWriter().close();
					return;
				}

				String formName = request.getParameter("fname");

				// insert form to db
				Form f = new Form(-1, formName);
				f.setId(f.insert());

				// JDBC/CLASS.forName ERROR
				if (f.getId() == -1)
					throw new RuntimeException("Adding form failed , id return : -1");
				// insert form components to db
				structure.publish(f.getId());
				// restart structure for the next wizard
				structure.restart();

			} else if (action.equals("remove")) {// remove component
				int id = Integer.parseInt(request.getParameter("id"));
				// delete requested component
				structure.delete(id);
			} else if (action.equals("add")) {// add component
				//lable name
				String lname = request.getParameter("lname");

				int choise = Integer.parseInt(request.getParameter("choise"));

				Type t = null;

				switch (choise) {
				case Type.Text:
					t = new TypeText(id, lname);
					break;
				case Type.Color:
					t = new TypeColor(id, lname);
					break;
				case Type.Date:
					t = new TypeDate(id, lname);
					break;
				case Type.Email:
					t = new TypeEmail(id, lname);
					break;
				case Type.Telephone:
					t = new TypeTel(id, lname);
					break;
				case Type.Number:
					t = new TypeNumber(id, lname);
					break;
				case Type.Paragraph:
					t = new TypeParagraph(id, lname);
					break;
				default:
					return;
				}
				//add generated component to the structure
				structure.add(t);
				//increase general key
				increaseKey();

			}
			//get structure HTML after action done
			newHTML = structure.getStructure("disabled");
			//send structure to AJAX 
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
