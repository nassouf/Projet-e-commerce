package Ctr;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BaseDonne;
import model.Produit;
import model.Utilisateur;
@WebServlet("/AjouterProduit")
public class AjoutPrServlet extends HttpServlet {
	BaseDonne bd;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nom         = req.getParameter("nom");
        String description = req.getParameter("description");
        double prixMin     = Double.parseDouble(req.getParameter("prixMin"));
        int    idVendeur   = Integer.parseInt(req.getParameter("idVendeur"));

        int id = bd.getProduits().stream()
                .mapToInt(Produit::getId)
                .max()
                .orElse(0) + 1;
        Produit produit = new Produit(id, nom, description, prixMin, idVendeur);
        bd.getProduits().add(produit);
        req.setAttribute("vendeur",  (Utilisateur) req.getSession().getAttribute("vendeur"));
        req.setAttribute("produits", bd.getProduits().stream()
                .filter(p -> p.getIdVendeur() == idVendeur)
                .collect(Collectors.toList()));

        req.getRequestDispatcher("/WEB-INF/vendeur.jsp").forward(req, resp);
	}

	@Override
	public void init() throws ServletException {
		bd = BaseDonne.getInstance(getServletContext());
	}
}
