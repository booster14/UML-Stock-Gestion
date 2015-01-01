/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockgestion.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import stockgestion.Controlleur.ArticleControlleur;
import stockgestion.Entite.Article;
import stockgestion.Entite.Fournisseur;

/**
 *
 * @author rubeus
 */
public class AjouterArticle extends javax.swing.JFrame {
    private static AjouterArticle instance = null;
    private ArrayList<Fournisseur> selectedFournisseur;
    private List<Fournisseur> listFournisseur;
    
    private AjouterArticle() {
        initComponents();
        setTitle("Ajouter un article");
        selectedFournisseur = new ArrayList<>();
        addActionListeners();
    }
    
    public static AjouterArticle getInstance(){
        if(instance == null){
            instance = new AjouterArticle();
        }
        return instance;
    }
    
    private void addActionListeners(){
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AjouterArticle.this.setVisible(false);
                InterfaceUtilisateur.getInstance().setVisible(true);
            }
        });
        
        ajouter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //Ajouter un article dans la BDD
                Article article = new Article();
                article.setNom(nom.getText());
                article.setCodeBarre(Integer.parseInt((codeBarre.getText())));
                article.setPrix(Integer.parseInt(prixUnitaire.getText()));
                article.setQuantite(Integer.parseInt(quantite.getText()));
                article.setSeuilDeReassortiment(Integer.parseInt(seuilCommander.getText()));
                article.setTypeDeVente(poids.isSelected());
                article.setListFournisseur(selectedFournisseur);
                
                ArticleControlleur.getInstance().ajouter(article);
                
                //Remettre a zero les champs
                resetUI();
                
                //Fermer cette fenetre
                InterfaceUtilisateur.getInstance().retournerEcranAccueil(AjouterArticle.this);
            }
        });
        
        fournisseur.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        fournisseur.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] indexes = fournisseur.getSelectedIndices();
                selectedFournisseur.clear();
                for(int i=0; i<indexes.length; i++){
                    selectedFournisseur.add(listFournisseur.get(indexes[i]));
                }

            }
        });
        
        ajouterFournisseur.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AjouterArticle.this.setVisible(false);
                NouveauFournisseur.getInstance().setVisible(true);
            }
        });
    }
    
    public void refreshListeFournisseur(List<Fournisseur> list){
        this.listFournisseur = list;
        DefaultListModel model = new DefaultListModel();
        for(Fournisseur f : list){
            model.addElement(f.getNom());
        }
        fournisseur.setModel(model);
    }
    
    private void resetUI(){
        nom.setText("");
        codeBarre.setText("");
        prixUnitaire.setText("");
        quantite.setText("");
        seuilCommander.setText("");
        poids.setText("");        
        buttonGroup.clearSelection();
        fournisseur.clearSelection();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        typeVenteLabel = new javax.swing.JLabel();
        poids = new javax.swing.JRadioButton();
        unite = new javax.swing.JRadioButton();
        codeBarreLabel = new javax.swing.JLabel();
        nomLabel = new javax.swing.JLabel();
        prixUnitaireLabel = new javax.swing.JLabel();
        quantiteLabel = new javax.swing.JLabel();
        seuilCommanderLabel = new javax.swing.JLabel();
        fournisseurLabel = new javax.swing.JLabel();
        nom = new javax.swing.JTextField();
        prixUnitaire = new javax.swing.JTextField();
        codeBarre = new javax.swing.JTextField();
        quantite = new javax.swing.JTextField();
        seuilCommander = new javax.swing.JTextField();
        back = new javax.swing.JButton();
        ajouter = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        fournisseur = new javax.swing.JList();
        explainFournisseur = new javax.swing.JLabel();
        ajouterFournisseur = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        typeVenteLabel.setText("Type de vente");

        buttonGroup.add(poids);
        poids.setText("poids");

        buttonGroup.add(unite);
        unite.setText("unité");

        codeBarreLabel.setText("Code barre");

        nomLabel.setText("Nom");

        prixUnitaireLabel.setText("Prix unitaire");

        quantiteLabel.setText("Quantité");

        seuilCommanderLabel.setText("Seuil de réapprovisionnement");

        fournisseurLabel.setText("Fournisseur");

        back.setText("Retouner à l'écran d'accueil");

        ajouter.setText("Ajouter");

        jScrollPane1.setViewportView(fournisseur);

        explainFournisseur.setText("Sélection multiple: Ctrl/Maj + souris");

        ajouterFournisseur.setText("Nouveau");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ajouter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fournisseurLabel)
                            .addComponent(seuilCommanderLabel)
                            .addComponent(quantiteLabel)
                            .addComponent(prixUnitaireLabel)
                            .addComponent(nomLabel)
                            .addComponent(codeBarreLabel)
                            .addComponent(typeVenteLabel))
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nom)
                            .addComponent(prixUnitaire)
                            .addComponent(quantite)
                            .addComponent(codeBarre)
                            .addComponent(seuilCommander)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(explainFournisseur)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(poids)
                                        .addGap(100, 100, 100)
                                        .addComponent(unite)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(18, 18, 18)
                .addComponent(ajouterFournisseur, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeVenteLabel)
                    .addComponent(poids)
                    .addComponent(unite))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeBarreLabel)
                    .addComponent(codeBarre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomLabel)
                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prixUnitaireLabel)
                    .addComponent(prixUnitaire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantiteLabel)
                    .addComponent(quantite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seuilCommanderLabel)
                    .addComponent(seuilCommander, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ajouterFournisseur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fournisseurLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(explainFournisseur)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(ajouter, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ajouter;
    private javax.swing.JButton ajouterFournisseur;
    private javax.swing.JButton back;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JTextField codeBarre;
    private javax.swing.JLabel codeBarreLabel;
    private javax.swing.JLabel explainFournisseur;
    private javax.swing.JList fournisseur;
    private javax.swing.JLabel fournisseurLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nom;
    private javax.swing.JLabel nomLabel;
    private javax.swing.JRadioButton poids;
    private javax.swing.JTextField prixUnitaire;
    private javax.swing.JLabel prixUnitaireLabel;
    private javax.swing.JTextField quantite;
    private javax.swing.JLabel quantiteLabel;
    private javax.swing.JTextField seuilCommander;
    private javax.swing.JLabel seuilCommanderLabel;
    private javax.swing.JLabel typeVenteLabel;
    private javax.swing.JRadioButton unite;
    // End of variables declaration//GEN-END:variables
}
