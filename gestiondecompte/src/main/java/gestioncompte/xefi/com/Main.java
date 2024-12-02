package gestioncompte.xefi.com;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestionDeComptes gestionComptes = new GestionDeComptes();

        while (true) {
            afficherMenu();
            int choix = scanner.nextInt();
            scanner.nextLine(); 

            switch (choix) {
                case 1:
                    creerCompteCourant(scanner, gestionComptes);
                    break;
                case 2:
                    creerCompteEpargne(scanner, gestionComptes);
                    break;
                case 3:
                    crediterCompte(scanner, gestionComptes);
                    break;
                case 4:
                    debiterCompte(scanner, gestionComptes);
                    break;
                case 5:
                    effectuerVirement(scanner, gestionComptes);
                    break;
                case 6:
                    gestionComptes.afficherComptes();
                    break;
                case 7:
                    gestionComptes.trierComptes();
                    System.out.println("Comptes triés par solde :");
                    gestionComptes.afficherComptes();
                    break;
                case 8:
                    System.out.println("Au revoir !");
                    scanner.close();
                    System.exit(0);
                case 9:
                    rechercherComptesParSolde(scanner, gestionComptes);
                    break;
                default:
                    System.out.println("Choix invalide. Réessayez.");
            }
        }
    }

    private static void afficherMenu() {
        System.out.println("\n--- Menu de Gestion de Comptes ---");
        System.out.println("1. Créer un compte courant");
        System.out.println("2. Créer un compte épargne");
        System.out.println("3. Créditer un compte");
        System.out.println("4. Débiter un compte");
        System.out.println("5. Effectuer un virement");
        System.out.println("6. Afficher la liste des comptes");
        System.out.println("7. Trier les comptes");
        System.out.println("8. Quitter");
        System.out.println("9. Rechercher des comptes par solde minimum"); 
        System.out.print("Choisissez une option : ");
    }

    private static void creerCompteCourant(Scanner scanner, GestionDeComptes gestionComptes) {
        System.out.print("Nom du propriétaire : ");
        String nom = scanner.nextLine();
        System.out.print("Découvert autorisé : ");
        double decouvert = scanner.nextDouble();
        CompteCourant compte = new CompteCourant(nom, decouvert);
        gestionComptes.ajouterCompte(compte);
        System.out.println("Compte courant créé avec succès.");
    }

    private static void creerCompteEpargne(Scanner scanner, GestionDeComptes gestionComptes) {
        System.out.print("Nom du propriétaire : ");
        String nom = scanner.nextLine();
        System.out.print("Taux d'abondement (%) : ");
        double taux = scanner.nextDouble() / 100;
        CompteEpargne compte = new CompteEpargne(nom, taux);
        gestionComptes.ajouterCompte(compte);
        System.out.println("Compte épargne créé avec succès.");
    }

    private static Compte trouverCompte(GestionDeComptes gestionComptes, String nomProprietaire) {
        for (Compte compte : gestionComptes.getComptes()) {
            if (compte.getProprietaire().equalsIgnoreCase(nomProprietaire)) {
                return compte;
            }
        }
        return null;
    }

    private static void crediterCompte(Scanner scanner, GestionDeComptes gestionComptes) {
        System.out.print("Nom du propriétaire du compte à créditer : ");
        String nom = scanner.nextLine();
        Compte compte = trouverCompte(gestionComptes, nom);
        if (compte != null) {
            System.out.print("Montant à créditer : ");
            double montant = scanner.nextDouble();
            compte.crediter(montant);
            System.out.println("Compte crédité avec succès.");
        } else {
            System.out.println("Compte non trouvé.");
        }
    }

    private static void debiterCompte(Scanner scanner, GestionDeComptes gestionComptes) {
        System.out.print("Nom du propriétaire du compte à débiter : ");
        String nom = scanner.nextLine();
        Compte compte = trouverCompte(gestionComptes, nom);
        if (compte != null) {
            System.out.print("Montant à débiter : ");
            double montant = scanner.nextDouble();
            compte.debiter(montant);
            System.out.println("Compte débité avec succès.");
        } else {
            System.out.println("Compte non trouvé.");
        }
    }

    private static void rechercherComptesParSolde(Scanner scanner, GestionDeComptes gestionComptes) {
    System.out.print("Entrez le solde minimum : ");
    double soldeMin = scanner.nextDouble();
    List<Compte> comptesTrouvés = gestionComptes.rechercherParSoldeMinimum(soldeMin);

    if (comptesTrouvés.isEmpty()) {
        System.out.println("Aucun compte trouvé avec un solde minimum de " + soldeMin + ".");
    } else {
        System.out.println("Comptes trouvés :");
        for (Compte compte : comptesTrouvés) {
            compte.information();
        }
    }
}


    private static void effectuerVirement(Scanner scanner, GestionDeComptes gestionComptes) {
        System.out.print("Nom du propriétaire du compte à débiter : ");
        String nomDebiteur = scanner.nextLine();
        Compte compteDebiteur = trouverCompte(gestionComptes, nomDebiteur);

        if (compteDebiteur != null) {
            System.out.print("Nom du propriétaire du compte à créditer : ");
            String nomCrediteur = scanner.nextLine();
            Compte compteCrediteur = trouverCompte(gestionComptes, nomCrediteur);

            if (compteCrediteur != null) {
                System.out.print("Montant du virement : ");
                double montant = scanner.nextDouble();
                compteDebiteur.debiter(montant, compteCrediteur);
                System.out.println("Virement effectué avec succès.");
            } else {
                System.out.println("Compte créditeur non trouvé.");
            }
        } else {
            System.out.println("Compte débiteur non trouvé.");
        }
    }
}