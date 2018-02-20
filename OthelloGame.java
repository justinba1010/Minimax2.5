public class OthelloGame {
  public static void main(String[] args) {
    OthelloBoard board = new OthelloBoard();
    //Testing the morphism nonsense, it functions as predicted with the pointer remaining intact, so when it gets recast the values stay the same.
    Object a = (Object)(board);
    board = (OthelloBoard) a;
    System.out.println(board);
    OthelloMove move = new OthelloMove(true,3,5);
    System.out.println(board.isLegalMove(move));
    move = new OthelloMove(true,3,2);
    System.out.println(board.isLegalMove(move));
  }
}
