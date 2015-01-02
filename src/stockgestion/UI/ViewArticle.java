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
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
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
public class ViewArticle extends javax.swing.JFrame {
    private static ViewArticle instance = null;
    private Article article;
    private List<Fournisseur> listFournisseurs;
    private ArrayList<Fournisseur> selectedFournisseur;
    private boolean isViewMode = true;

    private ViewArticle() {
        initComponents();
        selectedFournisseur = new ArrayList<>();
        addActionListeners();
    }
    
    public static ViewArticle getInstance(){
        if(instance == null){
            instance = new ViewArticle();
        }
        return instance;
    }
    
    private void addActionListeners(){
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ViewArticle.this.setVisible(false);
                Inventaire.getInstance().setVisible(true);
                stockgestion.StockGestion.getInstance().refreshUI();
            }
        });
        
        editer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(isViewMode){
                    editArticleMode();
                } else{
                    //Ajouter un article dans la BDD
                    article.setNom(nom.getText());
                    article.setCodeBarre(Integer.parseInt((codeBarre.getText())));
                    article.setPrix(Double.parseDouble(prixUnitaire.getText()));
                    article.setQuantite(Integer.parseInt(quantite.getText()));
                    article.setSeuilDeReassortiment(Integer.parseInt(seuilCommander.getText()));
                    article.setTypeDeVente(poids.isSelected());
                    article.setListFournisseur(selectedFournisseur);

                    ArticleControlleur.getInstance().editer(article);
                    
                    viewArticleMode(article);
                }
            }
        });
        
        fournisseur.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] indexes = fournisseur.getSelectedIndices();
                selectedFournisseur.clear();
                for(int i=0; i<indexes.length; i++){
                    selectedFournisseur.add(listFournisseurs.get(indexes[i]));
                }

            }
        });
        
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem viewItem = new JMenuItem("Consulter le fournisseur");
        popupMenu.add(viewItem);
        fournisseur.setComponentPopupMenu(popupMenu);
        viewItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ViewArticle.this.setVisible(false);
                ViewFournisseur.getInstance().viewFournisseurMode(listFournisseurs.get(fournisseur.getSelectedIndex()));
                ViewFournisseur.getInstance().setVisible(true);
            }
        });
    }
    
    public void viewArticleMode(Article article){
        isViewMode = true;
        this.article = article;
        
        if(article.isTypeDeVente()) buttonGroup.setSelected(poids.getModel(), true);
        else buttonGroup.setSelected(unite.getModel(), true);
        poids.setEnabled(false);
        
        codeBarre.setText(article.getCodeBarre()+"");
        codeBarre.setEditable(false);
        
        nom.setText(article.getNom());
        nom.setEditable(false);
        
        prixUnitaire.setText(article.getPrix()+"");
        prixUnitaire.setEditable(false);
        
        quantite.setText(article.getQuantite()+"");
        quantite.setEditable(false);
        
        seuilCommander.setText(article.getSeuilDeReassortiment()+"");
        seuilCommander.setEditable(false);
        
        fournisseur.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        List<Fournisseur> list = article.getListFournisseur();
        int[] indices = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            indices[i] = list.get(i).getId() - 1;
        }
        fournisseur.setSelectedIndices(indices);
        fournisseur.setEnabled(false);
        
        ajouterFournisseur.setVisible(false);
        
        editer.setText("Editer");
        
        setTitle("Article");
    }
    
    public void editArticleMode(){
        isViewMode = false;
        poids.setEnabled(true);        
        codeBarre.setEditable(true);        
        nom.setEditable(true);        
        prixUnitaire.setEditable(true);        
        quantite.setEditable(true);        
        seuilCommander.setEditable(true); 
        fournisseur.setEnabled(true);
        ajouterFournisseur.setVisible(true);
        editer.setText("OK");        
        setTitle("Editer l'article");
    }
    
    public void refreshListeFournisseur(List<Fournisseur> list){
        this.listFournisseurs = list;
        DefaultListModel model = new DefaultListModel();
        for(Fournisseur f : list){
            model.addElement(f.getNom());
        }
        fournisseur.setModel(model);
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
        fournisseurLabel = new javax.swing.JLabel();
        ajouterFournisseur = new javax.swing.JButton();
        nom = new javax.swing.JTextField();
        prixUnitaire = new javax.swing.JTextField();
        typeVenteLabel = new javax.swing.JLabel();
        codeBarre = new javax.swing.JTextField();
        poids = new javax.swing.JRadioButton();
        quantite = new javax.swing.JTextField();
        unite = new javax.swing.JRadioButton();
        seuilCommander = new javax.swing.JTextField();
        codeBarreLabel = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        nomLabel = new javax.swing.JLabel();
        prixUnitaireLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        fournisseur = new javax.swing.JList();
        quantiteLabel = new javax.swing.JLabel();
        seuilCommanderLabel = new javax.swing.JLabel();
        explainFournisseur = new javax.swing.JLabel();
        editer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fournisseurLabel.setText("Fournisseur");

        ajouterFournisseur.setText("Nouveau");

        typeVenteLabel.setText("Type de vente");

        buttonGroup.add(poids);
        poids.setText("poids");

        buttonGroup.add(unite);
        unite.setText("unité");

        codeBarreLabel.setText("Code barre");

        back.setText("Retourner");

        nomLabel.setText("Nom");

        prixUnitaireLabel.setText("Prix unitaire");

        jScrollPane1.setViewportView(fournisseur);

        quantiteLabel.setText("Quantité");

        seuilCommanderLabel.setText("Seuil de réapprovisionnement");

        explainFournisseur.setText("Sélection multiple: Ctrl/Maj + souris");

        editer.setText("Editer");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(codeBarreLabel)
                            .addComponent(typeVenteLabel)
                            .addComponent(nomLabel)
                            .addComponent(prixUnitaireLabel)
                            .addComponent(quantiteLabel)
                            .addComponent(seuilCommanderLabel)
                            .addComponent(fournisseurLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(poids)
                                    .addGap(94, 94, 94)
                                    .addComponent(unite)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(editer, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(codeBarre, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(nom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(prixUnitaire, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(quantite, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seuilCommander, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ajouterFournisseur, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(explainFournisseur)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(130, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(ajouterFournisseur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                    .addComponent(fournisseurLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(explainFournisseur)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(editer, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewArticle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewArticle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewArticle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewArticle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewArticle().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ajouterFournisseur;
    private javax.swing.JButton back;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JTextField codeBarre;
    private javax.swing.JLabel codeBarreLabel;
    private javax.swing.JButton editer;
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
