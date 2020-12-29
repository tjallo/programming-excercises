package nl.ru.ai.exercise5;

public class Candidate
{
  public Attempt attempt;
  public int parentCandidate;
  
  public Candidate(Attempt attempt, int parentCandidate)
  {
    this.attempt=attempt;
    this.parentCandidate=parentCandidate;
  }
}
