package ir.ac.kntu;

import static ir.ac.kntu.StoreProgram.makeHashie;

import java.util.ArrayList;
import java.util.Scanner;

import static ir.ac.kntu.Get.getInt;
import static ir.ac.kntu.Get.getString;
import static ir.ac.kntu.Get.getDouble;

public class AdminGameList {
    static ArrayList<Game> listOfGames = new ArrayList<>();

    public static ArrayList<Game> findGameByName(String gameName) {
        ArrayList<Game> filteredGames = new ArrayList<>();
        for (Game foundGame : listOfGames) {
            if (foundGame.getName().equals(gameName)) {
                filteredGames.add(foundGame);
            }
        }
        return filteredGames;
    }

    public AdminGameList(ArrayList<Game> listOfGames) {
        this.listOfGames = listOfGames;
    }

    //note: fix this >
    public static void removeGame(Game game) {
        for (User testUser : UserMainPage.allUsers) {
            if (!testUser.ownedGames.isEmpty()) {
                for (Game testGame : testUser.ownedGames) {
                    if (testGame == game) {
                        testUser.ownedGames.remove(game);
                        break;
                    }
                }
            }
        }
        listOfGames.remove(game);
        System.out.println("Game removed!");
    }

    public static void adminGameChangeDetails() {
        System.out.println("Enter game's name.");
        String filterName = getString();
        ArrayList<Game> filteredList = findGameByName(filterName);
        if (filteredList.isEmpty()) {
            System.out.println("No Games Matched. Enter anything to return to Admins gameList.");
            String tmp = getString();
            makeHashie();
            adminGameListMenu();
        } else {
            System.out.println("Choose a game between the filtered games:");
            showGivenListOfGames(filteredList);
            int gameChoice = getInt();
            while (gameChoice > filteredList.size() || gameChoice < 1) {
                System.out.println("Wrong input, try again:");
                gameChoice = getInt();
            }
            Game chosenGame = filteredList.get(gameChoice - 1);
            changeGameDetail(chosenGame);
        }
    }

    public static void adminGameListMenuOptions() {
        System.out.println("Admins gameList page.");
        System.out.println("1.Add a game.");
        System.out.println("2.Change a game's details.");
        System.out.println("3.Remove a game.");
        System.out.println("4.Show all of games.");
        System.out.println("5.Return.");
    }

    public static void adminGameListMenu() {
        adminGameListMenuOptions();
        String ans = getString();
        switch (ans) {
            case "1": {
                makeGame();
                adminGameListMenu();
                break;
            }
            case "2": {
                adminGameChangeDetails();
                break;
            }
            case "3": {
                System.out.println("Enter game's name.");
                String filterName = getString();
                ArrayList<Game> filteredList = findGameByName(filterName);
                if (filteredList.isEmpty()) {
                    System.out.println("No Games Matched. Enter anything to return to Admins gameList.");
                    getString();
                    adminGameListMenu();
                } else {
                    System.out.println("Choose a game between the filtered games:");
                    showGivenListOfGames(filteredList);
                    int gameChoice = getInt();
                    Game chosenGame = filteredList.get((gameChoice - 1) % filteredList.size());
                    removeGame(chosenGame);
                    adminGameListMenu();
                }
                break;
            }
            case "4": {
                showGivenListOfGames(listOfGames);
                System.out.println("Enter anything to return to Admins gameList.");
                String tmp = getString();
                makeHashie();
                adminGameListMenu();
                break;
            }
            case "5": {
                AdminMainPage.displayAdminPage();
                break;
            }
            default: {
                System.out.println("Wrong input, redirecting to start of page.");
                adminGameListMenu();
                break;
            }
        }
    }

    public static void changeGameDetailOptions() {
        System.out.println("Which detail do you want to change?");
        System.out.println("1.Name");
        System.out.println("2.Description");
        System.out.println("3.Genre");
        System.out.println("4.Price");
        System.out.println("5.Return");
        makeHashie();
    }

    public static void changeGameDetailName(Game game) {
        System.out.println("Current name: " + game.getName());
        System.out.println("Enter new name:");
        String newName = getString();
        game.setName(newName);
        System.out.println("Name changed!");
        makeHashie();
    }

    public static void changeGameDetail(Game game) {
        changeGameDetailOptions();
        int detailNumber = getInt();
        switch (detailNumber) {
            case 1: {
                changeGameDetailName(game);
                changeGameDetail(game);
                break;
            }
            case 2: {
                System.out.println("Current description: " + game.getDescription());
                System.out.println("Enter new description:");
                String newDescription = getString();
                game.setDescription(newDescription);
                System.out.println("Description changed!");
                makeHashie();
                changeGameDetail(game);
                break;
            }
            case 3: {
                System.out.println("Current genre: " + game.getGenre());
                System.out.println("Enter new genre:");
                String newGenre = getString();
                game.setGenre(newGenre);
                System.out.println("Genre changed!");
                makeHashie();
                changeGameDetail(game);
                break;
            }
            case 4: {
                System.out.println("Current price: " + game.getPrice());
                System.out.println("Enter new price:");
                double newPrice = getDouble();
                game.setPrice(newPrice);
                System.out.println("Price changed!");
                makeHashie();
                changeGameDetail(game);
                break;
            }
            case 5: {
                adminGameListMenu();
                break;
            }
            default: {
                System.out.println("Wrong input, redirecting to start of page.");
                changeGameDetail(game);
                break;
            }
        }
    }

    public static void makeGame() {
        System.out.println("Enter game's name:");
        String gameName = getString();
        System.out.println("Enter game's description");
        String gameDescription = getString();
        System.out.println("Enter game's genre");
        String gameGenre = getString();
        System.out.println("Enter game's price");
        double gamePrice = getDouble();
        Game newGame = new Game(gameName, gameDescription, gameGenre, gamePrice);
        addGame(newGame);
        System.out.println("Game added!");
        makeHashie();
    }

    public static void addGame(Game game) {
        listOfGames.add(game);
    }

    public static void showGivenListOfGames(ArrayList<Game> listOfGivenGames) {
        int gameCounter = 1;
        for (Game game : listOfGivenGames) {
            System.out.println(gameCounter + ". " + game.name + " => " + game.price + "$");
            gameCounter++;
        }
    }


}
