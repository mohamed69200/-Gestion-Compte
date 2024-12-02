package gestioncompte.xefi.com;

public class CompteEpargne extends Compte {
    private double tauxAbondement;

 
    public CompteEpargne() {
        super();
    }

    public CompteEpargne(String proprietaire, double tauxAbondement) {
        super(proprietaire);
        this.tauxAbondement = tauxAbondement;
    }

   
    @Override
    public double calculBenefice() {
        return calculSolde() * tauxAbondement;
    }

    @Override
    public void information() {
        System.out.println("*******************************************");
        System.out.println("Résumé du compte épargne de " + proprietaire);
        System.out.println("*******************************************");
        System.out.printf("Solde : %.2f\n", soldeFinal());
        System.out.printf("Taux d'abondement : %.1f %%\n", tauxAbondement * 100);
        System.out.println("Opérations :");
        for (Operation op : operations) {
            String signe = op.getType() == Mouvement.CREDIT ? "+" : "-";
            System.out.printf("%s%.2f\n", signe, op.getMontant());
        }
    }

   
    public double getTauxAbondement() {
        return tauxAbondement;
    }

    public void setTauxAbondement(double tauxAbondement) {
        this.tauxAbondement = tauxAbondement;
    }
}