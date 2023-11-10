import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameCore battleship = new GameCore();
        menu(battleship);
    }

    public static void menu(GameCore battleship) throws InterruptedException {

        Scanner sc = new Scanner(System.in);
        switch (sc.next()) {
            case "1":
                battleship.startModeBot();
                break;
            case "2":
                break;
        }
    }
}