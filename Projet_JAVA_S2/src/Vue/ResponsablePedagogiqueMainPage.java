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
public class ResponsablePedagogiqueMainPage extends JFrame implements ActionListener
{
    // Controleur
    private AdminGestionControleur controleur ; 
    
    private JPanel panelGlobal;  // panel global
    private ImagePanel panelImage;  // panel de l'image
    private Image m_img;
    
    private int WINDOW_WIDTH = 900;
    private int WINDOW_HEIGHT = 900;
    
    /// LA BARRE DE MENU
    private JMenuBar M_barreDeMenus = new JMenuBar();  // barre de menus
    
    private JMenu JM_edt = new JMenu("Visualiser un emploi du temps");

    
    private JMenuItem JMI_edt1 = new JMenuItem("Selectionner un emploi du temps");
    
    
    public ResponsablePedagogiqueMainPage (int height, int width, AdminGestionControleur cont)
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
        
        // régler la taille
        pack();
    }
    
    public void buildPanel()
    {
        // Border layout pour le panel global
        panelGlobal.setLayout(new BorderLayout());
        
        // dans la partie nord: on va ajouter la barre de menu
        // Mettre les parties du menu dans leurs catégories
        // ajouter les action listener sur chacun des items du menu
        JMI_edt1.addActionListener(this);
        JM_edt.add(JMI_edt1);
        
        
        


        
        
        //Ajouter les menus dans la barre de menu de gauche à droite
        M_barreDeMenus.add(JM_edt);
        M_barreDeMenus.setBackground(Color.white);
        
        // Ajouter la barre de menu (panelGlobal.add(M_barreDeMenus, BorderLayout.NORTH); )
        setJMenuBar(M_barreDeMenus);
        
        // Et dans le reste (partie sud) : une image ?
        panelImage = new ImagePanel(
        new ImageIcon("Images/eceOld.jpg").getImage());
        
        panelGlobal.add(panelImage);
        
         
        
    }
    
    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        // Clic sur sélectionner un emploi du temps
        if (e.getSource() == JMI_edt1)
        {
            controleur.selectionner_afficher_edt();
        }
        
        
        
    }
    
    
    private class ImagePanel extends JPanel 
    {
        public ImagePanel(String img) 
        {
          this(new ImageIcon(img).getImage());
        }

        public ImagePanel(Image imge) 
        {
            m_img = imge;
            Dimension size = new Dimension(m_img.getWidth(null), m_img.getHeight(null));
            
            
            //Dimension size = new Dimension(900,height);
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            

               
        }

        public void paintComponent(Graphics g) 
        {
          g.drawImage(m_img, 0, 0, null);
        }

    
    }
    
}
