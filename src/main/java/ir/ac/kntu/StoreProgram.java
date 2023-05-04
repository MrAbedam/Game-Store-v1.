package ir.ac.kntu;


import java.util.ArrayList;
import java.util.Scanner;

import static ir.ac.kntu.Get.getString;
import static ir.ac.kntu.Get.getDouble;
import static ir.ac.kntu.Get.getInt;
import static ir.ac.kntu.UserMainPage.allUsers;


public class StoreProgram {

    public static void makeHashie() {
        System.out.println("._._._._._.");
    }

    public static void displayMenu() {
        makeHashie();
        System.out.println("1.Admin");
        System.out.println("2.User");
        makeHashie();
        String ans = getString();
        switch (ans) {
            case "1": {
                adminLogIn();
                break;
            }
            case "2": {
                userChoices();
                break;
            }
            case "3": {
                UserLoggedInPage.showUserLoggedInMenu(allUsers.get(0));
                break;
            }
            default: {
                System.out.println("Wrong input, redirecting to start of page.");
                displayMenu();
                break;
            }
        }
    }

    public static void userChoices() {
        System.out.println("1.Log in");
        System.out.println("2.Sign up");
        System.out.println("3.Return");
        String ans = getString();
        switch (ans) {
            case "1": {
                UserGetIn.userLogin();
                break;
            }
            case "2": {
                UserGetIn.userSignUp();
                break;
            }
            case "3": {
                displayMenu();
                break;
            }
            //cheat_code
            default: {
                System.out.println("Wrong input, redirecting to start of menu.");
                userChoices();
                break;
            }
        }
    }

    private static void tryInputAgain() {
        System.out.println("Wrong Input!");
        System.out.println("1.Retry");
        System.out.println("2.Return");
    }

    private static void adminLogIn() {
        Scanner sc = new Scanner(System.in);
        Admin admin = new Admin("aaa", "123");
        System.out.println("Enter username:");
        String enteredUser = sc.nextLine();
        if (admin.username.equals(enteredUser)) {
            makeHashie();
            System.out.println("Enter password");
            String enteredPass = sc.nextLine();
            makeHashie();
            while (!admin.password.equals(enteredPass)) {
                tryInputAgain();
                String ans = sc.nextLine();
                switch (ans) {
                    case "1": {
                        System.out.println("Enter password");
                        enteredPass = sc.nextLine();
                        makeHashie();
                        break;
                    }
                    case "2": {
                        displayMenu();
                        break;
                    }
                }
            }
            System.out.println("Welcome to Admin's main page.");
            AdminMainPage.displayAdminPage();
        } else {
            tryInputAgain();
            String ans = getString();
            switch (ans) {
                case "1": {
                    adminLogIn();
                    break;
                }
                case "2": {
                    displayMenu();
                    break;
                }
            }
        }


    }

    public static void main(String[] args) {
        Game game1 = new Game("CoD: Advanced Warfare",
                "Call of Duty: Advanced Warfare is a 2014 first-person shooter video game published by Activision.",
                "Action fps-shooter",
                59.99);
        Game game2 = new Game("Dota2",
                "Dota 2 is a 2013 multiplayer online battle arena video game by Valve. The game is a sequel to Defense of the Ancients," +
                        "a community-created mod for Blizzard Entertainment's Warcraft III: Reign of Chaos.",
                "Strategy Moba",
                0.0);
        Game game3 = new Game("Dota",
                "Dota  is a 2013 multiplayer online battle arena video game by Valve. The game is a sequel to Defense of the Ancients," +
                        "a community-created mod for Blizzard Entertainment's Warcraft III: Reign of Chaos.",
                "Strategy Moba",
                10.0);
        Game game4 = new Game("CoD: ModernWarfare",
                "dish dish",
                "fps shooter",
                199.99);
        Game game5 = new Game("Dota",
                "Dota  is a 2013 multiplayer online battle arena video game by Valve. The game is a sequel to Defense of the Ancients," +
                        "a community-created mod for Blizzard Entertainment's Warcraft III: Reign of Chaos.",
                "Strategy Moba",
                1000.0);
        ArrayList<Game> user1Games = new ArrayList<>();
        user1Games.add(game2);
        user1Games.add(game3);
        user1Games.add(game4);

        User user1 = new User("mmd", "12345678Aa", "mmd@gmail.com", "09363340618", user1Games);
        allUsers.add(user1);
        AdminGameList.addGame(game1);
        AdminGameList.addGame(game2);
        AdminGameList.addGame(game3);
        AdminGameList.addGame(game4);
        displayMenu();
    }
}