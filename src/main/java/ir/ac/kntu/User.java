package ir.ac.kntu;

import java.util.ArrayList;

import static ir.ac.kntu.FriendOptions.removeUsersFromRequests;
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

    ArrayList<User> friends = new ArrayList<>();

    ArrayList<User> sentRequests = new ArrayList<>();

    ArrayList<User> receivedRequests = new ArrayList<>();

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

    public ArrayList<User> getFriends() {
        return friends;
    }

    public ArrayList<User> getSentRequests() {
        return sentRequests;
    }

    public ArrayList<User> getReceivedRequests() {
        return receivedRequests;
    }

    public void showDetails() {
        int gameCounter = 1;
        System.out.println("Username: " + getUserName());
        System.out.println("Password: " + getPassWord());
        System.out.println("Email: " + getEmail());
        System.out.println("Wallet: " + getWallet() + "$");
        System.out.println("Phone number: " + getPhoneNumber());
        StoreOptions.showStoreGames(ownedGames, this);
        System.out.println("Press anything to go back.");
        getString();
    }

    public boolean doesUserOwn(Game game) {
        for (Game testGame : ownedGames) {
            if (game == testGame) {
                return true;
            }
        }
        return false;
    }

    public boolean buyGame(Game game) {
        if (this.doesUserOwn(game)) {
            System.out.println("You already own this game!");
            return false;
        } else if (this.getWallet() >= game.price) {
            this.setWallet(this.getWallet() - game.price);
            System.out.println(game.getName() + " has been added to your games.");
            ownedGames.add(game);
            return true;
        } else {
            System.out.println("Not enough balance in your account.");
            return false;
        }
    }

    public void changeUserDetailsAsAdmin() {
        changeUserDetailsAsAdminMenu();
        String detailNumber = getString();
        switch (detailNumber) {
            case "1": {
                changeToValidUserAdmin();
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
                            this.changeUserDetailsAsAdmin();
                            return;
                        }
                    } else {
                        this.setPassWord(newPassword);
                        System.out.println("Password changed.");
                        this.changeUserDetailsAsAdmin();
                        break;
                    }
                }
                break;
            }
            case "3": {
                insertChangeEmailAsAdmin();
                break;
            }
            case "4": {
                insertChangePhoneAsAdmin();
                break;
            }
            case "5": {
                AdminUserList.adminUserListMenu();
                break;
            }
            default: {
                System.out.println("Wrong input, redirecting to start of page.");
                this.changeUserDetailsAsAdmin();
                break;
            }
        }
        AdminUserList.adminUserListMenu();
    }


    public void changeUserDetailsAsAdminMenu() {
        System.out.println("Which detail do you want to change?");
        System.out.println("1.Username");
        System.out.println("2.Password");
        System.out.println("3.Email");
        System.out.println("4.Phone number");
        System.out.println("5.Return");
        makeHashie();
    }

    public void changeToValidUserAdmin() {
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
                    this.changeUserDetailsAsAdmin();
                    break;
                }
            } else {
                this.setUserName(newUsername);
                System.out.println("Username changed.");
                this.changeUserDetailsAsAdmin();
                break;
            }
        }
    }

    public void changeToValidUser() {
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
                    break;
                }
            } else {
                this.setUserName(newUsername);
                System.out.println("Username changed.");
                this.changeUserDetails();
                break;
            }
        }
    }

    public void changeUserDetails() {
        changeUserDetailsAsAdminMenu();
        String detailNumber = getString();
        switch (detailNumber) {
            case "1": {
                changeToValidUser();
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
                insertChangeEmail();
                break;
            }
            case "4": {
                insertChangePhone();
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

    public void insertChangePhone() {
        System.out.println("Current phone number: " + this.getPhoneNumber());
        System.out.println("Enter your new phone number:");
        String newPhoneNumber = getString();
        this.setPhoneNumber(newPhoneNumber);
        System.out.println("Phone number changed.");
        this.changeUserDetails();
    }

    public void insertChangePhoneAsAdmin() {
        System.out.println("Current phone number: " + this.getPhoneNumber());
        System.out.println("Enter your new phone number:");
        String newPhoneNumber = getString();
        this.setPhoneNumber(newPhoneNumber);
        System.out.println("Phone number changed.");
        this.changeUserDetailsAsAdmin();
    }

    public void insertChangeEmail() {
        System.out.println("Current phone email: " + this.getEmail());
        System.out.println("Enter your new email:");
        String newEmail = getString();
        System.out.println("Email changed.");
        this.setEmail(newEmail);
        this.changeUserDetails();
    }

    public void insertChangeEmailAsAdmin() {
        System.out.println("Current phone email: " + this.getEmail());
        System.out.println("Enter your new email:");
        String newEmail = getString();
        System.out.println("Email changed.");
        this.setEmail(newEmail);
        this.changeUserDetailsAsAdmin();
    }

    public boolean requestPending(User newUser){
        for(User user : this.sentRequests){
            if (user.getUserName().equals(newUser.userName)){
                System.out.println("Request is already pending.");
                return true;
            }
        }
        return false;
    }

    public boolean areFriends(User newUser){
        for(User user : this.friends){
            if (user.getUserName().equals(newUser.userName)){
                System.out.println(newUser.userName+" is already your friend.");
                return true;
            }
        }
        return false;
    }

    public void sendRequest(User newUser){
        if(this.receivedRequests.contains(newUser)){
            System.out.println("This user had already sent you a friend request.");
            System.out.println("You guys are now friends!");
            removeUsersFromRequests(newUser,this);
            removeUsersFromRequests(this,newUser);
            newUser.friends.add(this);
            this.friends.add(newUser);
            return;
        }
        if(!areFriends(newUser) && !requestPending(newUser)){
            System.out.println("Request sent to "+newUser.getUserName());
            this.sentRequests.add(newUser);
            newUser.receivedRequests.add(this);
        }
    }

    public void showFriends(ArrayList<User> friends){
        int friendCounter = 1;
        for (User user: friends){
            System.out.println(friendCounter+". "+user.getUserName());
            friendCounter++;
        }
    }

    public ArrayList<User> searchNameFriends(String searchName){
        ArrayList<User> filteredList = new ArrayList<>();
        for(User testUser : this.friends){
            if(testUser.getUserName().startsWith(searchName)){
                filteredList.add(testUser);
            }
        }
        return(filteredList);
    }

    public void showGames(){
        int gameCounter = 1;
        for(Game game: this.ownedGames){
            System.out.println(gameCounter+". "+game.getName());
            gameCounter++;
        }
    }

    public void showRequests(){
        int requests = 1;
        System.out.println("Choose a request to answer.");
        for ( User testUser : receivedRequests){
            System.out.println(requests+". "+testUser.getUserName() +" has sent you a friend request.");
            requests++;
        }
    }
}