/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg121project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author hp
 */
public class User implements Cloneable, Comparable<User>, Serializable {

    private String name;
    private int userID;
    private Address address;
    private String username;
    private String password;
    //  private ArrayList<Plan> plans;
    private HashMap<Integer, Plan> plans;

    public User(String name, int userID, Address address, String username, String password) {
        this.name = name;
        this.userID = userID;
        this.address = address;
        this.username = username;
        this.password = password;
        //this.plans = new ArrayList<>();
        this.plans = new HashMap<>();
    }

    public User(User user) throws CloneNotSupportedException {
        this.name = user.name;
        this.userID = user.userID;
        this.address = new Address(user.address);
        this.username = user.username;
        this.password = user.password;
        this.plans = new HashMap<>();
        for (Plan plan : user.plans.values()) {
            plans.put(plan.getID(), plan.clone());
        }
    }

    public void removePlan(int id) {
        plans.remove(id);
    }

    public int getuserID() {
        return userID;
    }

    public String setName(String newName) {
        return name = newName;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String setPassword(String pass) {
        return password = pass;
    }

    public Address setAddress(Address newAddress) {
        return address = newAddress;
    }

    public Address getAddress() {
        return address;
    }

    public void setCity(String newCity) {
        address.setCity(newCity);
    }

    public String getCity() {
        return address.getCity();
    }

//    public ArrayList<Plan> getPlans()
//   {
//       return plans;
//    }
    public boolean validateUser(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            return true;
        }
        return false;
    }

    public String userIformation() {
        String Information = ("UserName " + this.username + ", password " + this.password + "\n ");
        return Information;
    }

    public HashMap<Integer, Plan> getPlans() {
        return plans;
    }
//    public  Plan findPlan(int id)
//    {
//        for (Plan plan:plans)
//        {
//            if(plan.getID()==id)
//            {
//                return plan;   
//            }
//        }
//        return null;    
//    }

    public Plan findPlan(int planID) // FIND plan
    {
        return plans.get(planID);
    }
//    public boolean addPlan( Plan plan)
//    {
//      if(findPlan(plan.getID())== null)
//        {
//           plans.add(plan);
//           return true;   
//        }
//        return false;        
//    }

    public boolean addPlan(Plan plan) {
        if (findPlan(plan.getID()) == null) {
            plans.put(plan.getID(), plan);
            return true;
        }
        return false;
    }

    public void print() {
        System.out.print("UserName: " + name + " userID: " + userID + " Address: " + address);
        Plan.printPlans(plans);
        System.out.println();
    }
//    public String toString()
//    {
//        String outPut = "UserName: "+name+" userID: "+userID+" Address: "+ address;
//        for(Plan plan: plans)
//        {
//            outPut += plan.toString();
//        }
//        return outPut;
//    }

    public String toString() {
        String outPut = "UserName: " + name + " userID: " + userID + " Address: " + address;
        for (Plan plan : plans.values()) {
            outPut += plan.toString();
        }
        return outPut;
    }

    public String toDelimitedString() {
        String outPut = name + "," + userID + "," + address.toDelimitedString() + "," + username + "," + password + "," + plans.size() + ",";
        for (Plan plan : plans.values()) {
            outPut += plan.toDelimitedString() + ",";
        }
        return outPut;
    }
//    public void printPlans(int flatRate)
//    {
//        for(Plan plan : plans)
//        {
//           plan.print();
//           System.out.printf("\n Monthly Payment: $%.2f",plan.calMonthlPay(flatRate));
//           System.out.println();
//        }
//        System.out.printf("Total mothly payments are: $%.2f\n",calcTotalPayments(flatRate));
//    }
//    public void printPlans(int flatRate)
//    {
//        for(Plan plan : plans)
//        {
//           plan.print();
//           System.out.printf("\n Monthly Payment: $%.2f",plan.calMonthlPay(flatRate));
//           System.out.println();
//        }
//        System.out.printf("Total mothly payments are: $%.2f\n",calcTotalPayments(flatRate));
//    }  

    public void printPlans(int flatRate) {
        for (Plan plan : plans.values()) {
            plan.print();
            System.out.printf("\n Monthly Payment: $%.2f", plan.calMonthlPay(flatRate));
            System.out.println();
        }
        System.out.printf("Total mothly payments are: $%.2f\n", calcTotalPayments(flatRate));
    }

    public static void printUsers(ArrayList<User> userList) {
        for (User user : userList) {
            user.print();
        }
    }

    public static void printUsers(HashMap<Integer, User> users) {
        for (User user : users.values()) {
            user.print();
        }
    }

    public double calcTotalPayments(int flatRate) {
        return Plan.calcTotalPayments(plans, flatRate);
    }

