/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg121project;

/**
 *
 * @author hp
 */
//import GUI.Login;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author hp
 */
public class Program {

    final static int Flatrate = 15;
    final static double pricRiseIndidual = 0.1, priceRise = 0.1;
    private static int id = 0;
    static Scanner userInput = new Scanner(System.in);

//      @param args the command line arguments
    public static void main(String[] args) throws CloneNotSupportedException, IOException {
        MobileCompnay mobileCompnay = new MobileCompnay("Bainscommunication", "admin", "password", 20);
        System.out.print("Test Code...\n");
        Address address1 = new Address(21, "kembla", "east", "melbourne");
        User user1 = new User("jot", 123, address1, "123", "123");
        Address address2 = new Address(22, "auburn", "west", "wollongong");
        User user2 = new User("milk", 126, address2, "milk126", "password126");
        Address address3 = new Address(23, "atchison", "north", "sydney");
        User user3 = new User("kuljeet", 124, address3, "kuljeet124", "password124");

        //      TODO code application logic here
        MobilePhone handset1 = new MobilePhone("oppophone", MobileType.Android, 64, 1400);
        MyDate date1 = new MyDate(2, 1, 2023);
        BusinessPlan businessPlan1 = new BusinessPlan("jot", 3, handset1, 10, 12, date1, 15, 1);

        MobilePhone handset2 = new MobilePhone("nokia", MobileType.IOS, 2, 4);
        MyDate date2 = new MyDate(2, 2, 2000);
        BusinessPlan businessPlan2 = new BusinessPlan("jass", 4, handset2, 102, 11, date2, 15, 4);

        MobilePhone handset3 = new MobilePhone("vivo", MobileType.Windows, 2, 4);
        MyDate date3 = new MyDate(2, 2, 2019);
        PersonalPlan personalPlan1 = new PersonalPlan("bains", 5, handset3, 100, 15, date3, "wollongang");

        MobilePhone handset4 = new MobilePhone("samsung", MobileType.IOS, 2, 4);
        MyDate date4 = new MyDate(2, 2, 2027);
        PersonalPlan personalPlan2 = new PersonalPlan("preet", 6, handset4, 103, 12, date4, "wollongang");

        //add users in compnay
        addUser(mobileCompnay, user1);
        addUser(mobileCompnay, user2);
        addUser(mobileCompnay, user3);

////         add some new plans 
        //user1
        addPlan(user1, personalPlan1);
        addPlan(user1, businessPlan2);
        //addPlan(user1, personalPlan2);
        addPlan(user1, businessPlan1);
        //user2
        addPlan(user2, personalPlan1);
        addPlan(user2, businessPlan2);
        addPlan(user2, personalPlan2);
        addPlan(user2, businessPlan1);
        //user3
        addPlan(user3, personalPlan1);
        addPlan(user3, businessPlan2);
        addPlan(user3, personalPlan2);
        addPlan(user3, businessPlan1);
////        user1.print();
//        System.out.print(user1.toString());
//        
        // ArrayList<Plan> shallowCopy = new ArrayList<> ();
        // ArrayList<Plan> deepCopyCC = new ArrayList<> ();
        HashMap<Integer, Plan> shallowCopy = new HashMap<>();
        HashMap<Integer, Plan> deepCopyCC = new HashMap<>();
//        for(Plan plan: shallowCopy)
//        {
//            plan.print();
//        }
//       
        shallowCopy = user1.shallowCopyPlans();
        deepCopyCC = user1.deepCopyPlans();
        // change city
        // user1.setCity("â€œNew York");
        // new plan 
        addPlan(user1, personalPlan2);
        // user1.sortPlansByDate();
        Plan.printPlans(shallowCopy);
        Plan.printPlans(deepCopyCC);
        Plan.printPlans(user1.getPlans());

//        ArrayList<User> shallowCopy1 = new ArrayList<> ();
//        ArrayList<User> deepCopyCC1 = new ArrayList<> ();
        HashMap<Integer, User> shallowCopy1 = new HashMap<>();
        HashMap<Integer, User> deepCopyCC1 = new HashMap<>();

        shallowCopy1 = mobileCompnay.shallowCopyUsers();
        deepCopyCC1 = mobileCompnay.deepCopyUsers();

        // Begin GUI
        Login login = new Login(mobileCompnay);
        // add users 
        addUser(mobileCompnay, user3);
        // sort user
        // mobileCompnay.sortUsers();
        // Plan.save(mobileCompnay.getAllPlans(), "Plan.ser");
        //  HashMap<Integer, Plan> plans = Plan.load("Plan.ser");
//        Plan.printPlans(plans);
//        plans.put(14, new PersonalPlan("charnjot", 14, handset3, 10, 12, date3, "wollongang"));
//        Plan.save(mobileCompnay.getAllPlans(), "Plan.ser");
//        plans.clear();
//        plans = Plan.load("Plan.ser");
//        Plan.printPlans(plans);
//------------------------------------------------------------------------
////testing binary file and list of users
//        User.saveTextFile(mobileCompnay.getUsers(), "userBinary.bin");
//        HashMap<Integer, User> users = User.load("userBinary.bin");
//        User.printUsers(users);
//        User usr = new User("milk", 120, address2, "username", "password");
//        usr.addPlan(new PersonalPlan("charnjot", 12, handset3, 10, 12, date3, "wollongang"));
//        users.put(120, usr);
//        User.save(users, "userBinary.bin");
//        users.clear();
//        users = User.load("userBinary.bin");
//        User.printUsers(users);
////-----------------------------------------------------------------------------
////testing text file and list of plans with toDilimitedString
//        Plan.saveTextFile(mobileCompnay.getAllPlans(), "planText.txt");
//        HashMap<Integer, Plan> plans = Plan.loadTextFile("planText.txt");
//        System.out.println("\n\n\n ===================== Printing after Loading from text file ========================== ");
//        Plan.printPlans(plans);
//        plans.put(12, new PersonalPlan("charnjot", 12, handset3, 10, 12, date3, "Las Vegas"));
//        Plan.saveTextFile(plans, "planText.txt");
//        plans.clear();
//        System.out.println("\n\n\n ===================== Printing after adding new plan ==========================");
//        plans = Plan.loadTextFile("planText.txt");
//        Plan.printPlans(plans);
////------------------------------------------------------------------------------
////testing text file and list of users with toDilimitedString

//        User.saveTextFile(mobileCompnay.getUsers(), "userText.txt");
//
//        HashMap<Integer, User> users = User.loadTextFile("userText.txt");
//        User.printUsers(users);
//        User use = users.put(121, new User("milk", 121, address2, "username", "password"));
//        use.addPlan(new PersonalPlan("charnjot", 12, handset3, 10, 12, date3, "wollongang"));
//
//        User.saveTextFile(users, "userText.txt");
//        users.clear();
//        users = User.loadTextFile("userText.txt");
//        User.printUsers(users);
//
////----------------------------------------------------------------------
////mobileCompany and binary file
////using default constructor
//        mobileCompnay.save("MobileCompenyBinary.ser");
//        mobileCompnay.load("MobileCompenyBinary.ser");//all users and all plans are loaded
//
//        mobileCompnay.addUser(user3);
//        mobileCompnay.addPlan(4, businessPlan2);
//        mobileCompnay.save("MobileCompenyBinary.ser");
//
//        mobileCompnay.load("MobileCompenyBinary.ser");
//        System.out.println(mobileCompnay);
//
////-------------------------------------------------------------------
////mobileCompany and text file
////using default constructor
        //     mobileCompnay.savetextfile("MobileCompenyText.txt");
        //     mobileCompnay.loadtext("MobileCompenyText.txt");
////all users and all plans are loaded
////
//        mobileCompnay.addUser(user1);
//        mobileCompnay.addPlan(3, businessPlan1);
//        mobileCompnay.savetextfile("MobileCompenyText.txt");
////
//        mobileCompnay.loadtext("MobileCompenyText.txt");
//        System.out.println(mobileCompnay);
//        User.printUsers(shallowCopy1);
//        User.printUsers(deepCopyCC1);
//        User.printUsers(mobileCompnay.getUsers());
//
//        MobileCompnay Compnay = mobileCompnay.clone();
//        //change something and show it is a deep copy
//        UserInterface.mainMenu(mobileCompnay);
//        UserInterface.displayMainMenu();
//        UserInterface.adminLogin(mobileCompnay);
//        UserInterface.adminMenu(mobileCompnay);
//        UserInterface.displayAdminMainMenu();
//        UserInterface.userLogin(mobileCompnay);
//        UserInterface.userMainMenu(mobileCompnay, user3);
//        UserInterface.displayUserMainMenu();
//        UserInterface.createUser(mobileCompnay);
//        UserInterface.createPersonalPlan(mobileCompnay);
//        UserInterface.createBusinessPlan(mobileCompnay);
//        UserInterface.createPersonalPlan(mobileCompnay, id);
//        UserInterface.createBusinessPlan(mobileCompnay, id);
//        UserInterface.printUser(user3);
//        UserInterface.filterByExpiryDate(mobileCompnay);
//        UserInterface.filterByMobileModel(mobileCompnay);
//        UserInterface.updateAddress(mobileCompnay);
//        UserInterface.updateAddress(mobileCompnay, id);
    }

//    public static void addPlan(User user, Plan plan)
//    {
//        if (user.addPlan(plan))
//            System.out.println("The plan has been added successfully: ");
//        else
//            System.out.println("The plan can not be added as the ID already exists: ");    
//    } 
    public static void addPlan(User user, Plan plan) {
        HashMap<Integer, Plan> plans = null;
        if (user.addPlan(plan)) {
            System.out.println("The plan has been added successfully: ");
        } else {
            System.out.println("The plan can not be added as the ID already exists: ");
        }
    }

    public static void addUser(MobileCompnay mobileCompnay, User user) {
        HashMap<Integer, User> users = null;
        if (mobileCompnay.addUser(user)) {
            System.out.println("The plan has been added successfully: ");
        } else {
            System.out.println("The plan can not be added as the ID already exists: ");
        }
    }
}
