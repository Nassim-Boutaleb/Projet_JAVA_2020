/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import controleur.LoginPageControleur;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Nassim
 */
public class LoginPage extends JFrame implements ActionListener
{
    private Image m_img;
    private JPanel panelGlobal;  // panel global
    private ImagePanel panelImage;  // panel de l'image

    private JButton B_connexion; 
    private JTextField T_email;
    private JTextField T_passwd;
    private JLabel L_email;
    private JLabel L_passwd ;
      
    private LoginPageControleur lpc ;  // stocker le controleur
    
    
    private int WINDOW_WIDTH = 900;
    private int WINDOW_HEIGHT = 900;
    
    
    public LoginPage (int width , int height , LoginPageControleur lpc)
    {
        // stocker le controleur
        this.lpc = lpc;
        
        // créer le panel global
        panelGlobal = new JPanel();
        
        // Titre de la fenetre
        setTitle ("Connexion");
        
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
    
    public void buildPanel ()
    {
        // le panel global sera constitué d'une border layout
        panelGlobal.setLayout(new BorderLayout());
        
        // Dans la région gauche : l'image donc on y ajoute le panel de type ImagePanel
        panelImage = new ImagePanel(
        new ImageIcon("Images/inseec_log.png").getImage());

        panelGlobal.add(panelImage,BorderLayout.WEST);


        //Créer les zones de texte
        //T_email = new JTextField("Entrez votre email ECE ",30);
        //T_passwd = new JTextField("Entrez votre mot de passe", 30);
        T_email = new JTextField("nassim.boutaleb@ece.fr");
        T_passwd = new JTextField("nassim");
        
        // Créer le bouton
        B_connexion = new JButton("Login");
        B_connexion.setBackground(Color.blue);
        B_connexion.setSize(90,60);  // W - H
       
        // Créer les labels
        L_email = new JLabel("Email ECE");
        L_passwd = new JLabel("Mot de passe");
        
        // réglages police
        Font font = new Font("Arial",Font.BOLD,15);
        L_email.setFont(font);
        L_passwd.setFont(font);
        
        //Ajouter un écouteur au bouton
        B_connexion.addActionListener(this);
        
        // Créer un second panel qui sera dans la partie est du grand panel
        JPanel contenuLog = new JPanel();
        contenuLog.setBackground(Color.white);
        
        // Lui ajouter une grid bag layout
        contenuLog.setLayout(new GridBagLayout());
        GridBagConstraints constr = new GridBagConstraints();  // contraintes de grille
        
        // Ajouter les éléments
        constr.insets = new Insets(5, 0, 5, 0);  // Top-left_bottom_right  marges
        constr.anchor = GridBagConstraints.CENTER;
        
        
        constr.gridx = 0;  constr.gridy = 1;  // se positionner
        contenuLog.add(L_email,constr);
        
        constr.gridy = 2;  // se positionner
        contenuLog.add(T_email,constr);
        
        constr.gridy = 3;  // se positionner
        contenuLog.add(L_passwd,constr);
        
        constr.gridy = 4;  // se positionner
        contenuLog.add(T_passwd,constr);
        
        constr.gridy = 5;  // se positionner
        contenuLog.add(B_connexion,constr);
        
        // Ajouter le panel des contenus au reste du layout
        panelGlobal.add(contenuLog);
        
        // Au clic : enlever les indications
        T_email.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                
               if(T_email.getText().equals("Entrez votre email ECE ")){ 
                  T_email.setText("");
               }
            }
        });
        T_passwd.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                
               if(T_passwd.getText().equals("Entrez votre mot de passe")){ 
                  T_passwd.setText("");
               }
            }
        });
    }
    

    @Override
    public void actionPerformed (ActionEvent e)
    {
        //dispose (); // fermer la fenetre
        
        // récupérer le login et le password
        String email = T_email.getText();
        String passwd = T_passwd.getText();
        
        // Appel à la méthode du controleur pour vérifier les informations de login
        // et charger l'utilisateur
        lpc.verifierLogin(email,passwd);

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

        @Override
        public void paintComponent(Graphics g) 
        {
          g.drawImage(m_img, 0, 0, null);
        }

    
    }

}
