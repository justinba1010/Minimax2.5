import java.util.*;
//Justin Baum
public class OthelloAI {
  private Node minimax;
  public OthelloAI() {
    minimax = new Node<OthelloMove, OthelloBoard>();
    minimax.generate(5);

  }

}
