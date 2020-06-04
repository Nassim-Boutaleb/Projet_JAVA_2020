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
public class Promotion 
{
    private int id;
    private String nom;
    
    public Promotion ()
    {
        
    }
    
    public Promotion (int id ,String nom)
    {
        this.id = id; 
        this.nom = nom;
      
    }
    
    public void setPromotion (int id, String nom)
    {
        this.id = id;
        this.nom = nom;
    }
    
    public int getId () { return id; }
    public String getNom () { return nom ; }
    
    /**
     * @param nom    
     * @return     
     */
    public static Promotion charger_Promotion_BDD (String nom)
    {
        
        // récupérer Groupe depuis la BDD
        PromotionDAO udao = new PromotionDAO();
        Promotion ut = udao.find(nom);
        
        return ut;
    }
    
    /**
     *
     * @param id
     * @return
     */
    public static Promotion charger_Promotion_BDD (int id)
    {
        
        // récupérer Groupe depuis la BDD
        PromotionDAO udao = new PromotionDAO();
        Promotion ut = udao.find(id);
        
        return ut;
    }
    
}
