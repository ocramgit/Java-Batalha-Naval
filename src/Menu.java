import java.util.Scanner;

public class Menu {
    GameCore gameCore = new GameCore();
    Scanner sc = new Scanner(System.in);
    public void init() throws InterruptedException {
        System.out.println("Select game mode:");
        System.out.println("1 - Player x Player");
        System.out.println("2 - Computer x Player");
        System.out.println("3 - CAOS MODE");
        System.out.println("4 - Exit");

        switch (sc.next()) {
            case "1":
                break;
            case "2":
                System.out.println("Options: ");
                System.out.println("1 - Random fields");
                System.out.println("2 - Select my field");
                System.out.println("3 - Exit");

                selectOption(sc.next());
                break;
        }
    }

    private void selectOption(String next) throws InterruptedException {
        switch (next) {
            case "1":
                gameCore.startPlayerVsComputer();
        }
    }
}