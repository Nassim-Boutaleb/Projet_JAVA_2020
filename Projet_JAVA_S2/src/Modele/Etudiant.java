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
public class Etudiant extends Utilisateur
{
    
    private String m_numero;
    private Groupe groupe;
    
    public Etudiant (int id , String email , String passwd , String nom, String prenom , int droit, String numero, Groupe g_etudiant)
    {
        super(id,email,passwd,nom,prenom,droit);
        m_numero=numero;
        groupe=g_etudiant;
    }


    public void setEtudiant (int id , String email , String passwd , String nom, String prenom , int droit, String numero, Groupe g_etudiant)
    {
        super.setUtilisateur(id, email, passwd, nom, prenom, droit);
        m_numero=numero;
        groupe=g_etudiant;
    }
    
 
   
    public String getNumero () { return m_numero ; }
    public Groupe getGroupe () { return groupe; }
    
    /**
     *
     * @param id
     * @return
     */
    public static Etudiant charger_etudiant_BDD (int  id)
    {
        
        // récupérer Etudiant depuis la BDD
        EtudiantDAO udao = new EtudiantDAO();
        Etudiant ut = udao.find(id);
        
        return ut;
    }
    
    /**
     *
     * @return
     */
    // cette méthode retourne une array list avec tous les étudiants dans la BDD
    public static ArrayList <Etudiant> getListeEtudiants ()
    {
        ArrayList <Etudiant> liste_etu = new ArrayList<>(100);
        EtudiantDAO etuDAO = new EtudiantDAO();
        liste_etu = etuDAO.getAllEtudiants();
        return liste_etu;
    }
    
    public static Etudiant  charger_etu_BDD_nom_prenom (String nom, String prenom)
    {
        Utilisateur util = Utilisateur.charger_utilisateur_BDD_nom_prenom(nom, prenom);
        System.out.println("UTILLL: "+util.getNom()+" "+util.getId());
        EtudiantDAO edao = new EtudiantDAO();
        Etudiant etudiant = edao.find(util);
        System.out.println("EDUUUUU: "+etudiant.getNom());
        
        return etudiant;
    }
    
    
}
