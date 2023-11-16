
public class Field {
    private String[][] field;

    public Field(int size) {
        field = new String[size][size];
    }

    public String[][] getField() {
        return field;
    }
}
