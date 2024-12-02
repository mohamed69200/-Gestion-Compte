package gestioncompte.xefi.com;

import java.util.ArrayList;
import java.util.List;

public abstract class Compte implements Comparable<Compte> {
    protected List<Operation> operations;
    protected String proprietaire;
    protected String typeCompte;
   
    public Compte() {
        this.operations = new ArrayList<>();
    }

    public Compte(String proprietaire) {
        this.proprietaire = proprietaire;
        this.operations = new ArrayList<>();
    }

   
    public void crediter(double montant) {
        Operation operation = new Operation(montant, Mouvement.CREDIT);
        operations.add(operation);
    }

  
    public void debiter(double montant) {
        Operation operation = new Operation(montant, Mouvement.DEBIT);
        operations.add(operation);
    }

 
    public void crediter(double montant, Compte compteADebiter) {
        this.crediter(montant);
        compteADebiter.debiter(montant);
    }

  
    public void debiter(double montant, Compte compteACrediter) {
        this.debiter(montant);
        compteACrediter.crediter(montant);
    }

  
    public double calculSolde() {
        double solde = 0;
        for (Operation op : operations) {
            if (op.getType() == Mouvement.CREDIT) {
                solde += op.getMontant();
            } else {
                solde -= op.getMontant();
            }
        }
        return solde;
    }

  
    public abstract double calculBenefice();

    public double soldeFinal() {
        return calculSolde() + calculBenefice();
    }

  
    public abstract void information();

  
    @Override
    public int compareTo(Compte autre) {
        return Double.compare(this.calculSolde(), autre.calculSolde());
    }

   
    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public List<Operation> getOperations() {
        return operations;
    }
    
}