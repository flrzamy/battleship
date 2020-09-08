import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Battleship {

    final String[] ships = {"Carrier", "Battleship", "Destroyer", "Submarine", "PatrolBoat"};


    public void start() {
        Board myBoard = new Board();
        myBoard.initializeBoard();
        System.out.println("===========================================================================================");
        System.out.println("Welcome to BattleShip!");
        System.out.println("Please start the game by placing your battleships on your field.\n");
        //Start placing ships
        boolean placed = false;
        int numPlaced = 0;
        String ship = ships[0];
        int size = 5;
        while (numPlaced<5) {
            while (!placed) {
                System.out.println("Now place your "+ship+", which is of size "+size+".");
                System.out.println("Would you like to place your "+ship+" horizontally(H) or vertically(V)?");
                System.out.print("[H/V]");
                Scanner scanner = new Scanner(System.in);
                String direction = scanner.next();
                System.out.println("\nOn Which square would you like to place the head of your "+ship+"?");
                myBoard.printBoard();
                System.out.print("[A1-H8]:");
                String index = scanner.next();
                if (!myBoard.placeShip(ship.charAt(0),index,direction)) {
                    System.out.println("Invalid position. Please try again.\n");
                } else {
                    placed = true;
                    numPlaced++;
                    ship = ships[numPlaced];
                }
            }
        }


    }
}
