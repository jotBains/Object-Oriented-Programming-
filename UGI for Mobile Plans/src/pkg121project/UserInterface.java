/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg121project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author hp
 */
public class UserInterface {

    MobileCompnay mobileCompany = new MobileCompnay("Bainscommunication", "admin", "password", 20);

    public static void testCode(MobileCompnay mobileCompany) throws CloneNotSupportedException {
        System.out.println("---Test Code---");

        MobileCompnay mobileCompnay = new MobileCompnay("Bainscommunication", "admin", "password", 20);
        System.out.print("Test Code...\n");
        Address address1 = new Address(21, "kembla", "east", "wollon");
        User user1 = new User("jot", 123, address1, "username", "password");
        Address address2 = new Address(22, "auburn", "west", "wollon");
        User user2 = new User("milk", 126, address2, "username", "password");
        Address address3 = new Address(23, "atchison", "north", "wollon");
        User user3 = new User("kuljeet", 124, address3, "username", "password");

        //TODO code application logic here
        MobilePhone handset1 = new MobilePhone("oppophone", MobileType.Android, 64, 1400);
        MyDate date1 = new MyDate(2, 2, 2020);
        BusinessPlan businessPlan1 = new BusinessPlan("charnjot", 3, handset1, 10, 12, date1, 15, 1);

        MobilePhone handset2 = new MobilePhone("oppophone", MobileType.Android, 2, 4);
        MyDate date2 = new MyDate(2, 2, 2020);
        BusinessPlan businessPlan2 = new BusinessPlan("charnjot", 4, handset2, 10, 12, date2, 15, 1);

        MobilePhone handset3 = new MobilePhone("oppophone", MobileType.Android, 2, 4);
        MyDate date3 = new MyDate(2, 2, 2020);
        PersonalPlan personalPlan1 = new PersonalPlan("charnjot", 5, handset3, 10, 12, date3, "wollongang");

        MobilePhone handset4 = new MobilePhone("oppophone", MobileType.Android, 2, 4);
        MyDate date4 = new MyDate(2, 2, 2020);
        PersonalPlan personalPlan2 = new PersonalPlan("charnjot", 6, handset4, 10, 12, date4, "wollongang");

        //        add users in compnay
        addUser(mobileCompnay, user1);
        addUser(mobileCompnay, user2);

//         add some new plans 
        addPlan(mobileCompnay, user1, personalPlan1);
        addPlan(mobileCompnay, user1, businessPlan2);
//        user1.print();
        System.out.print(user1.toString());

//        ArrayList<Plan> shallowCopy = new ArrayList<> ();
//        ArrayList<Plan> deepCopyCC = new ArrayList<> ();
        HashMap<Integer, Plan> shallowCopy = new HashMap<>();
        HashMap<Integer, Plan> deepCopyCC = new HashMap<>();
//        for(Plan plan: shallowCopy)
//        {
//            plan.print();
//        }

        shallowCopy = user1.shallowCopyPlans();
        deepCopyCC = user1.deepCopyPlans();
        //  change city
        user1.setCity("â€œNew York");
        //   new plan 
        addPlan(mobileCompnay, user1, personalPlan2);
        user1.sortPlansByDate();
        Plan.printPlans(shallowCopy);
        Plan.printPlans(deepCopyCC);
        Plan.printPlans(user1.getPlans());

        //  ArrayList<User> shallowCopy1 = new ArrayList<> ();
        //  ArrayList<User> deepCopyCC1 = new ArrayList<> ();
        HashMap<Integer, User> shallowCopy1 = new HashMap<>();
        HashMap<Integer, User> deepCopyCC1 = new HashMap<>();

        shallowCopy1 = mobileCompnay.shallowCopyUsers();
        deepCopyCC1 = mobileCompnay.deepCopyUsers();
        // add users 
        addUser(mobileCompnay, user3);
        //sort user
        mobileCompnay.sortUsers();

        User.printUsers(shallowCopy1);
        User.printUsers(deepCopyCC1);
        User.printUsers(mobileCompnay.getUsers());

        MobileCompnay Compnay = mobileCompnay.clone();
        //  change something and show it is a deep copy

    }

