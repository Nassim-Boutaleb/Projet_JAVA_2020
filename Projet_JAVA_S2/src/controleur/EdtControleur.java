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
 * @author pezi
 */
public class EdtControleur {
    private EdtPage EdtPage ;  // stockage de la vue (page graphique)
    private int width;
    private int height;


    public EdtControleur ()
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
        System.out.println(width);
        System.out.println(height);
        ouvrirEdt();

    }

    // Méthode pour ouvrir une page de login

    public void ouvrirEdt ()
    {
        // ouvrir et stocker la page de login et la rendre visible
        EdtPage = new EdtPage(width, height,this);

        EdtPage.setVisible(true);
    }



}