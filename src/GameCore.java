import java.util.Scanner;

public class GameCore {

    private PlayerField playerField;
    private ComputerField computerField;
    private boolean gameOver;
    private Scanner sc;

    public GameCore() {
        playerField = new PlayerField(10, 10);
        computerField = new ComputerField(10, 10);
        gameOver = false;
        sc = new Scanner(System.in);
    }
    public void renderPlayerField() {
        for (int i = 0; i < playerField.getPlayerField().length; i++) {
            for (int j = 0; j < playerField.getPlayerField()[i].length; j++) {

                System.out.print("\t" + playerField.getPlayerField()[i][j]);
            }
            System.out.println();
        }
    }

    public void renderComputerField() {
        for (int i = 0; i < computerField.getComputerFakeField().length; i++) {
            for (int j = 0; j < computerField.getComputerFakeField()[i].length; j++) {

                System.out.print("\t" + computerField.getComputerFakeField()[i][j]);
            }
            System.out.println();
        }
    }

    public void play() throws InterruptedException {
        int count = 0;
        while(count != 5) {
            System.out.println("Que barco:");
            System.out.println("1 - \uD83D\uDEA2");
            int barco = sc.nextInt();

            System.out.println("Qual linha?");
            int row = sc.nextInt();

            System.out.println("Onde começar?");
            int column = sc.nextInt();

            System.out.println("Posição: (H) ou (V)");

            switch (sc.next()) {
                case "H":
                    for (int i = 0; i < playerField.getShips().get(barco).getSize(); i++) {
                        playerField.getPlayerField()[row][column + i] = playerField.getShips().get(barco).getName();
                    }
                    renderPlayerField();
                    break;
                case "V":
                    for (int i = 0; i < playerField.getShips().get(barco).getSize(); i++) {
                        playerField.getPlayerField()[row + i][column] = playerField.getShips().get(barco).getName();
                    }
                    renderPlayerField();
                    break;
            }
            count++;
        }


        while(!gameOver) {
            System.out.println("COMPUTER PLAY:");
            computerPlay();
            renderComputerField();
            System.out.println("===============");
            System.out.println("PLAYER PLAY:");
            playerPlay();
            renderPlayerField();
            Thread.sleep(2000);
        }
    }

    private void computerPlay() {

        int row = getRandomComputerPlay();
        int column = getRandomComputerPlay();

        System.out.println("ROW: " + row + " COLUMN: "+column);

            if (computerField.haveAnyShipOnComputerField(row, column)) {
                computerField.insertOnComputerFakeField(row, column, "🌊");
                System.out.println("Misses");
            } else {
                computerField.getComputerFakeField()[row][column] = computerField.getComputerField()[row][column];
                System.out.println("Right");
            }
    }

    private void playerPlay() {
        System.out.print("Letter: ");
        int row = translateLetter(sc.next());
        System.out.print("Number: ");
        int column = sc.nextInt();

        if(playerField.haveAnyShipOnPlayerField(row, column)) {
            playerField.insertOnPlayerFakeField(row, column, "🌊");
            System.out.println("Misses");
        } else {
            playerField.insertOnPlayerFakeField(row, column, playerField.getPlayerField()[row][column]);
            System.out.println("Right");
        }
    }

    private int translateLetter(String message) {
        switch (message) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            case "E":
                return 4;
            case "F":
                return 5;
        }

        return 0;
    }

    public int getRandomComputerPlay() {
        return (int) (Math.random() * 6);
    }
}
