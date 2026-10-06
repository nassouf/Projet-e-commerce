package Ctr;

import model.BaseDonne;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/testBD")
public class TestBDServlet extends HttpServlet {

    BaseDonne bd ;
       
    

    @Override
	public void init() throws ServletException {
		bd= BaseDonne.getInstance(getServletContext());
	}



	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        // Passer la BaseDonne à la JSP via le request scope
        request.setAttribute("bd", bd);

        // Rediriger vers la JSP
        request.getRequestDispatcher("/WEB-INF/data.jsp").forward(request, response);
    }
}
