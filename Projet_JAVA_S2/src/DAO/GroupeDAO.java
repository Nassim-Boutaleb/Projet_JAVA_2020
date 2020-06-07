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
public class GroupeDAO extends DAO<Groupe>
{
    
     public GroupeDAO (Connection conn)
    {
        super(conn);
    }
    
    public GroupeDAO ()
    {
        super();
    }

    @Override
    public int create(Groupe gp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Groupe gp) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Groupe gp) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     /**
     *
     * @param id
     * @return
     */
    @Override
    public Groupe find(int id) 
    {
       Groupe ut;
         ut = null;
        try 
        {
            Statement stmtG1=connect.createStatement(); 

            // récupérer Groupe en fonction de son nom
            ResultSet rsG1=stmtG1.executeQuery("SELECT * FROM groupe WHERE id = '"+id+"'");  
            
            // sinon récupérer Groupe et le rendre
            while (rsG1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                String nom = rsG1.getString(2);
                int id_promotion= rsG1.getInt(3);
                //Promotion prom= Promotion.charger_Promotion_BDD(id_promotion);
                // Créer Groupe
                ut = new Groupe(id,nom,id_promotion);
            }
            
            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner Groupe rempli ou null
        return ut;
    }
    


    /**
     *
     * @param nom
     * @return
     */
    public Groupe find (String nom)
    {
        Groupe ut;
         ut = null;
        try 
        {
            Statement stmtG1=connect.createStatement(); 

            // récupérer Groupe en fonction de son nom
            ResultSet rsG1=stmtG1.executeQuery("SELECT * FROM groupe WHERE nom = '"+nom+"'");  

            
            // sinon récupérer Groupe et le rendre
            while (rsG1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                int id = rsG1.getInt(1);
                // Créer Groupe
               int id_promotion= rsG1.getInt(3);
               //Promotion prom= Promotion.charger_Promotion_BDD(id_promotion);
                // Créer Groupe
                ut = new Groupe(id,nom,id_promotion);
            }
            
            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner Groupe rempli ou null
        return ut;
    }
    
    /**
     * Cette méthode retourne la liste de tous les groupes de la BDD
     * @return
     */
    public ArrayList <Groupe> get_all_groups ()
    {
        ArrayList <Groupe> liste_groupes = new ArrayList<>(100);
        Groupe groupe = null;
        
        try 
        {
            Statement stmtG1=connect.createStatement(); 

            // récupérer Groupe en fonction de son nom
            ResultSet rsG1=stmtG1.executeQuery("SELECT * FROM groupe ");  

            
            // sinon récupérer Groupe et le rendre
            while (rsG1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                int id = rsG1.getInt(1);
                String nom = rsG1.getString(2);
                
                // Créer Groupe
                int id_promotion= rsG1.getInt(3);
                //Promotion prom= Promotion.charger_Promotion_BDD(id_promotion);
                // Créer Groupe
                groupe = new Groupe(id,nom,id_promotion);
                
                // l'ajouter à la liste des groupes
                liste_groupes.add(groupe);
            }
            
            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        return liste_groupes;
        
    }
    
    //public ArrayList <Groupe> get_groupes_par_promo ()
    
   
}
