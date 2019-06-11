package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

public class View extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

    }
    public static GridPane addTitleCli(Button btnBack) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(6);
        grid.setPadding(new Insets(5, 0, 5, 10));
        grid.setStyle("-fx-background-color: #C84FF8;");

        grid.add(btnBack, 2, 0);

        // Title in column 3, row 1
        Text Title = new Text("    ");
        Title.setFont(Font.font("Arial", FontWeight.BOLD, 60));
        grid.add(Title, 3, 0);

        // Title in column 4, row 1
        Text Title2 = new Text("CLIENTES ");
        Title2.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        grid.add(Title2, 4, 0);

        return grid;
    }

    public static GridPane addTextFieldCli(TextField txfIdCli, TextField txfNomCli, TextField txfApeCli, TextField txfDirCli){
        GridPane grid = new GridPane();
        grid.setHgap(6);
        grid.setVgap(20);
        grid.setPadding(new Insets(8, 2, 6, 10));
        grid.setStyle("-fx-background-color: #C84FF8;");

        Text id = new Text("ID:");
        Text nom = new Text("Nombre:");
        Text ape = new Text("Apellido:");
        Text dir = new Text("Dirección:");

        id.setFont(Font.font("Arial", FontWeight.BLACK, 20));
        nom.setFont(Font.font("Arial", FontWeight.BLACK, 20));
        ape.setFont(Font.font("Arial", FontWeight.BLACK, 20));
        dir.setFont(Font.font("Arial", FontWeight.BLACK, 20));

        grid.add(id,  4,0);
        grid.add(txfIdCli, 5,0);
        grid.add(nom,  4,1);
        grid.add(txfNomCli, 5,1);
        grid.add(ape, 4,2);
        grid.add(txfApeCli, 5,2);
        grid.add(dir, 4,3);
        grid.add(txfDirCli, 5,3);
        return grid;
    }

    public static VBox addButtonCli(Button btnConCli, Button btnInCli, Button btnUpdCli, Button btnDeCli){
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(1, 10, 10, 10));
        vBox.setStyle("-fx-background-color: #C84FF8;");
        vBox.setSpacing(8);

        vBox.getChildren().addAll(btnConCli,btnInCli,btnUpdCli,btnDeCli);

        return vBox;
    }

    public static VBox addButtonEspecificCli(Button btnEspecificCliId, Button btnEspecificCliNom, Button btnEspecificCliApe){
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(1, 10, 10, 10));
        vBox.setStyle("-fx-background-color: #C84FF8;");
        vBox.setSpacing(8);

        vBox.getChildren().addAll(btnEspecificCliId,btnEspecificCliNom,btnEspecificCliApe);

        return vBox;
    }

    public static GridPane addGridPane4(ObservableList idCli,ObservableList nomCli, ObservableList apeCli, ObservableList dirCli,
                                        ListView listId,ListView listNom,ListView listApe,ListView listDireccion){

        GridPane grid2 = new GridPane();
        grid2.setHgap(10);
        grid2.setVgap(10);
        grid2.setPadding(new Insets(0, 40, 20, 40));
        grid2.setStyle("-fx-background-color: #C84FF8;");

        Text Title = new Text("ID");
        Title.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        grid2.add(Title, 0, 2);

        Text Title2 = new Text("Nombre");
        Title2.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        grid2.add(Title2, 1, 2);

        Text Title3 = new Text("Apellidos");
        Title3.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        grid2.add(Title3, 2, 2);

        Text Title4 = new Text("Direccion");
        Title4.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        grid2.add(Title4, 3, 2);


        listId.setItems(idCli);
        grid2.add(listId, 0, 3);
        listNom.setItems(nomCli);
        grid2.add(listNom, 1, 3);
        listApe.setItems(apeCli);
        grid2.add(listApe, 2, 3);
        listDireccion.setItems(dirCli);
        grid2.add(listDireccion, 3, 3);


        return grid2;
    }

    public static GridPane addGridPane5(Button btnActual,Button btnBorrar){

        GridPane grid2 = new GridPane();
        grid2.setHgap(10);
        grid2.setVgap(5);
        grid2.setPadding(new Insets(0, 80, 20, 110));
        grid2.setStyle("-fx-background-color: #C84FF8;");

        grid2.add(btnActual, 4, 1);
        grid2.add(btnBorrar, 16, 1);

        return grid2;
    }

}
