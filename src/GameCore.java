import java.util.Scanner;

public class GameCore {

    private PlayerField playerField;
    private ComputerField computerField;
    private boolean gameOver;
    private Scanner sc;
    private int computerBoats = 5;
    private int playerBoats = 5;
    private int playerTwoBoats = 5;
    private int computerTwoBoats = 5;

    public GameCore() {
        playerField = new PlayerField(10, 10);
        computerField = new ComputerField(10, 10);
        gameOver = false;
        sc = new Scanner(System.in);
    }

    public void renderPlayerField() {
        System.out.println("\u001b[31mFIELD PLAYER 1");
        System.out.print("\t");
        for (int j = 0; j < playerField.getPlayerFakeField()[0].length; j++) {
            System.out.print(("\t" + j));
        }
        System.out.println();

        for (int i = 0; i < playerField.getPlayerFakeField().length; i++) {
            System.out.print("\t" + (char) ('A' + i));

            for (int j = 0; j < playerField.getPlayerFakeField()[i].length; j++) {
                System.out.print("\t" + playerField.getPlayerFakeField()[i][j]);
            }
            System.out.println();
        }

        System.out.print("\tx" + playerBoat0 + " " + playerField.getShips().get(0).getName() + " | x" + playerBoat1 + " " + playerField.getShips().get(1).getName() + " " + " | x" + playerBoat2 + " " + playerField.getShips().get(2).getName() + " " + " | x" + playerBoat3 + " " + playerField.getShips().get(3).getName() + " " + " | x" + playerBoat4 + " " + playerField.getShips().get(4).getName() + "\n");
        System.out.println("\n");
    }

    public void renderComputerField() {
        System.out.println("\u001b[34mFIELD COMPUTADOR");
        System.out.print("\t");
        for (int j = 0; j < computerField.getComputerFakeField()[0].length; j++) {
            System.out.print(("\t" + j));
        }
        System.out.println();

        for (int i = 0; i < computerField.getComputerFakeField().length; i++) {
            System.out.print("\t" + i);

            for (int j = 0; j < computerField.getComputerFakeField()[i].length; j++) {
                System.out.print("\t" + computerField.getComputerFakeField()[i][j]);
            }
            System.out.println();
        }
        System.out.print("\tx" + computerBoat0 + " " + computerField.getShips().get(0).getName() + " | x" + computerBoat1 + " " + computerField.getShips().get(1).getName() + " " + " | x" + computerBoat2 + " " + computerField.getShips().get(2).getName() + " " + " | x" + computerBoat3 + " " + computerField.getShips().get(3).getName() + " " + " | x" + computerBoat4 + " " + computerField.getShips().get(4).getName() + "\n");
        System.out.println("\n\n");
    }

    private int computerBoat0 = 3;
    private int computerBoat1 = 4;
    private int computerBoat2 = 2;
    private int computerBoat3 = 1;
    private int computerBoat4 = 5;

