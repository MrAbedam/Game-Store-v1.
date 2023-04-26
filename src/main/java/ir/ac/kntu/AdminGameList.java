package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminGameList {
    static ArrayList<Game> listOfGames = new ArrayList<>();

    public AdminGameList(ArrayList<Game> listOfGames) {
        this.listOfGames = listOfGames;
    }
    public static void adminGameListMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1.Add a game.");
        System.out.println("2.Change a game's details.");
        System.out.println("3.Remove a game.");
        String ans = sc.nextLine();
        switch (ans){
            case "1":{
                makeGame();
                break;
            }
            case "2":{
                System.out.println("Not completed yet!");
                break;
            }
            case "3":{
                System.out.println("Not bemola!");
                break;
            }
        }

    }
    public static void makeGame(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter game's name:");
        String gameName = sc.nextLine();
        System.out.println("Enter game's description");
        String gameDescription = sc.nextLine();
        System.out.println("Enter game's genre");
        String gameGenre = sc.nextLine();
        System.out.println("Enter game's price");
        double gamePrice = sc.nextInt();
        Game newGame = new Game(gameName,gameDescription,gameGenre,gamePrice);
        addGame(newGame);
    }
    public static void addGame(Game game){
        listOfGames.add(game);
    }
    public void showGames(){
        for(Game game :listOfGames ){
            System.out.println(game.name);
        }
    }
}
