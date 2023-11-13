import java.util.ArrayList;
import java.util.Arrays;

public class ComputerField {
    private ArrayList<Ship> ships;
    private String[][] computerField;
    private String[][] computerFakeField;

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

    public String[][] getComputerField() {
        return computerField;
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

    /*private void addShipsAndNulls(String[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = random();
            }
        }
    }*/

    private String random() {
        double probability = Math.random();

        if (probability > 0.2) {
            return "⚫";
        } else {
            int number = (int) (Math.random() * ships.size());
            return ships.get(number).getName();
        }
    }

    public String[][] getComputerFakeField() {
        return computerFakeField;
    }
}
