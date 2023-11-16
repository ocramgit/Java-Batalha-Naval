package me.marco;

public class Vehicles {
    private String name;
    private int size;
    private boolean isDestroyed;

    public Vehicles(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
