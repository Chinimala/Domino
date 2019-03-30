public class Domino
{  
  int nbGauche, nbDroite;
  
  public Domino(int nbGauche, int nbDroite)
  {
    if (nbGauche >= 0 && nbGauche <= 6 && nbDroite >= 0 && nbDroite <= 6)
    {
      this.nbGauche = nbGauche;
      this.nbDroite = nbDroite;
    }
    else throw new IllegalArgumentException("Valeurs entre 0 et 6");
  }
  
  public int getNbGauche()
  {
    return nbGauche;
  }
  
  public int getNbDroit()
  {
    return nbDroite;
  }
  
  public void retourne()
  {
    int ancienNbDroite = nbDroite;
    nbDroite = nbGauche;
    nbGauche = ancienNbDroite;
  }
  
  public boolean peutSuivre(Domino autreDomino)
  {
    return (autreDomino.nbDroite == nbGauche);
  }
  
  public boolean peutPreceder(Domino autreDomino)
  {
    return (autreDomino.nbGauche == nbDroite);
  }
  
  public String toString()
  {    
    return "(" + nbGauche + "," + nbDroite + ")";
  }
  
  public boolean equals(Domino autreDomino)
  {
    if (nbGauche == autreDomino.nbGauche && nbDroite == autreDomino.nbDroite)
      return true;
    else
      return false;
  }
}