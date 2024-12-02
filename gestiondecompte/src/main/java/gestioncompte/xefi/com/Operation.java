package gestioncompte.xefi.com;

public class Operation {
    private double montant;
    private Mouvement type;

    
    public Operation() {
    }

   
    public Operation(double montant, Mouvement type) {
        this.montant = montant;
        this.type = type;
    }

   
    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Mouvement getType() {
        return type;
    }

    public void setType(Mouvement type) {
        this.type = type;
    }
}