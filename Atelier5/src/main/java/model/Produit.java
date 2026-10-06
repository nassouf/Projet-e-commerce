package model;
import java.util.ArrayList;
public class Produit implements java.io.Serializable {
    private int id;
    private String nom;
    private String description;
    private int idVendeur;
    private double prixMin;
    public enum Etat {DISPONIBLE, VENDU}
    private Etat etat;
    private ArrayList<Offre> offres;


    public Produit() {
    }
    public Produit(int id, String nom, String description, double prixMin, int idVendeur) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prixMin = prixMin;
        this.etat = Etat.DISPONIBLE;
        this.idVendeur = idVendeur;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrixMin() {
        return prixMin;
    }
    public void setPrixMin(double prixMin) {
        this.prixMin = prixMin;
    }
    public  Etat getEtat() {
        return etat;
    }
    public void setEtat( Etat etat) {
        this.etat = etat;
    }
    public ArrayList<Offre> getOffres() {
        return offres;
    }
    public void setOffres(ArrayList<Offre> offres) {
        this.offres = offres;
    }
    public void addOffre(Offre offre) {
        if (offres == null) {
            offres = new ArrayList<>();
        }
        offres.add(offre);
    }
    public int getIdVendeur() {
        return idVendeur;
    }
    public void setIdVendeur(int idVendeur) {
        this.idVendeur = idVendeur;
    }
    public Offre vender() {
        if (offres == null || offres.isEmpty()) {
            throw new IllegalStateException("Aucune offre disponible pour ce produit.");
        }
        Offre meilleureOffre = offres.get(0);
        for (Offre offre : offres) {
            if (offre.getMontant() > meilleureOffre.getMontant()) {
                meilleureOffre = offre;
            }
        }
        this.etat = Etat.VENDU;
        return meilleureOffre;
    }
    private Offre meilleureVente;

    public Offre getMeilleureVente() { return meilleureVente; }
    public void setMeilleureVente(Offre meilleureVente) { this.meilleureVente = meilleureVente; }
}
