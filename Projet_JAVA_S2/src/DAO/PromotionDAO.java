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
public class PromotionDAO extends DAO<Promotion>
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
    public int create(Promotion promo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Promotion promo) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Promotion promo) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     /**
     *
     * @param id
     * @return
     */
    @Override
    // rechercher un Promotion à partir de son id
    // rend null si non trouvé 
    public Promotion find(int id) 
    {
        Promotion promo;
        ArrayList<Groupe> liste_groupes = new ArrayList<>(100);
        promo = null;
        try 
        {
            Statement stmtP1=connect.createStatement(); 

            // récupérer Promotion en fonction de son id
            ResultSet rsP1=stmtP1.executeQuery("SELECT * FROM promotion WHERE id = '"+id+"'");  
            
            while (rsP1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                String nom = rsP1.getString(2);
                
                Statement stmtP2=connect.createStatement(); 
                // récupérer Promotion en fonction de son id
                ResultSet rsP2=stmtP2.executeQuery("SELECT * FROM groupe WHERE IdPromotion = '"+id+"'"); 
                
                while (rsP2.next())
                {
                    Groupe gp = new Groupe(rsP2.getInt(1), rsP2.getString(2), id);
                    liste_groupes.add (gp);
                }
                
                // Créer Promotion
                promo = new Promotion(id,nom,liste_groupes);
            }
            
            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner Promotion rempli ou null
        return promo;
    }
    
    
    // rechercher un Promotion à partir de son nom
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
        ArrayList<Groupe> liste_groupes = new ArrayList<>(100);
        try 
        {
            Statement stmtP1=connect.createStatement(); 

            // récupérer Promotion en fonction de son nom
            ResultSet rsP1=stmtP1.executeQuery("SELECT * FROM promotion WHERE nom = '"+nom+"'");  

            
            // sinon récupérer Promotion et le rendre
            while (rsP1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                int id = rsP1.getInt(1);
               
                Statement stmtP2=connect.createStatement(); 
                // récupérer Promotion en fonction de son id
                ResultSet rsP2=stmtP2.executeQuery("SELECT * FROM groupe WHERE IdPromotion = '"+id+"'"); 
                
                while (rsP2.next())
                {
                    Groupe gp = new Groupe(rsP2.getInt(1), rsP2.getString(2), id);
                    liste_groupes.add (gp);
                }
                
                // Créer Promotion
                promo = new Promotion(id,nom,liste_groupes);
            }
            
            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner Promotion rempli ou null
        return promo;
    }
    
    // cette méthode permet de récupérer la totalité des promotions stockées dans la BDD

    /**
     *
     * @return
     */
    public ArrayList<Promotion> getAllPromotions ()
    {
        ArrayList<Promotion> liste_promos = new ArrayList<>(100);
        
        try 
        {
            Statement stmtP1=connect.createStatement(); 

            
            ResultSet rsP1=stmtP1.executeQuery("SELECT * FROM promotion");  

            System.out.println("BBBJL");
            
            while (rsP1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                System.out.println("HJ");
                int id = rsP1.getInt(1);
                String nom = rsP1.getString(2);
               
                Statement stmtP2=connect.createStatement(); 
                // récupérer Promotion en fonction de son id
                ResultSet rsP2=stmtP2.executeQuery("SELECT * FROM groupe WHERE IdPromotion = '"+id+"'"); 
                
                ArrayList<Groupe> liste_groupes = new ArrayList<>(100);
                
                while (rsP2.next())
                {
                    System.out.println("OKKKKKKK");
                    int idGp = rsP2.getInt(1);
                    String nomGp = rsP2.getString(2);
                    Groupe gp = new Groupe(idGp, nomGp , id);
                    System.out.println("?KL"+id);
                    liste_groupes.add (gp);
                }
                
                // Créer Promotion
                Promotion promo = new Promotion(id,nom,liste_groupes);
                System.out.println("HEYYYY");
                // L'ajouter à la liste
                liste_promos.add(promo);
                
            }
            
            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        return liste_promos;
    }
    
}
