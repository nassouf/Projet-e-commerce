package model;
public class Offre implements java.io.Serializable {
    private int id;
    private Produit Produit;
    private int idAcheteur;
    private double montant;

    public Offre() {
    }
    public Offre(int id, Produit Produit, int idAcheteur, double montant) {
        this.id = id;
        this.Produit = Produit;
        this.idAcheteur = idAcheteur;
        if (montant < Produit.getPrixMin()) {
            throw new IllegalArgumentException("Le montant de l'offre doit être supérieur ou égal au prix minimum du produit.");
        }
    }  
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Produit getProduit() {
        return Produit;
    }
    public void setProduit(Produit Produit) {
        this.Produit = Produit;
    }
    public int getIdAcheteur() {
        return idAcheteur;
    }
    public void setIdAcheteur(int idAcheteur) {
        this.idAcheteur = idAcheteur;
    }
    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        if (montant < Produit.getPrixMin()) {
            throw new IllegalArgumentException("Le montant de l'offre doit être supérieur ou égal au prix minimum du produit.");
        }
        this.montant = montant;
    }
}