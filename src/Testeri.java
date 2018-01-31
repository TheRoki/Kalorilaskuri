
/**
 * GUI-luokka, joka toteuttaa graafisen käyttöliittymän ohjelman suoritukselle
 *
 * @author Roope Ilvonen
 * @version 1.00 2017/4/25
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.function.Predicate;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Roope
 * @version 1.00 2017/4/25
 *
 */
public class Testeri extends Application {
	Connection conn;

	@Override
	public void start(Stage primaryStage) {
		CheckConnection();
		
		primaryStage.setTitle("Kalorilaskuri");

		BorderPane layout = new BorderPane();
		Scene newscene = new Scene(layout, 1000, 700, Color.rgb(0, 0, 0, 0));

		Group root = new Group();
		Scene scene = new Scene(root, 320, 200, Color.rgb(0, 0, 0, 0));
		

		TableView<Ruoka> table = new TableView<>();
		final ObservableList<Ruoka> ruoat = FXCollections.observableArrayList();

		// Name column
		TableColumn<Ruoka, String> nameColumn = new TableColumn<>("Ravintoainelista");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("nimi"));

		// Calorie column
		TableColumn<Ruoka, Double> calorieColumn = new TableColumn<>("Kcal/100g");
		calorieColumn.setMinWidth(150);
		calorieColumn.setCellValueFactory(new PropertyValueFactory<>("kalori"));

		// Protein column
		TableColumn<Ruoka, Double> proteinColumn = new TableColumn<>("Proteiini");
		proteinColumn.setMinWidth(150);
		proteinColumn.setCellValueFactory(new PropertyValueFactory<>("proteiini"));

		// Carb column
		TableColumn<Ruoka, Double> carbColumn = new TableColumn<>("Hiilihydraatti");
		carbColumn.setMinWidth(150);
		carbColumn.setCellValueFactory(new PropertyValueFactory<>("hiilihydraatti"));

		// Fat column
		TableColumn<Ruoka, Double> fatColumn = new TableColumn<>("Rasva");
		fatColumn.setMinWidth(150);
		fatColumn.setCellValueFactory(new PropertyValueFactory<>("rasva"));

		table.getColumns().addAll(nameColumn, calorieColumn, proteinColumn, carbColumn, fatColumn);

		layout.setRight(table);
		BorderPane.setMargin(table, new Insets(0, 10, 0, 10));
		
		primaryStage.setScene(newscene);
		primaryStage.show();
		
		
	}

	public void CheckConnection() {
		conn = DBconnect.dbConnector();
		if (conn == null) {
			System.out.println("Connection not successful");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}