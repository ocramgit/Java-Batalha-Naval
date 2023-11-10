import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GameCore {

    private ArrayList<Ship> ships = new ArrayList<>();
    private String[][] field;
    private String[][] fakeField;
    private String[][] computerField;
    private String[][] computerFakeField;
    private Scanner sc;
    private boolean gameOver;
    private boolean isValid;
    private int shipCount = 0;
    private Computer computer;

    public GameCore() {
        computer = new Computer();
        ships.add(new Ship("\uD83D\uDEA2", 2));
        ships.add(new Ship("⛵", 3));
        ships.add(new Ship("\uD83D\uDEE5\uFE0F", 3));
        ships.add(new Ship("\uD83D\uDEA3", 4));
        ships.add(new Ship("\uD83D\uDEA4", 5));

        sc = new Scanner(System.in);
        gameOver = false;

        field = new String[6][6];
        fakeField = new String[field.length][field.length];
        computerField = new String[field.length][field.length];
        computerFakeField = new String[field.length][field.length];
        fillArrayWithRandomObjects(field);
        fillArrayWithNulls(fakeField);
        fillArrayWithNulls(computerFakeField);
        fillArrayWithNulls(computerField);
    }

    public void createComputerField() {
        while (shipCount != 2) {
            System.out.println("Letter: ");
            String playerLetter = sc.next();
            System.out.println("Number:");
            int column = sc.nextInt() - 1;
            int row = translateLetter(playerLetter);

            System.out.println("1-\uD83D\uDEA2");
            System.out.println("2- \uD83D\uDEE5\uFE0F");
            System.out.println("3- \uD83D\uDEA3");
            System.out.println("4- \uD83D\uDEA4");
            int playerChoice = sc.nextInt();

            addComputerField(row, column, playerChoice);


            for (int i = 0; i < computerField.length; i++) {
                for (int j = 0; j < computerField[i].length; j++) {

                    System.out.print("\t" + computerField[i][j]);
                }
                System.out.println();
            }
            shipCount++;
        }
    }

    public void addComputerField(int row, int column, int playerChoice) {
        computerField[row][column] = ships.get(playerChoice).getName();
    }

    public boolean checkPlay(int row, int column) {
        if (field[row][column] != null) {
            fakeField[row][column] = field[row][column];
            return true;
        } else {
            fakeField[row][column] = "⚫";
            return false;
        }
    }

    public boolean checkComputerPlay(int row, int column) {
        if (computerFakeField[row][column].equals("⚫")) {
            computerFakeField[row][column] = computerField[row][column];
            return true;
        }
        return false;
    }

    public void renderGraph(boolean choice, int row, int column) throws InterruptedException {

        if (choice) {
            fakeField[row][column] = field[row][column];
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
        Thread.sleep(2000);
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

        if (probability > 0.2) {
            return null;
        } else {
            int number = (int) (Math.random() * ships.size());
            return ships.get(number).getName();
        }
    }

    public void startModeBot() throws InterruptedException {
        System.out.println("Welcome! Computer generated some ships. Good luck!");
        System.out.println("VAMOS CRIAR O TEU TABULEIRO");
        createComputerField();
        System.out.println("NICE AGORA PLAY");
        play();
    }

    private boolean isValidIndex(int row, int column, String[][] field) {
        return row >= 0 && row < field.length && column >= 0 && column < field[0].length;
    }
    private void play() throws InterruptedException {
        while (!gameOver) {
            isValid = false;
            int computerRow = computer.getRandom(computerFakeField.length);
            int computerColumn = computer.getRandom(computerFakeField[0].length);
            System.out.println("Computer's indices: Row=" + computerRow + ", Column=" + computerColumn);

            if (isValidIndex(computerRow, computerColumn, computerFakeField)) {
                if (!checkComputerPlay(computerRow, computerColumn)) {
                    System.out.println("Computer already played that position");
                    continue;
                }

                renderComputerGraph(isValid, computerRow, computerColumn);
            } else {
                System.out.println("Invalid computer indices!");
            }

            System.out.println("TUA VEZ CARO JOGADOR");

            System.out.println("Letter: ");
            String playerLetter = sc.next();
            System.out.println("Number:");
            int playerNumber = sc.nextInt() - 1;
            int playerLetterToInt = translateLetter(playerLetter);

            if (fakeField[playerLetterToInt][playerNumber].equals("⚫")) {
                isValid = checkPlay(playerLetterToInt, playerNumber);
                renderGraph(isValid, playerLetterToInt, playerNumber);
            } else {
                System.out.println("You already played that position!");
            }
        }
    }

    private void renderComputerGraph(boolean choice, int row, int column) {

        if (choice) {
            computerFakeField[row][column] = computerField[row][column];
            System.out.println("Computer destroyed "+computerFakeField[row][column]);
        } else {
            computerFakeField[row][column] = "\uD83C\uDF0A";
        }

        for (int i = 0; i < computerFakeField.length; i++) {
            for (int j = 0; j < computerFakeField[i].length; j++) {

                System.out.print("\t" + computerFakeField[i][j]);
            }
            System.out.println();
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
