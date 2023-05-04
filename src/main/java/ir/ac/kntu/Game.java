package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;

import static ir.ac.kntu.Get.*;
import static ir.ac.kntu.StoreProgram.makeHashie;

public class Game {

    String name;

    String description;

    String genre;

    double price;

    double avgRate;

    HashMap<User, Double> rateUser = new HashMap<>();

    public void addRate(User user, double rate) {
        this.rateUser.put(user, rate);
    }

    public void updateRate() {
        double sum = 0;
        int numberOfUsers = 0;
        for (double testRate : this.rateUser.values()) {
            sum += testRate;
            numberOfUsers++;
        }
        this.setAvgRate(sum / numberOfUsers);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(double avgRate) {
        this.avgRate = avgRate;
    }

    public Game(String name, String description, String genre, double price) {
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.price = price;
        this.avgRate = 0;
    }

    public void changeGameDetailOptions(){
        System.out.println("Which detail do you want to change?");
        System.out.println("1.Name");
        System.out.println("2.Description");
        System.out.println("3.Genre");
        System.out.println("4.Price");
        System.out.println("5.Return");
        makeHashie();
    }

    public void changeGameName(){
        System.out.println("Current name: " + this.getName());
        System.out.println("Enter new name:");
        String newName = getString();
        this.setName(newName);
        System.out.println("Name changed!");
        makeHashie();
        this.changeGameDetail();
    }

    public void changeGameDetail() {
        changeGameDetailOptions();
        int detailNumber = getInt();
        switch (detailNumber) {
            case 1: {
                changeGameName();
                break;
            }
            case 2: {
                System.out.println("Current description: " + this.getDescription());
                System.out.println("Enter new description:");
                String newDescription = getString();
                this.setDescription(newDescription);
                System.out.println("Description changed!");
                makeHashie();
                this.changeGameDetail();
                break;
            }
            case 3: {
                System.out.println("Current genre: " + this.getGenre());
                System.out.println("Enter new genre:");
                String newGenre = getString();
                this.setGenre(newGenre);
                System.out.println("Genre changed!");
                makeHashie();
                this.changeGameDetail();
                break;
            }
            case 4: {
                System.out.println("Current price: " + this.getPrice());
                System.out.println("Enter new price:");
                double newPrice = getDouble();
                this.setPrice(newPrice);
                System.out.println("Price changed!");
                makeHashie();
                this.changeGameDetail();
                break;
            }
            case 5: {
                AdminGameList.adminGameListMenu();
                break;
            }
            default: {
                System.out.println("Wrong input, redirecting to start of page.");
                this.changeGameDetail();
                break;
            }
        }
    }

    public void showGameDetails(User user) {
        System.out.println("Here are the details of the mentioned game:");
        System.out.println("Game name: " + this.getName());
        System.out.println("Game description: " + this.getDescription());
        System.out.println("Game genre: " + this.getGenre());
        System.out.println("Game price: " + this.getPrice() + "$");
        if (user.doesUserOwn(this)) {
            System.out.println(Colors.green + "Owned" + Colors.reset);
            System.out.println("Press Anything to go back to Store menu.");
            getString();
            StoreOptions.storeMenu(user);
        } else {
            System.out.println(Colors.red + "Not owned" + Colors.reset);
            System.out.println("Enter 'BUY' to buy this game, enter anything else to go back.");
            String ans = getString();
            ans = ans.toUpperCase();
            switch (ans) {
                case "BUY": {
                    boolean didBuy = user.buyGame(this);
                    System.out.println("Press Anything to go back to Store menu.");
                    getString();
                    StoreOptions.storeMenu(user);
                    break;
                }
                default: {
                    StoreOptions.storeMenu(user);
                    break;
                }
            }
        }
    }
}
