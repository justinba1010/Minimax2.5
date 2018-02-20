import java.util.*;
//Justin Baum
public class OthelloAI {
  public static void main(String[] args) {
    OthelloBoard aBoard = new OthelloBoard();
    Node<OthelloMove, OthelloBoard> minimax = new Node<OthelloMove, OthelloBoard>(aBoard);
    minimax.generate(1);
    System.out.println(minimax.children);
  }

}
