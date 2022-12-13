/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg121project;

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
import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author hp
 */
public abstract class Plan implements Cloneable, Serializable, Comparable<Plan> {

    protected String userName;
    protected int id;
    protected MobilePhone handset;
    protected int internetQuota;
    protected int capLimit;
    protected MyDate expiryDate;

    public Plan(String userName, int id, MobilePhone handset, int internetQuota, int capLimit, MyDate expiryDate) {
        this.userName = userName;
        this.id = id;
        this.handset = handset;
        this.internetQuota = internetQuota;
        this.capLimit = capLimit;
        this.expiryDate = expiryDate;
    }

    public Plan(Plan plan) {
        this.userName = plan.userName;
        this.id = plan.id;
        this.handset = new MobilePhone(plan.handset);
        this.internetQuota = plan.internetQuota;
        this.capLimit = plan.capLimit;
        this.expiryDate = new MyDate(expiryDate);

    }

    public int getID() {
        return id;
    }

    public int getInternetQuota() {
        return internetQuota;
    }

    public int setInternetQuota(int inte) {
        return internetQuota = inte;
    }

    public int getCapLimit() {
        return capLimit;
    }

    public int setCapLimit(int cap) {
        return capLimit = cap;
    }

    public String getUserName() {
        return userName;
    }

    public String getModel() {
        return handset.getModel();
    }

    public MobilePhone getHandset() {
        return handset;
    }

    public String setUserName(String userName) {
        return this.userName = userName;
    }

    public MobilePhone setMoible(MobilePhone phone) {
        return this.handset = phone;
    }

    public double getPrice() {
        return handset.price;
    }

    public void setDate(MyDate date) {
        this.expiryDate = date;
    }

    public void print() {
        System.out.println("\nuseName: " + userName + ", ID: " + id + "\nhandset: " + handset + "\nInternet Quota: " + internetQuota + ",  Cap Limit: " + capLimit + "ExpiryDate: " + expiryDate.toString() + ",");
    }

    public String toString() {
        return "\nuseName: " + userName + ", ID: " + id + "\nhandset: " + handset + "\nInternet Quota: " + internetQuota + ",  Cap Limit: " + capLimit + "ExpiryDate: " + expiryDate.toString() + ",";
    }

    public String toDelimitedString() {
        return "";
    }

    public abstract double calMonthlPay(int flatRate);

    public static void printPlans(ArrayList<Plan> plans) {
        for (Plan plan : plans) {
            plan.print();
            System.out.println();
        }
    }

    public static void printPlans(HashMap<Integer, Plan> plans) {
        for (Plan plan : plans.values()) {
            plan.print();
        }
    }

    public static double calcTotalPayments(ArrayList<Plan> plans, int flatRate) {
        double total = 0;
        for (Plan plan : plans) {
            total += plan.calMonthlPay(flatRate);
        }
        return total;
    }

    public static double calcTotalPayments(HashMap<Integer, Plan> plans, int flatRate) {
        double total = 0;
        for (Plan plan : plans.values()) {
            total += plan.calMonthlPay(flatRate);
        }
        return total;
    }

//    public  double calcTotalPayment (HashMap<Integer,Plan> plans, int flatRate) 
//    {
//        double total = 0;
//        for (Plan plan : plans.values())
//        {
//            total += plan.calMonthlPay(flatRate);
//        }
//        return total;       
//    }
    public void mobilePriceRise(double rise) {
        handset.priceRise(rise);
    }

    public static void mobilePriceRiseAll(ArrayList<Plan> plans, double risePercent) {
        for (Plan plan : plans) {
            plan.mobilePriceRise(risePercent);
        }
    }

    public static void mobilePriceRiseAll(HashMap<Integer, Plan> plans, double risePercent) {
        for (Plan plan : plans.values()) {
            plan.mobilePriceRise(risePercent);
        }
    }

    public static ArrayList<Plan> filterByMobileModel(ArrayList<Plan> plans, String Model) {
        ArrayList<Plan> filteredPlans = new ArrayList<Plan>();
        for (Plan plan : plans) {
            if (plan.handset.getModel().contains(Model)) {
                filteredPlans.add(plan);
            }
        }
        return filteredPlans;
    }

    public static ArrayList<Plan> filterByMobileModel(HashMap<Integer, Plan> plans, String Model) {
        ArrayList<Plan> filteredPlans = new ArrayList<Plan>();
        for (Plan plan : plans.values()) {
            if (plan.handset.getModel().contains(Model)) {
                filteredPlans.add(plan);
            }
        }
        return filteredPlans;
    }

    public String SetMobileModel(String newModel) {
        return handset.setModel(newModel);
    }

    static ArrayList<Plan> filterByExpiryDate(ArrayList<Plan> plans, MyDate date) {
        ArrayList<Plan> filteredPlans = new ArrayList<Plan>();
        for (Plan plan : plans) {
            if (date.isExpired(plan.getExpiryDate())) {
                filteredPlans.add(plan);
            }
        }
        return filteredPlans;
    }

    static ArrayList<Plan> filterByExpiryDate(HashMap<Integer, Plan> plans, MyDate date) {
        ArrayList<Plan> filteredPlans = new ArrayList<Plan>();
        for (Plan plan : plans.values()) {
            if (date.isExpired(plan.getExpiryDate())) {
                filteredPlans.add(plan);
            }
        }
        return filteredPlans;
    }

    public MyDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public Plan clone() throws CloneNotSupportedException {
        Plan output = (Plan) super.clone();
        output.handset = handset.clone();
        output.expiryDate = expiryDate.clone();
        return output;
    }

