import java.util.*;
//Justin Baum
public class OthelloBoard extends BaseBoard {
  private int[][] gameboard;
  private static final int[][] vectors = {{1,1},{1,-1},{-1,1},{-1,-1},{1,0},{0,1},{-1,0},{0,-1}};
  private static final int size = 8;

  public OthelloBoard() {
    init(true);
  }

  public void init(boolean build) {
    gameboard = new int[size][size];
    int i = 1;
    if(!build) return;
    for(int x = size/2-1; x < size/2+1; x++) {
      for(int y = size/2-1; y < size/2+1; y++) {
        gameboard[x][y] = i;
        i *= -1;
      }//for y
      i *= -1;
    }//Set board for start
  }//init

  public int evaluate() {
    int score = 0;
    for(int x = 0; x < size; x++) {
      for(int y = 0; y < size; y++) {
        score += gameboard[x][y];
      }// for y
    }//for x
    return score;
  }//evaluate

  public ArrayList<OthelloMove> generateLegalMoves(boolean turn) {
    ArrayList<OthelloMove> legalMoves = new ArrayList<OthelloMove>();
    return legalMoves;
  }

  public OthelloBoard deepCopy() {
    OthelloBoard newBoard = new OthelloBoard();
    for(int x = 0; x < size; x++) {
      for(int y = 0; y < size; y++) {
        newBoard.gameboard[x][y] = gameboard[x][y];
      }//for y
    }// for x
    return newBoard;
  }

  public String toString() {
    String s = "";
    for(int x = 0; x < size; x++) {
      for(int y = 0; y < size; y++) {
        s += (gameboard[x][y] == 0) ? " " : (gameboard[x][y] == 1) ? "X" : "O";
        s += (y == size -1) ? "" : "|";
      } //for y
      s += "\n";
    }// for x
    return s;
  }//toString

}
