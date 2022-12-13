/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg121project;

import java.io.Serializable;

/**
 *
 * @author hp
 */
public class PlanException extends Exception implements Serializable {

    static int id;

    public PlanException(int id) {
        this.id = generateID();
    }

    public static int getNewID() {
        return id;
    }

    public int generateID() {
        /*Random r = new Random ();
        int id = 3000000 + r.nextInt(999999);*/
        int id = (int) ((Math.random() * ((3999999 - 3000000) + 1)) + 3000000);
        return id;
    }

    public String toString() {
        return "The Plan ID was not valid and a new ID (" + getNewID() + ") is generated by admin and assigned to the plan";
    }

}
