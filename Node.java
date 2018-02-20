import java.util.*;
//Justin Baum

public class Node<Move extends BaseMove, Board extends BaseBoard> {
  public Board board;
  public int value;
  public Move bestMove;
  public Node bestNode;
  public Move lastMove;
  public Node lastNode;
  public boolean turn;
  public ArrayList<Node<Move, Board>> children;

  public Node() {
    board = (Board)(new BaseBoard());
    value = board.evaluate();
    bestMove = null;
    bestNode = null;
    lastMove = null;
    lastNode = null;
    children = new ArrayList<Node<Move, Board>>();
  }//Node
  public Node(Board aBoard, Node aNode, Move aMove) {
    board = aBoard;
    value = board.evaluate();
    bestMove = null;
    bestNode = null;
    lastMove = aMove;
    lastNode = aNode;
    children = new ArrayList<Node<Move, Board>>();
  }//Node

  public void generate(int depth) {
    //Get arraylist of moves made, for easy search so we don't double up.
    if(depth <= 0) return;//Halt
    ArrayList<Move> movesMade = new ArrayList<Move>();
    for(Node child : children) {
      movesMade.add((Move)child.lastMove);
    }
    //Make new moves, and check to see if it was already made
    for(Object o : board.generateLegalMoves(turn)) {
      Move move = (Move)o;
      if(!movesMade.contains(move)){ //Make sure it's not in there
        Board newBoard = (Board)board.deepCopy();
        Node newNode = new Node(newBoard, this, move);
        newNode.generate(depth-1);
        children.add(newNode);
      }//if Move is not made yet
    }//For move
  }//generate
}//Node
