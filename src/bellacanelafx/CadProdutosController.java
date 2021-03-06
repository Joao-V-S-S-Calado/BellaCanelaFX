package bellacanelafx;

import bellacanela.db.dal.DALCategoria;
import bellacanela.db.dal.DALMedida;
import bellacanela.db.dal.DALProduto;
import bellacanela.util.MaskFieldUtil;
import bellacanelafx.db.entidades.Categoria;
import bellacanelafx.db.entidades.Medida;
import bellacanelafx.db.entidades.Produtos;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author FIGUEIRINHA
 */
public class CadProdutosController implements Initializable {

    @FXML
    private JFXButton btNovo;
    @FXML
    private JFXButton btAlterar;
    @FXML
    private JFXButton btApagar;
    @FXML
    private JFXButton btSalvar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private AnchorPane apDados;
    @FXML
    private JFXTextField tfCod;
    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfPreço;
    @FXML
    private VBox pnSearch;
    @FXML
    private JFXTextField tfSearch;
    @FXML
    private TableView<Produtos> tbProdutos;
    @FXML
    private TableColumn<Produtos, String> colCod;
    @FXML
    private TableColumn<Produtos, String> colNome;   
    @FXML
    private TableColumn<Produtos, String> colPreço;
    @FXML
    private TableColumn<Produtos, String> colCategoria;
    @FXML
    private TableColumn<Produtos, String> colMedida;
    @FXML
    private TableColumn<Produtos, Character> colAVenda;
    @FXML
    private JFXComboBox<Medida> cbMedida;
    @FXML
    private JFXComboBox<Categoria> cbCategoria;
    @FXML
    private SplitPane pane;
    private JFXTextField tfSearch2;
    @FXML
    private JFXComboBox<Categoria> cbCategoriaSearch;
    @FXML
    private JFXComboBox<Character> cbAVenda;
    @FXML
    private JFXComboBox<Character> cbCtrlEstoque;

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCod.setCellValueFactory(new PropertyValueFactory("cod"));
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colPreço.setCellValueFactory(new PropertyValueFactory("preco"));
        colCategoria.setCellValueFactory((TableColumn.CellDataFeatures<Produtos, String> p) -> new SimpleStringProperty(p.getValue().getCat().getNome()));
        colMedida.setCellValueFactory((TableColumn.CellDataFeatures<Produtos, String> p) -> new SimpleStringProperty(p.getValue().getMed().getNome()));
        colAVenda.setCellValueFactory(new PropertyValueFactory("AVenda"));
        
