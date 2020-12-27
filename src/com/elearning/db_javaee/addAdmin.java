package com.elearning.db_javaee;

import java.sql.Connection;



import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import com.elearning.beans.admin;


public class addAdmin {
    private Connection connexion;
    
  
    
    private void loadDatabase() {
        // Chargement du driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }

        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_javaee", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void ajouterAdmin( admin admin) {
        loadDatabase();
        
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO users(nom, prenom,password,email,isAdmin) VALUES(?,?,?,?,?);");
            preparedStatement.setString(1, admin.getNom());
            preparedStatement.setString(2, admin.getPrenom());
            preparedStatement.setString(3, admin.getPass());
            preparedStatement.setString(4, admin.getEmail());
            preparedStatement.setString(5, admin.getIsAdmin());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}