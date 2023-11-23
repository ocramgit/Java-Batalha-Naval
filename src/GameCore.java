import java.util.Arrays;
import java.util.Scanner;

public class GameCore {

    private Player playerOne, playerTwo;
    private Computer computer;
    private Ship ship;
    boolean gameOver = false;
    private Scanner sc;

    public GameCore() {
        initGameEntities();
        sc = new Scanner(System.in);
    }

    public void askPlayerOneToPlaceShips() {
        System.out.print("\u001b[44;1mPLAYER 1\u001b[0m");
        playerOne.placeShips();
    }

    public void askPlayerTwoToPlaceShips() {
        System.out.print("\u001b[44;1mPLAYER 2\u001b[0m");
        playerTwo.placeShips();
    }

    public void startPvpManualMode() throws InterruptedException {
        askPlayerOneToPlaceShips();
        askPlayerTwoToPlaceShips();
        Music start = new Music("/321.wav");
        start.play();
        Thread.sleep(2800);
        while (!gameOver) {
            playOnPlayerTwoBoard();
            playOnPlayerOneBoard();
        }
    }

    private void playOnPlayerOneBoard() throws InterruptedException {
        int row, column;

        do {
            System.out.print("\n\u001b[44;1mRow:\u001b[0m ");
            while (!sc.hasNextInt()) {
                System.out.println("\u001b[31mThis option only accepts 0-9 number.\u001b[0m");
                sc.next();
            }
            row = sc.nextInt();

        } while (row < 0 || row > 9);

        do {
            System.out.print("\n\u001b[44;1mColumn:\u001b[0m ");
            while (!sc.hasNextInt()) {
                System.out.println("\u001b[31mThis option only accepts 0-9 number.\u001b[0m");
                sc.next();
            }
            column = sc.nextInt();

        } while (column < 0 || column > 9);

        System.out.print("\n\u001b[44;1mPLAYER 1 FIELD\u001b[0m\n\n");
        playerOne.play(row, column);
        playerOne.render();
        if(playerOne.getExplodedShips() >= 10) {
            System.out.println("Player 2 wins!");
            return;
        }
        System.out.println("\n");
    }

    private void playOnPlayerTwoBoard() throws InterruptedException {
        int row, column;

        do {
            System.out.print("\n\u001b[44;1mRow:\u001b[0m ");
            while (!sc.hasNextInt()) {
                System.out.println("\u001b[31mThis option only accepts 0-9 number.\u001b[0m");
                sc.next();
            }
            row = sc.nextInt();

        } while (row < 0 || row > 9);

        do {
            System.out.print("\n\u001b[44;1mColumn:\u001b[0m ");
            while (!sc.hasNextInt()) {
                System.out.println("\u001b[31mThis option only accepts 0-9 number.\u001b[0m");
                sc.next();
            }
            column = sc.nextInt();

        } while (column < 0 || column > 9);
        playerTwo.play(row, column);
        System.out.print("\n\u001b[41;1mPLAYER 2 FIELD\u001b[0m\n\n");
        playerTwo.render();
        if(playerOne.getExplodedShips() >= 10) {
            System.out.println("Player 1 wins!");
            return;
        }
        System.out.println("\n");
    }

    private void playOnComputerBoard() {
        int row, column;

        do {
            System.out.print("\n\u001b[44;1mRow:\u001b[0m ");
            while (!sc.hasNextInt()) {
                System.out.println("\u001b[31mThis option only accepts 0-9 number.\u001b[0m");
                sc.next();
            }
            row = sc.nextInt();

        } while (row < 0 || row > 9);

        do {
            System.out.print("\n\u001b[44;1mColumn:\u001b[0m ");
            while (!sc.hasNextInt()) {
                System.out.println("\u001b[31mThis option only accepts 0-9 number.\u001b[0m");
                sc.next();
            }
            column = sc.nextInt();

        } while (column < 0 || column > 9);
        computer.play(row, column);
        System.out.print("\u001b[41;1mCOMPUTER FIELD\u001b[0m");
        computer.render();
        System.out.println("\n");
    }

    public void playPlayerVsComputer() throws InterruptedException {
        computer.placeRandomShips();
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        askPlayerOneToPlaceShips();
        Music start = new Music("/321.wav");
        start.play();
        Thread.sleep(2800);
        while (!gameOver) {
            playOnComputerBoard();
            computerPlayOnPlayerOneBoard();
        }
    }

    private void computerPlayOnPlayerOneBoard() throws InterruptedException {
        int row = random();
        int column = random();
        playerOne.play(row, column);
        System.out.print("\u001b[41;1mComputer is playing...\u001b[0m");
        Thread.sleep(1500);
        System.out.print("\u001b[41;1mFIELD PLAYER\u001b[0m");
        playerOne.render();
    }

    public int random() {
        return (int) (Math.random() * 10);
    }

    private void fillFieldWithNulls(String[][] field) {
        for (String[] strings : field) {
            Arrays.fill(strings, "⬛");
        }
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void initGameEntities() {
        playerOne = new Player(new Field(10), new Field(10));
        playerTwo = new Player(new Field(10), new Field(10));
        computer = new Computer(new Field(10), new Field(10));

        fillFieldWithNulls(playerOne.getFakeField().getField());
        fillFieldWithNulls(playerOne.getField().getField());

        fillFieldWithNulls(playerTwo.getFakeField().getField());
        fillFieldWithNulls(playerTwo.getField().getField());

        fillFieldWithNulls(computer.getFakeField().getField());
        fillFieldWithNulls(computer.getField().getField());
    }

    public void startPvpRandomMode() throws InterruptedException {
        playerOne.placeRandomShips();
        playerTwo.placeRandomShips();
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        Music start = new Music("/321.wav");
        start.play();
        Thread.sleep(2800);
        while (!gameOver) {
            System.out.print("\u001b[44;1mPLAYER 1 TURN\u001b[0m");
            System.out.println();
            playOnPlayerTwoBoard();
            System.out.print("\u001b[41;1mPLAYER 2 TURN\u001b[0m");
            System.out.println();
            playOnPlayerOneBoard();
        }
    }
}
