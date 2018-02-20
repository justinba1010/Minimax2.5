public class OthelloGame {
  public static void main(String[] args) {
    OthelloBoard board = new OthelloBoard();
    System.out.println(board);
    for(OthelloMove move : board.generateLegalMoves(true)) {
      System.out.println(move);
    }
    for(OthelloMove move : board.generateLegalMoves(false)) {
      System.out.println(move);
    }
  }//main
}//OthelloGame
