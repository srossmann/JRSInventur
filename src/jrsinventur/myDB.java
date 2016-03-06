/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jrsinventur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author rossmann
 */
public class myDB {

    
    
    //-----------------------------------------------------------------------
    // Methode baut eine Verbindung zur MySQL auf
    //-----------------------------------------------------------------------
    public static Connection open_mysql() {

        String URL = "jdbc:mysql://localhost/wws";//   +properties.getProperty("MySQLIPAdr").trim()+"/RSHome";
        String Name = "root";//properties.getProperty("MySQLUser").trim();
        String Passw = "panama";//properties.getProperty("MySQLPassw").trim();
        Connection verbindung = null;
        try {
            verbindung = DriverManager.getConnection(URL, Name, Passw);
// 		    befehl = verbindung.createStatement();
        } catch (SQLException ex) {
            System.err.println("Verbindung fehlgeschlagen");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }

        return verbindung;
    }
    
    //-----------------------------------------------------------------------
    // Methode schliesst eine Verbindung zur MySQL
    //-----------------------------------------------------------------------
    public static void close_mysql(Connection verbindung) {
        try {
            verbindung.close();
        } catch (Exception e) {

        }
    }
    
    
        //-----------------------------------------------------------------------
    // Dateneinfügen
    //-----------------------------------------------------------------------
    public static void InsertDaten(int Wert) {
        try {
            Connection con = open_mysql();
            String query = " insert into PVDaten (Datum,Leistung,";
                    
            java.util.Date dt = new java.util.Date();
            Timestamp timestamp = new Timestamp(dt.getTime());

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setTimestamp(1, timestamp);
            preparedStmt.setInt(2, Wert);

            preparedStmt.execute();
            close_mysql(con);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    
}

