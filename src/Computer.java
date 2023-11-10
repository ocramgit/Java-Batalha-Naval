public class Computer {

    private int random = randomPlay();

    public int randomPlay(){
        return (int) (Math.random() * (6 + 1));
    }

    public int getRandom(int length) {
        return random;
    }
}
