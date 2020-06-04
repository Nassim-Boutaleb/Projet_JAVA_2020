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
public class EtudiantDAO extends DAO
{
    
    public EtudiantDAO (Connection conn)
    {
        super(conn);
    }
    
    public EtudiantDAO ()
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
    
   // rechercher un étudiant à partir de l'id utilisateur
    @Override
   public Etudiant find(int id) 
   {
        Etudiant etu = null;
        try 
        {
            Statement stmt=connect.createStatement(); 
            
            // récupérer l'utilisateur associé ainsi que les données
            Utilisateur utilisat = Utilisateur.charger_utilisateur_BDD(id);
            String email = utilisat.getEmail();
            String passwd = utilisat.getPasswd();
            String nom = utilisat.getNom();
            String prenom = utilisat.getPrenom();
            int droit = utilisat.getDroit();

            // récupérer l'Etudiant en fonction de son id
            ResultSet rs=stmt.executeQuery("SELECT * FROM etudiant WHERE IdUtilisateur = '"+id+"'");  

            
            // sinon récupérer l'Etudiant et le rendre
            while (rs.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
               
                
                String numero = rs.getString(2);
                int id_groupe=rs.getInt(3);
                Groupe groupe= Groupe.charger_groupe_BDD(id_groupe);
               
                // Créer l'Etudiant
                etu = new Etudiant(id, email, passwd, nom, prenom, droit,numero,groupe);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner l'Etudiant rempli ou null
        return etu;
    }

    
    // rechercher un Etudiant à partir de son email
    // rend null si non trouvé 
    public Etudiant find (String email)
    {
        Etudiant etu = null;
        
        // récupérer l'utilisateur associé ainsi que les données
        Utilisateur utilisat = Utilisateur.charger_utilisateur_BDD(email);
        int id = utilisat.getId();
        String passwd = utilisat.getPasswd();
        String nom = utilisat.getNom();
        String prenom = utilisat.getPrenom();
        int droit = utilisat.getDroit();
            
        try 
        {
            Statement stmt=connect.createStatement(); 

            // récupérer l'Etudiant en fonction de son id utilisateur
            ResultSet rs=stmt.executeQuery("SELECT * FROM etudiant WHERE IdUtilisateur = '"+id+"'");  

            
            // sinon récupérer l'Etudiant et le rendre
            while (rs.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
               
                String numero = rs.getString(2);
               int id_groupe=rs.getInt(3);
                Groupe groupe= Groupe.charger_groupe_BDD(id_groupe);
                
               
               
                // Créer l'Etudiant
                etu = new Etudiant(id, email, passwd, nom, prenom, droit,numero,groupe);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner l'Etudiant rempli ou null
        return etu;
    }
    
    // A partir d'un utilisateur, cette méthode charge l'étudiant correspondant, et renvoie null si l'utilisateur
    // en question n'est pas un étudiant
    
   public Etudiant find(Utilisateur utilisat) 
   {
        Etudiant etu = null;
        
        // récupérer les données de l'utilisateur
        int id = utilisat.getId();
        String email = utilisat.getEmail();
        String passwd = utilisat.getPasswd();
        String nom = utilisat.getNom();
        String prenom = utilisat.getPrenom();
        int droit = utilisat.getDroit();
         
        
        try 
        {
            Statement stmt=connect.createStatement(); 

            // récupérer l'Etudiant en fonction de son email
            ResultSet rs=stmt.executeQuery("SELECT * FROM etudiant WHERE IdUtilisateur = '"+id+"'");  

            
            // sinon récupérer l'Etudiant et le rendre
            while (rs.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
 
                String numero = rs.getString(2);
                int id_groupe=rs.getInt(3);
                Groupe groupe= Groupe.charger_groupe_BDD(id_groupe);
                
                // Créer l'Etudiant
                etu = new Etudiant(id, email, passwd, nom, prenom, droit,numero,groupe);
            }

            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        
        // retourner l'Etudiant rempli ou null
        return etu;
    }
    
   
    
}
