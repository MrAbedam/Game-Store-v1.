package ir.ac.kntu;

import java.util.ArrayList;

import static ir.ac.kntu.Get.*;
import static ir.ac.kntu.StoreProgram.makeHashie;
import static ir.ac.kntu.UserMainPage.isPasswordValid;
import static ir.ac.kntu.UserMainPage.usernameExists;

public class User {
    String userName;

    String passWord;

    String email;

    public User(String userName, String passWord, String email, String phoneNumber, ArrayList<Game> ownedGames) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.ownedGames = ownedGames;
        this.wallet = 0;
    }

    String phoneNumber;


    ArrayList<Game> ownedGames;

    double wallet;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public void chargeWallet(double amount) {
        setWallet(amount + getWallet());
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

    public void showDetails() {
        int gameCounter = 1;
        System.out.println("Username: " + getUserName());
        System.out.println("Password: " + getPassWord());
        System.out.println("Email: " + getEmail());
        System.out.println("Wallet: " + getWallet() + "$");
        System.out.println("Phone number: " + getPhoneNumber());
        System.out.println("Games: ");
        for (Game game : ownedGames) {
            System.out.println(gameCounter + ". " + game.getName());
            gameCounter++;
        }
        System.out.println("Press anything to go back.");
        getString();
        ProfileOption.profileChoices(this);
    }

    public void changeUserDetails() {
        System.out.println("Which detail do you want to change?");
        System.out.println("1.Username");
        System.out.println("2.Password");
        System.out.println("3.Email");
        System.out.println("4.Phone number");
        System.out.println("5.Return");
        makeHashie();
        String detailNumber = getString();
        switch (detailNumber) {
            case "1": {
                String newUsername;
                System.out.println("Current username: " + this.getUserName());
                while (true) {
                    System.out.println("Enter your new username:");
                    newUsername = getString();
                    if (usernameExists(newUsername)) {
                        System.out.println("Username already exists. Enter 'q' to quit, or any other key to try again.");
                        String input = getString();
                        if (input.equals("q")) {
                            System.out.println("Username change cancelled.");
                            this.changeUserDetails();
                            return;
                        }
                    } else {
                        this.setUserName(newUsername);
                        System.out.println("Username changed.");
                        this.changeUserDetails();
                        break;
                    }
                }
                break;
            }
            case "2": {
                String newPassword;
                while (true) {
                    System.out.println("Enter your new password:");
                    newPassword = getString();
                    if (!isPasswordValid(newPassword)) {
                        System.out.println("Password is not eligible. Enter 'q' to quit, or any other key to try again.");
                        String input = getString();
                        if (input.equals("q")) {
                            System.out.println("Password change cancelled.");
                            this.changeUserDetails();
                            return;
                        }
                    } else {
                        this.setPassWord(newPassword);
                        System.out.println("Password changed.");
                        this.changeUserDetails();
                        break;
                    }
                }
                break;
            }
            case "3": {
                System.out.println("Current phone email: "+this.getEmail());
                System.out.println("Enter your new email:");
                String newEmail = getString();
                System.out.println("Email changed.");
                this.setEmail(newEmail);
                this.changeUserDetails();
                break;
            }
            case "4": {
                System.out.println("Current phone number: "+this.getPhoneNumber());
                System.out.println("Enter your new phone number:");
                String newPhoneNumber = getString();
                this.setPhoneNumber(newPhoneNumber);
                System.out.println("Phone number changed.");
                this.changeUserDetails();
                break;
            }

            case "5": {
                ProfileOption.profileChoices(this);
                break;
            }
            default: {
                System.out.println("Wrong input, redirecting to start of page.");
                this.changeUserDetails();
                break;
            }
        }
    }
}