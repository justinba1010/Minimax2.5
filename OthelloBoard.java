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
    //TODO
    return legalMoves;
  }

  public boolean isLegalMove(OthelloMove move) {
    return !takeBlocks(move).isEmpty();
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

  //Insider Stuff


  private ArrayList<int[]> vectorBlocks(int x1, int y1, int[] vector) {
    ArrayList<int[]> blocks = new ArrayList<int[]>();
    while(onBoard(x1,y1)) {
      int[] newspaces = addVector(x1, y1, vector);
      x1 = newspaces[0];
      y1 = newspaces[1];
      if(onBoard(x1,y1)) {
        int[] block = {x1,y1};
        blocks.add(block);
      }
    }
    return blocks;
  }//vectorBlocks

  private boolean onBoard(int x, int y) {
    return (x >= 0) && (y >= 0) && (x < size) && (y < size);
  }//onBoard

  private boolean onBoard(int[] xy) {
    if(xy.length != 2) return false;
    return onBoard(xy[0],xy[1]);
  }//onBoard

  private int[] addVector(int x1, int y1, int[] vector) {
    if(vector.length != 2) return new int[0];
    int[] newblock = {x1+vector[0],y1+vector[1]};
    return newblock;
  }//addVector

  private int whoIs(int[] xy) {
    if(xy.length != 2) return 0;
    if(!onBoard(xy)) return 0;
    return gameboard[xy[0]][xy[1]];
  }//whoIs

  public ArrayList<int[]> takeBlocks(OthelloMove move) {
    //So we need at least one vector to have the other player indefinitely until we hit the player who made the move.
    int startx = move.x;
    int starty = move.y;
    ArrayList<int[]> takeBlocks = new ArrayList<int[]>();
    for(int[] vector : vectors) {
      ArrayList<int[]> blocks = new ArrayList<int[]>();
      boolean sandwich = false; //Check to make sure the pieces we are taking are sandwiched
      for(int[] block : vectorBlocks(startx, starty, vector)) {
        //Check if block is enemy, else we just break
        if(whoIs(block) == ((move.turn) ? 1 : -1)) {//If it is the same player as the move maker, we just break.
          break;
        }
        if(whoIs(block) == ((move.turn) ? -1 : 1)) {//If it is the opposite player we want to add this block.
          sandwich = true;
        } else if(whoIs(block) == 0) {//If it is not a taken spot, then we can cut our search because we never got a sandwich.
          sandwich = false;
          break;
        }// else if
        blocks.add(block);//Add block because we did not hit our conditions
      }//for block
      if(sandwich) {
        for(int[] block : blocks) {
          takeBlocks.add(block);
        }//for block
      }//if sandwich
    }//for vector
    return takeBlocks;
  }

}
