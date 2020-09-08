import java.util.*;

public class Battleship {


    public void start() {
        //Initialization
        Board myBoard = new Board();
        myBoard.initializeBoard();
        Ship[] ships  = new Ship[5];
        ships[0] = new Ship("Carrier",5);
        ships[1] = new Ship("Battleship",4);
        ships[2] = new Ship("Destroyer",3);
        ships[3] = new Ship("Submarine",3);
        ships[4] = new Ship("Patrol Boat",2);

        //Start Game
        System.out.println("===========================================================================================");
        System.out.println("Welcome to BattleShip!");
        System.out.println("Please start the game by placing your battleships on your field.\n");

        //Start placing ships
        boolean placed = false;
        int numPlaced = 0;
        Ship ship = ships[0];
        int playerPlaced = 0;
        while (playerPlaced<2) {
            while (numPlaced<5) {
                while (!placed) {
                    System.out.println("Now place your "+ship.name+", which is of size "+ship.size+".");
                    System.out.println("Would you like to place your "+ship.name+" horizontally(H) or vertically(V)?");
                    System.out.print("[H/V]");
                    Scanner scanner = new Scanner(System.in);
                    String direction = scanner.next();
                    System.out.println("\nOn Which square would you like to place the head of your "+ship.size+"?");
                    myBoard.printBoard();
                    System.out.print("[A1-H8]:");
                    String index = scanner.next();
                    if (!myBoard.placeShip(ship.name.charAt(0),index,direction)) {
                        System.out.println("Invalid position. Please try again.\n");
                    } else {
                        placed = true;
                        numPlaced++;
                        if (numPlaced<5) {
                            ship = ships[numPlaced];
                        }
                        System.out.println();
                    }
                }
                placed = false;
            }
            playerPlaced++;
            if (playerPlaced == 1) {
                System.out.println("Now you have placed all your ships. It's time for your friend to place ships.\n");
                System.out.println("===========================================================================================");
                numPlaced = 0;
                ship = ships[0];
            } else {
                System.out.println("Now you have both placed your ships. It's time to start the game!");
                System.out.println("===========================================================================================");
                break;
            }
        }



    }
}
