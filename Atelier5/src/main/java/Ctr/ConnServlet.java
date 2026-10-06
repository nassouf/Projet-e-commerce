package Ctr;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.*;

@WebServlet("/Conn")
public class ConnServlet extends HttpServlet {
	BaseDonne bd ;
    @Override
    public void init() throws ServletException {
        bd = BaseDonne.getInstance(getServletContext());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 req.getRequestDispatcher("/WEB-INF/connection.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String nom = request.getParameter("nom");
        boolean userExists = bd.getUtilisateurs().stream()
                .anyMatch(u -> u.getEmail().equals(email) && u.getNom().equals(nom));
        if (userExists) {
            String role = bd.getUtilisateurs().stream()
                    .filter(u -> u.getEmail().equals(email) && u.getNom().equals(nom))
                    .findFirst()
                    .map(u -> u.getRole().name())
                    .orElse("Utilisateur");
            Utilisateur ut = bd.getUtilisateurs().stream()
                    .filter(u -> u.getEmail().equals(email) && u.getNom().equals(nom))
                    .findFirst().orElse(null);
            if (role.equals("CLIENT")) {
                request.getSession().setAttribute("client", ut);

            	request.setAttribute("produits", bd.getProduits().stream()
                        .filter(p -> p.getEtat() == Produit.Etat.DISPONIBLE)
                        .collect(java.util.stream.Collectors.toList()));
            	 request.getRequestDispatcher("/WEB-INF/client.jsp").forward(request, response);
            } else {

                request.getSession().setAttribute("vendeur", ut);

                List<Produit> pr=bd.getProduits().stream()
                        .filter(p -> p.getIdVendeur() == ut.getId())
                        .collect(java.util.stream.Collectors.toList());
                request.setAttribute("produits", pr);
            	 request.getRequestDispatcher("/WEB-INF/vendeur.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("/WEB-INF/connection.jsp?error=1");
        }
        


    }

}
