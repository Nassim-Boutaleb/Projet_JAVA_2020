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
public class TypeCours 
{
    private int id;
    private String nom;
    
    public TypeCours ()
    {
        
    }
    
    public TypeCours (int id ,String nom)
    {
        this.id = id; 
        this.nom = nom;
      
    }
    
    public void setTypeCours (int id, String nom)
    {
        this.id = id;
        this.nom = nom;
    }
    
    public void setNom (String nom) {this.nom = nom; }
    public int getId () { return id; }
    public String getNom () { return nom ; }
    
    /**
     * @param nom
     * @return    
     */
    public static TypeCours charger_typecours_BDD (String nom)
    {
        
        // récupérer TypeCours depuis la BDD
        TypeCoursDAO udao = new TypeCoursDAO();
        TypeCours ut = udao.find(nom);
        
        return ut;
    }
    
    /**
     *
     * @param id
     * @return
     */
    public static TypeCours charger_typecours_BDD (int id)
    {
        
        // récupérer TypeCours depuis la BDD
        TypeCoursDAO udao = new TypeCoursDAO();
        TypeCours ut = udao.find(id);
        
        return ut;
    }
    
    /**
     * Cette méthode retourne la liste de tous les type_cours stockés en BDD
     * @return
     */
    public static ArrayList<TypeCours> get_liste_types_cours ()
    {
        ArrayList<TypeCours> liste_typesCours = new ArrayList<>(100);
        
        TypeCoursDAO tcdao = new TypeCoursDAO();
        liste_typesCours = tcdao.get_all_types_cours();
        
        return liste_typesCours;
    }
    
}