    public void mobilePriceRiseAll(double risePercent) {
        Plan.mobilePriceRiseAll(plans, risePercent);

    }

    public ArrayList<Plan> filterByMobileModel(String Model) {
        return Plan.filterByMobileModel(plans, Model);
    }
// boolean createPersonalPlan(String username, int id, MobilePhone mobilePhone, int internetQuota, int capLimit, MyDate expiryDate, String city)
//    {
//        
//            Plan plan = new PersonalPlan(username, id, mobilePhone, internetQuota, capLimit, expiryDate, city);
//            return addPlan(plan);
//
//      
//    }

    boolean createPersonalPlan(String username, int id, MobilePhone mobilePhone, int internetQuota, int capLimit, MyDate expiryDate, String city) {

        Plan plan = new PersonalPlan(username, id, mobilePhone, internetQuota, capLimit, expiryDate, city);
        return addPlan(plan);
    }
//    boolean createBusinessPlan(String username, int id, MobilePhone mobilePhone, int internetQuota, int capLimit, MyDate expiryDate, int numberOfEmployees, int ABN)
//    {
//        if (findPlan(id) == null)
//        {
//            Plan plan = new BusinessPlan(username, id, mobilePhone, internetQuota, capLimit, expiryDate, numberOfEmployees, ABN);
//            addPlan(plan);
//            return true;
//        }
//
//        else
//        {
//            return false;
//        }
//    }

    boolean createBusinessPlan(String username, int id, MobilePhone mobilePhone, int internetQuota, int capLimit, MyDate expiryDate, int numberOfEmployees, int ABN) {
        if (findPlan(id) == null) {
            Plan plan = new BusinessPlan(username, id, mobilePhone, internetQuota, capLimit, expiryDate, numberOfEmployees, ABN);
            addPlan(plan);
            return true;
        } else {
            return false;
        }
    }

    ArrayList<Plan> filterByExpiryDate(MyDate date) {
        ArrayList<Plan> filteredList;
        filteredList = Plan.filterByExpiryDate(plans, date);

        return filteredList;
    }

    @Override
    public User clone() throws CloneNotSupportedException {
        User output = (User) super.clone();
        output.address = address.clone();
        output.plans = new HashMap<>();;
        for (Plan plan : plans.values()) {
            output.plans.put(plan.getID(), plan.clone());
        }
        return output;
    }

    public int compareTo1(User otherUser) {
        return address.compareTo(otherUser.address);
    }

    public int compareTo(User otherUser) {
        int flatRate = 15;
        if (calcTotalPayments(flatRate) > otherUser.calcTotalPayments(flatRate)) {
            return 1;
        } else if (calcTotalPayments(flatRate) < otherUser.calcTotalPayments(flatRate)) {
            return -1;
        } else {
            return 0;
        }
    }
//     public static ArrayList<User> shallowCopy(ArrayList<User>users)
//      {
//        ArrayList<User> shallowCopy = new ArrayList <User>();
//        for (User user:users )
//        {
//          shallowCopy.add(user);
//        }
//        return shallowCopy;
//      }

    public static ArrayList<User> shallowCopy(HashMap<Integer, User> users) {
        ArrayList<User> shallowCopy = new ArrayList<User>();
        for (User user : users.values()) {
            shallowCopy.add(user);
        }
        return shallowCopy;
    }
//      public static ArrayList<User> deepCopy(ArrayList <User> users ) throws CloneNotSupportedException
//      {
//        ArrayList<User>deepCopy = new ArrayList<User>(); 
//        for (User user :users )
//        {
//            deepCopy.add(user.clone());
//        }
//        return deepCopy;
//      }

    public static ArrayList<User> deepCopy(HashMap<Integer, User> users) throws CloneNotSupportedException {
        ArrayList<User> deepCopy = new ArrayList<User>();
        for (User user : users.values()) {
            deepCopy.add(user.clone());
        }
        return deepCopy;
    }

    public static HashMap< Integer, User> shallowCopyHashMap(HashMap< Integer, User> users) {
        HashMap<Integer, User> shallowCopy = new HashMap<>();
        for (User user : users.values()) {
            shallowCopy.put(user.getuserID(), user);
        }
        return shallowCopy;

    }

    public static HashMap< Integer, User> deepCopyHashMap(HashMap< Integer, User> users) throws CloneNotSupportedException {
        HashMap<Integer, User> deepCopy = new HashMap<>();
        for (User user : users.values()) {
            deepCopy.put(user.userID, user.clone());
        }
        return deepCopy;

    }
//    ArrayList<Plan> deepCopyPlans()throws CloneNotSupportedException
//    {
//       return Plan.deepCopy(plans);
//    }

