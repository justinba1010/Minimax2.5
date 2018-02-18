import java.util.*;
//Justin Baum

public class Node<Move extends BaseMove, Board extends BaseBoard> {
  public Board board;
  public int value;
  public Node bestMove;
  public Node lastMove;
  public ArrayList<Node<Move, Board>> children;

  public Node() {

  }


}
