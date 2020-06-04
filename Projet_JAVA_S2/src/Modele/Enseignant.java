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
public class Enseignant extends Utilisateur{

    private ArrayList<Cours> liste_cours  = new ArrayList<>(500);
    
    public Enseignant (int id , String email , String passwd , String nom, String prenom , int droit, ArrayList<Cours> cours)
    {
        super(id,email,passwd,nom,prenom,droit);
        liste_cours = cours;
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
    
    
    
}
