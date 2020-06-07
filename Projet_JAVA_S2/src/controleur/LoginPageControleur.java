/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Modele.*;
import static Modele.Enseignant.charger_enseignant_BDD;
import static Modele.Etudiant.charger_etudiant_BDD;
import Vue.*;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.JOptionPane;

/**
 *
 * @author Nassim
 */
public class LoginPageControleur {
    
    private LoginPage logPage ;  // stockage de la vue (page graphique)
    private int width;
    private int height;
    
    public LoginPageControleur ()
    {
        // définir taille des fenetres
        //Récupérer taille utile de l'écran
        GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
         
        //Stocker cette taille
        Rectangle maximumWindowBounds=graphicsEnvironment.getMaximumWindowBounds();

        // récupérer la largeur et la heuteur
        width = (int)maximumWindowBounds.getWidth();
        height = (int)maximumWindowBounds.getHeight();
        
        // ouvrir la page de login
        ouvrirLoginPage();
        
    }
    
    // Méthode pour ouvrir une page de login
    public void ouvrirLoginPage ()
    {
        // ouvrir et stocker la page de login et la rendre visible
        logPage = new LoginPage(width, height,this);
        
        logPage.setVisible(true);
    }
    
    // Méthode pour vérifier que les informations entrées correspondent à celles stockées dans
    // la BDD et charger l'utilisateur en question
    public void verifierLogin (String email , String passwd)
    {
        Utilisateur ut = Utilisateur.charger_utilisateur_BDD(email);
        
        
        // Si on nous a rendu null = l'utilisateur n'a pas été trouvé
        if (ut == null)
        {
            JOptionPane.showMessageDialog(logPage,"Email incorrect.","Probleme de connexion",JOptionPane.ERROR_MESSAGE);
           
        }
        
        // Sinon vérifier les mots de passe
        else
        {
            String passwdBDD = ut.getPasswd();
            
            // Si les mots de passe correspondent
            if (passwdBDD.equals(passwd))
            {
               
                //JOptionPane.showMessageDialog(logPage,"Connexion réussie. Bonjour "+ut.getPrenom()+" "+ut.getNom());
                logPage.setVisible(false);
                
                
                // Vérfier de qui il s'agit
                if (ut.getDroit() == 4 )  // etudiant
                {
                    // instancier un étudiant
                    Etudiant etudiant=charger_etudiant_BDD(ut.getId());
                    // appler la méthode pour fermer la page de login et appeler la page edt
                    logPage.dispose();
                    
                    // Créer nouveau controleur EdtControleur
                    new EdtControleur(etudiant);
                    
                } 
                else if(ut.getDroit()== 3){
                // instancier un étudiant
                    Enseignant prof=charger_enseignant_BDD(ut.getId());
                    // appler la méthode pour fermer la page de login et appeler la page edt
                    logPage.dispose();
                    
                    // Créer nouveau controleur EdtControleur
                    new EdtControleurProf(prof);
                    
                }else if(ut.getDroit()==2){
                    AdminGestionControleur agc = new AdminGestionControleur();
                    agc.ouvrirAdminpage(); 
                }
                else if(ut.getDroit()==1){
                    AdminGestionControleur agc = new AdminGestionControleur();
                    agc.ouvrirAdminpage(); 
                }
            }
            
            // les mots de passe ne correspondent pas
            else 
            {
                JOptionPane.showMessageDialog(logPage,"Mot de passe incorrect","Probleme de connexion",JOptionPane.ERROR_MESSAGE);
            
            }
        }
    }
    
}
