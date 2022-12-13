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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileNotFoundException;
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
import java.util.HashMap;

/**
 *
 * @author hp
 */
public class MobileCompnay implements Cloneable, Serializable {

    private String name;
    // private ArrayList<User> users;
    private HashMap<Integer, User> users;
    private String adminUsername;
    private String adminPassword;
    private int flatRate;

    public MobileCompnay(String name, String adminUsername, String adminPassword, int flatRate) {
        this.name = name;
        //  this.users = new ArrayList<User>();
        this.users = new HashMap<Integer, User>();
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.flatRate = flatRate;
    }

    public MobileCompnay(MobileCompnay company) throws CloneNotSupportedException {
        this.name = company.name;
        this.users = new HashMap<>();
        for (User user : company.users.values()) {
            users.put(user.getuserID(), new User(user));
        }

        this.adminUsername = company.adminUsername;
        this.adminPassword = company.adminPassword;
        this.flatRate = company.flatRate;
    }

    @Override
    public MobileCompnay clone() throws CloneNotSupportedException {
        MobileCompnay output = (MobileCompnay) super.clone();
        output.users = new HashMap<>();
        for (User user : users.values()) {
            output.users.put(user.getuserID(), user.clone());
        }
        return output;

    }

    public String getName() {
        return name;
    }

    public int flatRate() {
        return flatRate;
    }
//    public ArrayList<User> getUsers()
//    {
//        return users;
//    }

    public HashMap<Integer, User> getUsers() {
        return users;
    }

    public HashMap<Integer, Plan> getAllPlans() {
        HashMap<Integer, Plan> allPlans = new HashMap<>();
        for (User user : users.values()) {
            for (Plan plan : user.getPlans().values()) {
                allPlans.put(plan.getID(), plan);
            }
        }
        return allPlans;
    }

    public ArrayList<String> userIformation() {
        ArrayList<String> cities = new ArrayList<String>();
        for (User user : users.values()) {
            cities.add(user.userIformation() + " ");
        }
        return cities;
    }

    public boolean validateAdmin(String username, String password) {
        if (username.equals(adminUsername) && password.equals(adminPassword)) {
            return true;
        } else {
            return false;
        }
    }

    public User validateUser(String username, String password) {
        for (User user : users.values()) {
            if (user.validateUser(username, password)) {
                return user;
            }
        }
        return null;
    }

