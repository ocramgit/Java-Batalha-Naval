import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player implements GameEntities {

    private ArrayList<Ship> shipArrayList;
    private Field field;
    private Field fakeField;
    private Scanner sc;
    private int explodedShips = 0;

    public Player(Field field, Field fakeField) {
        this.field = field;
        this.fakeField = fakeField;
        this.shipArrayList = new ArrayList<>();
        this.sc = new Scanner(System.in);

        shipArrayList.add(new Ship("🚢", 4, 4));
        shipArrayList.add(new Ship("🛳️", 3, 3));
        shipArrayList.add(new Ship("⛴️", 2, 2));
        shipArrayList.add(new Ship("🛥️", 1, 1));
    }

    public Field getField() {
        return field;
    }

    public Field getFakeField() {
        return fakeField;
    }

    @Override
    public void play(int row, int column) throws InterruptedException {
        if (fakeField.getField()[row][column].equals("⬛")) {
            if (field.getField()[row][column].equals("⬛")) {
                fakeField.getField()[row][column] = "🌊";
                Music water = new Music("/water.wav");
                water.play();
                water.stop();
            } else {
                fakeField.getField()[row][column] = field.getField()[row][column];
                String shipName = field.getField()[row][column];
                Music explosion = new Music("/explosion.wav");
                explosion.play();
                explosion.stop();
                for (Ship ship : shipArrayList) {
                    if (ship.getName().equals(shipName)) {
                        if(ship.getName().equals("\uD83D\uDD25")) {
                            explodedShips++;
                        }

                        ship.setTimesHit(ship.getTimesHit() + 1);
                        ship.setSizeChange(ship.getSizeChange()-1);
                        if (ship.getTimesHit() >= ship.getSize()) {
                            explodeShips(shipName);
                        }
                    }
                }
            }
        } else {
            System.out.println("Already played on that position!");
        }
    }

    public int getExplodedShips() {
        return explodedShips;
    }

    private void explodeShips(String shipName) {
        for (int i = 0; i < field.getField().length; i++) {
            for (int j = 0; j < field.getField()[i].length; j++) {
                if(fakeField.getField()[i][j].equals(shipName)) {
                    fakeField.getField()[i][j] = "🔥";
                }
            }
            System.out.println();
        }
    }

    public void placeShips() {
        boolean isShipsPlaced = false;
        int shipsPlayerPlaced = 0;
        int ship = 0;

        while (!isShipsPlaced) {

            int boatSize = shipArrayList.get(ship).getSize();

            System.out.println("\nPut this ship on the field: " + shipArrayList.get(ship).getName() + " occupates "+shipArrayList.get(ship).getSize() + " spaces.");

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

            String direction;
            do {
                System.out.print("\n\u001b[44;1mVertical (V) or Horizontal (H)\u001b[0m ");
                direction = sc.next().toLowerCase();

            } while (!direction.equals("v") && !direction.equals("h"));

            switch (direction) {
                case "v":
                    try {
                        for (int i = 0; i < boatSize; i++) {
                            if (row + boatSize > field.getField().length) {
                                System.out.println("Can't place here. Out of bounds.");
                                break;
                            }
                            if (field.getField()[row + i][column].equals("⬛")) {
                                field.getField()[row + i][column] = shipArrayList.get(ship).getName();
                                shipsPlayerPlaced++;
                            } else {
                                System.out.println("Can't place here. Already have a ship on this position.");
                                break;
                            }
                        }
                        ship++;
                    } catch (Exception e) {
                        System.out.println("Can't place here. Out of bounds.");
                        break;
                    }
                    break;
                case "h":
                    try {
                        for (int i = 0; i < boatSize; i++) {
                            if (row + boatSize > field.getField().length) {
                                System.out.println("Can't place here. Out of bounds.");
                                break;
                            }

                            if (field.getField()[row][column + i].equals("⬛")) {
                                field.getField()[row][column + i] = shipArrayList.get(ship).getName();
                                shipsPlayerPlaced++;
                            } else {
                                System.out.println("Can't place here. Already have a ship on this position.");
                                break;
                            }
                        }
                        ship++;
                    } catch (Exception e) {
                        System.out.println("Can't place here. Out of bounds.");
                        break;
                    }
                    break;
            }
            if (shipsPlayerPlaced >= 10) isShipsPlaced = true;
            renderCreationGraphic();
        }
    }

    public void render() {
        System.out.print("\t");
        for (int i = 0; i < fakeField.getField().length; i++) {
            System.out.print("\u001b[1m\u001b[31m"+i + "\t");
        }
        System.out.println();

        for (int i = 0; i < fakeField.getField().length; i++) {
            System.out.print("\u001b[1m\u001b[31m"+i + "\t");

            for (int j = 0; j < fakeField.getField()[i].length; j++) {
                System.out.print(fakeField.getField()[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.print("\t\t\u001b[31m x" + shipArrayList.get(0).getSizeChange() + " " + shipArrayList.get(0).getName() + "| x" + shipArrayList.get(1).getSizeChange() + " " + shipArrayList.get(1).getName() + " " + "| x" + shipArrayList.get(2).getSizeChange() + " " + shipArrayList.get(2).getName() + " " + "| x" + shipArrayList.get(3).getSizeChange() + " " + shipArrayList.get(3).getName() + "\u001b[0m \n");
        System.out.println();
    }


    public void renderCreationGraphic() {
        System.out.print("\t");
        for (int i = 0; i < field.getField().length; i++) {
            System.out.print("\u001b[1m\u001b[31m"+i + "\t");
        }
        System.out.println();

        for (int i = 0; i < field.getField().length; i++) {
            System.out.print("\u001b[1m\u001b[31m"+i + "\t");

            for (int j = 0; j < field.getField()[i].length; j++) {
                System.out.print(field.getField()[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.print("\t\t\u001b[31m x" + shipArrayList.get(0).getSizeChange() + " " + shipArrayList.get(0).getName() + "| x" + shipArrayList.get(1).getSizeChange() + " " + shipArrayList.get(1).getName() + " " + "| x" + shipArrayList.get(2).getSizeChange() + " " + shipArrayList.get(2).getName() + " " + "| x" + shipArrayList.get(3).getSizeChange() + " " + shipArrayList.get(3).getName() + "\u001b[0m \n");
        System.out.println();
    }

    public void placeRandomShips() {
        for (Ship ship : shipArrayList) {
            boolean shipPlaced = false;

            while (!shipPlaced) {
                int row = randomRowAndColumns();
                int column = randomRowAndColumns();
                int direction = direction();

                try {
                    switch (direction) {
                        case 0:
                            for (int i = 0; i < ship.getSize(); i++) {
                                if (row + i >= field.getField().length || !field.getField()[row + i][column].equals("⬛")) {
                                    throw new Exception("Invalid placement");
                                }
                            }

                            for (int i = 0; i < ship.getSize(); i++) {
                                field.getField()[row + i][column] = ship.getName();
                            }

                            shipPlaced = true;
                            break;

                        case 1:
                            for (int i = 0; i < ship.getSize(); i++) {
                                if (column + i >= field.getField()[row].length || !field.getField()[row][column + i].equals("⬛")) {
                                    throw new Exception("Invalid placement");
                                }
                            }

                            for (int i = 0; i < ship.getSize(); i++) {
                                field.getField()[row][column + i] = ship.getName();
                            }

                            shipPlaced = true;
                            break;
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public int randomRowAndColumns() {
        return (int) (Math.random() * 10);
    }

    public int direction() {
        return (int) (Math.random() * 2);
    }
}