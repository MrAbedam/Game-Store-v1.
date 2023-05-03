package ir.ac.kntu;

import static ir.ac.kntu.Get.getString;
import static ir.ac.kntu.Get.getInt;
import static ir.ac.kntu.Get.getDouble;

public class ProfileOption {
    public static void profileChoices(User user){
        System.out.println("Enter an option:");
        System.out.println("1.Show profile.");
        System.out.println("2.Change a detail.");
        System.out.println("3.Return.");
        String ans = getString();
        switch (ans){
            case "1":{
                user.showDetails();
                break;
            }
            case "2":{
                user.changeUserDetails();
                //user.changeUserDetails();
                break;
            }
            case "3":{
                UserLoggedInPage.showUserLoggedInMenu(user);
                break;
            }
            default:{
                System.out.println("Wrong input redirecting to start of page.");
                profileChoices(user);
                break;
            }
        }
    }
}