       fadeout();
       loadMasks();
       original();
    } 
    
    private void fadeout() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), this.pane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play(); 
    }
    
    private void loadMasks() {
        MaskFieldUtil.monetaryField(this.tfPreço);
        MaskFieldUtil.onlyAlfa(tfNome);
    }
    
    private void edition() {        
        this.pnSearch.setDisable(true);
        this.pnSearch.setOpacity(0.5);
        this.apDados.setDisable(false);
        this.apDados.setOpacity(1);
        
        this.btSalvar.setDisable(false);
        this.btApagar.setDisable(true);
        this.btAlterar.setDisable(true);
        this.tfSearch.clear();
        
        this.tfNome.requestFocus();
    }
    
    private void loadTable(String filtro) {
        DALProduto dal = new DALProduto();
        
        ArrayList<Produtos> Prod = dal.get(filtro);
      
        ObservableList<Produtos> modelo = FXCollections.observableArrayList(Prod);
        
        this.tbProdutos.setItems(modelo);
        
        this.tbProdutos.refresh();       
        
        
    }   
    
    private void loadCB (){
        ArrayList <Character> auxc = new ArrayList<>();
        auxc.add('S');
        auxc.add('N');
        this.cbAVenda.setItems(FXCollections.observableArrayList(auxc));
        this.cbCtrlEstoque.setItems(FXCollections.observableArrayList(auxc));
        
        this.cbCategoria.setItems(FXCollections.observableArrayList(new DALCategoria().get("")));
        this.cbMedida.setItems(FXCollections.observableArrayList(new DALMedida().get("")));  
        ArrayList<Categoria> aux = new DALCategoria().get("");
        aux.add(new Categoria (0, ""));
        
        this.cbCategoriaSearch.setItems(FXCollections.observableArrayList(aux));
        
        
        
        this.cbCategoria.setConverter(new StringConverter<Categoria>(){
            @Override
            public String toString(Categoria cat) {
                return cat.getNome();
            }

            @Override
            public Categoria fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            } 
            
            public Categoria toValue(Categoria cat){
               return cat;
            }
    
    
         });
        
        this.cbMedida.setConverter(new StringConverter<Medida>(){
            @Override
            public String toString(Medida cat) {
                return cat.getNome();
            }

            @Override
            public Medida fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
            public Medida toValue(Medida cat){
               return cat;
            }
    
         });
        
        this.cbCategoriaSearch.setConverter(new StringConverter<Categoria>(){
            @Override
            public String toString(Categoria cat) {
                return cat.getNome();
            }

            @Override
            public Categoria fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            } 
            
            public Categoria toValue(Categoria cat){
               return cat;
            }
    
    
         });
    }

    
    private void original() {
        this.pnSearch.setDisable(false);
        this.pnSearch.setOpacity(1);
        this.apDados.setDisable(true);
        this.apDados.setOpacity(0.5);
        
        this.btSalvar.setDisable(true);
        this.btCancelar.setDisable(false);
        this.btApagar.setDisable(true);
        this.btAlterar.setDisable(true);
        this.btNovo.setDisable(false);
        this.tfSearch.clear();
        
        ObservableList <Node> componentes = this.apDados.getChildren(); //”limpa” os componentes
        for(Node n : componentes)
        {
            if (n instanceof TextInputControl)  // textfield, textarea e htmleditor
                ((TextInputControl)n).setText("");
            if(n instanceof ComboBox)
                ((ComboBox)n).getItems().clear();
        }
        
        loadTable("");
        loadCB();;
    }
    
    private void snackbar(String message, String cor){
        JFXSnackbar sb = new JFXSnackbar(this.apDados);
        Label mens = new Label(message);
        mens.setStyle("-fx-text-fill: "+cor+";"
                    + "-fx-font-size: 14px;"
                    + "-fx-padding: 10px;");
        sb.enqueue(new JFXSnackbar.SnackbarEvent(mens));
    }  
    
    private String convertStr(String s){
        String dst = "";
        
        for(char c : s.toCharArray())
            if(c != '.')
                dst += c == ',' ? '.' : c;
        
        return dst;
    }    
    
    @FXML
    private void clkNovo(ActionEvent event) {
        this.edition();
    }

    @FXML
    private void clkAlterar(ActionEvent event) {
        if(this.tbProdutos.getSelectionModel().getSelectedItem() != null){            
            Produtos f = new DALProduto().get(this.tbProdutos.getSelectionModel().getSelectedItem().getCod());
            
            String strSal = f.getPreco()+"";
            int i = (strSal).indexOf(".");
            if(strSal.length() - i == 2)
                strSal += "0";

            this.tfCod.setText(f.getCod()+"");
            this.tfNome.setText(f.getNome());
            this.tfPreço.setText(strSal);
            
            this.cbAVenda.getSelectionModel().select(0);
            this.cbCtrlEstoque.getSelectionModel().select(0); 
            
            this.cbCategoria.getSelectionModel().select(0);
            this.cbMedida.getSelectionModel().select(0);
            
            this.cbCategoria.getSelectionModel().select(f.getCat());
            this.cbMedida.getSelectionModel().select(f.getMed());

           
            this.edition();
     }
    }

    @FXML
    private void clkApagar(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        
        a.setContentText("Realmente deseja excluir o Produto " + this.tfNome.getText() + "?");
        if(a.showAndWait().get() == ButtonType.OK){
            DALProduto dal = new DALProduto();
            
           
            if(dal.apagar(this.tbProdutos.getSelectionModel().getSelectedItem())){
                this.snackbar("Excluido com sucesso!", "green");
                
                this.original();
                this.loadTable("");
            }
            else{
                this.snackbar("Erro ao excluir.", "red");
            }
        }
    }

    @FXML
    private void clkSalvar(ActionEvent event) {
    
        
        DALProduto dal = new DALProduto();
        Produtos f;
        boolean flag = true;
        
        this.tfNome.setStyle("-fx-background-color: transparent");
        this.tfPreço.setStyle("-fx-background-color: transparent");
        this.cbCategoria.setStyle("-fx-background-color: transparent");
        this.cbMedida.setStyle("-fx-background-color: transparent");
        this.cbAVenda.setStyle("-fx-background-color: transparent");
        this.cbCtrlEstoque.setStyle("-fx-background-color: transparent");

        if("".equals(this.tfNome.getText())){
            this.tfNome.setStyle("-fx-background-color: #FF6347");
            flag = false;
        }
        if("".equals(this.tfPreço.getText())){
            this.tfPreço.setStyle("-fx-background-color: #FF6347");
            flag = false;
        }
        if(this.cbCategoria.getValue() == null){
            this.cbCategoria.setStyle("-fx-background-color: #FF6347");
            flag = false;
        }
        if(this.cbMedida.getValue() == null){
            this.cbMedida.setStyle("-fx-background-color: #FF6347");
            flag = false;
        }
        if(this.cbAVenda.getValue() == null){
            this.cbAVenda.setStyle("-fx-background-color: #FF6347");
            flag = false;
        }
        if(this.cbCtrlEstoque.getValue() == null){
            this.cbCtrlEstoque.setStyle("-fx-background-color: #FF6347");
            flag = false;
        }
           
        
        if(flag){
            if("".equals(tfCod.getText())){
                f = new Produtos(this.tfNome.getText(), cbCategoria.getValue(), cbMedida.getValue(), Double.parseDouble(this.convertStr(this.tfPreço.getText())), cbAVenda.getValue());
                if(this.cbCtrlEstoque.getValue() == 'N'){
                    f.setEstoque(-1);
                }
                if(dal.gravar(f)){
                    this.snackbar("Produto gravado com sucesso!", "green");

                    this.original();
                    this.loadTable("");
                }
                else{
                    this.snackbar("Problemas ao gravar Produto!", "red");
                }
            }
            else{
                f = new Produtos(Integer.parseInt(this.tfCod.getText()),this.tfNome.getText(), cbCategoria.getValue(), cbMedida.getValue(), Double.parseDouble(this.convertStr(this.tfPreço.getText())), cbAVenda.getValue());
                if(this.cbCtrlEstoque.getValue() == 'N'){
                    f.setEstoque(-1);
                }
                if(dal.alterar(f)){
                    this.snackbar("Produto atualizado com sucesso!", "green");

                    this.original();
                    this.loadTable("");
                }
                else{
                    this.snackbar("Problemas ao atualizar Produto!", "red");
                }
            }
        }
            
    }

    @FXML
    private void clkCancelar(ActionEvent event) {
        if (!this.apDados.isDisabled())
            this.original();
        else 
            HomeController.spnprincipal.setCenter(null);
    }

    @FXML
    private void evtSearch(KeyEvent event) {
        String filtro, f1, f2;
        
        
        
        f1 = this.tfSearch.getText().isEmpty() ? "" : "UPPER(prod_nome) LIKE '%#1%'";
        f1 = f1.replaceAll("#1", this.tfSearch.getText().toUpperCase());
        
        
        if(this.cbCategoriaSearch.getValue() != null && !"".equals(this.cbCategoriaSearch.getValue().getNome())){
            f2 = "prod_cat = '#2'";
            f2 = f2.replaceAll("#2", this.cbCategoriaSearch.getValue().getCod()+"");
        }
        else
            f2 = "";
        
        
        if("".equals(f1)){
            filtro = f2;
        }
        else
            if("".equals(f2)){
                filtro = f1;
            }
            else
                filtro = f1 + " and " + f2;
         
        
        
        
        loadTable(filtro);
    }

    @FXML
    private void clkTable(MouseEvent event) {
        if(this.tbProdutos.getSelectionModel().getSelectedItem() != null){
            this.btAlterar.setDisable(false);
            this.btApagar.setDisable(false);
        }
    }

    
}