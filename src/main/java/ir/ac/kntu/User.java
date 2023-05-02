package ir.ac.kntu;

import java.util.ArrayList;

public class User {
    String userName;

    String passWord;

    String email;

    public User(String userName, String passWord, String email, String phoneNumber, ArrayList<Game> ownedGames, double wallet) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.ownedGames = ownedGames;
        this.wallet = wallet;
    }

    String phoneNumber;


    ArrayList<Game> ownedGames;

    double wallet;

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<Game> getOwnedGames() {
        return ownedGames;
    }

    public double getWallet() {
        return wallet;
    }


}
