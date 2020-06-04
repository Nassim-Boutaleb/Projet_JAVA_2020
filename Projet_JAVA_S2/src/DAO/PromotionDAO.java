/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modele.*;
import java.sql.*;


/**
 *
 * @author Oumou Sow, Nassim Boutaleb, Grace Gnenago
 */
public class PromotionDAO extends DAO
{
    public PromotionDAO(Connection conn)
    {
        super(conn);
        
    }
    
    public PromotionDAO()
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
    public Promotion find(int id) 
    {
        Promotion promo;
        promo = null;
        try 
        {
            Statement stmt=connect.createStatement(); 

            // récupérer Promotion en fonction de son nom
            ResultSet rs=stmt.executeQuery("SELECT * FROM promotion WHERE id = '"+id+"'");  
            
            // sinon récupérer Promotion et le rendre
            while (rs.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                String nom = rs.getString(2);
                
                
                // Créer Promotion
                promo = new Promotion(id,nom);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner Promotion rempli ou null
        return promo;
    }
    
    
    // rechercher un Promotion à partir de son id
    // rend null si non trouvé 

    /**
     *
     * @param nom
     * @return
     */
    public Promotion find (String nom)
    {
        Promotion promo;
        promo = null;
        try 
        {
            Statement stmt=connect.createStatement(); 

            // récupérer Promotion en fonction de son nom
            ResultSet rs=stmt.executeQuery("SELECT * FROM promotion WHERE nom = '"+nom+"'");  

            
            // sinon récupérer Promotion et le rendre
            while (rs.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                int id = rs.getInt(1);
               
                // Créer Promotion
                promo = new Promotion(id,nom);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner Promotion rempli ou null
        return promo;
    }
    
}
