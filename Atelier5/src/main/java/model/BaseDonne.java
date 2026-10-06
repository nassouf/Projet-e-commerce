package model;
import java.util.ArrayList;
import javax.servlet.ServletContext;  
public class BaseDonne implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    private ArrayList<Utilisateur> utilisateurs;
    private ArrayList<Produit> produits;

    private static final String CLE = "baseDonne";
    public BaseDonne() {
        this.utilisateurs = new ArrayList<>();
        this.produits = new ArrayList<>();
       
    }
    public static BaseDonne getInstance(ServletContext context) {
        BaseDonne instance = (BaseDonne) context.getAttribute(CLE);
        if (instance == null) {
            instance = new BaseDonne();
            context.setAttribute(CLE, instance);
        }
        return instance;
    }


    public ArrayList<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(ArrayList<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public ArrayList<Produit> getProduits() {
        return produits;
    }

    public void setProduits(ArrayList<Produit> produits) {
        this.produits = produits;
    }

    
}