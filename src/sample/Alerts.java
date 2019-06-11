package sample;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Optional;

public class Alerts extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

    }

    public static void informConsultCliId(Cliente conCliente){
        Alert inform = new Alert(Alert.AlertType.INFORMATION);
        inform.setTitle("bdBoletaje - Consulta");
        inform.setHeaderText("Consulta de datos exitosa");
        inform.setContentText("Cliente \n"+ "ID = " + conCliente.getClienteId() + "\n" + "Nombre = " +
                conCliente.getNombre() + "\n" + "Apellidos = " + conCliente.getApellidos() + "\n" +
                "Direccion = " + conCliente.getDireccion());
        inform.showAndWait();
    }
    public static void errorConsultCli(){
        Alert inform = new Alert(Alert.AlertType.ERROR);
        inform.setTitle("bdBoletaje - Consulta");
        inform.setHeaderText("Fallo al intentar consultar cliente");
        inform.setContentText("No se encontraron clientes haciendo esa busqueda");
        inform.showAndWait();
    }


    public static void informInsertCliId(Cliente inCliente){
        Alert inform = new Alert(Alert.AlertType.INFORMATION);
        inform.setTitle("bdBoletaje - Agregado");
        inform.setHeaderText("Nuevo cliente agregado exitosamente");
        inform.setContentText("Cliente \n"+ "ID = " + inCliente.getClienteId() + "\n" + "Nombre = " +
                inCliente.getNombre() + "\n" + "Apellidos = " + inCliente.getApellidos() + "\n" +
                "Direccion = " + inCliente.getDireccion());
        inform.showAndWait();
    }
    public static void errorInsertCliId(){
        Alert inform = new Alert(Alert.AlertType.ERROR);
        inform.setTitle("bdBoletaje - Agregado");
        inform.setHeaderText("Fallo al intentar ingresar nuevo cliente");
        inform.setContentText("El ID del cliente ya esta en uso");
        inform.showAndWait();
    }

    public static void informUpdateCliId(Cliente oldCliente, Cliente nvCliente){
        Alert inform = new Alert(Alert.AlertType.INFORMATION);
        inform.setTitle("bdBoletaje - Actualización");
        inform.setHeaderText("Actualización de datos exitosa");
        inform.setContentText("Antiguo Cliente \n"+ "ID = " + oldCliente.getClienteId() + "\n" + "Nombre = " +
                oldCliente.getNombre() + "\n" + "Apellidos = " + oldCliente.getApellidos() + "\n" +
                "Direccion = " + oldCliente.getDireccion() + "\n" + "Nuevo Cliente" + "\n" + "ID = " +
                nvCliente.getClienteId() + "\n" + "Nombre = " + nvCliente.getNombre() + "\n" + "Apellidos = " +
                nvCliente.getApellidos() + "\n" + "Direccion = " + nvCliente.getDireccion());
        inform.showAndWait();
    }
    public static void errorUpdateCliId(){
        Alert inform = new Alert(Alert.AlertType.ERROR);
        inform.setTitle("bdBoletaje - Actualización");
        inform.setHeaderText("Fallo al intentar actualizar cliente");
        inform.setContentText("El ID del cliente no fue encontrado");
        inform.showAndWait();
    }

    public static void informDeleteCli(int numRegs){
        Alert inform = new Alert(Alert.AlertType.INFORMATION);
        inform.setTitle("bdBoletaje - Borrado");
        inform.setHeaderText("Borrado de datos exitosa");
        inform.setContentText("Cantidad de registros afectados: " + numRegs);
        inform.showAndWait();
    }
    public static void errorDeleteCli(){
        Alert inform = new Alert(Alert.AlertType.ERROR);
        inform.setTitle("bdBoletaje - Borrado");
        inform.setHeaderText("Fallo al intentar borrar cliente");
        inform.setContentText("No se encontraron clientes");
        inform.showAndWait();
    }
    public static void errorDeleteCliSQL(String ex,String st,int error){
        Alert inform = new Alert(Alert.AlertType.ERROR);
        inform.setTitle("bdBoletaje - Borrado");
        inform.setHeaderText("Fallo al intentar borrar cliente");
        inform.setContentText("SQLException: "+ex+"\n"+"SQLState: "+st+"\n"+"ERROR: "+error);
        inform.showAndWait();
    }
    public static void informDeleteObs(){
        Alert inform = new Alert(Alert.AlertType.INFORMATION);
        inform.setTitle("bdBoletaje - Consulta");
        inform.setHeaderText("Todos los clientes fueron eliminados");
        inform.setContentText(null);
        inform.showAndWait();
    }

    public static void idNullCliInserts(Cliente oCliente, int id, String nombre, String apellido, String direccion) throws Exception {
        DBManager accesoDB = new DBManager();
        OperacionCliente opCliente = new OperacionCliente(accesoDB.getConnection());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("bdBoletaje");
        alert.setHeaderText("Problema, algunos de los campos se encuentran vacios");
        alert.setContentText("¿Le gustaria seguir con la operacion aun asi?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            oCliente = opCliente.insertCliente(id,nombre, apellido, direccion);

            if (oCliente!=null){
                Alerts.informInsertCliId(oCliente);
            }else{
                Alerts.errorInsertCliId();
            }
        }
    }
    public static void idNullCliUpdates(Cliente oldCliente, Cliente nvCliente, int id, String nombre, String apellido, String direccion) throws Exception {
        DBManager accesoDB = new DBManager();
        OperacionCliente opCliente = new OperacionCliente(accesoDB.getConnection());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("bdBoletaje");
        alert.setHeaderText("Problema, algunos de los campos se encuentran vacios");
        alert.setContentText("¿Le gustaria seguir con la operacion aun asi?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            oldCliente = opCliente.consultCliente(id);
            nvCliente = opCliente.updateCliente(id, nombre, apellido, direccion);

            if (oldCliente!=null){
                Alerts.informUpdateCliId(oldCliente,nvCliente);
            }else{
                Alerts.errorUpdateCliId();
            }
        }
    }
    public static void deleteConfirmationNom(String nom, TextField txfIdCli,TextField txfNomCli,TextField txfApeCli,
                                             TextField txfDirCli,BorderPane pnl,Button btnConCli,Button btnInCli,
                                             Button btnUpdCli, Button btnDeCli,Button btnBack)
            throws Exception {
        DBManager accesoDB = new DBManager();
        OperacionCliente opCliente = new OperacionCliente(accesoDB.getConnection());
        Main  main= new Main();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("bdBoletaje");
        alert.setHeaderText("Existe la posibilidad de borrar multiples clientes al mismo tiempo");
        alert.setContentText("¿Le gustaria seguir con la operacion aun asi?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            int numRegs = opCliente.deleteClienteNom(nom);

            if (numRegs!=0){

                Alerts.informDeleteCli(numRegs);
                main.limpiar(txfIdCli,txfNomCli,txfApeCli,txfDirCli);
                pnl.setBottom(View.addButtonCli(btnConCli,btnInCli,btnUpdCli,btnDeCli));
                main.original(txfIdCli,txfNomCli,txfApeCli,txfDirCli,btnUpdCli,btnDeCli,btnInCli,btnConCli,btnBack);

            }else{
                if(opCliente.getException()==null){
                    Alerts.errorDeleteCli();
                }else{
                    Alerts.errorDeleteCliSQL(opCliente.getException(),opCliente.getState(),opCliente.getError());
                }
            }

        }
    }
    public static void deleteConfirmationApe(String apellido, TextField txfIdCli,TextField txfNomCli,TextField txfApeCli,
                                             TextField txfDirCli,BorderPane pnl,Button btnConCli,Button btnInCli,
                                             Button btnUpdCli, Button btnDeCli,Button btnBack)
            throws Exception {
        DBManager accesoDB = new DBManager();
        OperacionCliente opCliente = new OperacionCliente(accesoDB.getConnection());
        Main  main= new Main();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("bdBoletaje");
        alert.setHeaderText("Existe la posibilidad de borrar multiples clientes al mismo tiempo");
        alert.setContentText("¿Le gustaria seguir con la operacion aun asi?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            int numRegs = opCliente.deleteClienteApe(apellido);

            if (numRegs!=0){

                Alerts.informDeleteCli(numRegs);
                main.limpiar(txfIdCli,txfNomCli,txfApeCli,txfDirCli);
                pnl.setBottom(View.addButtonCli(btnConCli,btnInCli,btnUpdCli,btnDeCli));
                main.original(txfIdCli,txfNomCli,txfApeCli,txfDirCli,btnUpdCli,btnDeCli,btnInCli,btnConCli,btnBack);

            }else{
                if(opCliente.getException()==null){
                    Alerts.errorDeleteCli();
                }else{
                    Alerts.errorDeleteCliSQL(opCliente.getException(),opCliente.getState(),opCliente.getError());
                }
            }

        }
    }

    public static void nullId(){
        Alert inform = new Alert(Alert.AlertType.ERROR);
        inform.setTitle("bdBoletaje");
        inform.setHeaderText("No fue proporcionado el ID");
        inform.setContentText(null);
        inform.showAndWait();
    }
    public static void nullNom(){
        Alert inform = new Alert(Alert.AlertType.ERROR);
        inform.setTitle("bdBoletaje");
        inform.setHeaderText("No fue proporcionado el nombre");
        inform.setContentText(null);
        inform.showAndWait();
    }
    public static void nullApe(){
        Alert inform = new Alert(Alert.AlertType.ERROR);
        inform.setTitle("bdBoletaje");
        inform.setHeaderText("No fue proporcionado el apellido");
        inform.setContentText(null);
        inform.showAndWait();
    }

}
