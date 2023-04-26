package ir.ac.kntu;

import java.util.Scanner;

public class StoreProgram {

    public static void makeHashie(){
        System.out.println("._._._._._.");
    }

    public static void displayMenu(){
        makeHashie();
        System.out.println("1.Admin");
        System.out.println("2.User");
        makeHashie();
        Scanner sc = new Scanner(System.in);
        int ans = sc.nextInt();
        switch ( ans ){
            case 1:
                adminLogIn();
        }
    }

    private static void tryInputAgain(){
        System.out.println("Wrong Input!");
        System.out.println("1.Retry");
        System.out.println("2.Return");
    }

    private static void adminLogIn() {
        Scanner sc = new Scanner(System.in);
        Admin admin = new Admin("aaa","123");
        System.out.println("Enter username:");
        String enteredUser = sc.nextLine();
        if(admin.username.equals(enteredUser)){
            makeHashie();
            System.out.println("Enter password");
            String enteredPass = sc.nextLine();
            makeHashie();
            while(!admin.password.equals(enteredPass)){
                tryInputAgain();
                String ans = sc.nextLine();
                switch (ans){
                    case "1":{
                        System.out.println("Enter password");
                        enteredPass = sc.nextLine();
                        makeHashie();
                        break;
                    }
                    case "2":{
                        displayMenu();
                        break;
                    }
                }
            }
            System.out.println("Welcome to Admin's main page.");
            AdminMainPage.displayAdminPage();
        }else {
            tryInputAgain();
            String ans = sc.nextLine();
            switch (ans) {
                case "1":{
                    adminLogIn();
                    break;
                }
                case "2":{
                    displayMenu();
                    break;
                }
            }
        }


    }

    public static void main(String[] args) {
        displayMenu();
    }
}