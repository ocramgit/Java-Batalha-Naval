import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player implements GameEntities {

    private ArrayList<Ship> shipArrayList;
    private Field field;
    private Field fakeField;
    private Scanner sc;

    public Player(Field field, Field fakeField) {
        this.field = field;
        this.fakeField = fakeField;
        this.shipArrayList = new ArrayList<>();
        this.sc = new Scanner(System.in);

        shipArrayList.add(new Ship("🚢", 4));
        shipArrayList.add(new Ship("🛳️", 3));
        shipArrayList.add(new Ship("⛴️", 2));
        shipArrayList.add(new Ship("🛥️", 1));
    }

    public Field getField() {
        return field;
    }

    public Field getFakeField() {
        return fakeField;
    }

    @Override
    public void play(int row, int column) {
        if (fakeField.getField()[row][column].equals("⬛")) {
            if (field.getField()[row][column].equals("⬛")) {
                fakeField.getField()[row][column] = "🌊";
            } else {
                fakeField.getField()[row][column] = field.getField()[row][column];
                String shipName = field.getField()[row][column];
                for (Ship ship : shipArrayList) {
                    if (ship.getName().equals(shipName)) {
                        ship.setTimesHit(ship.getTimesHit() + 1);
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

    private void explodeShips(String shipName) {
        for (int i = 0; i < field.getField().length; i++) {
            for (int j = 0; j < field.getField()[i].length; j++) {
                if (fakeField.getField()[i][j].equals(shipName)) {
                    System.out.println("You destroyed " + shipName);
                    fakeField.getField()[i][j] = "🔥";
                }
            }
            System.out.println();
        }
    }

    public void placeShips() {
        boolean isShipsPlaced = false;
        int shipsPlayerPlaced = 0;

        while (!isShipsPlaced) {
            System.out.println("Row: ");
            int row = sc.nextInt();
            System.out.println("Column: ");
            int column = sc.nextInt();
            System.out.println("V or H?");
            String direction = sc.next().toLowerCase();
            int boatCount = 0;
            for (Ship ship : shipArrayList) {
                System.out.println(boatCount + " " + ship.getName() + " | Size: " + ship.getSize());
                boatCount++;
            }

            System.out.println("What ship?");
            int ship;
            ship = sc.nextInt();

            int boatSize = 0;
            boolean isShipRight = false;

            while (!isShipRight) {
                if (ship < 4 && ship >= 0) {
                    boatSize = shipArrayList.get(ship).getSize();
                    isShipRight = true;
                } else {
                    System.out.println("The ship doesn't exist. Try again!");
                    ship = sc.nextInt();
                }
            }

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

    public void placeShipsRandom1() {
        boolean isShipsPlaced = false;
        int count = 0;
        while (!isShipsPlaced) {
            int row = randomRowAndColumn();
            int column = randomRowAndColumn();
            int ship = count++;
            int boatSize = 0;
            boolean isShipRight = false;
            while (!isShipRight) {
                if (ship < 4 && ship >= 0) {
                    boatSize = shipArrayList.get(ship).getSize();
                    isShipRight = true;
                }
            }
            boolean canPlaceShip = false;
            switch (randomDirection()) {
                case 0:
                    try {
                        canPlaceShip = true;
                        for (int i = 0; i < boatSize; i++) {
                            if (row + i >= field.getField().length || !field.getField()[row + i][column].equals("⬛")) {
                                canPlaceShip = false;
                                break;
                            }
                        }
                        if (canPlaceShip) {
                            for (int i = 0; i < boatSize; i++) {
                                field.getField()[row + i][column] = shipArrayList.get(ship).getName();
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // Não faz nada se ultrapassar os limites
                        canPlaceShip = false;
                    }
                    break;
                case 1:
                    try {
                        canPlaceShip = true;
                        for (int i = 0; i < boatSize; i++) {
                            if (column + i >= field.getField()[0].length || !field.getField()[row][column + i].equals("⬛")) {
                                canPlaceShip = false;
                                break;
                            }
                        }
                        if (canPlaceShip) {
                            for (int i = 0; i < boatSize; i++) {
                                field.getField()[row][column + i] = shipArrayList.get(ship).getName();
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // Não faz nada se ultrapassar os limites
                        canPlaceShip = false;
                    }
                    break;
            }
            if (canPlaceShip && count >= 4) {
                isShipsPlaced = true;
            }
            System.out.println();
            renderCreationGraphic();
        }
    }

    public int randomDirection() {
        return (int) (Math.random() * 2);
    }

    public int randomRowAndColumn() {
        Random random = new Random();

        return random.nextInt(9 + 1);
    }

    @Override
    public void renderGraphic() {
        for (int i = 0; i < fakeField.getField().length; i++) {
            for (int j = 0; j < fakeField.getField()[i].length; j++) {
                System.out.print("\t" + fakeField.getField()[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public void renderCreationGraphic() {
        for (int i = 0; i < field.getField().length; i++) {
            for (int j = 0; j < field.getField()[i].length; j++) {
                System.out.print("\t" + field.getField()[i][j]);
            }
            System.out.println();
        }
    }

    public void placeShipsRandom() {
        boolean isShipsPlaced = false;
        boolean shipPlaced;
        boolean placeBoat;
        int shipsPlayerPlaced = 0;
        Random random = new Random();


        int ship = 0;
        while (!isShipsPlaced) {
            int counter = 0;
            placeBoat = false;
            shipPlaced = false;
            int row = random.nextInt(9 + 1);
            int column = random.nextInt(9 + 1);
            int direction = random.nextInt(1 + 1);


            int boatSize = shipArrayList.get(ship).getSize();

            try {
                switch (direction) {
                    case 0:
                        try {
                            if (row + shipArrayList.get(ship).getSize() > 6) {
                                row = 6;
                            }

                            for (int i = 0; i < boatSize; i++) {
                                if (field.getField()[row + i][column].equals("⬛")) {
                                    counter++;
                                }
                                shipsPlayerPlaced++;
                            }
                            if (counter == shipArrayList.get(ship).getSize()) {
                                for (int i = 0; i < boatSize; i++) {
                                    if (field.getField()[row + i][column].equals("⬛")) {
                                        field.getField()[row + i][column] = shipArrayList.get(ship).getName();

                                    }
                                    shipsPlayerPlaced++;
                                }
                                shipPlaced = true;
                            }
                            if (shipPlaced) {
                                ship++;
                            }
                        } catch (Exception e) {
                            break;
                        }
                        break;

                    case 1:
                        try {
                            if (column + shipArrayList.get(ship).getSize() > 6) {
                                column = 6;
                            }

                            for (int i = 0; i < boatSize; i++) {
                                if (field.getField()[row][column + i].equals("⬛")) {
                                    counter++;
                                }
                                shipsPlayerPlaced++;
                            }
                            if (counter == shipArrayList.get(ship).getSize()) {
                                for (int i = 0; i < boatSize; i++) {
                                    if (field.getField()[row][column + i].equals("⬛")) {
                                        field.getField()[row][column + i] = shipArrayList.get(ship).getName();
                                    }
                                    shipsPlayerPlaced++;
                                }
                                shipPlaced = true;
                            }
                            if (shipPlaced) {
                                ship++;
                            }
                        } catch (Exception e) {
                            break;
                        }
                        break;
                }
                if (shipsPlayerPlaced == 10) {
                    isShipsPlaced = true;
                }
            } catch (Exception ignored) {
            }
            renderCreationGraphic();
            System.out.println();

        }
    }
}