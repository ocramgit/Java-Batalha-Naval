package me.marco;

import java.util.ArrayList;

public class Computer implements GameEntities {

    private ArrayList<Ship> shipArrayList;
    private Field field;
    private Field fakeField;

    public Computer(Field field, Field fakeField) {
        this.field = field;
        this.fakeField = fakeField;
        this.shipArrayList = new ArrayList<>();

        shipArrayList.add(new Ship("\uD83D\uDEA2", 4));
    }

    @Override
    public void play(int row, int column) {

    }

    @Override
    public void renderGraphic() {

    }

    public Field getFakeField() {
        return fakeField;
    }

    public Field getField() {
        return field;
    }

    public ArrayList<Ship> getShipArrayList() {
        return shipArrayList;
    }
}
