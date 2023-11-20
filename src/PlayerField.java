import java.util.ArrayList;
import java.util.Arrays;

public class PlayerField {

    private ArrayList<Ship> ships;
    private String[][] playerField;
    private String[][] playerFakeField;
    private String[][] playerTwoField;
    private String[][] playerTwoFakeField;
    private int rows = 10;
    private int columns = 10;

    public PlayerField(int row, int column) {
        playerField = new String[row][column];
        ships = new ArrayList<>();
        playerField = new String[row][column];
        playerFakeField = new String[row][column];
        playerTwoField = new String[row][column];
        playerTwoFakeField = new String[row][column];

        ships.add(new Ship("\uD83D\uDEA2", 3));
        ships.add(new Ship("⛵", 4));
        ships.add(new Ship("\uD83D\uDEE5\uFE0F", 2));
        ships.add(new Ship("\uD83D\uDEA3", 1));
        ships.add(new Ship("\uD83D\uDEA4", 5));

        addNulls(playerField);
        addNulls(playerFakeField);
        addNulls(playerTwoField);
        addNulls(playerTwoFakeField);
    }

    public String[][] getPlayerField() {
        return playerField;
    }

    public void placeBoatsOnField(String[][] field) {
        for (int boat = 0; boat < 5; boat++) {
            boolean placed = false;

            while (!placed) {
                int row = getRandomRowAndColumn();
                int column = getRandomRowAndColumn();

                if (row + ships.get(boat).getSize() > 10 || column + ships.get(boat).getSize() > 10) {
                    continue;
                }

                switch (getRandomVorH()) {
                    case 1:
                        placed = tryPlaceHorizontal(field, boat, row, column);
                        break;
                    case 0:
                        placed = tryPlaceVertical(field, boat, row, column);
                        break;
                }
            }
        }

        fillEmptySpaces();
    }

    public String[][] getPlayerTwoFakeField() {
        return playerTwoFakeField;
    }

    public String[][] getPlayerTwoField() {
        return playerTwoField;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    private boolean tryPlaceHorizontal(String[][] field, int boat, int row, int column) {
        for (int i = 0; i < ships.get(boat).getSize(); i++) {
            if (!field[row][column + i].equals("⚫")) {
                return false;
            }
        }

        for (int i = 0; i < ships.get(boat).getSize(); i++) {
            field[row][column + i] = ships.get(boat).getName();
        }

        return true;
    }

    private boolean tryPlaceVertical(String[][] field, int boat, int row, int column) {
        for (int i = 0; i < ships.get(boat).getSize(); i++) {
            if (!field[row + i][column].equals("⚫")) {
                return false;
            }
        }

        for (int i = 0; i < ships.get(boat).getSize(); i++) {
            field[row + i][column] = ships.get(boat).getName();
        }

        return true;
    }

    private void fillEmptySpaces() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (playerField[i][j].equals("⚫")) {
                    playerField[i][j] = "⚫";
                }
            }
        }
    }


    public int getRandomVorH() {
        return (int) (Math.random() * 2);
    }

    public int getRandomRowAndColumn() {
        return (int) (Math.random() * 10);
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public String[][] getPlayerFakeField() {
        return playerFakeField;
    }

    public boolean haveAnyShipOnPlayerField(int row, int column) {
        return playerField[row][column].equals("⚫");
    }

    public void insertOnPlayerFakeField(int row, int column, String insert) {
        playerFakeField[row][column] = insert;
    }

    private void addNulls(String[][] field) {
        for (String[] strings : field) {
            Arrays.fill(strings, "⚫");
        }
    }
    public boolean haveAnyShipOnPlayerTwoField(int row, int column) {
        return playerTwoField[row][column].equals("⚫");
    }

    public void insertOnPlayerTwoFakeField(int row, int column, String insert) {
        playerTwoFakeField[row][column] = insert;
    }
}
