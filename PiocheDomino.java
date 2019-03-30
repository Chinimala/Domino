import java.lang.Math;
import java.util.ArrayList;

public class PiocheDomino
{
  ArrayList<Domino> pioche;
  
  public PiocheDomino()
  {
    pioche = new ArrayList<Domino>();
    for (int i = 0 ; i <= 6 ; i++)
    {
      for (int j = i ; j <= 6 ; j++)
      {
        pioche.add(new Domino(i, j));
      }
    }
    melanger();
  }
  
  public Domino retirer()
  {
    Domino dernierDomino = pioche.get(pioche.size() - 1);
    pioche.remove(pioche.size() - 1);
    return dernierDomino;
  }
  
  public void melanger()
  {
    System.out.println("Mélange en cours...");
    ArrayList<Domino> nouvellePioche = new ArrayList<Domino>();
    for (int i = 28 ; i > 0 ; i--)
    {
      int nbAleatoire = (int)(Math.random() * i);
      nouvellePioche.add(pioche.get(nbAleatoire));
      pioche.remove(nbAleatoire);
    }
    pioche = nouvellePioche;
  }
  
  public String toString()
  {
    String res = "";
    for (int i = 0 ; i < pioche.size() ; i++)
    {
      res += pioche.get(i) + ", ";
    }
    return res;
  }
}