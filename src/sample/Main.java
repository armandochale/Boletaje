package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        DBManager accesoDB = new DBManager();
        OperacionCliente opCliente = new OperacionCliente(accesoDB.getConnection());

        BorderPane pnl= new BorderPane();

        //secondStage
        Stage secondStage = new Stage();
        BorderPane pn2 =new BorderPane();
        
        //addGridPane4
        ObservableList<Integer> IdCli = FXCollections.observableArrayList();
        ObservableList<String> nomCli = FXCollections.observableArrayList();
        ObservableList<String> apeCli = FXCollections.observableArrayList();
        ObservableList<String> dirCli = FXCollections.observableArrayList();

        ListView<Integer> listId=new ListView <Integer>();
        ListView<String> listNom=new ListView <String>();
        ListView<String> listApe=new ListView <String>();
        ListView<String> listDireccion=new ListView <String>();

        //addGridPane5
        Button btnActualApe = new Button();
        btnActualApe.setText("Actualizar");
        btnActualApe.setMinWidth(Double.MIN_VALUE);
        btnActualApe.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        Button btnBorrarApe = new Button();
        btnBorrarApe.setText("Borrar");
        btnBorrarApe.setMinWidth(Double.MIN_VALUE);
        btnBorrarApe.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Button btnActualNom = new Button();
        btnActualNom.setText("Actualizar");
        btnActualNom.setMinWidth(Double.MIN_VALUE);
        btnActualNom.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        Button btnBorrarNom = new Button();
        btnBorrarNom.setText("Borrar");
        btnBorrarNom.setMinWidth(Double.MIN_VALUE);
        btnBorrarNom.setFont(Font.font("Arial", FontWeight.BOLD, 16));


        //addTitleCli
        Button btnBack = new Button();
        btnBack.setText("<- Atras");
        btnBack.setMinWidth(Double.MIN_VALUE);
        btnBack.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        btnBack.setDisable(true);

        Button btnBack1 = new Button();
        btnBack1.setText("<- Atras");
        btnBack1.setMinWidth(Double.MIN_VALUE);
        btnBack1.setFont(Font.font("Arial", FontWeight.BOLD, 16));


        //addButtonCli
        Button btnInCli = new Button();
        Button btnDeCli = new Button();
        Button btnUpdCli = new Button();
        Button btnConCli = new Button();

        btnInCli.setText("Agregar Cliente");
        btnDeCli.setText("Borrar Cliente");
        btnUpdCli.setText("Actualizar Cliente");
        btnConCli.setText("Consultar Cliente");

        btnInCli.setMaxWidth(Double.MAX_VALUE);
        btnDeCli.setMaxWidth(Double.MAX_VALUE);
        btnUpdCli.setMaxWidth(Double.MAX_VALUE);
        btnConCli.setMaxWidth(Double.MAX_VALUE);

        btnInCli.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        btnDeCli.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        btnUpdCli.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        btnConCli.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        btnDeCli.setDisable(true);
        btnUpdCli.setDisable(true);

        //addButtonConCli
        Button btnConCliId = new Button();
        Button btnConCliNom = new Button();
        Button btnConCliApe = new Button();

        btnConCliId.setText("Consultar Cliente por ID");
        btnConCliNom.setText("Consultar Cliente por Nombre");
        btnConCliApe.setText("Consultar Cliente por Apellido");

        btnConCliId.setMaxWidth(Double.MAX_VALUE);
        btnConCliNom.setMaxWidth(Double.MAX_VALUE);
        btnConCliApe.setMaxWidth(Double.MAX_VALUE);

        btnConCliId.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        btnConCliNom.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        btnConCliApe.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        //addButtonDeCli
        Button btnDeCliId = new Button();
        Button btnDeCliNom = new Button();
        Button btnDeCliApe = new Button();

        btnDeCliId.setText("Borrar Cliente por ID");
        btnDeCliNom.setText("Borrar Cliente por Nombre");
        btnDeCliApe.setText("Borrar Cliente por Apellido");

        btnDeCliId.setMaxWidth(Double.MAX_VALUE);
        btnDeCliNom.setMaxWidth(Double.MAX_VALUE);
        btnDeCliApe.setMaxWidth(Double.MAX_VALUE);

        btnDeCliId.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        btnDeCliNom.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        btnDeCliApe.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        //addTextFieldCli
        TextField txfIdCli = new TextField();
        TextField txfNomCli = new TextField();
        TextField txfApeCli = new TextField();
        TextField txfDirCli = new TextField();


        btnConCliId.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (txfIdCli.getText().length()!=0){

                    int id = Integer.parseInt(txfIdCli.getText());
                    Cliente conCliente = opCliente.consultCliente(id);

                    limpiar(txfIdCli,txfNomCli,txfApeCli,txfDirCli);

                    if (conCliente != null){
                        txfIdCli.setText(String.valueOf(conCliente.getClienteId()));
                        txfNomCli.setText(conCliente.getNombre());
                        txfApeCli.setText(conCliente.getApellidos());
                        txfDirCli.setText(conCliente.getDireccion());
                        consultBlock(txfIdCli,txfNomCli,txfApeCli,txfDirCli,btnUpdCli,btnDeCli,btnInCli,btnConCli,btnBack);
                        pnl.setBottom(View.addButtonCli(btnConCli,btnInCli,btnUpdCli,btnDeCli));

                    }else{
                        Alerts.errorConsultCli();
                    }
                }else{
                    Alerts.nullId();
                }

            }
        });

        btnConCliApe.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {


                limpiarObsList(IdCli,nomCli,apeCli,dirCli);


                ArrayList <Cliente> arrayCliente = new ArrayList<Cliente>();

                if (txfApeCli.getText().length()!=0){

                    String ape = txfApeCli.getText();
                    arrayCliente = opCliente.consultClienteApe(ape);

                    Iterator<Cliente> iteratorApe=arrayCliente.iterator();
                    Iterator<Cliente> iteratorApe1=arrayCliente.iterator();

                    limpiar(txfIdCli,txfNomCli,txfApeCli,txfDirCli);

                    if (arrayCliente.isEmpty()){
                        Alerts.errorConsultCli();

                    }else{
                        if(arrayCliente.size()==1){
                            Cliente consultApeCli1=iteratorApe1.next();
                            txfIdCli.setText(String.valueOf(consultApeCli1.getClienteId()));
                            txfNomCli.setText(consultApeCli1.getNombre());
                            txfApeCli.setText(consultApeCli1.getApellidos());
                            txfDirCli.setText(consultApeCli1.getDireccion());
                            consultBlock(txfIdCli,txfNomCli,txfApeCli,txfDirCli,btnUpdCli,btnDeCli,btnInCli,btnConCli,btnBack);
                            pnl.setBottom(View.addButtonCli(btnConCli,btnInCli,btnUpdCli,btnDeCli));
                        }else{
                            while(iteratorApe.hasNext()){

                                Cliente consultApeCli=iteratorApe.next();

                                IdCli.add(consultApeCli.getClienteId());
                                nomCli.add(consultApeCli.getNombre());
                                apeCli.add(consultApeCli.getApellidos());
                                dirCli.add(consultApeCli.getDireccion());

                            }

                            pn2.setCenter(View.addGridPane4(IdCli,nomCli,apeCli,dirCli,listId,listNom,listApe,listDireccion));
                            pn2.setBottom(View.addGridPane5(btnActualApe,btnBorrarApe));
                            secondStage.show();
                            original(txfIdCli,txfNomCli,txfApeCli,txfDirCli,btnUpdCli,btnDeCli,btnInCli,btnConCli,btnBack);
                            pnl.setBottom(View.addButtonCli(btnConCli,btnInCli,btnUpdCli,btnDeCli));
                        }


                    }
                }else{
                    Alerts.nullApe();
                }
            }
        });
        btnConCliNom.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                limpiarObsList(IdCli,nomCli,apeCli,dirCli);
                ArrayList <Cliente> arrayCliente = new ArrayList<Cliente>();

                if (txfNomCli.getText().length()!=0){

                    String dir = txfNomCli.getText();
                    arrayCliente = opCliente.consultClienteNom(dir);

                    Iterator<Cliente> iteratorDir=arrayCliente.iterator();
                    Iterator<Cliente> iteratorDir1=arrayCliente.iterator();
                    limpiar(txfIdCli,txfNomCli,txfApeCli,txfDirCli);

                    if (arrayCliente.isEmpty()){
                        Alerts.errorConsultCli();

                    }else{
                        if(arrayCliente.size()==1){
                            Cliente consultDirCli1=iteratorDir1.next();
                            txfIdCli.setText(String.valueOf(consultDirCli1.getClienteId()));
                            txfNomCli.setText(consultDirCli1.getNombre());
                            txfApeCli.setText(consultDirCli1.getApellidos());
                            txfDirCli.setText(consultDirCli1.getDireccion());
                            consultBlock(txfIdCli,txfNomCli,txfApeCli,txfDirCli,btnUpdCli,btnDeCli,btnInCli,btnConCli,btnBack);
                            pnl.setBottom(View.addButtonCli(btnConCli,btnInCli,btnUpdCli,btnDeCli));
                        }else{
                            while(iteratorDir.hasNext()){

                                Cliente consultDirCli=iteratorDir.next();

                                IdCli.add(consultDirCli.getClienteId());
                                nomCli.add(consultDirCli.getNombre());
                                apeCli.add(consultDirCli.getApellidos());
                                dirCli.add(consultDirCli.getDireccion());

                            }

                            pn2.setCenter(View.addGridPane4(IdCli,nomCli,apeCli,dirCli,listId,listNom,listApe,listDireccion));
                            pn2.setBottom(View.addGridPane5(btnActualNom,btnBorrarNom));
                            secondStage.show();
                            original(txfIdCli,txfNomCli,txfApeCli,txfDirCli,btnUpdCli,btnDeCli,btnInCli,btnConCli,btnBack);
                            pnl.setBottom(View.addButtonCli(btnConCli,btnInCli,btnUpdCli,btnDeCli));
                        }


                    }
                }else{
                    Alerts.nullNom();
                }
            }
        });


        btnInCli.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Cliente inCliente = null;

                if (txfIdCli.getText().length()!=0  ){

                    int id = Integer.parseInt(txfIdCli.getText());
                    String nombre = txfNomCli.getText();
                    String apellido = txfApeCli.getText();
                    String direccion = txfDirCli.getText();



                    if (txfNomCli.getText().length()!=0 & txfApeCli.getText().length()!=0 &
                            txfDirCli.getText().length()!=0){
                        inCliente = opCliente.insertCliente(id,nombre, apellido, direccion);

                        if (inCliente!=null){
                            Alerts.informInsertCliId(inCliente);
                        }else{
                            Alerts.errorInsertCliId();
                        }

                        limpiar(txfIdCli,txfNomCli,txfApeCli,txfDirCli);

                    }else{

                        try{
                            Alerts.idNullCliInserts(inCliente,id,nombre,apellido,direccion);
                            limpiar(txfIdCli,txfNomCli,txfApeCli,txfDirCli);
                        }catch (Exception e){

                        }


                    }

                }else{
                    Alerts.nullId();

                }
            }
        });

        btnUpdCli.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Cliente oldCliente = null;
                Cliente nvCliente = null;

                if (txfIdCli.getText().length()!=0){

                    int Id = Integer.parseInt(txfIdCli.getText());
                    String nombre = txfNomCli.getText();
                    String apellido = txfApeCli.getText();
                    String direccion = txfDirCli.getText();

                    if (txfNomCli.getText().length()!=0 & txfApeCli.getText().length()!=0 &
                            txfDirCli.getText().length()!=0){

                        oldCliente = opCliente.consultCliente(Id);
                        nvCliente = opCliente.updateCliente(Id, nombre, apellido, direccion);

                        if (oldCliente!= null){
                            Alerts.informUpdateCliId(oldCliente,nvCliente);
                        }else{
                            Alerts.errorUpdateCliId();
                            limpiar(txfIdCli,txfNomCli,txfApeCli,txfDirCli);
                        }



                    }else{

                        try{
                            Alerts.idNullCliUpdates(oldCliente,nvCliente,Id,nombre,apellido,direccion);

                        }catch (Exception e){

                        }
                    }
                    pnl.setBottom(View.addButtonCli(btnConCli,btnInCli,btnUpdCli,btnDeCli));
                    consultBlock(txfIdCli,txfNomCli,txfApeCli,txfDirCli,btnUpdCli,btnDeCli,btnInCli,btnConCli,btnBack);
                }else{
                    Alerts.nullId();
                }
            }
        });

        btnDeCliId.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (txfIdCli.getText().length()!=0){

                    int Id = Integer.parseInt(txfIdCli.getText());

                    int numRegs = opCliente.deleteCliente(Id);
                    if (numRegs!=0){

                        Alerts.informDeleteCli(numRegs);

                    }else{
                        if(opCliente.getException()==null){
                            Alerts.errorDeleteCli();
                        }else{
                            Alerts.errorDeleteCliSQL(opCliente.getException(),opCliente.getState(),opCliente.getError());
                        }
                    }
                    pnl.setBottom(View.addButtonCli(btnConCli,btnInCli,btnUpdCli,btnDeCli));
                    original(txfIdCli,txfNomCli,txfApeCli,txfDirCli,btnUpdCli,btnDeCli,btnInCli,btnConCli,btnBack);

                    limpiar(txfIdCli,txfNomCli,txfApeCli,txfDirCli);

                }else{
                    Alerts.nullId();
                }
            }
        });
        btnDeCliNom.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (txfNomCli.getText().length()!=0){

                    String nom = txfNomCli.getText();

                    boolean secure = opCliente.prbClienteNom(nom);
                    System.out.println(secure);
                    if (secure==true){

                        try{
                            Alerts.deleteConfirmationNom(nom,txfIdCli,txfNomCli,txfApeCli,txfDirCli,pnl,
                                    btnConCli,btnInCli,btnUpdCli,btnDeCli,btnBack);


                        }catch (Exception e){

                        }

                    }else{
                        Alerts.errorDeleteCli();
                    }


                }else{
                    Alerts.nullNom();
                }
            }
        });
        btnDeCliApe.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (txfApeCli.getText().length()!=0){

                    String ape = txfApeCli.getText();

                    boolean secure = opCliente.prbClienteApe(ape);
                    System.out.println(secure);
                    if (secure==true){

                        try{
                            Alerts.deleteConfirmationApe(ape,txfIdCli,txfNomCli,txfApeCli,txfDirCli,pnl,
                                    btnConCli,btnInCli,btnUpdCli,btnDeCli,btnBack);


                        }catch (Exception e){

                        }

                    }else{
                        Alerts.errorDeleteCli();
                    }

                }else{
                    Alerts.nullApe();
                }
            }
        });

        btnActualApe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList <Cliente> arrayCliente = new ArrayList<Cliente>();
                        String ape= apeCli.get(0);
                        arrayCliente = opCliente.consultClienteApe(ape);
                limpiarObsList(IdCli,nomCli,apeCli,dirCli);
                if (arrayCliente.isEmpty()){
                    Alerts.errorConsultCli();
                }else{
                    Iterator<Cliente> iteratorCli=arrayCliente.iterator();
                    while(iteratorCli.hasNext()){
                        Cliente consultCli=iteratorCli.next();
                        IdCli.add(consultCli.getClienteId());
                        nomCli.add(consultCli.getNombre());
                        apeCli.add(consultCli.getApellidos());
                        dirCli.add(consultCli.getDireccion());
                    }
                    pn2.setCenter(View.addGridPane4(IdCli,nomCli,apeCli,dirCli,listId,listNom,listApe,listDireccion));
                    pn2.setBottom(View.addGridPane5(btnActualApe,btnBorrarApe));
                }

            }
        });
        btnActualNom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList <Cliente> arrayCliente = new ArrayList<Cliente>();
                String dir= nomCli.get(0);
                arrayCliente = opCliente.consultClienteNom(dir);
                limpiarObsList(IdCli,nomCli,apeCli,dirCli);
                if (arrayCliente.isEmpty()){
                    Alerts.errorConsultCli();
                }else{
                    Iterator<Cliente> iteratorCli=arrayCliente.iterator();
                    while(iteratorCli.hasNext()){
                        Cliente consultCli=iteratorCli.next();
                        IdCli.add(consultCli.getClienteId());
                        nomCli.add(consultCli.getNombre());
                        apeCli.add(consultCli.getApellidos());
                        dirCli.add(consultCli.getDireccion());
                    }
                    pn2.setCenter(View.addGridPane4(IdCli,nomCli,apeCli,dirCli,listId,listNom,listApe,listDireccion));
                    pn2.setBottom(View.addGridPane5(btnActualNom,btnBorrarNom));
                }

            }
        });
        btnBorrarApe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int id=listId.getSelectionModel().getSelectedItem();
                System.out.println(id);
                String ape= apeCli.get(0);
                int numRegs = opCliente.deleteCliente(id);
                if (numRegs!=0){

                    Alerts.informDeleteCli(numRegs);


                }else{
                    if(opCliente.getException()==null){
                        Alerts.errorDeleteCli();
                    }else{
                        Alerts.errorDeleteCliSQL(opCliente.getException(),opCliente.getState(),opCliente.getError());
                    }
                }

                ArrayList <Cliente> arrayCliente = new ArrayList<Cliente>();

                arrayCliente = opCliente.consultClienteApe(ape);
                limpiarObsList(IdCli,nomCli,apeCli,dirCli);
                if (arrayCliente.isEmpty()){
                        Alerts.informDeleteObs();
                        secondStage.hide();
                }else{
                    Iterator<Cliente> iteratorCli=arrayCliente.iterator();
                    while(iteratorCli.hasNext()){
                        Cliente consultCli=iteratorCli.next();
                        IdCli.add(consultCli.getClienteId());
                        nomCli.add(consultCli.getNombre());
                        apeCli.add(consultCli.getApellidos());
                        dirCli.add(consultCli.getDireccion());
                    }
                    pn2.setCenter(View.addGridPane4(IdCli,nomCli,apeCli,dirCli,listId,listNom,listApe,listDireccion));
                    pn2.setBottom(View.addGridPane5(btnActualApe,btnBorrarApe));
                }

            }
        });
        btnBorrarNom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int id=listId.getSelectionModel().getSelectedItem();
                System.out.println(id);
                String dir= nomCli.get(0);
                int numRegs = opCliente.deleteCliente(id);
                if (numRegs!=0){
                    Alerts.informDeleteCli(numRegs);
                }else{
                    if(opCliente.getException()==null){
                        Alerts.errorDeleteCli();
                    }else{
                        Alerts.errorDeleteCliSQL(opCliente.getException(),opCliente.getState(),opCliente.getError());
                    }
                }


                ArrayList <Cliente> arrayCliente = new ArrayList<Cliente>();

                arrayCliente = opCliente.consultClienteNom(dir);
                limpiarObsList(IdCli,nomCli,apeCli,dirCli);
                if (arrayCliente.isEmpty()){
                        Alerts.informDeleteObs();
                        secondStage.hide();
                }else{
                    Iterator<Cliente> iteratorCli=arrayCliente.iterator();
                    while(iteratorCli.hasNext()){
                        Cliente consultCli=iteratorCli.next();
                        IdCli.add(consultCli.getClienteId());
                        nomCli.add(consultCli.getNombre());
                        apeCli.add(consultCli.getApellidos());
                        dirCli.add(consultCli.getDireccion());
                    }
                    pn2.setCenter(View.addGridPane4(IdCli,nomCli,apeCli,dirCli,listId,listNom,listApe,listDireccion));
                    pn2.setBottom(View.addGridPane5(btnActualNom,btnBorrarNom));
                }

            }
        });



        btnDeCli.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                pnl.setBottom(View.addButtonEspecificCli(btnDeCliId,btnDeCliNom,btnDeCliApe));
                pnl.setTop(View.addTitleCli(btnBack1));
                deleteBlock(txfIdCli,txfNomCli,txfApeCli,txfDirCli,btnUpdCli,btnDeCli,btnInCli,btnConCli,btnBack);
            }
        });

        btnConCli.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                pnl.setBottom(View.addButtonEspecificCli(btnConCliId,btnConCliNom,btnConCliApe));
                btnBack.setDisable(false);
            }
        });

        btnBack.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                pnl.setBottom(View.addButtonCli(btnConCli,btnInCli,btnUpdCli,btnDeCli));
                limpiar(txfIdCli,txfNomCli,txfApeCli,txfDirCli);
                original(txfIdCli,txfNomCli,txfApeCli,txfDirCli,btnUpdCli,btnDeCli,btnInCli,btnConCli,btnBack);
            }
        });
        btnBack1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                pnl.setBottom(View.addButtonCli(btnConCli,btnInCli,btnUpdCli,btnDeCli));
                consultBlock(txfIdCli,txfNomCli,txfApeCli,txfDirCli,btnUpdCli,btnDeCli,btnInCli,btnConCli,btnBack);
                pnl.setTop(View.addTitleCli(btnBack));
            }
        });

        pnl.setTop(View.addTitleCli(btnBack));
        pnl.setCenter(View.addTextFieldCli(txfIdCli, txfNomCli, txfApeCli, txfDirCli));
        pnl.setBottom(View.addButtonCli(btnConCli,btnInCli,btnUpdCli,btnDeCli));

        primaryStage.setTitle("bdBoletaje");
        primaryStage.setScene(new Scene(pnl, 600, 440));
        primaryStage.show();

        secondStage.setTitle("bdBoletaje");
        secondStage.setScene(new Scene(pn2, 600, 440));
    }


    public void limpiar(TextField txfIdCli,TextField txfNomCli,TextField txfApeCli, TextField txfDirCli){
        txfIdCli.clear();
        txfNomCli.clear();
        txfApeCli.clear();
        txfDirCli.clear();
    }

    public void limpiarObsList(ObservableList<Integer> IdCli,ObservableList<String> nomCli,ObservableList<String> apeCli,
                               ObservableList<String> dirCli){
        IdCli.clear();
        nomCli.clear();
        apeCli.clear();
        dirCli.clear();
    }

    public void original(TextField txfIdCli,TextField txfNomCli,TextField txfApeCli,TextField txfDirCli,
                         Button btnUpdCli, Button btnDeCli, Button btnInCli,Button btnConCli,Button btnBack){
        txfIdCli.setDisable(false);
        txfNomCli.setDisable(false);
        txfApeCli.setDisable(false);
        txfDirCli.setDisable(false);
        btnUpdCli.setDisable(true);
        btnDeCli.setDisable(true);
        btnInCli.setDisable(false);
        btnConCli.setDisable(false);
        btnBack.setDisable(true);
    }
    public void consultBlock(TextField txfIdCli,TextField txfNomCli,TextField txfApeCli,TextField txfDirCli,
                             Button btnUpdCli, Button btnDeCli, Button btnInCli,Button btnConCli,Button btnBack){
        txfIdCli.setDisable(true);
        txfNomCli.setDisable(false);
        txfApeCli.setDisable(false);
        txfDirCli.setDisable(false);
        btnUpdCli.setDisable(false);
        btnDeCli.setDisable(false);
        btnInCli.setDisable(true);
        btnConCli.setDisable(true);
        btnBack.setDisable(false);
    }
    public void deleteBlock(TextField txfIdCli,TextField txfNomCli,TextField txfApeCli,TextField txfDirCli,
                             Button btnUpdCli, Button btnDeCli, Button btnInCli,Button btnConCli,Button btnBack){
        txfIdCli.setDisable(true);
        txfNomCli.setDisable(true);
        txfApeCli.setDisable(true);
        txfDirCli.setDisable(true);
        btnUpdCli.setDisable(false);
        btnDeCli.setDisable(false);
        btnInCli.setDisable(true);
        btnConCli.setDisable(true);
        btnBack.setDisable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
