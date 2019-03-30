/* Le jeu est presque complet.
 * Il reste à gérer les mauvaises entrées et de nouvelles fonctionnaliés :
 * premier joueur selon les règles, variantes avec pioche...*/

import java.util.Scanner;
import java.util.ArrayList;

public class JeuDomino
{
  PiocheDomino pioche;
  ArrayList<Domino> mainsJoueurs[];
  int nbJoueurs;
  SequenceDomino sequence;
  int gagnant;
  
  public JeuDomino(int nbJoueurs)
  {
    System.out.println("Préparation du jeu...");
    this.nbJoueurs = nbJoueurs;
    pioche = new PiocheDomino();
    gagnant = 0;
    mainsJoueurs = new ArrayList[nbJoueurs];
    for (int j = 0 ; j < nbJoueurs ; j++)
    {
      mainsJoueurs[j] = new ArrayList<Domino>();
    }
    
    distribution();
  }
  
  public void distribution()
  {
    System.out.println("Distribution en cours...");
    for (int i = 0 ; i < 4/nbJoueurs ; i++)
    {
      for (int j = 0 ; j < nbJoueurs ; j++)
      {
        mainsJoueurs[j].add(pioche.retirer());
      }
    }
  }
    
  public void afficherMain(int joueur)
  {
    for (int i = 0 ; i < mainsJoueurs[joueur-1].size() ; i++)
    {
      System.out.print(mainsJoueurs[joueur - 1].get(i));
      if (i != mainsJoueurs[joueur - 1].size() - 1) System.out.print(", ");
    }    
  }
  
  public void executerCommande(String entree, int joueur)
  {
    entree = entree.trim();
    
    if (entree.equals("passe"))
    {
      System.out.println("Le joueur " + joueur + " passe.");
    }
    else
    {    
      int n1 = entree.charAt(0) - '0';
      int n2 = entree.charAt(2) - '0';
      Domino domino = new Domino(n1, n2);
      
      int idx = 0;
      while (idx < mainsJoueurs[joueur - 1].size() && !(mainsJoueurs[joueur - 1].get(idx).equals(domino)))
      {
        idx++;
      }
      if (idx == mainsJoueurs[joueur - 1].size())
        throw new IllegalArgumentException("Vous n'avez pas ce domino.");
      
      if (sequence == null)
      {
        sequence = new SequenceDomino(domino);
        mainsJoueurs[joueur - 1].remove(idx);
      }
      else
      {
        if (entree.charAt(3) == 'd')
          sequence.ajouterADroite(domino);
        else if (entree.charAt(3) == 'g')
          sequence.ajouterAGauche(domino);
        mainsJoueurs[joueur - 1].remove(idx);
        if (mainsJoueurs[joueur - 1].isEmpty())
          gagnant = joueur;
      }
    }
  }
  
  public static void main(String[] args)
  {    
    Scanner scan = new Scanner(System.in);
    System.out.println("Bienvenue au jeu de Domino ! Combien de joueurs ?");
    
    JeuDomino jeu = new JeuDomino(Integer.parseInt(scan.nextLine()));
    
    String entree;
    int joueur = 1;
    while(jeu.gagnant == 0)
    {
      if (jeu.sequence != null) System.out.print("\n\nPlateau : " + jeu.sequence);
      System.out.print("\n\nJoueur " + joueur + ", voici votre main : ");
      jeu.afficherMain(joueur);
      System.out.println("\nQuel domino souhaitez-vous poser ? (exemples : 3,2d ; 5,4g ; passe)");      
      entree = scan.nextLine();
      jeu.executerCommande(entree, joueur);
      joueur++;
      if (joueur == jeu.nbJoueurs + 1) joueur = 1;
    }
    
    System.out.println("La partie est terminée : le joueur " + jeu.gagnant + " a gagné ! Bravo !");
  }
}