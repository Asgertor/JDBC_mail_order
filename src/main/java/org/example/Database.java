package org.example;

import java.sql.*;

public class Database {

    //a)En metode, der udskriv produktnavn på alle produkter (tabellen PARTS).
    public void printAllProductnames() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mail_order", "root", "Tor42Am41")){
            String SQL = "SELECT pname FROM mail_order.parts;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("pname"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //b)En metode, der udskriv produktnavn på et bestemt produktnummer. Produktnummer angives som parameter (tabellen PARTS).
    public void printSpecificProductNames(int pno) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mail_order", "root", "Tor42Am41");
            String SQL = "SELECT pname from mail_order.parts WHERE pno = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, pno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("pname"));
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //c)En metode, der udskriver produktnavn og pris for de produkter, der koster det samme eller mere end et givent beløb,
    // der angives som parameter (tabellen PARTS).
    public void printProductNameDependingOnPrice(int price) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mail_order", "root", "Tor42Am41");
            String SQL = "SELECT pname, price from mail_order.parts WHERE price >= ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, price);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("pname") + rs.getInt("price"));
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //d)En metode, der returnerer en String med navn, gade og tlf.nr. på alle kunder (tabellen CUSTOMERS). Tip: Benyt StringBuilder.
    //Main metoden udskriver returnværdien.
    public void printAllCostumers() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mail_order", "root", "Tor42Am41");
            String SQL = "SELECT cname, street, phone from mail_order.customers ";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                StringBuilder sb = new StringBuilder();

                sb.append(rs.getString("cname") + " ");
                sb.append(rs.getString("street") + " ");
                sb.append(rs.getString("phone") + " ");

                System.out.println(sb);
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //e)En metode, der returnerer en String med navn, gade, tlf.nr., postnummer og by på alle kunder (tabellen CUSTOMERS). Tip: Benyt StringBuilder.
    //Main metoden udskriver returnværdien.
    public void printAllCostumersWithCity(){
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mail_order", "root", "Tor42Am41");
            String SQL =
                    "SELECT customers.cname, customers.street, customers.phone, zipcodes.zip, zipcodes.city\n" +
                    "FROM customers\n" +
                    "LEFT JOIN zipcodes\n" +
                    "ON customers.zip = zipcodes.zip;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                StringBuilder sb = new StringBuilder();
                sb.append(rs.getString("cname") + " ");
                sb.append(rs.getString("street") + " ");
                sb.append(rs.getString("phone") + " ");
                sb.append(rs.getInt("zip") + " ");
                sb.append(rs.getString("city") + " ");

                System.out.println(sb.toString());
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
