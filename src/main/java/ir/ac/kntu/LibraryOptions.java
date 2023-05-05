package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

import static ir.ac.kntu.Colors.green;
import static ir.ac.kntu.Colors.reset;
import static ir.ac.kntu.Get.*;

public class LibraryOptions {

    public static double getMinPrice() {
        System.out.println("Enter the min price:");
        double minRange = getDouble();
        return minRange;
    }

    public static double getMaxPrice() {
        System.out.println("Enter the max price:");
        double maxRange = getDouble();
        return maxRange;
    }

    public static String libraryMenuList() {
        System.out.println("Welcome to Library.");
        System.out.println("1.Show all of games.");
        System.out.println("2.Search by name.");
        System.out.println("3.Search by price range.");
        System.out.println("4.Return.");
        String ans = getString();
        return ans;
    }

    public static void communityOpt(Game game, User user){
        System.out.println("Enter choice:");
        System.out.println("1.Add a review.");
        System.out.println("2.See all reviews.");
        System.out.println("3.Return");
        String ans = getString();
        switch (ans){
            case "1":{
                System.out.println("Enter your review of "+game.getName());
                String review = getString();
                game.addReview(review,user);
                System.out.println("Press anything to go back.");
                getString();
                communityOpt(game,user);
                break;
            }
            case "2":{
                game.showReviews();
                System.out.println("Press anything to go back.");
                getString();
                communityOpt(game,user);
                break;
            }
            case "3":{
                gameCommunityAndRate(game,user);
                break;
            }
            default:{
                System.out.println("Redirecting to start of page.");
                communityOpt(game,user);
                break;
            }
        }
    }

    public static void rateOpt(Game game, User user){
        System.out.println(game.getName() +"'s current rate: "+ game.getAvgRate());
        System.out.println("Enter rate:");
        double newRate = getDouble();
        while(newRate < 0 || newRate>10){
            System.out.println("Enter a number between 0 and 10!");
            newRate =getDouble();
        }
        game.addRate(user,newRate);
        game.updateRate();
        System.out.println("new Rate: "+ game.getAvgRate());
    }

    public static void gameCommunityAndRate(Game game, User user){
        System.out.println("Enter choice:");
        System.out.println("1.Community");
        System.out.println("2.Rate");
        System.out.println("3.Return");
        String ans = getString();
        switch (ans){
            case "1":{
                communityOpt(game, user);
                break;
            }
            case "2":{
                rateOpt(game, user);
                break;
            }
            case "3":{
                libraryMenu(user);
                break;
            }
            default:{
                System.out.println("Wrong input, redirecting to start of page.");
                gameCommunityAndRate(game,user);
                break;
            }
        }
    }

    public static void libraryMenuAllGames(User user){
        if (user.ownedGames.isEmpty()) {
            System.out.println("No games matched, try again.");
            libraryMenu(user);
        } else {
            Game chosenGame = chooseGame(user.ownedGames, user);
            chosenGame.showLibraryGameDetails(user);
            libraryMenu(user);
        }
    }

    public static void libraryMenu(User user) {
        String ans = libraryMenuList();
        switch (ans) {
            case "1": {
                libraryMenuAllGames(user);
                break;
            }
            case "2": {
                System.out.println("Enter the starting name:");
                String name = getString();
                ArrayList<Game> foundByName = searchByName(name, user);
                if (foundByName.isEmpty()) {
                    System.out.println("No games matched, try again.");
                    libraryMenu(user);
                } else {
                    Game chosenGame = chooseGame(foundByName, user);
                    chosenGame.showLibraryGameDetails(user);
                    libraryMenu(user);
                }
                break;
            }
            case "3": {
                double minRange = getMinPrice();
                double maxRange = getMaxPrice();
                ArrayList<Game> foundByPrice = searchByPrice(minRange, maxRange, user);
                if (foundByPrice.isEmpty()) {
                    System.out.println("No games matched, try again.");
                    libraryMenu(user);
                } else {
                    Game chosenGame = chooseGame(foundByPrice, user);
                    chosenGame.showLibraryGameDetails(user);
                    libraryMenu(user);
                }
                break;
            }
            case "4": {
                UserLoggedInPage.showUserLoggedInMenu(user);
                break;

            }
            default: {
                redirectToLibraryMenu(user);
                break;
            }
        }
    }

    public static void redirectToLibraryMenu(User user) {
        System.out.println("Wrong input redirecting to start of page.");
        libraryMenu(user);
    }

    public static void showLibraryGames(ArrayList<Game> listOfGivenGames, User user) {
        int gameCounter = 1;
        System.out.println("Games:");
        for (Game game : listOfGivenGames) {
            System.out.print(gameCounter + ". " + game.name + " => " + game.price + "$");
            if (user.doesUserOwn(game)) {
                System.out.print(green + " Owned." + reset);
            }
            System.out.println(" ");
            gameCounter++;
        }
    }

    public static Game chooseGame(ArrayList<Game> finalGameList, User user) {
        showLibraryGames(finalGameList,user);
        int ans = getInt();
        while ((ans > finalGameList.size() || ans < 1)) {
            System.out.println("Wrong input, try again:");
            ans = getInt();
        }
        return (finalGameList.get(ans - 1));
    }

    public static ArrayList<Game> searchByName(String searchName, User user) {
        ArrayList<Game> filteredGameByName = new ArrayList<>();
        for (Game testGame : user.ownedGames) {
            if (testGame.getName().startsWith(searchName)) {
                filteredGameByName.add(testGame);
            }
        }
        return filteredGameByName;
    }


    public static ArrayList<Game> searchByPrice(double minPrice, double maxPrice, User user) {
        System.out.println("Choose the game you want to view:");
        ArrayList<Game> filteredGameByPrice = new ArrayList<>();
        for (Game testGame : user.ownedGames) {
            if (testGame.getPrice() >= minPrice && testGame.getPrice() <= maxPrice) {
                filteredGameByPrice.add(testGame);
            }
        }
        return filteredGameByPrice;
    }

}
