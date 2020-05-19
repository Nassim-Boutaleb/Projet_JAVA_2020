/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Vue.*;
import java.awt.*;


/**
 *
 * @author Nassim
 */
public class AdminGestionControleur 
{
    private AdminGestionMainPage adminPage ;  // stockage de la vue (page graphique)
    private int width;
    private int height;
    
    public AdminGestionControleur ()
    {
        // définir taille des fenetres
        //Récupérer taille utile de l'écran
        GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
         
        //Stocker cette taille
        Rectangle maximumWindowBounds=graphicsEnvironment.getMaximumWindowBounds();

        // récupérer la largeur et la heuteur
        width = (int)maximumWindowBounds.getWidth();
        height = (int)maximumWindowBounds.getHeight();
    }
    
    public void ouvrirAdminpage ()
    {
        adminPage = new AdminGestionMainPage(height,width,this);
        adminPage.setVisible (true);
    }
}
