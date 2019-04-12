/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai4.bai1;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author thanglongsp
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DBConnection db = new DBConnection();
        db.connectDB();

        CallableStatement stmt;
        db.conn.prepareCall("{call selectAll()}");
        ResultSet rs;
        int select;

        do {
            System.out.println("\n\t ***MENU***\n"
                    + "\t1. Select all \n"
                    + "\t2. Select by ID \n"
                    + "\t3. Select by Name \n"
                    + "\t4. Delete by ID \n"
                    + "\t5. Insert \n"
                    + "\t6. Exit \n"
            );
            System.out.print("Your select : ");
            select = (new Scanner(System.in)).nextInt();

            switch (select) {
                case 1:
                    stmt = db.conn.prepareCall("{call selectAll()}");
                    rs = stmt.executeQuery();
                    System.out.println("\tID\tNAME\t\tSCHOOL\tAGE");
                    while (rs.next()) {
                        System.out.println(
                                "\t" + rs.getString("id")
                                + "\t" + rs.getString("name")
                                + "\t" + rs.getString("school")
                                + "\t" + rs.getString("age")
                        );
                    }
                    break;
                case 2:
                    int stId;
                    stmt = db.conn.prepareCall("{call selectStudentById(?)}");

                    System.out.print("Enter your id : ");
                    stId = (new Scanner(System.in)).nextInt();

                    stmt.setInt(1, stId);
                    rs = stmt.executeQuery();

                    System.out.println("\tID\tNAME\t\tSCHOOL\tAGE");
                    while (rs.next()) {
                        System.out.println(
                                "\t" + rs.getString("id")
                                + "\t" + rs.getString("name")
                                + "\t" + rs.getString("school")
                                + "\t" + rs.getString("age")
                        );
                    }
                    break;
                case 3:
                    String stName;
                    stmt = db.conn.prepareCall("{call selectStudentByName(?)}");

                    System.out.print("Enter your name : ");
                    stName = (new Scanner(System.in)).nextLine();

                    stmt.setString(1, stName);
                    rs = stmt.executeQuery();

                    System.out.println("\tID\tNAME\t\tSCHOOL\tAGE");
                    while (rs.next()) {
                        System.out.println(
                                "\t" + rs.getString("id")
                                + "\t" + rs.getString("name")
                                + "\t" + rs.getString("school")
                                + "\t" + rs.getString("age")
                        );
                    }
                    break;
                case 4:
                    int dId;
                    stmt = db.conn.prepareCall("{call deleteById(?)}");
                    System.out.print("Enter ID : ");
                        dId = (new Scanner(System.in)).nextInt();
                    stmt.setInt(1, dId);
                    stmt.executeQuery();
                    // show result
                    stmt = db.conn.prepareCall("{call selectAll()}");
                    rs = stmt.executeQuery();
                    System.out.println("\tID\tNAME\t\tSCHOOL\tAGE");
                    while (rs.next()) {
                        System.out.println(
                                "\t" + rs.getString("id")
                                + "\t" + rs.getString("name")
                                + "\t" + rs.getString("school")
                                + "\t" + rs.getString("age")
                        );
                    }
                    break;
                case 5:
                    // get ID max
                    int temId = 0;
                    stmt = db.conn.prepareCall("{call selectAll()}");
                    ResultSet _rs1 = stmt.executeQuery();
                    while (_rs1.next()) {
                        temId = _rs1.getInt("id");
                    }

                    //input info
                    String _name;
                    String _school;
                    int _age;

                    stmt = db.conn.prepareCall("{call insertStudent(?,?,?,?)}");

                    System.out.print("Enter name : ");
                    _name = (new Scanner(System.in)).nextLine();
                    System.out.print("Enter school : ");
                    _school = (new Scanner(System.in)).nextLine();
                    System.out.print("Enter age : ");
                    _age = (new Scanner(System.in)).nextInt();

                    stmt.setInt(1, temId + 1);
                    stmt.setString(2, _name);
                    stmt.setString(3, _school);
                    stmt.setInt(4, _age);

                    stmt.executeQuery();

                    // show result
                    stmt = db.conn.prepareCall("{call selectAll()}");
                    rs = stmt.executeQuery();
                    System.out.println("\tID\tNAME\t\tSCHOOL\tAGE");
                    while (rs.next()) {
                        System.out.println(
                                "\t" + rs.getString("id")
                                + "\t" + rs.getString("name")
                                + "\t" + rs.getString("school")
                                + "\t" + rs.getString("age")
                        );
                    }
                    break;
                default:
                    System.out.println(">> Exit !");
            }
        } while (select != 6);
        db.closeDB();
    }
}
