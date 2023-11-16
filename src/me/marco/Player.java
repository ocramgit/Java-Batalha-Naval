package me.marco;

import java.util.ArrayList;
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
                    if (shipName.equals(ship.getName())) {
                        ship.setSize(ship.getSize() - 1);
                    }
                }
            }
        } else {
            System.out.println("Already played on that position!");
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
                System.out.println(boatCount + " " + ship.getName() + "| Size: " + ship.getSize());
                boatCount++;
            }

            System.out.println("What ship?");
            int ship;
            ship = sc.nextInt();

            int boatSize = 0;
            boolean isShipRight = false;

            while (!isShipRight) {
                if(ship < 4 && ship >= 0) {
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
                    }
                    break;
                case "h":
                    try {
                        for (int i = 0; i < boatSize; i++) {
                            if (field.getField()[row][column+i].equals("⬛")) {
                                field.getField()[row][column+i] = shipArrayList.get(ship).getName();
                                shipsPlayerPlaced++;
                            } else {
                                System.out.println("Can't place here. Already have a ship on this position.");
                                break;
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Can't place here. Out of bounds.");
                    }
                    break;
            }
            if (shipsPlayerPlaced == 10) isShipsPlaced = true;
            renderGraphic();
        }
    }

    @Override
    public void renderGraphic() {
        for (int i = 0; i < field.getField().length; i++) {
            for (int j = 0; j < field.getField()[i].length; j++) {
                System.out.print("\t" + field.getField()[i][j]);
            }
            System.out.println();
        }
    }
}
