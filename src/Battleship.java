import java.util.Scanner;

public class Battleship {

    public void start() {
        Board myBoard = new Board();
        myBoard.initializeBoard();
        System.out.println("===========================================================================================");
        System.out.println("Welcome to BattleShip!");
        System.out.println("Please start the game by placing your battleships on your field.\n");
        //Start placing ships
        boolean placed = false;
        while (!placed) {
            System.out.println("Now place your Carrier, which is of size 5.");
            System.out.println("Would you like to place your Carrier horizontally(H) or vertically(V)?");
            System.out.print("[H/V]");
            Scanner scanner = new Scanner(System.in);
            String direction = scanner.next();
            System.out.println("\nOn Which square would you like to place the head of your Carrier?");
            myBoard.printBoard();
            System.out.print("[A1-H8]:");
            String index = scanner.next();
            if (!myBoard.placeShip('C',index,direction)) {
                System.out.println("Invalid position. Please try again.\n");
            } else {
                placed = true;
            }
        }

    }
}
