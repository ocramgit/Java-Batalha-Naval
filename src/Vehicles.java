public class Vehicles {
    private String name;
    private int size;
    private int timesHit;

    public Vehicles(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public int getTimesHit() {
        return timesHit;
    }

    public void setTimesHit(int timesHit) {
        this.timesHit = timesHit;
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