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
    
    /// LA BARRE DE MENU
    private JMenuBar M_barreDeMenus = new JMenuBar();  // barre de menus
    
    private JMenu JM_edt = new JMenu("Visualiser un emploi du temps");
    private JMenu JM_seances = new JMenu("Gérer les séances");
    private JMenu JM_cours = new JMenu("Gérer les matières");
    private JMenu JM_reporting = new JMenu("Module reporting");
    
    private JMenuItem JMI_edt1 = new JMenuItem("Selectionner un emploi du temps");
    private JMenuItem JMI_seances1 = new JMenuItem("Ajouter une séance");
    private JMenuItem JMI_seances2 = new JMenuItem("Modifier une séance");
    private JMenuItem JMI_seances3 = new JMenuItem("Supprimer une séance");
    private JMenuItem JMI_cours1 = new JMenuItem("Ajouter une matière");
    private JMenuItem JMI_cours2 = new JMenuItem("Modifier une matière");
    private JMenuItem JMI_cours3 = new JMenuItem("Supprimer une matière");
    
    
    
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
        // Border layout pour le panel global
        panelGlobal.setLayout(new BorderLayout());
        
        // dans la partie nord: on va ajouter la barre de menu
        // Mettre les parties du menu dans leurs catégories
        JM_edt.add(JMI_edt1);
        JM_seances.add(JMI_seances1);
        JM_seances.add(JMI_seances2);
        JM_seances.add(JMI_seances3);
        JM_cours.add(JMI_cours1);
        JM_cours.add(JMI_cours2);
        JM_cours.add(JMI_cours3);
        
        
        //Ajouter les menus dans la barre de menu de gauche à droite
        M_barreDeMenus.add(JM_edt);
        M_barreDeMenus.add(JM_seances);
        M_barreDeMenus.add(JM_cours);
        M_barreDeMenus.add(JM_reporting);
        M_barreDeMenus.setBackground(Color.blue);
        
        // Ajouter la barre de menu dans la partie nord du panel global
        panelGlobal.add(M_barreDeMenus, BorderLayout.NORTH);
        
        
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
