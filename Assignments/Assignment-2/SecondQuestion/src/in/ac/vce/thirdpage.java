package in.ac.vce;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class thirdpage
 */
@WebServlet(name = "thirdpage", urlPatterns = { "/thirdpage" })
public class thirdpage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public thirdpage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		CheckAuthenticationServelet obj = new CheckAuthenticationServelet();
		String username =  obj.getUser();
		String password = obj.getPassword();
		PrintWriter out=response.getWriter();
		MySQLDBCon obj2 = new MySQLDBCon();
		Boolean result= obj2.authenticate(username, password);		
		if(result) {
			RequestDispatcher rd =request.getRequestDispatcher("/thirdpage.html");
			rd.forward(request, response);
		}
		else {
			out.print("Login to view third page");
			RequestDispatcher rd =request.getRequestDispatcher("/login.html");
			rd.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