    public boolean validUser(String username, String password) {
        for (User user : users.values()) {
            if (user.validateUser(username, password)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> populateDistinctCityNames() {
        ArrayList<String> cities = new ArrayList<String>();
        for (User user : users.values()) {
            boolean found = false;
            for (String city : cities) {
                if (user.getCity().equals(city)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                cities.add(user.getCity());
            }
        }
        return cities;
    }
//    boolean addUser(User user)
//    {
//        if (findUser(user.getuserID())== null)
//        {
//           users.add(user);
//           return true;
//        }
//        else
//        {
//           return false;
//        }
//    }

    public boolean addUser(User user) {
        if (findUser(user.getuserID()) == null) {
            users.put(user.getuserID(), user);
            return true;
        }
        return false;
    }
//    public boolean addUser(String name, int userID, Address address )
//    {
//        User user = new User(name, userID, address);
//        return addUser(user);
//    }

    public boolean addUser(String name, int userID, Address address, String adminUsernamea, String dminPassword) {
        User user = new User(name, userID, address, adminUsernamea, dminPassword);

        return addUser(user);
    }
//    public User findUser(int userID)
//    {
//        for(User user : users){
//            if (user.getuserID()==(userID)) 
//            {
//                return user;
//            }
//        }
//        return null;
//    }

    public User findUser(int userID) {
        return users.get(userID);
    }
//    boolean addPlan(int userID, Plan plan)
//    {
//        User user1 = findUser(userID);
//        if ( user1 == null)
//        {
//            return false;
//        }
//        else
//        {
//            return user1.addPlan(plan);
//        }
//    }

    boolean addPlan(int userID, Plan plan) {
        User user1 = findUser(userID);
        if (user1 == null) {
            return false;
        } else {
            return user1.addPlan(plan);
        }
    }

//    public Plan findPlan (int userID ,int planID)
//    {
//        User user = findUser(userID);
//        if(user != null)
//        {
//        return user.findPlan(planID);
//        }
//        else
//        {
//        return null;
//        }  
//    }
    public Plan findPlan(int userID, int planID) {
        User user = findUser(userID);
        if (user != null) {
            return user.findPlan(planID);
        }
        return null;
    }
//   public void printPlans(int userID)
//   {
//        User user = findUser(userID);
//        user.print(); //null     
//   }

    public void printPlans(int userID) {
        User user = findUser(userID);
        user.print(); //null     
    }

    void print() {
        System.out.println("Name: " + name);
        for (User user : users.values()) {
            user.print();
        }
    }
//   void print()
//   {
//        System.out.println("Name: "+name);
//        for(User user : users)
//        {
//           user.print();
//        }
//    }
//    public String toString()
//    {
//        String output;
//        output= ("name: "+name+"users: ");
//        for (User user:users)
//        {
//            output += user.toString();
//        }
//        return output;
//    }

    public String toString() {
        String output;
        output = ("name: " + name + "users: ");
        for (User user : users.values()) {
            output += user.toString();
        }
        return output;
    }

    public String toDelimitedString() {
        String output;
        output = this.name + "," + this.adminUsername + "," + this.adminPassword + "," + this.flatRate + "," + users.size() + ",";
        for (User user : users.values()) {
            output += user.toDelimitedString();
        }
        return output;
    }

//    public boolean createPersonalPlan(int userID, String username, int id, MobilePhone mobilePhone, int internetQuota, int capLimit, MyDate expiryDate, String city) 
//    {
//        User user = findUser(userID);
//        if (user == null) 
//        {
//            return false;
//        } 
//        else 
//        {
//            return (user.createPersonalPlan(username, id, mobilePhone, internetQuota, capLimit, expiryDate, city));
//        }
//    }
    public boolean createPersonalPlan(int userID, String username, int id, MobilePhone mobilePhone, int internetQuota, int capLimit, MyDate expiryDate, String city) {
        User user = findUser(userID);
        if (user == null) {
            return false;
        } else {
            return (user.createPersonalPlan(username, id, mobilePhone, internetQuota, capLimit, expiryDate, city));
        }
    }

//    public boolean createBusinessPlan(int userID, String username, int id, MobilePhone mobilePhone, int internetQuota, int capLimit, MyDate expiryDate, int numberOfEmployees, int ABN) {
//        User user = findUser(userID);
//        if (user == null) 
//        {
//            return false;
//        } 
//        else 
//        {
//            return (user.createBusinessPlan(username, id, mobilePhone, internetQuota, capLimit, expiryDate, numberOfEmployees, ABN));
//        }
//    }
    public boolean createBusinessPlan(int userID, String username, int id, MobilePhone mobilePhone, int internetQuota, int capLimit, MyDate expiryDate, int numberOfEmployees, int ABN) {
        User user = findUser(userID);
        if (user == null) {
            return false;
        } else {
            return (user.createBusinessPlan(username, id, mobilePhone, internetQuota, capLimit, expiryDate, numberOfEmployees, ABN));
        }
    }
//    public double calcTotalPayments(int userID) 
//    {
//        User user = findUser(userID);
//        return user.calcTotalPayments(flatRate);    
//    }

    public double calcTotalPayments(int userID) {
        User user = findUser(userID);
        return user.calcTotalPayments(flatRate);
    }

//    public double calcTotalPayments() 
//    {
//        double total = 0;
//        for (User user : users) 
//        {
//           total += user.calcTotalPayments(flatRate);
//        }
//        return total;
//    }
    public double calcTotalPayments() {
        double total = 0;
        for (User user : users.values()) {
            total += user.calcTotalPayments(flatRate);
        }
        return total;
    }

//    public boolean mobilePriceRise(int userID, double risePercent) 
//    {
//        User user = findUser(userID);
//        if (user != null) 
//        {
//          user.mobilePriceRiseAll(risePercent);
//          return true;
//        }
//        else
//        {
//          return false;
//        }
//    }
    public boolean mobilePriceRise(int userID, double risePercent) {
        User user = findUser(userID);
        if (user != null) {
            user.mobilePriceRiseAll(risePercent);
            return true;
        } else {
            return false;
        }
    }
//    void mobilePriceRise( double risePercent)
//    {
//        for(User user:users)
//        {
//            user.mobilePriceRiseAll(risePercent);
//        }
//    }

    void mobilePriceRise(double risePercent) {
        for (User user : users.values()) {
            user.mobilePriceRiseAll(risePercent);
        }
    }
//    ArrayList<Plan> allPlans ()
//    {
//        ArrayList<Plan> planList = new ArrayList<Plan>();
//        for (User user: users)
//        {
//            for(Plan plan : user.getPlans())
//            {
//                planList.add(plan);
//            }
//        }
//        return planList;
//    }
//   

//    ArrayList<Plan> filterByMobileModel (int userID, String mobileModel)
//    {
//        User user = findUser(userID);
//
//        if(user==null)
//        {
//          return new ArrayList<Plan>();
//        }
//        else
//        {
//            return user.filterByMobileModel(mobileModel);
//        } 
//       
//    }
    ArrayList<Plan> filterByMobileModel(int userID, String mobileModel) {
        User user = findUser(userID);

        if (user == null) {
            return new ArrayList<Plan>();
        } else {
            return user.filterByMobileModel(mobileModel);
        }

    }
//    ArrayList<Plan> filterByExpiryDate (int userID, MyDate date)
//    {
//      User user = findUser(userID);
//      ArrayList<Plan> filtered = new ArrayList<Plan>();
//      if(user==null)
//      {
//          return filtered;
//      }
//      else
//      {
//        return user.filterByExpiryDate(date);
//      } 
//    }

    ArrayList<Plan> filterByExpiryDate(int userID, MyDate date) {
        User user = findUser(userID);
        ArrayList<Plan> filtered = new ArrayList<Plan>();
        if (user == null) {
            return filtered;
        } else {
            return user.filterByExpiryDate(date);
        }
    }

//    ArrayList<Plan> filterByMobileModel (String mobileModel)
//    {
//        ArrayList<Plan> filterByMobileModel  = new ArrayList<Plan>();
//        ArrayList<Plan> userPlans  = new ArrayList<Plan>();
//        for(User user : users)
//          {
//            userPlans = user.filterByMobileModel(mobileModel);
//            for(Plan plan:userPlans)
//            {
//               filterByMobileModel.add(plan);
//            }
//          }
//          return filterByMobileModel;
//    } 
    ArrayList<Plan> filterByMobileModel(String mobileModel) {
        ArrayList<Plan> filterByMobileModel = new ArrayList<Plan>();
        ArrayList<Plan> userPlans = new ArrayList<Plan>();
        for (User user : users.values()) {
            userPlans = user.filterByMobileModel(mobileModel);
            for (Plan plan : userPlans) {
                filterByMobileModel.add(plan);
            }
        }
        return filterByMobileModel;
    }
//    ArrayList<Plan>filterByExpiryDate (MyDate date)
//    {
//        ArrayList<Plan> filterByExpiryDate = new ArrayList<Plan>();
//        ArrayList<Plan> userPlans  = new ArrayList<Plan>();
//        for(User user : users)
//          {
//            userPlans = user.filterByExpiryDate(date);
//            for(Plan plan:userPlans)
//            {
//                filterByExpiryDate.add(plan);
//            }
//          }
//        return filterByExpiryDate;
//    }

    ArrayList<Plan> filterByExpiryDate(MyDate date) {
        ArrayList<Plan> filterByExpiryDate = new ArrayList<Plan>();
        ArrayList<Plan> userPlans = new ArrayList<Plan>();
        for (User user : users.values()) {
            userPlans = user.filterByExpiryDate(date);
            for (Plan plan : userPlans) {
                filterByExpiryDate.add(plan);
            }
        }
        return filterByExpiryDate;
    }
//    ArrayList<User> deepCopyUsers()throws CloneNotSupportedException
//    {
//        return User.deepCopy(users);
//    }

    HashMap< Integer, User> deepCopyUsers() throws CloneNotSupportedException {
        return User.deepCopyHashMap(users);
    }
//     ArrayList<User> shallowCopyUsers()
//     {
//       return User.shallowCopy(users);
//     }

    HashMap< Integer, User> shallowCopyUsers() {
        return User.shallowCopyHashMap(users);
    }
//   ArrayList<User> sortUsers() 
//   {
//        Collections.sort(users);
//        return users;
//   }

    ArrayList<User> sortUsers() {
        ArrayList<User> shallowCopy = new ArrayList<User>();
        for (User user : users.values()) {
            shallowCopy.add(user);
        }

        Collections.sort(shallowCopy);
        return shallowCopy;
    }

    HashMap<String, Double> getTotalPaymentPerCity() {
        int flatRate = 15;
        HashMap<String, Double> cityList = new HashMap<>();
        for (User user : users.values()) {
            Double price = cityList.get(user.getCity());
            if (price != null) {
                cityList.put(user.getCity(), price + user.calcTotalPayments(flatRate));
            } else {
                cityList.put(user.getCity(), user.calcTotalPayments(flatRate));
            }
        }
        return cityList;
    }

    HashMap<String, Integer> getTotalCountPerMobileModel() {
        HashMap<String, Integer> modelCount = new HashMap<>();
        for (User user : users.values()) {
            for (Plan plan : user.getPlans().values()) {
                Integer count = modelCount.get(plan.getModel());
                if (count != null) {
                    modelCount.put(plan.getModel(), count + 1);
                } else {
                    modelCount.put(plan.getModel(), 1);
                }
            }
        }
        return modelCount;
    }

    HashMap<String, Double> getTotalPaymentPerMobileModel() {

        HashMap<String, Double> models = new HashMap<>();
        for (User user : users.values()) {
            for (Plan plan : user.getPlans().values()) {
                Double price = models.get(plan.getModel());
                if (price != null) {
                    models.put(plan.getModel(), price.doubleValue() + user.calcTotalPayments(flatRate));
                } else {
                    models.put(plan.getModel(), user.calcTotalPayments(flatRate));
                }
            }

        }
        return models;
    }

    public void printReportTotalPaymentPerCity(HashMap<String, Double> citys) // PRINT THE REPORT
    {
        for (String city : citys.keySet()) {
            System.out.println(city + ": " + citys.get(city));
        }
        System.out.println();
    }

    public void printReportTotalPaymentPerMobileModel(HashMap<String, Double> modelPayments, HashMap<String, Integer> modelCount) // PRINT THE REPORT
    {
        System.out.println("Model         monthlypay       Avg Payment");
        System.out.println("--------------------------------------------");
        for (String mod : modelCount.keySet()) {
            System.out.println(mod + ": " + modelCount.get(mod) + "" + modelPayments.get(mod) / modelCount.get(mod));
        }
        System.out.println();
    }

    /*
    public  Boolean load (String fileName)
    {
       
    }
    public Boolean save (String fileName)
    {
        
    }
     */
    public Boolean save(String fileName) {
        ObjectOutputStream outputst = null;

        try {
            outputst = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)));
        } catch (IOException ex) {
            System.err.println("error in create/open the file ");
            System.exit(1);
        }
        try {
            outputst.writeObject(this);
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

    public boolean load(String fileName) throws CloneNotSupportedException {
        ObjectInputStream in = null;
        MobileCompnay mc;

        try {
            in = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
        } catch (IOException ex) {
            System.err.println("error in create/open the file ");
            System.exit(1);
        }
        try {

            mc = (MobileCompnay) in.readObject();

            this.adminPassword = mc.adminPassword;
            this.adminUsername = mc.adminUsername;
            this.name = mc.name;
            this.users = User.deepCopyHashMap(mc.users);
        } catch (EOFException ex) {
            System.out.println("no more record!");
        } catch (ClassNotFoundException ex) {
            System.err.println("error in wrong class in the file ");
        } catch (IOException ex) {
            System.err.println("error in add object to the file ");
            System.exit(1);
        }
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException ex) {
            System.err.println("error in close the file ");
            System.exit(1);
        }
        return true;
    }

    public boolean savetextfile(String fileName) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
        out.write(this.toDelimitedString() + "\n");
        out.close();
        return true;
    }

    public Boolean loadtext(String fileName) throws FileNotFoundException, IOException {

        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String line = in.readLine();
        while (line != null) {
            line = line.trim();
            String[] field = line.split(",");
            String Name = field[0];
            String userName = field[1];
            String userpassword = field[2];
            int amount = Integer.parseInt(field[3]);
            int flatRate = Integer.parseInt(field[4]);
            MobileCompnay company = new MobileCompnay(Name, userName, userpassword, flatRate);
            int counter = 5;
            for (int i = 0; i < amount; i++) {
                String UName = field[counter++];
                int userid = Integer.parseInt(field[counter++]);
                int streetNum = Integer.parseInt(field[counter++]);
                String street = field[counter++];
                String suburb = field[counter++];
                String city = field[counter++];
                String username = field[counter++];
                String Pass = field[counter++];
                Address address = new Address(streetNum, street, suburb, city);
                User user = new User(userName, userid, address, username, Pass);

                int Planamount = Integer.parseInt(field[6]);
                HashMap<Integer, Plan> loadList = new HashMap<Integer, Plan>();
                for (int j = 0; j < Planamount; j++) {
                    switch (field[counter]) {
                        case "PP":
                            String UserName = field[++counter];
                            int id = Integer.parseInt(field[++counter]);
                            String model = field[++counter];
                            MobileType type = MobileType.valueOf(field[++counter]);
                            int memorySize = Integer.parseInt(field[++counter]);
                            double price = Double.parseDouble(field[++counter]);
                            int internetQuota = Integer.parseInt(field[++counter]);
                            int capLimit = Integer.parseInt(field[++counter]);
                            int day = Integer.parseInt(field[++counter]);
                            int month = Integer.parseInt(field[++counter]);
                            int year = Integer.parseInt(field[++counter]);
                            String City = field[++counter];

                            MobilePhone phone = new MobilePhone(model, type, memorySize, price);
                            MyDate date = new MyDate(year, month, day);
                            PersonalPlan pPlan = new PersonalPlan(UserName, id, phone, internetQuota, capLimit, date, City);
                            loadList.put(id, pPlan);
                            break;
                        case "BP":
                            UserName = field[++counter];
                            id = Integer.parseInt(field[++counter]);
                            model = field[3];
                            type = MobileType.valueOf(field[++counter]);
                            memorySize = Integer.parseInt(field[++counter]);
                            price = Double.parseDouble(field[++counter]);
                            internetQuota = Integer.parseInt(field[++counter]);
                            capLimit = Integer.parseInt(field[++counter]);
                            day = Integer.parseInt(field[++counter]);
                            month = Integer.parseInt(field[++counter]);
                            year = Integer.parseInt(field[++counter]);
                            int numberOfEmployess = Integer.parseInt(field[++counter]);
                            int ABN = Integer.parseInt(field[++counter]);

                            phone = new MobilePhone(model, type, memorySize, price);
                            date = new MyDate(year, month, day);
                            BusinessPlan bPlan = new BusinessPlan(userName, id, phone, internetQuota, capLimit, date, numberOfEmployess, ABN);
                            loadList.put(id, bPlan);
                            break;
                    }
                }
            }
        }
        line = in.readLine();
        in.close();
        return true;
    }
}
