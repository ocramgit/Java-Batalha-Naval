import java.util.ArrayList;
import java.util.Arrays;

public class PlayerField {

    private ArrayList<Ship> ships;
    private String[][] playerField;
    private String[][] playerFakeField;

    public PlayerField(int row, int column) {
        playerField = new String[row][column];
        ships = new ArrayList<>();
        playerField = new String[row][column];
        playerFakeField = new String[row][column];

        ships.add(new Ship("\uD83D\uDEA2", 3));
        ships.add(new Ship("⛵", 4));
        ships.add(new Ship("\uD83D\uDEE5\uFE0F", 2));
        ships.add(new Ship("\uD83D\uDEA3", 1));
        ships.add(new Ship("\uD83D\uDEA4", 5));

        addNulls(playerField);
        addNulls(playerFakeField);
    }

    public String[][] getPlayerField() {
        return playerField;
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

    private boolean tryPlaceHorizontal(int boat, int row, int column) {
        for (int i = 0; i < ships.get(boat).getSize(); i++) {
            if (!playerField[row][column + i].equals("⚫")) {
                return false;
            }
        }

        for (int i = 0; i < ships.get(boat).getSize(); i++) {
            playerField[row][column + i] = ships.get(boat).getName();
        }

        return true;
    }

    private boolean tryPlaceVertical(int boat, int row, int column) {
        for (int i = 0; i < ships.get(boat).getSize(); i++) {
            if (!playerField[row + i][column].equals("⚫")) {
                return false;
            }
        }

        for (int i = 0; i < ships.get(boat).getSize(); i++) {
            playerField[row + i][column] = ships.get(boat).getName();
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

    private void addShipsAndNulls(String[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = random();
            }
        }
    }

    private void addNulls(String[][] field) {
        for (String[] strings : field) {
            Arrays.fill(strings, "⚫");
        }
    }

    private String random() {
        double probability = Math.random();

        if (probability > 0.2) {
            return "⚫";
        } else {
            int number = (int) (Math.random() * ships.size());
            return ships.get(number).getName();
        }
    }
}
