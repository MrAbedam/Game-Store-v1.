package ir.ac.kntu;

import static ir.ac.kntu.Get.*;
import static ir.ac.kntu.StoreProgram.makeHashie;

public class Game {
    String name;
    String description;
    String genre;
    double price;
    double avgRate;
    int numberOfRates;

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

    public double addRate(double newRate){
        numberOfRates++;
        this.avgRate = (this.avgRate * numberOfRates + newRate)/numberOfRates;
        return avgRate;
    }

    public void setAvgRate(double avgRate) {
        this.avgRate = avgRate;
        numberOfRates = 1;
    }

    public Game(String name, String description, String genre, double price) {
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.price = price;
        this.avgRate = 0;
        this.numberOfRates = 0;
    }

    public void changeGameDetail() {
        System.out.println("Which detail do you want to change?");
        System.out.println("1.Name");
        System.out.println("2.Description");
        System.out.println("3.Genre");
        System.out.println("4.Price");
        System.out.println("5.Return");
        makeHashie();
        int detailNumber = getInt();
        switch (detailNumber) {
            case 1: {
                System.out.println("Current name: " + this.getName());
                System.out.println("Enter new name:");
                String newName = getString();
                this.setName(newName);
                System.out.println("Name changed!");
                makeHashie();
                this.changeGameDetail();
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
}
