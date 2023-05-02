package ir.ac.kntu;

import java.util.Scanner;

import static ir.ac.kntu.StoreProgram.makeHashie;


public class AdminMainPage {


    public static void adminUserList(){
        System.out.println("1.Show a user's details.");
        System.out.println("2.Add a user.");
        System.out.println("3.Change a user's details.");
        System.out.println("4.Remove a user.");
        Scanner sc = new Scanner(System.in);
        String ans = sc.nextLine();
    }

    public static void displayAdminPage(){
        System.out.println("1.Games");
        System.out.println("2.Users");
        makeHashie();
        Scanner sc = new Scanner(System.in);
        String ans = sc.nextLine();
        switch (ans){
            case "1":{
                AdminGameList.adminGameListMenu();
                break;
            }
            case "2":{
                adminUserList();
                break;
            }
            default:{
                System.out.println("Wrong input, redirecting to start of page.");
                displayAdminPage();
                break;
            }
        }
    }
}
