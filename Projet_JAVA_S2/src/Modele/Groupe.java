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
public class Groupe 
{
    private int id;
    private String nom;
    private int id_promotion;
    
    public Groupe ()
    {
        
    }
    
    public Groupe (int id ,String nom,int g_promotion)
    {
        this.id = id; 
        this.nom = nom;
        id_promotion= g_promotion;
        
    }
    
    public void setGroupe (int id, String nom)
    {
        this.id = id;
        this.nom = nom;
    }
    
    public int getId () { return id; }
    public String getNom () { return nom ; }
    public int getIdPromotion () { return id_promotion ; }
    
    
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
    
    /**
     * Cette méthode retourne la liste de tous les groupes stockés en BDD
     * @return
     */
    public static ArrayList<Groupe> get_liste_groupes ()
    {
        ArrayList<Groupe> liste_groupes = new ArrayList<>(100);
        
        GroupeDAO gdao = new GroupeDAO();
        liste_groupes = gdao.get_all_groups();
        
        return liste_groupes;
    }
    
}