    public static void mainMenu(MobileCompnay mobileCompany) throws CloneNotSupportedException {
        Scanner sc = new Scanner(System.in);
        int input = 0;
        while (input != 3) {
            displayMainMenu();
            input = sc.nextInt();
            switch (input) {
                case 1:
                    adminLogin(mobileCompany);
                    break;
                case 2:
                    userLogin(mobileCompany);
                    break;
                case 3:
                    pressEnterToContinue();
                    break;
                default:
                    System.out.println("Wrong Option. Please enter a number between 1-3");
                    pressEnterToContinue();
            }
        }
    }

    public static void displayMainMenu() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("---Main Menu---");
        System.out.println("(1) Admin Login");
        System.out.println("(2) User Login");
        System.out.println("(3) Exit");
        System.out.println("\n\nSelect and Option from 1-3");
    }

    public static void adminLogin(MobileCompnay mobileCompany) throws CloneNotSupportedException {
        boolean loggedIn = false;
        Scanner sc = new Scanner(System.in);
        String username;
        String password;

        while (!loggedIn) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("--Admin Login---");
            System.out.println("Enter Username:");
            username = sc.nextLine();
            System.out.println("Enter Password:");
            password = sc.nextLine();
            if (mobileCompany.validateAdmin(username, password)) {
                loggedIn = true;
                System.out.println("Login Successful");
            } else {
                System.out.println("Login Unsuccessful");
            }
        }
        adminMenu(mobileCompany);
    }

    public static void adminMenu(MobileCompnay mobileCompany) throws CloneNotSupportedException {
        Scanner sc = new Scanner(System.in);
        int option = 0;

        while (option != 9) {
            displayAdminMainMenu();
            option = sc.nextInt();
            switch (option) {
                case 1:
                    testCode(mobileCompany);
                    break;
                case 2:
                    createUser(mobileCompany);
                    break;
                case 3:
                    createPersonalPlan(mobileCompany);
                    break;
                case 4:
                    createBusinessPlan(mobileCompany);
                    break;
                case 5:
                    printUserInformation(mobileCompany);
                    break;
                case 6:
                    filterByMobileModel(mobileCompany);
                    break;
                case 7:
                    filterByExpiryDate(mobileCompany);
                    break;
                case 8:
                    updateAddress(mobileCompany);
                    break;
                case 9:
                    break;
                default:
                    System.out.println("That option doesn't exist try a number within 1-9");
            }
            pressEnterToContinue();
        }
    }

    public static void displayAdminMainMenu() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("---Admin Menu---");
        System.out.println("(1) Test Code");
        System.out.println("(2) Create User");
        System.out.println("(3) Create Personal Plan");
        System.out.println("(4) Create Business Plan");
        System.out.println("(5) Print User Information");
        System.out.println("(6) Filter By Mobile Model");
        System.out.println("(7) Filter By Expiry Date");
        System.out.println("(8) Update Address");
        System.out.println("(9) Log Out");
        System.out.println("\n\nSelect and Option from 1-9");
    }

    public static void userLogin(MobileCompnay mobileCompany) {
        boolean loggedIn = false;
        Scanner sc = new Scanner(System.in);
        String username;
        String password;

        while (!loggedIn) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("--User Login---");
            System.out.println("Enter Username:");
            username = sc.nextLine();
            System.out.println("Enter Password:");
            password = sc.nextLine();
            User user = mobileCompany.validateUser(username, password);
            if (user != null) {
                loggedIn = true;
                System.out.println("Login Successful");
                userMainMenu(mobileCompany, user);
            } else {
                System.out.println("Login Unsuccessful");
            }
        }

    }

    public static void userMainMenu(MobileCompnay mobileCompany, User user) {
        Scanner sc = new Scanner(System.in);
        int input = 0;

        while (input != 7) {
            displayUserMainMenu();
            input = sc.nextInt();

            switch (input) {
                case 1:
                    printUser(user);
                    break;
                case 2:
                    createPersonalPlan(mobileCompany, user.getuserID());
                    break;
                case 3:
                    createBusinessPlan(mobileCompany, user.getuserID());
                    break;
                case 4:
                    printUser(user);
                    break;
                case 5:
                    updateAddress(mobileCompany, user.getuserID());
                    break;
                case 6:
                    break;
                default:
                    System.out.println("That option doesn't exist try a number within 1-6");
            }
            pressEnterToContinue();
        }
    }

    public static void displayUserMainMenu() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("---User Menu---");
        System.out.println("(1) Print All Plans and Total Monthly Payments");
        System.out.println("(2) Create Personal Plan");
        System.out.println("(3) Create Business Plan");
        System.out.println("(4) Print User Information");
        System.out.println("(5) Update Address");
        System.out.println("(6) Log Out");
        System.out.println("\n\nSelect and Option from 1-6");
    }

    public static void createUser(MobileCompnay mobileCompany) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Create User---");
        System.out.println("Enter User ID:");
        int userID = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter User Name:");
        String name = sc.nextLine();
        System.out.println("Address:");
        System.out.println("Enter Street Number:");
        int steetNum = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Street Name:");
        String streetName = sc.nextLine();
        System.out.println("Enter Suburb:");
        String suburb = sc.nextLine();
        System.out.println("Enter City:");
        String city = sc.nextLine();
        System.out.println("Enter Username:");
        String username = sc.nextLine();
        System.out.println("Enter Password:");
        String password = sc.nextLine();
        addUser(mobileCompany, new User(name, userID, new Address(steetNum, streetName, suburb, city), "username", "password"));
    }

    public static void createPersonalPlan(MobileCompnay mobileCompany) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Create Personal Plan---");

        System.out.println("Enter User Id to add Plan to:");
        int userID = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Username:");
        String userName = sc.nextLine();
        System.out.println("Enter Plan ID:");
        int planID = sc.nextInt();
        sc.nextLine();
        System.out.println("Mobile Phone:");
        System.out.println("Enter Model:");
        String model = sc.nextLine();
        System.out.println("Enter Type a number between 0-2");
        System.out.println("0. Android");
        System.out.println("1. IOS");
        System.out.println("2. Windows");
        int type = sc.nextInt();
        MobileType phoneType = MobileType.values()[type];
        System.out.println("Enter Memory Size:");
        int memorySize = sc.nextInt();
        System.out.println("Enter Price:");
        double price = sc.nextInt();
        MobilePhone mobilePhone = new MobilePhone(model, phoneType, memorySize, price);
        System.out.println("Enter Internet Quota:");
        int internetQuota = sc.nextInt();
        System.out.println("Enter Cap Limit:");
        int capLimit = sc.nextInt();
        System.out.println("Expiry Date--");
        System.out.println("Enter Day:");
        int day = sc.nextInt();
        System.out.println("Enter Month:");
        int month = sc.nextInt();
        System.out.println("Enter Year:");
        int year = sc.nextInt();
        sc.nextLine();
        MyDate expiryDate = new MyDate(year, month, day);
        System.out.println("Enter City:");
        String city = sc.nextLine();
        addPersonalPlan(mobileCompany, userID, userName, planID, mobilePhone, internetQuota, capLimit, expiryDate, city);
    }

    public static void createPersonalPlan(MobileCompnay mobileCompany, int userID) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Create Personal Plan---");
        System.out.println("Enter Username:");
        String userName = sc.nextLine();
        System.out.println("Enter Plan ID:");
        int planID = sc.nextInt();
        sc.nextLine();
        System.out.println("Mobile Phone--");
        System.out.println("Enter Model:");
        String model = sc.nextLine();
        System.out.println("Enter Type a number between 0-2");
        System.out.println("0. Android");
        System.out.println("1. IOS");
        System.out.println("2. Windows");
        int type = sc.nextInt();
        MobileType phoneType = MobileType.values()[type];
        System.out.println("Enter Memory Size:");
        int memorySize = sc.nextInt();
        System.out.println("Enter Price:");
        double price = sc.nextInt();
        MobilePhone mobilePhone = new MobilePhone(model, phoneType, memorySize, price);
        System.out.println("Enter Internet Quota:");
        int internetQuota = sc.nextInt();
        System.out.println("Enter Cap Limit:");
        int capLimit = sc.nextInt();
        System.out.println("Expiry Date--");
        System.out.println("Enter Day:");
        int day = sc.nextInt();
        System.out.println("Enter Month:");
        int month = sc.nextInt();
        System.out.println("Enter Year:");
        int year = sc.nextInt();
        sc.nextLine();
        MyDate expiryDate = new MyDate(year, month, day);
        System.out.println("Enter City:");
        String city = sc.nextLine();
        addPersonalPlan(mobileCompany, userID, userName, planID, mobilePhone, internetQuota, capLimit, expiryDate, city);
    }

    public static void createBusinessPlan(MobileCompnay mobileCompany) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Create Business Plan---");

        System.out.println("Enter User Id to add Plan to:");
        int userID = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Username:");
        String userName = sc.nextLine();
        System.out.println("Enter Plan ID:");
        int planID = sc.nextInt();
        sc.nextLine();
        System.out.println("Mobile Phone--");
        System.out.println("Enter Model:");
        String model = sc.nextLine();
        System.out.println("Enter Type a number between 0-2");
        System.out.println("0. Android");
        System.out.println("1. IOS");
        System.out.println("2. Windows");
        int type = sc.nextInt();
        MobileType phoneType = MobileType.values()[type];
        System.out.println("Enter Memory Size:");
        int memorySize = sc.nextInt();
        System.out.println("Enter Price:");
        double price = sc.nextInt();
        MobilePhone mobilePhone = new MobilePhone(model, phoneType, memorySize, price);
        System.out.println("Enter Internet Quota:");
        int internetQuota = sc.nextInt();
        System.out.println("Enter Cap Limit:");
        int capLimit = sc.nextInt();
        System.out.println("Expiry Date--");
        System.out.println("Enter Day:");
        int day = sc.nextInt();
        System.out.println("Enter Month:");
        int month = sc.nextInt();
        System.out.println("Enter Year:");
        int year = sc.nextInt();
        MyDate expiryDate = new MyDate(year, month, day);

        System.out.println("Enter Number Of Employees:");
        int numOfEmployees = sc.nextInt();
        System.out.println("Enter ABN:");
        int ABN = sc.nextInt();
        addBusinessPlan(mobileCompany, userID, userName, planID, mobilePhone, internetQuota, capLimit, expiryDate, numOfEmployees, ABN);
    }

    public static void createBusinessPlan(MobileCompnay mobileCompany, int userID) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Create Business Plan---");
        System.out.println("Enter Username:");
        String userName = sc.nextLine();
        System.out.println("Enter Plan ID:");
        int planID = sc.nextInt();
        sc.nextLine();
        System.out.println("Mobile Phone--");
        System.out.println("Enter Model:");
        String model = sc.nextLine();
        System.out.println("Enter Type a number between 0-2");
        System.out.println("0. Android");
        System.out.println("1. IOS");
        System.out.println("2. Windows");
        int type = sc.nextInt();
        MobileType phoneType = MobileType.values()[type];
        System.out.println("Enter Memory Size:");
        int memorySize = sc.nextInt();
        System.out.println("Enter Price:");
        double price = sc.nextInt();
        MobilePhone mobilePhone = new MobilePhone(model, phoneType, memorySize, price);
        System.out.println("Enter Internet Quota:");
        int internetQuota = sc.nextInt();
        System.out.println("Enter Cap Limit:");
        int capLimit = sc.nextInt();
        System.out.println("Expiry Date--");
        System.out.println("Enter Day:");
        int day = sc.nextInt();
        System.out.println("Enter Month:");
        int month = sc.nextInt();
        System.out.println("Enter Year:");
        int year = sc.nextInt();
        MyDate expiryDate = new MyDate(year, month, day);
        System.out.println("Enter Number Of Employees:");
        int numOfEmployees = sc.nextInt();
        System.out.println("Enter ABN:");
        int ABN = sc.nextInt();
        addBusinessPlan(mobileCompany, userID, userName, planID, mobilePhone, internetQuota, capLimit, expiryDate, numOfEmployees, ABN);
    }

    public static void printUserInformation(MobileCompnay mobileCompany) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Print User Information---");
        System.out.println("Enter User ID:");
        int userID = sc.nextInt();
        mobileCompany.printPlans(userID);
    }

    public static void printUser(User user) {
        System.out.println("---User Information---");
        user.print();
    }

    public static void filterByMobileModel(MobileCompnay mobileCompany) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Filter By Mobile Model---");
        System.out.println("Enter Mobile Model:");
        String mobileModel = sc.nextLine();
        ArrayList<Plan> filteredPlans = mobileCompany.filterByMobileModel(mobileModel);
        System.out.println("Filtered Plans--");
        Plan.printPlans(filteredPlans);
        double totalMonthlyPayments = Plan.calcTotalPayments(filteredPlans, mobileCompany.flatRate());
        System.out.println("Total Monthly Payments:$" + totalMonthlyPayments);
    }

    public static void filterByExpiryDate(MobileCompnay mobileCompany) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Filter By Expiry Date---");
        System.out.println("Expiry Date--");
        System.out.println("Enter Day:");
        int day = sc.nextInt();
        System.out.println("Enter Month:");
        int month = sc.nextInt();
        System.out.println("Enter Year:");
        int year = sc.nextInt();
        MyDate expiryDate = new MyDate(year, month, day);
        System.out.println("Enter User ID:");
        int userId = sc.nextInt();
        ArrayList<Plan> filteredPlans = mobileCompany.filterByExpiryDate(userId, expiryDate);
        System.out.println("Expired Plans--");
        Plan.printPlans(filteredPlans);
    }

    public static void updateAddress(MobileCompnay mobileCompany) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Update Address---");
        System.out.println("Enter User ID for Address to be changed:");
        int userId = sc.nextInt();
        System.out.println("Address--");
        System.out.println("Enter Street Number:");
        int steetNum = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Street Name:");
        String streetName = sc.nextLine();
        System.out.println("Enter Suburb:");
        String suburb = sc.nextLine();
        System.out.println("Enter City:");
        String city = sc.nextLine();
        User user = mobileCompany.findUser(userId);
        if (user != null) {
            user.setAddress(new Address(steetNum, streetName, suburb, city));
            System.out.println("New Address:");
            System.out.println(user.getAddress());
        } else {
            System.out.println("User has not been found");
        }
    }

    public static void updateAddress(MobileCompnay mobileCompany, int userID) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Update Address---");
        System.out.println("Address--");
        System.out.println("Enter Street Number:");
        int steetNum = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Street Name:");
        String streetName = sc.nextLine();
        System.out.println("Enter Suburb:");
        String suburb = sc.nextLine();
        System.out.println("Enter City:");
        String city = sc.nextLine();
        User user = mobileCompany.findUser(userID);
        if (user != null) {
            user.setAddress(new Address(steetNum, streetName, suburb, city));
            System.out.println("New Address:");
            System.out.println(user.getAddress());
        } else {
            System.out.println("User has not been found");
        }
    }

    public static void pressEnterToContinue() {
        System.out.println("Press Enter Key to Continue...");
        try {
            System.in.read();
        } catch (Exception e) {

        }
    }

    public static void validateAdmin(MobileCompnay mobileCompany, String username, String password) {
        if (mobileCompany.validateAdmin(username, password)) {
            System.out.println("Login Successful");
        } else {
            System.out.println("Login Unsuccessful");
        }
    }

    public static void addUser(MobileCompnay mobileCompany, User user) {
        HashMap<Integer, User> users = null;
        if (mobileCompany.addUser(user)) {
            System.out.println("The plan has been added successfully: ");
        } else {
            System.out.println("The plan can not be added as the ID already exists: ");
        }
    }

    public static void addPlan(MobileCompnay mobileCompany, User user, Plan plan) {
        HashMap<Integer, Plan> plans = null;
        if (user.addPlan(plan)) {
            System.out.println("The plan has been added successfully: ");
        } else {
            System.out.println("The plan can not be added as the ID already exists: ");
        }
    }

    public static void addUser(MobileCompnay mobileCompany, String name, int id, Address address) {
        if (mobileCompany.addUser(name, id, address, "username", "password")) {
            System.out.println("The user has been added successfully");
        } else {
            System.out.println("The user can't be added ID already exists");
        }
    }

    public static void addBusinessPlan(MobileCompnay mobileCompany, int userID, String userName, int planID, MobilePhone mobile, int internetQuota, int capLimit, MyDate expiryDate, int numOfEmployees, int ABN) {
        if (mobileCompany.createBusinessPlan(userID, userName, planID, mobile, internetQuota, capLimit, expiryDate, numOfEmployees, ABN)) {
            System.out.println("The plan was added successfully");
        } else {
            System.out.println("The plan can't be added a plan with the same ID exists");
        }
    }

    public static void addPersonalPlan(MobileCompnay mobileCompany, int userID, String userName, int planID, MobilePhone mobile, int internetQuota, int capLimit, MyDate expiryDate, String city) {
        if (mobileCompany.createPersonalPlan(userID, userName, planID, mobile, internetQuota, capLimit, expiryDate, city)) {
            System.out.println("The plan was added successfully");
        } else {
            System.out.println("The plan can't be added a plan with the same ID exists");
        }
    }

}
