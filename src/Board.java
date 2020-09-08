public class Board {
    char[][] board = new char[8][8];

    void initializeBoard() {
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                board[i][j] = ' ';
            }
        }
    }

    void printBoard() {
        System.out.println("   A B C D E F G H");
        for (int i=0; i<8; i++) {
            System.out.print(i+1+" ");
            for (int j=0; j<8; j++) {
                System.out.print("|"+board[i][j]);
            }
            System.out.println("|");
        }
        System.out.println();
    }

    private boolean isValidPosition(char ship, String index, String direction) {
        return true;
    }

    boolean placeShip(char ship, String index, String direction) {
        if (!isValidPosition(ship, index, direction)) {
            return false;
        }
        return true;
    }
}
