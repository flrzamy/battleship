import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Board {
    char[][] board = new char[8][8];
    int num_Placed = 0;

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

    private boolean isValidIndex(String index) {
        String indexPattern = "^[a-hA-H][1-8]$";
        Pattern r = Pattern.compile(indexPattern);
        Matcher m = r.matcher(index);
        if (!m.find()) {
            return false;
        }
        return true;
    }

    private boolean isValidPosition(Ship ship, String index, String direction) {
        if (!(direction.equalsIgnoreCase("h") || direction.equalsIgnoreCase("v"))) {
            System.out.println("Wrong direction");
            return false;
        }

        if (!isValidIndex(index)) {
            System.out.println("Wrong index input");
            return false;
        }

        if (direction.equalsIgnoreCase("h")) {
            if (index.toLowerCase().charAt(0) - 'a' + ship.size > 8) {
                System.out.println("Out of range");
                return false;
            }
            for (int i = 0; i < ship.size; i++) {
                if (this.board[index.charAt(1) - '1'][(index.toLowerCase().charAt(0) - 'a') + i] != ' ') {
                    System.out.println("Conflict");
                    return false;
                }
            }
        }else if (direction.equalsIgnoreCase("v")) {
            if (index.charAt(1) - '0' + ship.size > 9) {
                System.out.println("Out of range");
                return false;
            }
            for (int i = 0; i < ship.size; i++) {
                if (this.board[index.charAt(1) - '1' + i][(index.toLowerCase().charAt(0) - 'a')] != ' ') {
                    System.out.println("Conflict");
                    return false;
                }
            }
        }

        return true;
    }

    boolean placeShip(Ship ship, String index, String direction) {
        if (!isValidPosition(ship, index, direction)) {
            return false;
        }

        if (direction.equalsIgnoreCase("h")) {
            for (int i = 0; i < ship.size; i++) {
                this.num_Placed++;
                if (i == 0) {
                    this.board[index.charAt(1) - '1'][(index.toLowerCase().charAt(0) - 'a') + i] = '<';
                }else if(i == ship.size-1) {
                    this.board[index.charAt(1) - '1'][(index.toLowerCase().charAt(0) - 'a') + i] = '>';
                }else {
                    this.board[index.charAt(1) - '1'][(index.toLowerCase().charAt(0) - 'a') + i] = '+';
                }
            }
        }else if (direction.equalsIgnoreCase("v")) {
            for (int i = 0; i < ship.size; i++) {
                this.num_Placed++;
                if (i == 0) {
                    this.board[index.charAt(1) - '1' + i][(index.toLowerCase().charAt(0) - 'a')] = '^';
                }else if(i == ship.size-1) {
                    this.board[index.charAt(1) - '1' + i][(index.toLowerCase().charAt(0) - 'a')] = 'v';
                }else {
                    this.board[index.charAt(1) - '1' + i][(index.toLowerCase().charAt(0) - 'a')] = '+';                }
            }
        }

        return true;
    }

    int checkHit(String index) {
        int index_r = index.charAt(1) - '1';
        int index_c = (index.toLowerCase().charAt(0) - 'a');
        if (this.board[index_r][index_c] != ' ') {
            this.num_Placed--;
            if (this.num_Placed == 0) {
                return 0;
            }
            return 1;
        }else {
            return 2;
        }
    }

    int checkHistory(String index) {
        if (!isValidIndex(index)) {
            return 2;
        }

        int index_r = index.charAt(1) - '1';
        int index_c = (index.toLowerCase().charAt(0) - 'a');
        if (this.board[index_r][index_c] != ' ') {
            return 1;
        }else {
            return 0;
        }
    }

    void markBoard(String index, boolean hit) {
        int index_r = index.charAt(1) - '1';
        int index_c = (index.toLowerCase().charAt(0) - 'a');
        if (hit) {
            this.board[index_r][index_c] = 'O';
        }else {
            this.board[index_r][index_c] = 'X';
        }
    }
}
