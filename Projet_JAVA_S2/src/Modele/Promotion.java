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
    
    // cette méthode rend une lsite avec ttes les promotions de la BDD
    public static ArrayList<Promotion> getAllPromotions ()
    {
        ArrayList<Promotion> liste_promotions = new ArrayList<>(100);
        PromotionDAO pdao = new PromotionDAO();
        liste_promotions = pdao.getAllPromotions();
        return liste_promotions;
    }
    
}
