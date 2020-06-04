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
 * @author Oumou Sow, Nassim Boutaleb, Grace Gnenago
 */
public class SiteDAO extends DAO 
{
    
    public SiteDAO (Connection conn)
    {
        super(conn);
    }
    
    public SiteDAO ()
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
    public Site find(int id) {
        Site ut = null;
        try 
        {
            Statement stmt=connect.createStatement(); 

            // récupérer Site en fonction de son id
            ResultSet rs=stmt.executeQuery("SELECT * FROM site WHERE id = '"+id+"'");  

            
            // sinon récupérer Site et le rendre
            while (rs.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                String nom = rs.getString(2);
               
                
                
                // Créer Site
                ut = new Site(id,nom);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner Site rempli ou null
        return ut;
    }
    
    
    // rechercher un Site à partir de son id
    // rend null si non trouvé 

    /**
     *
     * @param nom
     * @return
     */
    public Site find (String nom)
    {
        Site ut = null;
        try 
        {
            Statement stmt=connect.createStatement(); 

            // récupérer Site en fonction de son email
            ResultSet rs=stmt.executeQuery("SELECT * FROM site WHERE nom = '"+nom+"'");  

            
            // sinon récupérer Site et le rendre
            while (rs.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                int id = rs.getInt(1);
              
                
                // Créer Site
                ut = new Site(id,nom);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner Site rempli ou null
        return ut;
    }
}