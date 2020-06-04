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
    public Groupe getIdGroupe () { return groupe; }
    
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
    
    
}
