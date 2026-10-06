package Ctr;

import model.BaseDonne;
import model.Offre;
import model.Produit;
import model.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/Vendre")
public class VendreServlet extends HttpServlet {

    private BaseDonne bd;

    @Override
    public void init() throws ServletException {
        bd = BaseDonne.getInstance(getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Utilisateur vendeur = (Utilisateur) request.getSession().getAttribute("vendeur");
        if (vendeur == null) {
            response.sendRedirect(request.getContextPath() + "/Conn");
            return;
        }

        int idProduit = Integer.parseInt(request.getParameter("idProduit"));

        Produit produit = bd.getProduits().stream()
                .filter(p -> p.getId() == idProduit)
                .findFirst()
                .orElse(null);

        if (produit == null) {
            request.setAttribute("erreur", "Produit introuvable.");
        } else if (produit.getEtat() == Produit.Etat.VENDU) {
            request.setAttribute("msg", "Ce produit est déjà vendu.");
        } else if (produit.getOffres() == null || produit.getOffres().isEmpty()) {
            request.setAttribute("msg", "Aucune enchère reçue pour ce produit.");
        } else {
            Offre meilleureOffre = produit.vender(); 
            produit.setMeilleureVente(meilleureOffre);
            request.setAttribute("msg",
                "Produit \"" + produit.getNom() + "\" vendu à l'acheteur #"
                + meilleureOffre.getIdAcheteur()
                + " pour " + meilleureOffre.getMontant() + " DT !");
        }

        
        request.setAttribute("vendeur", vendeur);
        request.setAttribute("produits", bd.getProduits().stream()
                .filter(p -> p.getIdVendeur() == vendeur.getId())
                .collect(Collectors.toList()));

        request.getRequestDispatcher("/WEB-INF/vendeur.jsp").forward(request, response);
    }
}