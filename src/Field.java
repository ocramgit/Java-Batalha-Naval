public class Field {

    private String[][] field;
    private int size;
    private int ok;

    public Field(int size) {
        this.size = size;
        field = new String[size][size];
    }

    public String[][] getField() {
        return field;
    }

    public int getSize() {
        return size;
    }
}
