/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import DAO.*;
import java.util.ArrayList;
/**
 *
 * @author Oumou Sow, Nassim Boutaleb, Grace Gnenago
 */
public class Enseignant extends Utilisateur
{

    private ArrayList<Cours> liste_cours  = new ArrayList<>(500);
    
    public Enseignant (int id , String email , String passwd , String nom, String prenom , int droit, ArrayList<Cours> cours)
    {
        super(id,email,passwd,nom,prenom,droit);
        liste_cours = cours;
    }
    
    // Ce constructeur permet de créer un enseignant sans sa liste de cours (mise à jour plus tard via le setter)
    public Enseignant (int id , String email , String passwd , String nom, String prenom , int droit)
    {
        super(id,email,passwd,nom,prenom,droit);
    }
    

    public void setEnseignant (int id , String email , String passwd , String nom, String prenom , int droit, ArrayList<Cours> cours)
    {
        super.setUtilisateur(id, email, passwd, nom, prenom, droit);
        liste_cours = cours;
        
    }
    

    public void addCours (Cours cours)
    {
        liste_cours.add (cours);
    }
    
    public void setListeCours (ArrayList<Cours> lc)
    {
        liste_cours = lc ;
    }
    
    
    /**
     *
     * @return
     */
    public ArrayList<Cours> getListeCours () { return liste_cours ; }
    
    /**
     *
     * @param index
     * @return
     */
    public Cours getCoursIndex (int index) { return liste_cours.get(index); }
    
    
    
    /**
     *
     * @param email
     * @return
     */
    public static Enseignant charger_enseignant_BDD (String email)
    {
        
        // récupérer Enseignant depuis la BDD
        EnseignantDAO udao = new EnseignantDAO();
        Enseignant ut = udao.find(email);
        
        return ut;
    }
    
    public static Enseignant charger_enseignant_BDD (int id)
    {
        
        // récupérer Enseignant depuis la BDD
        EnseignantDAO udao = new EnseignantDAO();
        Enseignant ut = udao.find(id);
        
        return ut;
    }
    
    public static Enseignant charger_enseignant_BDD_nom_prenom (String nom, String prenom)
    {
        
        // récupérer Enseignant depuis la BDD
        EnseignantDAO udao = new EnseignantDAO();
        Enseignant ut = udao.find_nom_prenom(nom, prenom);
        
        return ut;
    }
    
    // Cette méthode retourne une liste avec tous les enseignants de la BDD

    /**
     *
     * @return
     */
    public static ArrayList <Enseignant> getListeEnseignants ()
    {
        ArrayList<Enseignant> liste_ens = new ArrayList<>(100);
        EnseignantDAO ensDao = new EnseignantDAO();
        liste_ens = ensDao.getAllEnseignants();
                
        return liste_ens;
    }
    
    
    
}
