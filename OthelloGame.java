public class OthelloGame {
  public static void main(String[] args) {
    OthelloBoard board = new OthelloBoard();
    System.out.println(board);
    OthelloMove move = new OthelloMove(true,3,5);
    System.out.println(board.isLegalMove(move));
    move = new OthelloMove(true,3,2);
    System.out.println(board.isLegalMove(move));
  }
}
