import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GameCore {

    private ArrayList<Ship> ships = new ArrayList<>();
    private Field field;
    private String[][] fakeField;
    private Scanner sc;
    private boolean gameOver;
    private boolean isValid;

    public GameCore() {
        ships.add(new Ship("\uD83D\uDEA2", 2));
        ships.add(new Ship("⛵", 3));
        ships.add(new Ship("\uD83D\uDEE5\uFE0F", 3));
        ships.add(new Ship("\uD83D\uDEA3", 4));
        ships.add(new Ship("\uD83D\uDEA4", 5));

        sc = new Scanner(System.in);
        gameOver = false;

        field = new Field(6);
        fakeField = new String[field.getSize()][field.getSize()];
        fillArrayWithRandomObjects(field.getField());
        fillArrayWithNulls(fakeField);
    }

    public boolean checkPlay(int row, int column) {
        if(field.getField()[row][column] != null) {
            fakeField[row][column] = field.getField()[row][column];
            return true;
        }
        return false;
    }


    public void renderGraph(boolean choice, int row, int column) {

        if(choice) {
            fakeField[row][column] = field.getField()[row][column];
            System.out.println("Nice!");
        } else {
            fakeField[row][column] = "\uD83C\uDF0A";
        }

        for (int i = 0; i < fakeField.length; i++) {
            for (int j = 0; j < fakeField[i].length; j++) {

                System.out.print("\t" + fakeField[i][j]);
            }
            System.out.println();
        }
    }

    private void fillArrayWithRandomObjects(String[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = random();
            }
        }
    }

    private void fillArrayWithNulls(String[][] fakeField) {
        for (String[] strings : fakeField) {
            Arrays.fill(strings, "⚫");
        }
    }

    private String random() {
        double probability = Math.random();

        if (probability > 0.5) {
            return null;
        } else {
            int number = (int) (Math.random() * ships.size());
            return ships.get(number).getName();
        }
    }

    public void startModeBot() {
        System.out.println("Welcome! Computer generated some ships. Good luck!");
        play();
    }

    private void play() {
        while(!gameOver) {
            System.out.println("Letter: ");
            String playerLetter = sc.next();
            System.out.println("Number:");
            int playerNumber = sc.nextInt()-1;
            int playerLetterToInt = translateLetter(playerLetter);

            if(fakeField[playerLetterToInt][playerNumber].equals("⚫")) {
                isValid = checkPlay(playerLetterToInt, playerNumber);
                renderGraph(isValid, playerLetterToInt, playerNumber);
            } else {
                System.out.println("You already player that position!");
            }
        }
    }

    public static int translateLetter(String letter) {
        switch (letter) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            case "E":
                return 4;
            case "F":
                return 5;
            default:
                System.out.println("Opção inválida. Digite uma posição entre A e F.");
                break;
        }

        return 0;
    }
}
