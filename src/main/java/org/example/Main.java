package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Database db = new Database();
        db.printAllProductnames();
        //db.printSpecificProductNames(10506);
        //db.printProductNameDependingOnPrice(25);
        //db.printAllCostumers();
        //db.printAllCostumersWithCity();
    }
}