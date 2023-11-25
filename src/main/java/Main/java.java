/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import model.*;
import utils.*;
import java.util.UUID;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kahffi
 */
public class java {
    static Scanner scanner = new Scanner (System.in);
    
    public static void main(String[] args){

        /*
         uuIDGenerator();
         */
        Admin admin = new Admin ("daffa","123","Muhammad Daffa Al Kahffi","0818","daffa@upi.edu","admin");
        //KontenEduDAO.saveKontenEdu(admin.createKontenEdukasi());
        AkunDAO.updateNama("daffa", "Muhammad Daffa Al Kahffi");
       
    }
    
    
    public static void uuIDGenerator(){
        int start = 1;
        int menu;
        while (start != 0){
            System.out.println("UUID: " + UUID.randomUUID().toString());
            System.out.println("1. generate lagi\n0. keluar");
            menu = Integer.parseInt(scanner.nextLine());
        }
    }
}
