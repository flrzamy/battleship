import java.util.*;

public class Battleship {


    public void start() {
        //Initialization
        Board[] myBoards = new Board[2];
        Board[] enemyBoards = new Board[2];
        for (int i=0; i<2; i++) {
            myBoards[i] = new Board();
            enemyBoards[i] = new Board();
            myBoards[i].initializeBoard();
            enemyBoards[i].initializeBoard();
        }
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
        Board board= myBoards[0];
        Scanner scanner = new Scanner(System.in);
        while (playerPlaced<2) {
            while (numPlaced<5) {
                while (!placed) {
                    System.out.println("Now place your "+ship.name+", which is of size "+ship.size+".");
                    board.printBoard();
                    System.out.println("Would you like to place your "+ship.name+" horizontally(H) or vertically(V)?");
                    System.out.print("[H/V]");
                    String direction = scanner.next();
                    System.out.println("\nOn Which square would you like to place the head of your "+ship.name+"?");
                    System.out.print("[A1-H8]:");
                    String index = scanner.next();
                    if (!board.placeShip(ship,index,direction)) {
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
                board = enemyBoards[0];
            } else {
                System.out.println("Now you have both placed your ships. It's time to start the game!");
                System.out.println("===========================================================================================");
                break;
            }
        }

        //Start game play
        int player = 1;
        while (true) {
            System.out.println("It's time for player "+player+" to hit! Where would you like to target?");

            Board hitBoard;
            Board markBoard;
            if (player == 1) {
                hitBoard = enemyBoards[0];
                markBoard = myBoards[1];
            }else {
                hitBoard = myBoards[0];
                markBoard = enemyBoards[1];
            }
            markBoard.printBoard();

            System.out.print("[A1-H8]:");
            String index = scanner.next();

            if (markBoard.checkHistory(index) == 2) {
                System.out.println("Invalid position, try again");
                continue;
            }else if (markBoard.checkHistory(index) == 1) {
                System.out.println("You have hit the position before, try another");
                continue;
            }

            if (hitBoard.checkHit(index) == 1) {
                System.out.println("Hit!!!!\n");
                markBoard.markBoard(index, true);
            }else if (hitBoard.checkHit(index) == 2){
                System.out.println("Miss!!!!\n");
                markBoard.markBoard(index, false);
            }else {
                System.out.println("Player " + player + " wins!!!!");
                System.out.println("===========================================================================================");
                markBoard.markBoard(index, true);
                markBoard.printBoard();
                break;
            }

            if (player == 1) {
                player = 2;
            }else {
                player = 1;
            }
        }
    }
}
