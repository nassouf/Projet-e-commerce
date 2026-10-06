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
import model.Offre;
@WebServlet("/Enchere")
public class AjouEncherServlet extends HttpServlet {
	BaseDonne bd;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int    idProduit = Integer.parseInt(request.getParameter("idProduit"));
        double montant   = Double.parseDouble(request.getParameter("montant"));
        int    idClient  = Integer.parseInt(request.getParameter("idClient"));

        Produit produit = bd.getProduits().stream()
                .filter(p -> p.getId() == idProduit && p.getEtat() == Produit.Etat.DISPONIBLE)
                .findFirst()
                .orElse(null);
        if (produit == null) {

            request.setAttribute("msg", "Ce produit n'est plus disponible.");
            
            request.getRequestDispatcher("/WEB-INF/client.jsp").forward(request, response);
            
        }
        if (montant < produit.getPrixMin()) {
            request.setAttribute("msg", "Le montant doit être ≥ " + produit.getPrixMin() + " DT.");
            request.setAttribute("produits", bd.getProduits().stream()
                    .filter(p -> p.getEtat() == Produit.Etat.DISPONIBLE)
                    .collect(Collectors.toList()));
            request.getRequestDispatcher("/WEB-INF/client.jsp").forward(request, response);
            
        }

        int id = bd.getProduits().stream()
                .flatMap(p -> p.getOffres() == null ? 
                        java.util.stream.Stream.empty() : p.getOffres().stream())
                .mapToInt(Offre::getId)
                .max()
                .orElse(0) + 1;

        Offre offre = new Offre();
        offre.setId(id);
        offre.setProduit(produit);
        offre.setIdAcheteur(idClient);
        offre.setMontant(montant);
        produit.addOffre(offre);

        request.setAttribute("msg", "Votre enchère de " + montant + " DT a été enregistrée !");
        request.setAttribute("produits", bd.getProduits().stream()
                .filter(p -> p.getEtat() == Produit.Etat.DISPONIBLE)
                .collect(Collectors.toList()));
        request.getRequestDispatcher("/WEB-INF/client.jsp").forward(request, response);
    }
	

	@Override
	public void init() throws ServletException {
		bd = BaseDonne.getInstance(getServletContext());
	}
	

}
