/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import DAO.*;


/**
 *
 * @author Oumou Sow, Nassim Boutaleb, Grace Gnenago
 */
public class Groupe 
{
    private int id;
    private String nom;
    private Promotion promotion;
    
    public Groupe ()
    {
        
    }
    
    public Groupe (int id ,String nom,Promotion g_promotion)
    {
        this.id = id; 
        this.nom = nom;
        promotion= g_promotion;
        
    }
    
    public void setGroupe (int id, String nom)
    {
        this.id = id;
        this.nom = nom;
    }
    
    public int getId () { return id; }
    public String getNom () { return nom ; }
    public Promotion getIdGroupe () { return promotion ; }
    
    
    /**
     * @param nom    
     * @return     
     */
    public static Groupe charger_groupe_BDD (String nom)
    {
        
        // récupérer Groupe depuis la BDD
        GroupeDAO udao = new GroupeDAO();
        Groupe ut = udao.find(nom);
        
        return ut;
    }
    
    /**
     *
     * @param id
     * @return
     */
    public static Groupe charger_groupe_BDD (int id)
    {
        
        // récupérer Groupe depuis la BDD
        GroupeDAO udao = new GroupeDAO();
        Groupe ut = udao.find(id);
        
        return ut;
    }
    
}
