package ir.ac.kntu;

import java.util.ArrayList;

import static ir.ac.kntu.Colors.green;
import static ir.ac.kntu.Colors.reset;
import static ir.ac.kntu.Get.getString;

import static ir.ac.kntu.Get.getInt;

import static ir.ac.kntu.Get.getDouble;

public class StoreOptions {
    public static void storeMenu(User user){
        System.out.println("Welcome to store.");
        System.out.println("1.Show all of games.");
        System.out.println("2.Search by name.");
        System.out.println("3.Search by price range.");
        System.out.println("4.Return.");
        String ans = getString();
        switch (ans){
            case "1":{
                if(AdminGameList.listOfGames.isEmpty()){
                    System.out.println("No games matched, try again.");
                    storeMenu(user);
                }
                else {
                    Game chosenGame = chooseGame(AdminGameList.listOfGames,user);
                    chosenGame.showGameDetails(user);
                }
                break;
            }
            case "2":{
                System.out.println("Enter the starting name:");
                String name = getString();
                ArrayList <Game> foundByName = searchByName(name);
                if(foundByName.isEmpty()){
                    System.out.println("No games matched, try again.");
                    storeMenu(user);
                }
                else {
                    Game chosenGame = chooseGame(foundByName,user);
                    chosenGame.showGameDetails(user);
                }
                break;
            }
            case "3":{
                System.out.println("Enter the min price:");
                double minRange = getDouble();
                System.out.println("Enter the max price:");
                double maxRange = getDouble();
                ArrayList <Game> foundByPrice = searchByPrice(minRange,maxRange);
                if(foundByPrice.isEmpty()){
                    System.out.println("No games matched, try again.");
                    storeMenu(user);
                }
                else {
                    Game chosenGame = chooseGame(foundByPrice,user);
                    chosenGame.showGameDetails(user);
                }
                break;
            }
            case "4":{
                UserLoggedInPage.showUserLoggedInMenu(user);
                break;

            }
            default:{
                System.out.println("Wrong input redirecting to start of page.");
                storeMenu(user);
                break;
            }
        }
    }

    public static Game chooseGame(ArrayList<Game> finalGameList,User user){
        showStoreGames(finalGameList,user);
        int ans = getInt();
        while((ans> finalGameList.size() || ans<1)){
            System.out.println("Wrong input, try again:");
            ans = getInt();
        }
        return (finalGameList.get(ans -1));
    }

    public static ArrayList<Game> searchByName(String searchName){
        ArrayList<Game> filteredGameByName = new ArrayList<>();
        for(Game testGame : AdminGameList.listOfGames){
            if (testGame.getName().startsWith(searchName)){
                filteredGameByName.add(testGame);
            }
        }
        return  filteredGameByName;
    }



    public static ArrayList<Game> searchByPrice(double minPrice, double maxPrice){
        System.out.println("Choose the game you want to view:");
        ArrayList<Game> filteredGameByPrice = new ArrayList<>();
        for(Game testGame : AdminGameList.listOfGames){
            if( testGame.getPrice() >= minPrice && testGame.getPrice() <= maxPrice){
                filteredGameByPrice.add(testGame);
            }
        }
        return  filteredGameByPrice;
    }

    public static void showStoreGames(ArrayList<Game> listOfGivenGames, User user) {
        int gameCounter = 1;
        System.out.println("Games:");
        for (Game game : listOfGivenGames) {
            System.out.print(gameCounter + ". " + game.name + " => " + game.price + "$");
            if(user.doesUserOwn(game)){
                System.out.print(green +" Owned." + reset);
            }
            System.out.println(" ");
            gameCounter++;
        }
    }
}