    private void computerPlay() {
        int row = getRandomComputerPlay();
        int column = getRandomComputerPlay();

        System.out.println("\u001b[37mROW: " + row + " COLUMN: " + column + "\u001b[0m\n");

        if (computerField.haveAnyShipOnComputerField(row, column)) {
            computerField.insertOnComputerFakeField(row, column, "🌊");
        } else {
            computerField.getComputerFakeField()[row][column] = computerField.getComputerField()[row][column];
            String boatName = computerField.getComputerField()[row][column];
            System.out.println("Hit! " + boatName);

            if (boatName.equals("🚢")) {
                computerBoat0--;
                if (computerBoat0 == 0) {
                    for (int i = 0; i < computerField.getRows(); i++) {
                        for (int j = 0; j < computerField.getColumns(); j++) {
                            if (computerField.getComputerField()[i][j].equals("🚢")) {
                                computerField.getComputerFakeField()[i][j] = "💥";
                            }
                        }
                    }
                    System.out.println("🚢 DESTRUIDO");
                    computerBoats--;
                }
            }

            if (boatName.equals("⛵")) {
                computerBoat1--;
                if (computerBoat1 == 0) {
                    for (int i = 0; i < computerField.getRows(); i++) {
                        for (int j = 0; j < computerField.getColumns(); j++) {
                            if (computerField.getComputerField()[i][j].equals("⛵")) {
                                computerField.getComputerFakeField()[i][j] = "💥";
                            }
                        }
                    }
                    System.out.println("⛵ DESTRUIDO");
                    computerBoats--;
                }
            }

            if (boatName.equals("\uD83D\uDEE5\uFE0F")) {
                computerBoat2--;
                if (computerBoat2 == 0) {
                    for (int i = 0; i < computerField.getRows(); i++) {
                        for (int j = 0; j < computerField.getColumns(); j++) {
                            if (computerField.getComputerField()[i][j].equals("\uD83D\uDEE5\uFE0F")) {
                                computerField.getComputerFakeField()[i][j] = "💥";
                            }
                        }
                    }
                    System.out.println("\uD83D\uDEE5\uFE0F DESTRUIDO");
                    computerBoats--;
                }
            }
            if (boatName.equals("🚣")) {
                computerBoat3--;
                if (computerBoat3 == 0) {
                    for (int i = 0; i < computerField.getRows(); i++) {
                        for (int j = 0; j < computerField.getColumns(); j++) {
                            if (computerField.getComputerField()[i][j].equals("🚣")) {
                                computerField.getComputerFakeField()[i][j] = "💥";
                            }
                        }
                    }
                    System.out.println("🚣 DESTRUIDO");
                    computerBoats--;
                }
            }
            if (boatName.equals("\uD83D\uDEA4")) {
                computerBoat4--;
                if (computerBoat4 == 0) {
                    for (int i = 0; i < computerField.getRows(); i++) {
                        for (int j = 0; j < computerField.getColumns(); j++) {
                            if (computerField.getComputerField()[i][j].equals("\uD83D\uDEA4")) {
                                computerField.getComputerFakeField()[i][j] = "💥";
                            }
                        }
                    }
                    System.out.println("\uD83D\uDEA4 DESTRUIDO");
                    computerBoats--;
                }
            }
        }
    }

    public void startPlayerVsComputer() throws InterruptedException {
        System.out.println("Creating fields...");
        Thread.sleep(2000);
        playerField.placeBoatsOnField(playerField.getPlayerField());
        computerField.placeBoatsOnField();
        System.out.println("Done!");
        Thread.sleep(1000);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        while (!gameOver) {
            renderPlayerField();
            System.out.println("PLAYER PLAY:");
            playerPlay();
            renderPlayerField();
            System.out.println("\n");
            System.out.println("\u001b[37mCOMPUTER PLAYING...\u001b[0m");
            Thread.sleep(2000);
            computerPlay();
            renderComputerField();
        }
    }

    private int playerBoat0 = 3;
    private int playerBoat1 = 4;
    private int playerBoat2 = 2;
    private int playerBoat3 = 1;
    private int playerBoat4 = 5;

