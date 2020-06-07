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
public class EtudiantDAO extends DAO <Etudiant>
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
    public int create(Etudiant etu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Etudiant etu) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Etudiant etu) 
    {
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
            Statement stmtE4=connect.createStatement(); 
            
            // récupérer l'utilisateur associé ainsi que les données
            Utilisateur utilisat = Utilisateur.charger_utilisateur_BDD(id);
            String email = utilisat.getEmail();
            String passwd = utilisat.getPasswd();
            String nom = utilisat.getNom();
            String prenom = utilisat.getPrenom();
            int droit = utilisat.getDroit();

            // récupérer l'Etudiant en fonction de son id
            ResultSet rsE4=stmtE4.executeQuery("SELECT * FROM etudiant WHERE IdUtilisateur = '"+id+"'");  

            
            // sinon récupérer l'Etudiant et le rendre
            while (rsE4.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
               
                
                String numero = rsE4.getString(2);
                int id_groupe=rsE4.getInt(3);
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
            Statement stmtE4=connect.createStatement(); 

            // récupérer l'Etudiant en fonction de son id utilisateur
            ResultSet rsE4=stmtE4.executeQuery("SELECT * FROM etudiant WHERE IdUtilisateur = '"+id+"'");  

            
            // sinon récupérer l'Etudiant et le rendre
            while (rsE4.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
               
                String numero = rsE4.getString(2);
                int id_groupe=rsE4.getInt(3);
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
            Statement stmtE4=connect.createStatement(); 

            // récupérer l'Etudiant en fonction de son email
            ResultSet rsE4=stmtE4.executeQuery("SELECT * FROM etudiant WHERE IdUtilisateur = '"+id+"'");  

            
            // sinon récupérer l'Etudiant et le rendre
            while (rsE4.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
 
                String numero = rsE4.getString(2);
                int id_groupe=rsE4.getInt(3);
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
   
   // Cette méthode renvoie une arrayList avec tous les étudiants stockés en BDD
   public ArrayList <Etudiant> getAllEtudiants ()
   {
        ArrayList<Etudiant> liste_etudiants = new ArrayList<>(100);
       try 
        {
            Statement stmtE4=connect.createStatement(); 

            // récupérer tous les étudiants
            ResultSet rsE4=stmtE4.executeQuery("SELECT * FROM etudiant ");  

            
            
            while (rsE4.next())  // si rien n'est trouvé on ne rentre pas dans le while
            {                
                int id = rsE4.getInt(1);
                String numero = rsE4.getString(2);
                
                // Récupérer le groupe
                int idGroupe = rsE4.getInt(3);
                Groupe groupe = Groupe.charger_groupe_BDD(idGroupe);
                
                // Il faut maintenant récupérer les infos contenues dans la table utilisateur à partir de l'ID
                Utilisateur utEtu = Utilisateur.charger_utilisateur_BDD(id);
                
                // Créer l'Etudiant
                Etudiant etu = new Etudiant(id, utEtu.getEmail(), utEtu.getPasswd(), utEtu.getNom(), utEtu.getPrenom(), utEtu.getDroit(),numero,groupe);
                
                // L'ajouter dans la liste des étudiants
                liste_etudiants.add(etu);
            }
            
            
        }
        catch(SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
       
       return liste_etudiants;
   }
    
   
    
}
