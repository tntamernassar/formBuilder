
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Home() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HTMLTable T = new HTMLTable(5);
		T.buildHeader("Form Id", "Form Name", "#Submissions", "Submit Page", "Submitions Page");
		int i=0;
		for (Form f : Form.getForms()) {
			T.addRow(f);
			i++;
		}
		String msg = "";
		if(i == 0)
			msg = "<b>Welcome to form Builder , to add a form press the '+' button in upper right corner :)</b> <br></br>";
		request.setAttribute("msg", msg);
		request.setAttribute("table", T.toString());
		RequestDispatcher view = request.getRequestDispatcher("forumList.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		doGet(request, response);
	}

}
