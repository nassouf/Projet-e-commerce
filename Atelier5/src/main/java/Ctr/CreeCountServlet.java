package Ctr;

import model.BaseDonne;
import model.Utilisateur;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Inscription")
public class CreeCountServlet extends HttpServlet {

    private BaseDonne bd;

    @Override
    public void init() throws ServletException {
        bd = BaseDonne.getInstance(getServletContext());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Cree.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String roleParam = request.getParameter("role");

        Utilisateur.Role role = Utilisateur.Role.valueOf(roleParam.toUpperCase());

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(nom);
        utilisateur.setEmail(email);
        int nouvelId = bd.getUtilisateurs().size() + 1;
        utilisateur.setId(nouvelId);
        utilisateur.setRole(role);

        bd.getUtilisateurs().add(utilisateur);
        request.getRequestDispatcher("/WEB-INF/Cree.jsp").forward(request, response);
    }
}