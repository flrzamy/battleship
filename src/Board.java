import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private boolean isValidPosition(Ship ship, String index, String direction) {
        if (!(direction.equalsIgnoreCase("h") || direction.equalsIgnoreCase("v"))) {
            return false;
        }

        String indexPattern = "^[a-gA-G][1-8]$";
        Pattern r = Pattern.compile(indexPattern);
        Matcher m = r.matcher(index);
        if (!m.find()) {
            return false;
        }

        if (direction.equalsIgnoreCase("h")) {
            if (index.toLowerCase().charAt(0) - 'a' + ship.size > 8) {
                return false;
            }
        }else if (direction.equalsIgnoreCase("v")) {
            if (index.charAt(1) - '0' + ship.size > 9) {
                return false;
            }
        }

        return true;
    }

    boolean placeShip(Ship ship, String index, String direction) {
        if (!isValidPosition(ship, index, direction)) {
            return false;
        }

        if (direction.equalsIgnoreCase("h")) {
            for (int i=0; i<ship.size; i++) {
                this.board[index.charAt(1)-'1'][(index.toLowerCase().charAt(0)-'a') + i] = ship.name.charAt(0);
            }
        }else if (direction.equalsIgnoreCase("v")) {
            for (int i=0; i<ship.size; i++) {
                this.board[index.charAt(1)-'1'+i][(index.toLowerCase().charAt(0)-'a')] = ship.name.charAt(0);
            }
        }

        return true;
    }
}
