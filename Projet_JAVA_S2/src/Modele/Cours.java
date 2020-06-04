/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import DAO.*;

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
    
}