    private void playerPlay() {
        System.out.print("Letter: ");
        int row = translateLetter(sc.next().toLowerCase());
        System.out.print("Number: ");
        int column = sc.nextInt();

        System.out.println("\u001b[37mROW: " + row + " COLUMN: " + column + "\u001b[0m\n");

        if (playerField.haveAnyShipOnPlayerField(row, column)) {
            playerField.insertOnPlayerFakeField(row, column, "🌊");
        } else {
            playerField.getPlayerFakeField()[row][column] = playerField.getPlayerField()[row][column];
            String boatName = playerField.getPlayerField()[row][column];
            System.out.println("Hit! " + boatName);

            if (boatName.equals("🚢")) {
                playerBoat0--;
                System.out.println(playerBoat0);
                if (playerBoat0 == 0) {
                    for (int i = 0; i < playerField.getRows(); i++) {
                        for (int j = 0; j < playerField.getColumns(); j++) {
                            if (playerField.getPlayerField()[i][j].equals("🚢")) {
                                playerField.getPlayerFakeField()[i][j] = "💥";
                            }
                        }
                    }
                    System.out.println("🚢 DESTRUIDO");
                    playerBoats--;
                }
            }

            if (boatName.equals("⛵")) {
                playerBoat1--;
                System.out.println(playerBoat1);
                if (playerBoat1 == 0) {
                    for (int i = 0; i < playerField.getRows(); i++) {
                        for (int j = 0; j < playerField.getColumns(); j++) {
                            if (playerField.getPlayerField()[i][j].equals("⛵")) {
                                playerField.getPlayerFakeField()[i][j] = "💥";
                            }
                        }
                    }
                    System.out.println("⛵ DESTRUIDO");
                    playerBoats--;
                }
            }

            if (boatName.equals("\uD83D\uDEE5\uFE0F")) {
                playerBoat2--;
                System.out.println(playerBoat2);
                if (playerBoat2 == 0) {
                    for (int i = 0; i < playerField.getRows(); i++) {
                        for (int j = 0; j < playerField.getColumns(); j++) {
                            if (playerField.getPlayerField()[i][j].equals("\uD83D\uDEE5\uFE0F")) {
                                playerField.getPlayerFakeField()[i][j] = "💥";
                            }
                        }
                    }
                    System.out.println("\uD83D\uDEE5\uFE0F DESTRUIDO");
                    playerBoats--;
                }
            }
            if (boatName.equals("🚣")) {
                playerBoat3--;
                if (playerBoat3 == 0) {
                    for (int i = 0; i < playerField.getRows(); i++) {
                        for (int j = 0; j < playerField.getColumns(); j++) {
                            if (playerField.getPlayerField()[i][j].equals("🚣")) {
                                playerField.getPlayerFakeField()[i][j] = "💥";
                            }
                        }
                    }
                    System.out.println("🚣 DESTRUIDO");
                    playerBoats--;
                }
            }
            if (boatName.equals("\uD83D\uDEA4")) {
                playerBoat4--;
                if (playerBoat4 == 0) {
                    for (int i = 0; i < playerField.getRows(); i++) {
                        for (int j = 0; j < playerField.getColumns(); j++) {
                            if (playerField.getPlayerField()[i][j].equals("\uD83D\uDEA4")) {
                                playerField.getPlayerFakeField()[i][j] = "💥";
                            }
                        }
                    }
                    System.out.println("\uD83D\uDEA4 DESTRUIDO");
                    playerBoats--;
                }
            }
        }
    }

    private int playerTwoBoat0 = 3;
    private int playerTwoBoat1 = 4;
    private int playerTwoBoat2 = 2;
    private int playerTwoBoat3 = 1;
    private int playerTwoBoat4 = 5;
    private void playerTwoPlay() {
        System.out.print("Letter: ");
        int row = translateLetter(sc.next().toLowerCase());
        System.out.print("Number: ");
        int column = sc.nextInt();

        System.out.println("\u001b[37mROW: " + row + " COLUMN: " + column + "\u001b[0m\n");

        if (playerField.haveAnyShipOnPlayerTwoField(row, column)) {
            playerField.insertOnPlayerTwoFakeField(row, column, "🌊");
        } else {
            playerField.getPlayerTwoFakeField()[row][column] = playerField.getPlayerTwoField()[row][column];
            String boatName = playerField.getPlayerTwoField()[row][column];
            System.out.println("Hit! " + boatName);

            if (boatName.equals("🚢")) {
                playerTwoBoat0--;
                System.out.println(playerTwoBoat0);
                if (playerTwoBoat0 == 0) {
                    for (int i = 0; i < playerField.getRows(); i++) {
                        for (int j = 0; j < playerField.getColumns(); j++) {
                            if (playerField.getPlayerTwoField()[i][j].equals("🚢")) {
                                playerField.getPlayerTwoFakeField()[i][j] = "💥";
                            }
                        }
                    }
                    System.out.println("🚢 DESTRUIDO");
                    playerTwoBoats--;
                }
            }

            if (boatName.equals("⛵")) {
                playerTwoBoat1--;
                System.out.println(playerTwoBoat1);
                if (playerTwoBoat1 == 0) {
                    for (int i = 0; i < playerField.getRows(); i++) {
                        for (int j = 0; j < playerField.getColumns(); j++) {
                            if (playerField.getPlayerTwoField()[i][j].equals("⛵")) {
                                playerField.getPlayerTwoFakeField()[i][j] = "💥";
                            }
                        }
                    }
                    System.out.println("⛵ DESTRUIDO");
                    playerBoats--;
                }
            }

            if (boatName.equals("\uD83D\uDEE5\uFE0F")) {
                playerTwoBoat2--;
                System.out.println(playerTwoBoat2);
                if (playerTwoBoat2 == 0) {
                    for (int i = 0; i < playerField.getRows(); i++) {
                        for (int j = 0; j < playerField.getColumns(); j++) {
                            if (playerField.getPlayerTwoField()[i][j].equals("\uD83D\uDEE5\uFE0F")) {
                                playerField.getPlayerTwoFakeField()[i][j] = "💥";
                            }
                        }
                    }
                    System.out.println("\uD83D\uDEE5\uFE0F DESTRUIDO");
                    playerTwoBoats--;
                }
            }
            if (boatName.equals("🚣")) {
                playerTwoBoat3--;
                if (playerTwoBoat3 == 0) {
                    for (int i = 0; i < playerField.getRows(); i++) {
                        for (int j = 0; j < playerField.getColumns(); j++) {
                            if (playerField.getPlayerTwoField()[i][j].equals("🚣")) {
                                playerField.getPlayerTwoFakeField()[i][j] = "💥";
                            }
                        }
                    }
                    System.out.println("🚣 DESTRUIDO");
                    playerTwoBoats--;
                }
            }
            if (boatName.equals("\uD83D\uDEA4")) {
                playerTwoBoat4--;
                if (playerTwoBoat4 == 0) {
                    for (int i = 0; i < playerField.getRows(); i++) {
                        for (int j = 0; j < playerField.getColumns(); j++) {
                            if (playerField.getPlayerTwoField()[i][j].equals("\uD83D\uDEA4")) {
                                playerField.getPlayerTwoFakeField()[i][j] = "💥";
                            }
                        }
                    }
                    System.out.println("\uD83D\uDEA4 DESTRUIDO");
                    playerTwoBoats--;
                }
            }
        }
    }

