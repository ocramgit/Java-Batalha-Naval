package me.marco;
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

    public void askPlayerOneToPlaceShips(){
        System.out.println("Player 1: ");
        playerOne.placeShips();
    }

    public void askPlayerTwoToPlaceShips() {
        System.out.println("Player 2: ");
        playerTwo.placeShips();
    }

    public void init() {
        askPlayerOneToPlaceShips();
        askPlayerTwoToPlaceShips();
        System.out.println("Start game.....");
        while (!gameOver) {
           playOnPlayerTwoBoard();
           playOnPlayerOneBoard();
        }
    }

    private void playOnPlayerOneBoard(){
        System.out.println("Player 2: ");
        System.out.println("Row: ");
        int row = sc.nextInt();
        System.out.println("Column");
        int column = sc.nextInt();
        playerOne.play(row, column);
        playerOne.renderGraphic();
        System.out.println("\n");
    }

    private void playOnPlayerTwoBoard(){
        System.out.println("Player 1: ");
        System.out.println("Row: ");
        int row = sc.nextInt();
        System.out.println("Column");
        int column = sc.nextInt();
        playerTwo.play(row, column);
        playerTwo.renderGraphic();
        System.out.println("\n");
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

    public int random() {
        return 0;
    }

    public void checkIfShipIsDestroyed(Ship ship) {
        if(ship.isDestroyed()) {
            ship.setName("💥");
        }
    }
}
