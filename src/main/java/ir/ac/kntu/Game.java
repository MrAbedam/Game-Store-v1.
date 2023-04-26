package ir.ac.kntu;

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
}