    public static ArrayList<Plan> shallowCopy(ArrayList<Plan> mobilePlan) {
        ArrayList<Plan> shallowCopy = new ArrayList<Plan>();
        for (Plan plan : mobilePlan) {
            shallowCopy.add(plan);
        }
        return shallowCopy;
    }

    public static ArrayList<Plan> deepCopy(ArrayList<Plan> mobilePlan) throws CloneNotSupportedException {
        ArrayList<Plan> deepCopy = new ArrayList<Plan>();
        for (Plan plan : mobilePlan) {
            deepCopy.add(plan.clone());
        }
        return deepCopy;
    }

    public static ArrayList< Plan> shallowCopy(HashMap< Integer, Plan> plans) {
        ArrayList<Plan> shallowCopy = new ArrayList<Plan>();
        for (Plan plan : plans.values()) {
            shallowCopy.add(plan);
        }
        return shallowCopy;
    }

    public static ArrayList<Plan> deepCopy(HashMap< Integer, Plan> plans) throws CloneNotSupportedException {
        ArrayList<Plan> deepCopy = new ArrayList<Plan>();
        for (Plan plan : plans.values()) {
            deepCopy.add(plan.clone());
        }
        return deepCopy;
    }

    public static HashMap< Integer, Plan> shallowCopyHashMap(HashMap< Integer, Plan> plans) {
        HashMap<Integer, Plan> shallowCopy = new HashMap<>();
        for (Plan plan : plans.values()) {
            shallowCopy.put(plan.getID(), plan);
        }
        return shallowCopy;
    }

    public static HashMap< Integer, Plan> deepCopyHashMap(HashMap< Integer, Plan> plans) throws CloneNotSupportedException {
        HashMap<Integer, Plan> deepCopy = new HashMap<>();
        for (Plan plan : plans.values()) {
            deepCopy.put(plan.getID(), plan.clone());
        }
        return deepCopy;

    }

    @Override
    public int compareTo(Plan otherPlan) {
        return expiryDate.compareTo(otherPlan.expiryDate);
    }

    public static HashMap<Integer, Plan> load(String fileName) {
        HashMap<Integer, Plan> loadList = new HashMap<>();
        ObjectInputStream inputst = null;

        try {
            inputst = new ObjectInputStream(Files.newInputStream(Paths.get("test.ser")));
        } catch (IOException ex) {
            System.err.println("error in create/open the file ");
            System.exit(1);
        }
        try {
            while (true) {
                Plan plan = (Plan) inputst.readObject();
                loadList.put(plan.getID(), plan);
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

    public static Boolean save(HashMap<Integer, Plan> plans, String fileName) throws IOException {
        ObjectOutputStream outputst = null;

        try {
            outputst = new ObjectOutputStream(Files.newOutputStream(Paths.get("fileName")));
        } catch (IOException ex) {
            System.err.println("error in create/open the file ");
            System.exit(1);
        }
        try {
            for (Plan rec : plans.values()) {
                outputst.writeObject(rec);
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

    public static HashMap<Integer, Plan> loadTextFile(String fileName) throws FileNotFoundException, IOException {
        HashMap<Integer, Plan> loadList = new HashMap<>();
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String line = in.readLine();

        while (line != null) {
            line = line.trim();
            String[] field = line.split(",");

            switch (field[0]) {
                case "PP":
                    String userName = field[1];
                    int id = Integer.parseInt(field[2]);
                    String model = field[3];
                    MobileType type = MobileType.valueOf(field[4]);
                    int memorySize = Integer.parseInt(field[5]);
                    double price = Double.parseDouble(field[6]);
                    int internetQuota = Integer.parseInt(field[7]);
                    int capLimit = Integer.parseInt(field[8]);
                    int day = Integer.parseInt(field[9]);
                    int month = Integer.parseInt(field[10]);
                    int year = Integer.parseInt(field[11]);
                    String city = field[12];

                    MobilePhone phone = new MobilePhone(model, type, memorySize, price);
                    MyDate date = new MyDate(year, month, day);
                    PersonalPlan pPlan = new PersonalPlan(userName, id, phone, internetQuota, capLimit, date, city);
                    loadList.put(id, pPlan);
                    break;
                case "BP":
                    userName = field[1];
                    id = Integer.parseInt(field[2]);
                    model = field[3];
                    type = MobileType.valueOf(field[4]);
                    memorySize = Integer.parseInt(field[5]);
                    price = Double.parseDouble(field[6]);
                    internetQuota = Integer.parseInt(field[7]);
                    capLimit = Integer.parseInt(field[8]);
                    day = Integer.parseInt(field[9]);
                    month = Integer.parseInt(field[10]);
                    year = Integer.parseInt(field[11]);
                    int numberOfEmployess = Integer.parseInt(field[12]);
                    int ABN = Integer.parseInt(field[13]);

                    phone = new MobilePhone(model, type, memorySize, price);
                    date = new MyDate(year, month, day);
                    BusinessPlan bPlan = new BusinessPlan(userName, id, phone, internetQuota, capLimit, date, numberOfEmployess, ABN);
                    loadList.put(id, bPlan);
                    break;
            }

            line = in.readLine();
        }
        in.close();
        return loadList;
    }

    public static Boolean saveTextFile(HashMap<Integer, Plan> plans, String fileName) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
        for (Plan plan : plans.values()) {
            out.write(plan.toDelimitedString() + "\n");
        }
        out.close();
        return true;
    }

    public static Comparator<Plan> userNameComparator = new Comparator<Plan>() {
        public int compare(Plan p1, Plan p2) {
            return p1.getUserName().compareTo(p2.getUserName());
        }
    };
}
