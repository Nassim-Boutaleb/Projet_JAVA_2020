/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import DAO.UtilisateurDAO;
import Modele.Utilisateur;
import controleur.EdtControleur;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;


/**
 *
 * @author pezi
 */
public class EdtPage extends JFrame implements ActionListener{
    private GridLayout calendar;
    private JPanel panelGlobal;// panels
    private JPanel panelliste;
    private JPanel panelmenu; 
    private JPanel panelrecherche;
    private JPanel panelsemaines;
    private JPanel panelmois;
    private JPanel paneltableau;
    private JPanel panelgrille;
    private JPanel panelheure;
    private JComboBox affichage;
    private JComboBox recherche;
    private JButton rechercher;
    private JTextField saisieRecherche;
    private JMenuBar menubar;
    private EdtControleur edtc ;  // stocker le controleur
    private int WINDOW_WIDTH = 1280;
    private int WINDOW_HEIGHT = 660;


public EdtPage (int width , int height , EdtControleur edtC)
    {
        panelGlobal = new JPanel();
        // stocker le controleur
        this.edtc = edtC;

        // créer les différents panels
        panelliste = new JPanel();
        panelmenu = new JPanel();
        //panelmenu.setBackground(Color.red);
        panelrecherche=new JPanel();
        panelsemaines=new JPanel();
        panelmois=new JPanel();
        paneltableau=new JPanel();
        panelgrille=new JPanel();

        // Titre de la fenetre
        setTitle ("Emploi du temps");

        //Quitter le programme si on clique sur la croix
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // stocker les tailles à partir des parametres
       // WINDOW_WIDTH = width;
       // WINDOW_HEIGHT = height;

        // régler la taille
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        // construire le panel global
        buildPanelmenu();
        buildPanelrecherche();
        buildPanelsemaines();
        buildPaneltableau();
        buildpanelliste(); 
        //definir les tailles des panels

        // Ajouter le panel global à la frame
        panelGlobal.setLayout(new BoxLayout(panelGlobal,BoxLayout.PAGE_AXIS));
        //GridBagConstraints c = new GridBagConstraints();
        //c.fill =GridBagConstraints.VERTICAL;
        //c.gridwidth = 100;
        //c.gridheight=40;
        //c.gridx =0;
        //c.gridy=0;

        panelGlobal.add(panelmenu);
        /*c.gridwidth = 100;
        c.gridheight=20;
        c.gridx =0;
        c.gridy=50; */
        panelGlobal.add(panelrecherche);
        /*c.gridwidth = 100;
        c.gridheight=20;
        c.gridx =0;
        c.gridy=100; */
        panelGlobal.add(panelsemaines);
        /*c.gridwidth =100 ;
        c.gridheight=20;
        c.gridx =0;
        c.gridy=120; */
        //panelGlobal.add(panelmois);
        /*c.gridwidth = 200;
        c.gridheight=200;
        c.gridx =0;
        c.gridy=150; */
       
        //panelGlobal.add(paneltableau);
        //JPanel pp = new JPanel();
        /*pp.add (new JLabel ("Graaaace")); 
        pp.setBackground(Color.red);
        panelGlobal.add (pp); */
        panelGlobal.add(panelliste);


        add(panelGlobal);

        // Nécéssaire pour l'image de fond
        //pack();




    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void buildPanelmenu() {

        menubar = new JMenuBar();
        JMenu edt = new JMenu("Emploi du temps ");
        JMenu cours = new JMenu("Récapitulatif des cours");
        JMenuItem size = new JMenuItem("size");
        edt.add(size);
        cours.add(size);
        menubar.add(edt);
        menubar.add(cours);
        panelmenu.add(menubar);

    }
    private void buildPanelrecherche(){


        String[] choices1 = { "En grille","En liste"};
        String[] choices2 = { "Saisie du nom"};
        affichage = new JComboBox(choices1);
        recherche = new JComboBox(choices2);
        rechercher = new JButton("Recherche");
        saisieRecherche = new JTextField("",30);
        panelrecherche.add(affichage);
        panelrecherche.add(recherche);
        panelrecherche.add(saisieRecherche);
        panelrecherche.add(rechercher);


    }
    private void buildPanelsemaines()
    {
        Font font = new Font("Arial",Font.PLAIN,11);
        
        String[] numerosSemaine = new String [500];
        int k = 0;
        for(int i=31;i<53;i++)
        {
            numerosSemaine[k] = new String(""+i+"   ");
            k++;
            
           if(i==52){
               i=0;
           }
           if(i==30){
               i=54;
           }
        }
        JList<String> lisetSemaines = new JList(numerosSemaine);
        lisetSemaines.setPreferredSize(new Dimension(1250,20));
        lisetSemaines.setBackground(Color.lightGray);
        lisetSemaines.setFont(font);
        lisetSemaines.setVisibleRowCount(-1);
        //lisetSemaines.setBackground(Color.red);
        lisetSemaines.setLayoutOrientation(JList.HORIZONTAL_WRAP);
       
        panelsemaines.add(lisetSemaines);
        //panelsemaines.setBackground(Color.green);
        //panelsemaines.setPreferredSize(new Dimension(1250,30));

        
        
        String[] mois = { "Aout","Septembre","Octobre","Novembre","Decembre","Janvier","Fevrier","Mars","Avril","Mai","Juin","juillet"};
        for(int i=0;i<12;i++){
           panelmois.add(new JButton(mois[i]));

        }
               for(Component c : panelmois.getComponents()){
            if(c instanceof JButton){
                ((JButton)c).setPreferredSize(new Dimension(100,20));
            }
        }
               panelsemaines.add(panelmois);

    }
    
 private void buildPaneltableau(){
     
     // panel des heures (hope it will work !)
     //paneltableau.setLayout(new GridBagLayout());
     GridBagConstraints consTableau = new GridBagConstraints();
     //paneltableau.setBackground(Color.blue);
     panelheure = new JPanel();
     //panelheure.setBackground(Color.red);
     //panelheure.setPreferredSize(new Dimension(1200,30));
     panelheure.setLayout(new GridBagLayout() );
     GridBagConstraints cons = new GridBagConstraints();
    String[] heures ={"                      8h30","  10h00 ","10h15","11h45 ","12h00","13h30 ","13h45","15h15 ","15h30","17h00 ","17h15","18h45 ","19h00","    20h30"};
    cons.gridy = 0;
    cons.gridx = 0;

    
    
   for (int i = 0; i <heures.length ; i++) 
     {
           JLabel lab = new JLabel(heures[i]);
           lab.setFont(new Font ("Dialog",Font.BOLD,12));
           panelheure.add(lab); 
           cons.gridx ++;
           
           if (i % 2 == 0)
           {
                for (int j = 0; j < 20; j++)
                {
                    panelheure.add(new JLabel (" "));
                    cons.gridx ++; 
                } 
           }
           
           
           
     }     
     
     
     
     
     
        //panelgrille.setSize( 400,400 );
         panelgrille.setLayout( new GridBagLayout() );
         //panelgrille.setLayout(new GridLayout());
         GridBagConstraints c = new GridBagConstraints();
         c.fill =GridBagConstraints.HORIZONTAL;


        //Container container = panelgrille.getContentPane();

        ArrayList < JPanel > components = new ArrayList < JPanel >();
        JPanel temp = null;
        String[] jours = { "Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"};
        // Populating Arraylist object.
        //c.gridwidth = 1;
        for(int i =0;i<6;i++)
        {
          temp = new JPanel();
          
          temp.add(new JLabel(jours[i]));
          c.gridy = i;
          c.gridx = 0;
          panelgrille.add(temp,c);
          int z =1;
          while(z<8)
          {
            c.gridx = z;
            temp= new JPanel();
            javax.swing.border.Border line =  BorderFactory.createLineBorder(Color.white);
            JPanel panel = new JPanel();
            LayoutManager layout = new FlowLayout(); 
            panel.setLayout(layout);       
            String spaces = "                   ";

            temp.add(new JLabel(""+(components.size()+1))); 

            temp.setPreferredSize(new Dimension(160, 60));
            temp.setBackground(Color.lightGray);
            temp.setBorder(line);
            components.add(temp);

            //container.add(temp);
            panelgrille.add(temp,c);
            z++;
          } 
        }
        JPanel p = new JPanel();
        JPanel m = components.get(5);
        panelgrille.remove(m);
        p.add(new JLabel("5"));
        p.setPreferredSize(new Dimension(160, 60));

        //components.set(5,p);
        p.setBackground(Color.blue);
        c.gridx =6;
        c.gridy=0;
        //c.gridwidth = 1;
        panelgrille.add(p,c);

        /*for(Component e : panelgrille.getComponents()){
            if(e instanceof JPanel){
                ((JPanel)e).setPreferredSize(new Dimension(160,60));
            }
        } */
        //JScrollPane scroll = new JScrollPane(temp);


        //components.get( 5 ).setBackground( Color.BLUE );
        //components.get( 8 ).setBackground( Color.GREEN );
        consTableau.gridx = 0;
        consTableau.gridy = 0;
        paneltableau.add(panelheure);
        
        consTableau.gridy = 1;
        paneltableau.add(panelgrille);
        
        //paneltableau.setPreferredSize(new Dimension(1250,5));
    }
     private void buildpanelliste() {
            JPanel semaine=new JPanel();
            JPanel jour=new JPanel();
            JPanel cours=new JPanel();
            JPanel date= new JPanel();
            JPanel carre= new JPanel();
            JPanel heure= new JPanel();
            JPanel titre= new JPanel();
            JPanel groupe= new JPanel();
            JPanel salle= new JPanel();
            JPanel format= new JPanel();

            date.add(new JLabel("date"));
            carre.add(new JLabel("carre"));
            heure.add(new JLabel("heure"));
            titre.add(new JLabel("titre"));
            groupe.add(new JLabel("groupe"));
            salle.add(new JLabel("salle"));
            format.add(new JLabel("format"));
            
            jour.setBorder(BorderFactory.createLineBorder(Color.black));
            semaine.setBorder(BorderFactory.createLineBorder(Color.red));
            //cours.setPreferredSize(new Dimension(400,60));
            semaine.setLayout(new GridLayout(3,1));
            jour.setLayout(new GridBagLayout());
            date.setBackground(Color.red);
            cours.setBackground(Color.blue);
            cours.setLayout(new GridBagLayout());
            GridBagConstraints e = new GridBagConstraints();
            GridBagConstraints o = new GridBagConstraints();
            
            e.fill =GridBagConstraints.HORIZONTAL;
            o.fill =GridBagConstraints.HORIZONTAL;
           
            e.gridx=0;
            e.gridy=5;
            cours.add(carre,e);
            
            e.gridx=5;
            cours.add(heure,e);
            
            e.gridx=10;
           cours.add(titre,e);
           
           e.gridx=15;
           cours.add(groupe,e);
           e.gridx=20;
           cours.add(salle,e);
           e.gridx=25;
           cours.add(format,e);
           o.gridx=0;
           o.gridy=0;
           jour.add(date,o);
           o.gridy=5;
           jour.add(cours,o);
           semaine.add(jour);
           panelliste.add(semaine);
            
        
       

    }
}