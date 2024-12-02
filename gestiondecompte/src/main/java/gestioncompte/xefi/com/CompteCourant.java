package gestioncompte.xefi.com;

public class CompteCourant extends Compte {
    private double decouvertAutorise;

    public CompteCourant() {
        super();
    }

    public CompteCourant(String proprietaire, double decouvertAutorise) {
        super(proprietaire);
        this.decouvertAutorise = decouvertAutorise;
    }

    @Override
    public double calculBenefice() {
        return 0;
    }

    
    @Override
    public void information() {
        System.out.println("*******************************************");
        System.out.println("Résumé du compte courant de " + proprietaire);
        System.out.println("*******************************************");
        System.out.printf("Solde : %.2f\n", calculSolde());
        System.out.printf("Découvert autorisé : %.2f\n", decouvertAutorise);
        System.out.println("Opérations :");
        for (Operation op : operations) {
            String signe = op.getType() == Mouvement.CREDIT ? "+" : "-";
            System.out.printf("%s%.2f\n", signe, op.getMontant());
        }
    }

    public double getDecouvertAutorise() {
        return decouvertAutorise;
    }

    public void setDecouvertAutorise(double decouvertAutorise) {
        this.decouvertAutorise = decouvertAutorise;
    }
}