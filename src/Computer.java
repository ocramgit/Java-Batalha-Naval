import java.util.ArrayList;

public class Computer implements GameEntities {
    private ArrayList<Ship> shipArrayList;
    private Field field;
    private Field fakeField;

    public Computer(Field field, Field fakeField) {
        this.field = field;
        this.fakeField = fakeField;
        this.shipArrayList = new ArrayList<>();


        shipArrayList.add(new Ship("🚢", 4, 4));
        shipArrayList.add(new Ship("🛳️", 3, 3));
        shipArrayList.add(new Ship("⛴️", 2, 2));
        shipArrayList.add(new Ship("🛥️", 1, 1));
    }

    @Override
    public void play(int row, int column) {
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

    @Override
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

    @Override
    public void renderCreationGraphic() {

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

    public Field getFakeField() {
        return fakeField;
    }

    public Field getField() {
        return field;
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

    public ArrayList<Ship> getShipArrayList() {
        return shipArrayList;
    }
}