    private int translateLetter(String message) {
        switch (message) {
            case "a":
                return 0;
            case "b":
                return 1;
            case "c":
                return 2;
            case "d":
                return 3;
            case "e":
                return 4;
            case "f":
                return 5;
            case "g":
                return 6;
            case "h":
                return 7;
            case "i":
                return 8;
            case "j":
                return 9;
        }

        return 0;
    }

    public void renderPlayerTwoField() {
        System.out.println("\u001b[34mFIELD PLAYER 2");
        System.out.print("\t");
        for (int j = 0; j < playerField.getPlayerTwoFakeField()[0].length; j++) {
            System.out.print(("\t" + j));
        }
        System.out.println();

        for (int i = 0; i < playerField.getPlayerTwoFakeField().length; i++) {
            System.out.print("\t" + (char) ('A' + i));

            for (int j = 0; j < playerField.getPlayerTwoFakeField()[i].length; j++) {
                System.out.print("\t" + playerField.getPlayerTwoFakeField()[i][j]);
            }
            System.out.println();
        }

        System.out.print("\tx" + playerTwoBoat0 + " " + playerField.getShips().get(0).getName() + " | x" + playerTwoBoat1 + " " + playerField.getShips().get(1).getName() + " " + " | x" + playerTwoBoat2 + " " + playerField.getShips().get(2).getName() + " " + " | x" + playerTwoBoat3 + " " + playerField.getShips().get(3).getName() + " " + " | x" + playerTwoBoat4 + " " + playerField.getShips().get(4).getName() + "\n");
        System.out.println("\n");
    }

    public void startPlayer() throws InterruptedException {
        System.out.println("Creating fields...");
        Thread.sleep(2000);
        playerField.placeBoatsOnField(playerField.getPlayerField());
        playerField.placeBoatsOnField(playerField.getPlayerTwoField());
        System.out.println("Done!!!!");
        Thread.sleep(1000);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        while(!gameOver){
            renderPlayerField();
            System.out.println("\u001b[31mPlayer 1 turn: ");
            playerPlay();
            renderPlayerField();
            System.out.println("\n");
            System.out.println("\u001b[34mPlayer 2 turn: ");
            Thread.sleep(2000);
            playerTwoPlay();
            renderPlayerTwoField();
            System.out.println("\n");
        }

    }
    public int getRandomComputerPlay() {
        return (int) (Math.random() * 10);
    }
}