    HashMap< Integer, Plan> deepCopyPlans() throws CloneNotSupportedException {
        return Plan.deepCopyHashMap(plans);
    }

//    ArrayList<Plan> shallowCopyPlans()
//    {
//      return Plan.shallowCopy(plans);
//    }
    HashMap< Integer, Plan> shallowCopyPlans() {
        return Plan.shallowCopyHashMap(plans);
    }

//    ArrayList<Plan> sortPlansByDate()
//    {
//        Collections.sort (plans);
//        return plans;
//    }
    ArrayList<Plan> sortPlansByDate() {
        ArrayList<Plan> shallowCopy = new ArrayList<Plan>();
        for (Plan plan : plans.values()) {
            shallowCopy.add(plan);
        }
        Collections.sort(shallowCopy);
        return shallowCopy;
    }

    public HashMap<String, Integer> getTotalCountPerMobileModel() {
        HashMap<String, Integer> Model = new HashMap<>();
        for (Plan plan : plans.values()) {
            Integer count = Model.get(plan.getModel());
            if (count != null) {
                Model.put(plan.getModel(), count + 1);
            } else {
                Model.put(plan.getModel(), 1);
            }
        }
        return Model;
    }

    public HashMap<String, Double> getTotalPaymentPerMobileModel(int flatRate) {

        HashMap<String, Double> models = new HashMap<>();
        for (Plan plan : plans.values()) {
            Double price = models.get(plan.getModel());
            if (price != null) {
                models.put(plan.getModel(), price.doubleValue() + plan.calMonthlPay(flatRate));
            } else {
                models.put(plan.getModel(), plan.calMonthlPay(flatRate));
            }
        }
        return models;
    }

    public void printReportTotalPricePerMobileModel(HashMap<String, Double> models, HashMap<String, Integer> Model) {
        for (String mod : models.keySet()) {
            System.out.println(mod + ": " + models.get(mod) + "" + models.get(mod) / Model.get(mod));
        }
        System.out.println();
    }

    public static HashMap<Integer, User> load(String fileName) {
        HashMap<Integer, User> loadList = new HashMap<>();
        ObjectInputStream inputst = null;

        try {
            inputst = new ObjectInputStream(Files.newInputStream(Paths.get("test.ser")));
        } catch (IOException ex) {
            System.err.println("error in create/open the file ");
            System.exit(1);
        }
        try {
            while (true) {
                User par = (User) inputst.readObject();
                loadList.put(par.getuserID(), par);
            }
        } catch (EOFException ex) {
            System.out.println("no more record!");
        } catch (ClassNotFoundException ex) {
            System.err.println("error in wrong class in the file ");
        } catch (IOException ex) {
            System.err.println("error in add object to the file ");
            System.exit(1);
        }
        try {
            if (inputst != null) {
                inputst.close();
            }
        } catch (IOException ex) {
            System.err.println("error in close the file ");
            System.exit(1);
        }
        return loadList;
    }

    public static Boolean save(HashMap<Integer, User> users, String fileName) {
        ObjectOutputStream outputst = null;

        try {
            outputst = new ObjectOutputStream(Files.newOutputStream(Paths.get("test.ser")));
        } catch (IOException ex) {
            System.err.println("error in create/open the file ");
            System.exit(1);
        }
        try {
            for (User user : users.values()) {
                outputst.writeObject(user);
            }
        } catch (IOException ex) {
            System.err.println("error in adding the objects to the file ");
            System.exit(1);
        }
        try {
            if (outputst != null) {
                outputst.close();
            }
        } catch (IOException ex) {
            System.err.println("error in closing the file ");
            System.exit(1);
        }
        return true;
    }

    public static HashMap<Integer, User> loadTextFile(String fileName) throws IOException {
        HashMap<Integer, User> loadList = new HashMap<>();
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String line = in.readLine();
        while (line != null) {
            line = line.trim();
            String[] field = line.split(",");

            String userName = field[0];
            int userid = Integer.parseInt(field[1]);
            int streetNum = Integer.parseInt(field[2]);
            String street = field[3];
            String suburb = field[4];
            String city = field[5];
            String username = field[6];
            String Pass = field[7];
            Address address = new Address(streetNum, street, suburb, city);
            User user = new User(userName, userid, address, username, Pass);

            loadList.put(userid, user);
        }

        line = in.readLine();

        in.close();
        return loadList;
    }

    public static Boolean saveTextFile(HashMap<Integer, User> users, String fileName) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
        for (User user : users.values()) {
            out.write(user.toDelimitedString() + "\n");
        }
        out.close();
        return true;
    }

}
