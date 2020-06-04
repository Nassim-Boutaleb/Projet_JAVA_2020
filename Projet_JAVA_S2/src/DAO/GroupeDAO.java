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
 * @author adjasow
 */
public class GroupeDAO extends DAO{
    
     public GroupeDAO (Connection conn)
    {
        super(conn);
    }
    
    public GroupeDAO ()
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
    public Groupe find(int id) 
    {
       Groupe ut;
         ut = null;
        try 
        {
            Statement stmt=connect.createStatement(); 

            // récupérer Groupe en fonction de son nom
            ResultSet rs=stmt.executeQuery("SELECT * FROM groupe WHERE id = '"+id+"'");  

            // regarder si on a des résultats :
            // récupération du résultat de l'ordre
            ResultSetMetaData rsetMeta = rs.getMetaData();

            // calcul du nombre de colonnes  
            int nbColonne = rsetMeta.getColumnCount();
            
            // sinon récupérer Groupe et le rendre
            while (rs.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                String nom = rs.getString(2);
                int id_promotion= rs.getInt(3);
                PromotionDAO promdao=new PromotionDAO();
                Promotion prom= promdao.find(id_promotion);
                // Créer Groupe
                ut = new Groupe(id,nom,prom);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner Groupe rempli ou null
        return ut;
    }
    
    // rechercher un TypeCoursDAO à partir de son id
    // rend null si non trouvé 

    /**
     *
     * @param idc
     * @return
     */
    public Groupe find (String nom)
    {
        Groupe ut;
         ut = null;
        try 
        {
            Statement stmt=connect.createStatement(); 

            // récupérer Groupe en fonction de son nom
            ResultSet rs=stmt.executeQuery("SELECT * FROM groupe WHERE nom = '"+nom+"'");  

            // regarder si on a des résultats :
            // récupération du résultat de l'ordre
            ResultSetMetaData rsetMeta = rs.getMetaData();

            // calcul du nombre de colonnes  
            int nbColonne = rsetMeta.getColumnCount();
            
            // sinon récupérer Groupe et le rendre
            while (rs.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                int id = rs.getInt(1);
                // Créer Groupe
                 int id_promotion= rs.getInt(3);
                PromotionDAO promdao=new PromotionDAO();
                Promotion prom= promdao.find(id_promotion);
                // Créer Groupe
                ut = new Groupe(id,nom,prom);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner Groupe rempli ou null
        return ut;
    }
    
   
}
