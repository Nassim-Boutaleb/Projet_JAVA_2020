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
 * @author Oumou Sow, Grace Gnenago, Nassim Boutaleb
 */
public class Cours 
{
    
    private int id;
    private String nom;
    
    /**
     *
     * @param id
     * @param nom
     */
    public Cours (int id ,String nom)
    {
        this.id = id; 
        this.nom = nom;
      
    }
    
    // Setters

    /**
     *
     * @param id
     * @param nom
     */
    public void setCours (int id, String nom)
    {
        this.id = id;
        this.nom = nom;
    }
    
    /**
     *
     * @param nom
     */
    public void setNom (String nom) {this.nom = nom; }
    
    // getters

    /**
     *
     * @return
     */
    public int getId () { return id; }

    /**
     *
     * @return
     */
    public String getNom () { return nom ; }
    
    /**
     *
     * @param nom
     * @return
     */
    public static Cours charger_cours_BDD (String nom)
    {
        
        // récupérer Cours depuis la BDD
        CoursDAO udao = new CoursDAO();
        Cours ut = udao.find(nom);
        
        return ut;
    }
    
    /**
     *
     * @param id
     * @return
     */
    public static Cours charger_cours_BDD (int id)
    {
        
        // récupérer Cours depuis la BDD
        CoursDAO udao = new CoursDAO();
        Cours ut = udao.find(id);
        
        return ut;
    }
    
    /**
     * Cette méthode retourne la liste de tous les cours stockés en BDD
     * @return
     */
    public static ArrayList<Cours> get_liste_cours ()
    {
        ArrayList<Cours> liste_cours = new ArrayList<>(100);
        
        CoursDAO cdao = new CoursDAO();
        liste_cours = cdao.get_all_cours();
        
        return liste_cours;
    }
    
    /**
     * Cette méthode insère un cours dans la BDD
     * @return
     */
    public int inserer_cours_BDD ()
    {
        CoursDAO cdao = new CoursDAO();
        int success = cdao.create(this);
        return success; 
    }
    
    /**
     * Cette méthode change le nom d'un cours dans la BDD
     * @return
     */
    public int renommer_cours (String nouveauNom)
    {
        CoursDAO cdao = new CoursDAO();
        int success = cdao.update(this,nouveauNom);
        return success; 
    }
    
}
