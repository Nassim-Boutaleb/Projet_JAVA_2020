/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modele.*;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Nassim
 */
public class UtilisateurDAO extends DAO
{

    public UtilisateurDAO (Connection conn)
    {
        super(conn);
    }
    
    public UtilisateurDAO ()
    {
        super();
    }
    
    @Override
    public boolean create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Utilisateur find(int id) 
    {
        Utilisateur ut = null;
        try 
        {
            Statement stmt=connect.createStatement(); 

            // récupérer l'utilisateur en fonction de son id
            ResultSet rs=stmt.executeQuery("SELECT * FROM utilisateur WHERE id = '"+id+"'");  

            
            // sinon récupérer l'utilisateur et le rendre
            while (rs.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                String email = rs.getString(2);
                String passwd = rs.getString(3);
                String nom = rs.getString(4);
                String prenom = rs.getString(5);
                int droit = rs.getInt(6);
                
                // Créer l'utilisateur
                ut = new Utilisateur(id, email, passwd, nom, prenom, droit);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner l'utilisateur rempli ou null
        return ut;
    }
    
    // rechercher un utilisateur à partir de son email
    // rend null si non trouvé 
    public Utilisateur find (String email)
    {
        Utilisateur ut = null;
        try 
        {
            Statement stmt=connect.createStatement(); 

            // récupérer l'utilisateur en fonction de son email
            ResultSet rs=stmt.executeQuery("SELECT * FROM utilisateur WHERE email = '"+email+"'");  

            // regarder si on a des résultats :
            // récupération du résultat de l'ordre
            ResultSetMetaData rsetMeta = rs.getMetaData();

            // calcul du nombre de colonnes  (inutile ici)
            //int nbColonne = rsetMeta.getColumnCount();
            
            // sinon récupérer l'utilisateur et le rendre
            while (rs.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                int id = rs.getInt(1);
                String passwd = rs.getString(3);
                String nom = rs.getString(4);
                String prenom = rs.getString(5);
                int droit = rs.getInt(6);
                
                // Créer l'utilisateur
                ut = new Utilisateur(id, email, passwd, nom, prenom, droit);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner l'utilisateur rempli ou null
        return ut;
    }
    
}
