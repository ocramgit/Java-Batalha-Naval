import java.util.Scanner;

public class Menu {
    Scanner scan = new Scanner(System.in);
    GameCore gameCore = new GameCore();
    public void init(){
        System.out.println("1- Player vs Player");
        switch (scan.next()){
            case "1":
                openPvpMode();
                break;
        }
    }

    private void openPvpMode() {
        System.out.println("1- Random Field");
        System.out.println("2- Place Field");
        switch (scan.next()){
            case "1":
                gameCore.initPVPRandom();
                break;
            case "2":
                gameCore.initPVPManual();
                break;
        }
    }
}
