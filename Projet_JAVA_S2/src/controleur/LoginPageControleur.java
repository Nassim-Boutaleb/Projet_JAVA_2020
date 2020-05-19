/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Modele.*;
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
               
                JOptionPane.showMessageDialog(logPage,"Connexion réussie. Bonjour "+ut.getPrenom()+" "+ut.getNom());
           
                // Vérfier de qui il s'agit
                if (ut.getDroit() == 1)  // etudiant
                {
                    // instancier un étudiant
                    
                    // appler la méthode pour fermer la page de login et appeler la page edt
                    logPage.dispose();
                    
                    // Créer nouveau controleur EdtControleur
                    
                    
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
