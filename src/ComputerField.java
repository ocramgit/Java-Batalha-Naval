import java.util.ArrayList;
import java.util.Arrays;

public class ComputerField {
    private ArrayList<Ship> ships;
    private String[][] computerField;
    private String[][] computerFakeField;
    private int row = 10;
    private int columns = 10;

    public ComputerField(int row, int column) {
        computerField = new String[row][column];
        ships = new ArrayList<>();
        computerFakeField = new String[row][column];

        ships.add(new Ship("\uD83D\uDEA2", 3));
        ships.add(new Ship("⛵", 4));
        ships.add(new Ship("\uD83D\uDEE5\uFE0F", 2));
        ships.add(new Ship("\uD83D\uDEA3", 1));
        ships.add(new Ship("\uD83D\uDEA4", 5));

        addNulls(computerField);
        addNulls(computerFakeField);
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public String[][] getComputerField() {
        return computerField;
    }

    public int getRandomRowAndColumn() {
        return (int) (Math.random() * 10);
    }

    public void placeBoatsOnField() {
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
                        placed = tryPlaceHorizontal(boat, row, column);
                        break;
                    case 0:
                        placed = tryPlaceVertical(boat, row, column);
                        break;
                }
            }
        }

        fillEmptySpaces();
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return row;
    }

    private boolean tryPlaceHorizontal(int boat, int row, int column) {
        for (int i = 0; i < ships.get(boat).getSize(); i++) {
            if (!computerField[row][column + i].equals("⚫")) {
                return false;
            }
        }

        for (int i = 0; i < ships.get(boat).getSize(); i++) {
            computerField[row][column + i] = ships.get(boat).getName();
        }

        return true;
    }

    private boolean tryPlaceVertical(int boat, int row, int column) {
        for (int i = 0; i < ships.get(boat).getSize(); i++) {
            if (!computerField[row + i][column].equals("⚫")) {
                return false;
            }
        }

        for (int i = 0; i < ships.get(boat).getSize(); i++) {
            computerField[row + i][column] = ships.get(boat).getName();
        }

        return true;
    }

    private void fillEmptySpaces() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (computerField[i][j].equals("⚫")) {
                    computerField[i][j] = "⚫";
                }
            }
        }
    }

    public int getRandomVorH() {
        return (int) (Math.random() * 2);
    }

    public boolean haveAnyShipOnComputerField(int row, int column) {
        return computerField[row][column].equals("⚫");
    }

    public void insertOnComputerFakeField(int row, int column, String insert) {
        computerFakeField[row][column] = insert;
    }

    private void addNulls(String[][] field) {
        for (String[] strings : field) {
            Arrays.fill(strings, "⚫");
        }
    }

    public String[][] getComputerFakeField() {
        return computerFakeField;
    }
}