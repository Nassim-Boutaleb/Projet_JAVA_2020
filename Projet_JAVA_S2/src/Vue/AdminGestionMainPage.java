/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import controleur.*;

/**
 *
 * @author Nassim
 */
public class AdminGestionMainPage extends JFrame implements ActionListener
{
    private JPanel panelGlobal;  // panel global
    private AdminGestionControleur controleur ;
    
    private int WINDOW_WIDTH = 900;
    private int WINDOW_HEIGHT = 900;
    
    public AdminGestionMainPage (int height, int width, AdminGestionControleur cont)
    {
        // stocker le controleur
        this.controleur = cont;
        
        // créer le panel global
        panelGlobal = new JPanel();
        
        // Titre de la fenetre
        setTitle ("Administrateur");
        
        //Quitter le programme si on clique sur la croix 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // stocker les tailles à partir des parametres
        WINDOW_WIDTH = width;
        WINDOW_HEIGHT = height;
        
        // régler la taille
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        
        // construire le panel global
        buildPanel();
        
        // Ajouter le panel global à la frame
        add(panelGlobal);
        
        // Nécéssaire pour l'image de fond
        pack();
    }
    
    public void buildPanel()
    {
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
