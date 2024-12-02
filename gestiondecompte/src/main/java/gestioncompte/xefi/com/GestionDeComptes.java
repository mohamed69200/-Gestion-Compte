package gestioncompte.xefi.com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestionDeComptes {
    private final List<Compte> comptes;

    public GestionDeComptes() {
        this.comptes = new ArrayList<>();
    }

 
    public void ajouterCompte(Compte compte) {
        comptes.add(compte);
    }

  
    public void afficherComptes() {
        for (Compte compte : comptes) {
            compte.information();  
        }
    }

  
    public void trierComptes() {
        Collections.sort(comptes);
    }

    
    public List<Compte> getComptes() {
        return comptes;
    }
    
    // GestionDeComptes.java
public List<Compte> rechercherParSoldeMinimum(double soldeMin) {
    List<Compte> comptesFiltrés = new ArrayList<>();
    for (Compte compte : comptes) {
        if (compte.calculSolde() >= soldeMin) {
            comptesFiltrés.add(compte);
        }
    }
    return comptesFiltrés;
}

}