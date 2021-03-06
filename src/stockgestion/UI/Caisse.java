package stockgestion.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map.Entry;
import stockgestion.Controlleur.ArticleControlleur;
import stockgestion.Entite.Article;
import javax.swing.table.DefaultTableModel;
import stockgestion.Controlleur.CaisseControlleur;
import stockgestion.Controlleur.ClientControlleur;
import stockgestion.Entite.Client;
import stockgestion.Outil.Verificateur;


public class Caisse extends javax.swing.JFrame {
    
private static Caisse instance = null;
private static stockgestion.Entite.Caisse caisse;
private Client client;
private boolean tiroirOuvert;
private boolean retourBool;
private boolean totalBool;
private Article article;

    private Caisse() {
        initComponents();
        addActionListeners();
        setTitle("Caisse");

        client = new Client();
        totalBool = false;
        retourBool = false;
        tiroirOuvert = false;
        tiroirButton.setText("Fermé");
    }
    
    public static Caisse getInstance(stockgestion.Entite.Caisse pcaisse){
        if(instance == null){
            instance = new Caisse();
            caisse = pcaisse;
        }
        return instance;
    }
    
    private void addActionListeners(){
        
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Caisse.this.setVisible(false);
                SessionCaisse.getInstance().setVisible(true);
                totalBool = false;
                retourBool = false;
                CaisseControlleur.getInstance().fermer(caisse);
                clearUI();
                ClientControlleur.getInstance().resetArticles(client);
            }
        });
        
        randomizer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                article = ArticleControlleur.getInstance().getRandomArticle();
                codeBarre.setText(String.valueOf(article.getCodeBarre()));
                
                passageArticle();
            }
        });
        
        enter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                article = null;
                if(!(codeBarre.getText().isEmpty()) && Verificateur.isValidInt(codeBarre.getText())){
                    
                    for(Article a : ArticleControlleur.getInstance().getAllArticles()){
                        if(a.getCodeBarre()== Integer.parseInt(codeBarre.getText())){
                            article = ArticleControlleur.getInstance().getArticleByCodebarre(Integer.parseInt(codeBarre.getText()));
                            break;
                        }
                    }
                }
                
                passageArticle();
                
            }
        });
        
        totalButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ouvrirTiroir();
            }
            
            
        });
        
        totalButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount()==2){
                    totalBool = true;
                    totalButton.setForeground(Color.red);
                    ouvrirTiroir();
                    total.setText(String.valueOf(CaisseControlleur.getInstance().calculerSomme(caisse)));
                    tableTotalCaisse();
                }
            }
        });
        
        tiroirButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(tiroirOuvert) // fermeture du tiroir
                {
                    fermerTiroir();
                    retourArticle.setEnabled(true);
                    
                    if(retourBool){
                        clearUI();
                        //Client client2 = new Client();
                        //ClientControlleur.getInstance().retournerArticle(client2, article);
                        //CaisseControlleur.getInstance().ajouterClient(caisse, client2);
                        CaisseControlleur.getInstance().ajouterClient(caisse, client);
                        client = new Client();
                        retourBool = false;
                        retourArticle.setForeground(Color.black);
                        
                    }else if(totalBool){
                        totalBool = false;
                        totalButton.setForeground(Color.black);
                        clearUI();
                        caisse = new stockgestion.Entite.Caisse();
                        client = new Client();
                    }else{
                        clearUI();
                        ClientControlleur.getInstance().terminerVente(client);
                        
                        CaisseControlleur.getInstance().ajouterClient(caisse, client);
                        client = new Client();
                    }
                }
            }
        });
        
        retourArticle.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                retourBool = true;
                retourArticle.setForeground(Color.red);
            }
        });
    }
    
    private void passageArticle(){
        if(article == null){
            codeBarreEtat.setForeground(Color.red);
            codeBarreEtat.setText("Code barre non reconnu");

        }else{
            retourArticle.setEnabled(false);
            codeBarreEtat.setForeground(Color.BLACK);
            codeBarreEtat.setText(article.getNom() + "\t\t" + article.getPrix());
            if(retourBool){
                ClientControlleur.getInstance().retournerArticle(client, article);
                updateTable();
                total.setText(String.valueOf(ClientControlleur.getInstance().calculerSomme(client)));
                ouvrirTiroir();
            }else{
                ClientControlleur.getInstance().ajouterArticle(client, article.getId());
                updateTable();
                total.setText(String.valueOf(ClientControlleur.getInstance().calculerSomme(client)));
            }
        } 
    }
    
    private void ouvrirTiroir(){
        tiroirOuvert = true;
        tiroirButton.setForeground(Color.red);
        tiroirButton.setText("Ouvert");
    }
    
    private void fermerTiroir(){
        tiroirOuvert = false;
        tiroirButton.setForeground(Color.black);
        tiroirButton.setText("Fermé");
    }
    
    private void clearUI(){
        codeBarre.setText(null);
        codeBarreEtat.setText(null);
        total.setText(null);
        retourBool = false;
        retourArticle.setForeground(Color.black);
        resetTable();
    }
    
    private void updateTable(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for(Entry <Article, Integer> entry : client.getListArticles().entrySet()){
            model.addRow(new Object[]{entry.getKey().getNom(), entry.getValue(), entry.getKey().getPrix()});
        }
        table.setModel(model);
    }
    
    private void tableTotalCaisse(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for(Client c : caisse.getListClients()){
            for(Entry <Article, Integer> entry : c.getListArticles().entrySet()){
                model.addRow(new Object[]{entry.getKey().getNom(), entry.getValue(), entry.getKey().getPrix()});
            }
        }
        
        table.setModel(model);
    }
    
    private void resetTable(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        table.setModel(model);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        back = new javax.swing.JButton();
        codeBarreLabel = new javax.swing.JLabel();
        retourArticle = new javax.swing.JButton();
        enter = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        totalButton = new javax.swing.JButton();
        codeBarreEtat = new javax.swing.JTextField();
        total = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tiroirButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        codeBarre = new javax.swing.JTextField();
        randomizer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        back.setText("Fermer la caisse");

        codeBarreLabel.setText("Référence Article");

        retourArticle.setText("Retourner un article");

        enter.setText("Entrer manuellement");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Article", "Quantité", "Prix Unitaire"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
        }

        totalButton.setText("Terminer");

        codeBarreEtat.setEditable(false);

        total.setEditable(false);
        total.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        total.setFocusable(false);

        jLabel1.setText("Total");

        jLabel2.setText("Etat du tiroir");

        randomizer.setText("Passer un article");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(totalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(retourArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(codeBarreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(codeBarre, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(randomizer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(enter, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(codeBarreEtat, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tiroirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(5, 5, 5)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(retourArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tiroirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(codeBarreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(codeBarre, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(randomizer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(enter, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(codeBarreEtat))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JTextField codeBarre;
    private javax.swing.JTextField codeBarreEtat;
    private javax.swing.JLabel codeBarreLabel;
    private javax.swing.JButton enter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton randomizer;
    private javax.swing.JButton retourArticle;
    private javax.swing.JTable table;
    private javax.swing.JButton tiroirButton;
    private javax.swing.JTextField total;
    private javax.swing.JButton totalButton;
    // End of variables declaration//GEN-END:variables
}
