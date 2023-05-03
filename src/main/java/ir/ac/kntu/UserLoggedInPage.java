package ir.ac.kntu;

import static ir.ac.kntu.Get.getString;
import static ir.ac.kntu.Get.getInt;
import static ir.ac.kntu.Get.getDouble;

public class UserLoggedInPage {
    public static void showUserLoggedInMenu(User user) {
        System.out.println("Welcome to your main page " + user.getUserName());
        System.out.println("Choose what you want to do:");
        System.out.println("1.Profile");
        System.out.println("2.Store");
        System.out.println("3.Library");
        System.out.println("4.Friends");
        System.out.println("5.Return");
        String ans = getString();
        switch (ans) {
            case "1": {
                ProfileOption.profileChoices(user);
                break;
            }
            case "2": {
                System.out.println("Store");
                break;
            }
            case "3": {
                System.out.println("Library");
                break;
            }
            case "4": {
                System.out.println("Friends");
                break;
            }
            case "5": {
                System.out.println("Redirecting to main menu.");
                StoreProgram.displayMenu();
                break;
            }
            default: {
                System.out.println("Wrong input, redirecting to start of page.");
                showUserLoggedInMenu(user);
                break;
            }

        }

    }
}
