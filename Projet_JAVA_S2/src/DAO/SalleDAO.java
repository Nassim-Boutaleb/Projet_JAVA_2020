/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Modele.*;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Oumou Sow, Nassim Boutaleb, Grace Gnenago
 */
public class SalleDAO extends DAO<Salle> 
{
    
    public SalleDAO (Connection conn)
    {
        super(conn);
    }
    
    public SalleDAO ()
    {
        super();
    }

    @Override
    public int create(Salle s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Salle s) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Salle s) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Salle find(int id) 
    {
        Salle salle = null;
        try 
        {
            Statement stmtSa1=connect.createStatement(); 

            // récupérer Salle en fonction de son id
            ResultSet rsSa1=stmtSa1.executeQuery("SELECT * FROM salle WHERE id = '"+id+"'");  
            
            // sinon récupérer Salle et le rendre
            while (rsSa1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                String nom = rsSa1.getString(2);
                int capacite = rsSa1.getInt(3);
                
                int id_site=rsSa1.getInt(4);
                //Site site = Site.charger_site_BDD(id_site);
                
                // Créer Salle
                salle = new Salle(id,nom,capacite,id_site);
            }
            
            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner Salle rempli ou null
        return salle;    
    }
    
  

    /**
     *
     * @param nom
     * @return
     */
    public Salle find (String nom)
    {
        Salle salle = null;
        try 
        {
            Statement stmtSa1=connect.createStatement(); 

            // récupérer Salle en fonction de son nom
            ResultSet rsSa1=stmtSa1.executeQuery("SELECT * FROM salle WHERE nom = '"+nom+"'");  
            
            // sinon récupérer Salle et le rendre
            while (rsSa1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                int id = rsSa1.getInt(1);
                int capacite = rsSa1.getInt(3);
                
                int id_site=rsSa1.getInt(4);
                //Site site = Site.charger_site_BDD(id_site);
                
                // Créer Salle
                salle = new Salle(id,nom,capacite,id_site);
            }
            
            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner Salle rempli ou null
        return salle;
    }
    
    public ArrayList<Salle> getAllSalles ()
    {
        Salle salle = null;
        ArrayList<Salle> liste_salles = new ArrayList<>(100);
        try 
        {
            Statement stmtSa1=connect.createStatement(); 

            // récupérer Salle en fonction de son nom
            ResultSet rsSa1=stmtSa1.executeQuery("SELECT * FROM salle");  
            
            // sinon récupérer Salle et le rendre
            while (rsSa1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                int id = rsSa1.getInt(1);
                String nom = rsSa1.getString(2);
                int capacite = rsSa1.getInt(3);
                
                int id_site=rsSa1.getInt(4);
                //Site site = Site.charger_site_BDD(id_site);
                
                // Créer Salle
                salle = new Salle(id,nom,capacite,id_site);
                
                // Ajouter à la liste
                liste_salles.add (salle);
            }
            
            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner Salle rempli ou null
        return liste_salles;
    }
    
}
