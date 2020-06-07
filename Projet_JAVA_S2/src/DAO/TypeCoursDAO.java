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
 * @author Oumou Sow, Grace Gnenago, Nassim Boutaleb
*/
public class TypeCoursDAO extends DAO<TypeCours>
{
    
    public TypeCoursDAO (Connection conn)
    {
        super(conn);
    }
    
    public TypeCoursDAO ()
    {
        super();
    }

    @Override
    public int create(TypeCours tc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(TypeCours tc) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(TypeCours tc) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    /**
     *
     * @param id
     * @return
     */
    // rechercher un TypeCoursDAO à partir de son id
    // rend null si non trouvé 
    @Override
    public TypeCours find(int id) 
    {
        TypeCours ut = null;
        try 
        {
            Statement stmtTc1=connect.createStatement(); 

            // récupérer TypeCoursDAO en fonction de son id
            ResultSet rsTc1=stmtTc1.executeQuery("SELECT * FROM type_cours WHERE id = '"+id+"'");  

            
            // sinon récupérer TypeCoursDAO et le rendre
            while (rsTc1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                String nom = rsTc1.getString(2);
               
                // Créer TypeCoursDAO
                ut = new TypeCours(id,nom);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner TypeCoursDAO rempli ou null
        return ut;
    }
    


    /**
     *
     * @param nom
     * @return
     */
    public TypeCours find (String nom)
    {
        TypeCours ut = null;
        try 
        {
            Statement stmtTc1=connect.createStatement(); 

            // récupérer TypeCoursDAO en fonction de son nom
            ResultSet rsTc1=stmtTc1.executeQuery("SELECT * FROM type_cours WHERE nom = '"+nom+"'");  
            
            // sinon récupérer TypeCoursDAO et le rendre
            while (rsTc1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                int id = rsTc1.getInt(1);
                
                
                // Créer TypeCoursDAO
                ut = new TypeCours(id,nom);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner TypeCoursDAO rempli ou null
        return ut;
    }
    
    /**
     * Cette méthode retourne la liste de tous les types_cours de la BDD
     * @return
     */
    public ArrayList <TypeCours> get_all_types_cours ()
    {
        ArrayList <TypeCours> liste_typeCours = new ArrayList<>(100);
        TypeCours tc = null;
        
        try 
        {
            Statement stmtTC1=connect.createStatement(); 

            // récupérer Groupe en fonction de son nom
            ResultSet rsTC1=stmtTC1.executeQuery("SELECT * FROM type_cours ");  

            
            // sinon récupérer Groupe et le rendre
            while (rsTC1.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                int id = rsTC1.getInt(1);
                String nom = rsTC1.getString(2);
                
                
                // Créer Groupe
                tc = new TypeCours(id,nom);
                
                // l'ajouter à la liste des groupes
                liste_typeCours.add(tc);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        return liste_typeCours;
        
    }
}
