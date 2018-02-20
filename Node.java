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

  public Node(Board aBoard) {
    board = aBoard;
    board.init();
    System.out.println(board);
    value = board.evaluate();
    turn = true;
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
    turn = !aMove.turn;//Opposite of the last move. Just for additional security.
  }//Node

  public void generate(int depth) {
    //Get arraylist of moves made, for easy search so we don't double up.
    if(depth <= 0) return;//Halt
    ArrayList<Move> movesMade = new ArrayList<Move>();
    for(Node child : children) {
      movesMade.add((Move)child.lastMove);
      child.generate(depth-1);
    }
    //Make new moves, and check to see if it was already made
    for(BaseMove move : board.generateLegalMoves(turn)) {
      if(!movesMade.contains(move)){ //Make sure it's not in there
        Board newBoard = (Board)board.deepCopy();
        newBoard.makeMove(move);
        Node<Move, Board> newNode = new Node<Move, Board>(newBoard, this, (Move)move);
        newNode.turn = !turn;//Alternate turns.
        newNode.generate(depth-1);
        children.add(newNode);
      }//if Move is not made yet
    }//For move
  }//generate

  public int minimax(int depth) {
    if(depth <= 0) return value;//Halt
    if(children.isEmpty()) return value;//Bottom of tree
    int maximin = children.get(0).value;
    for(Node child : children) {
      child.value = child.minimax(depth-1); //First traverse to bottom of the tree
      if((child.value > maximin && turn) || (child.value < maximin && !turn)) {
        maximin = child.value;
        bestMove = (Move)child.lastMove;
        bestNode = child;
      }
    }
    return maximin;
  }
}//Node
