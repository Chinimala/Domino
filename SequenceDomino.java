import java.util.ArrayList;
public class SequenceDomino
{
  ArrayList<Domino> sequence;
  
  public SequenceDomino(Domino premierDomino)
  {
    sequence = new ArrayList<Domino>();
    sequence.add(premierDomino);
  }
  
  public int valeurAGauche()
  {
    return sequence.get(0).getNbGauche();
  }
  
  public int valeurADroite()
  {
    return sequence.get(sequence.size() - 1).getNbDroit();
  }
  
  public void ajouterAGauche(Domino domino)
  {
    if (domino.peutPreceder(sequence.get(0)))
    {
      sequence.add(0, domino);
    }
    else
    {
      domino.retourne();
      if (domino.peutPreceder(sequence.get(0)))
      {
        sequence.add(0, domino);
      }
      else
      {        
        domino.retourne();
        throw new IllegalArgumentException("Le domino ne peut pas preceder.");
      }
    }
  }
  
  public void ajouterADroite(Domino domino)
  {
    if (domino.peutSuivre(sequence.get(sequence.size() - 1)))
    {
      sequence.add(domino);
    }
    else
    {
      domino.retourne();
      if (domino.peutSuivre(sequence.get(sequence.size() - 1)))
      {
        sequence.add(domino);
      }
      else
      {        
        domino.retourne();
        throw new IllegalArgumentException("Le domino ne peut pas suivre.");
      }
    }
  }
  
  public String toString()
  {
    String res = "";
    for (int i = 0 ; i < sequence.size() ; i++)
    {
      res += sequence.get(i);
    }
    return res;
  }  
}