/**
 * GUI-luokka, joka toteuttaa graafisen käyttöliittymän ohjelman suoritukselle
 *
 * @author Roope Ilvonen
 * @version 1.00 2017/4/25
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.function.Predicate;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.beans.value.ChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
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
import javafx.stage.Stage;

/**
 *
 * @author Roope
 * @version 1.00 2017/4/25
 *
 */
public class GUI extends Application{
	Connection conn;

	Stage window;
	TableView<Ruoka> table;
	TableView<Ruoka> table2;
	final ObservableList<Ruoka> ruoat = FXCollections.observableArrayList();

	TextField nameInput, calorieInput, proteinInput, carbInput, fatInput, filterField, kalorimaara;

	TextField tfSukupuoli = new TextField();
	TextField tfIka = new TextField();
	TextField tfPituus = new TextField();
	TextField tfPaino = new TextField();
	TextField tfAktiivisuus = new TextField();
	TextField tfKevyt = new TextField();
	TextField tfKohtalainen = new TextField();
	TextField tfKuormittava = new TextField();
	TextField tfRaskas = new TextField();
	final TextField tfKulutus = new TextField();



	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Kalorilaskuri");

		BorderPane border = new BorderPane();

		/*
		 *
		 *
		 *
		 * RUOKA-AINE TAULUKKO
		 *
		 *
		 *
		 *
		 */

		//Name column
		TableColumn<Ruoka, String> nameColumn = new TableColumn<>("Ravintoainelista");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("nimi"));

		//Calorie column
		TableColumn<Ruoka, Double> calorieColumn = new TableColumn<>("Kcal/100g");
		calorieColumn.setMinWidth(150);
		calorieColumn.setCellValueFactory(new PropertyValueFactory<>("kalori"));

		//Protein column
		TableColumn<Ruoka, Double> proteinColumn = new TableColumn<>("Proteiini");
		proteinColumn.setMinWidth(150);
		proteinColumn.setCellValueFactory(new PropertyValueFactory<>("proteiini"));

		//Carb column
		TableColumn<Ruoka, Double> carbColumn = new TableColumn<>("Hiilihydraatti");
		carbColumn.setMinWidth(150);
		carbColumn.setCellValueFactory(new PropertyValueFactory<>("hiilihydraatti"));

		//Fat column
		TableColumn<Ruoka, Double> fatColumn = new TableColumn<>("Rasva");
		fatColumn.setMinWidth(150);
		fatColumn.setCellValueFactory(new PropertyValueFactory<>("rasva"));

		//Name input
		nameInput = new TextField();
		nameInput.setPromptText("Nimi");
		nameInput.setMinWidth(100);

		//Calorie input
		calorieInput = new TextField();
		calorieInput.setPromptText("Kalorit");

		//Protein input
		proteinInput = new TextField();
		proteinInput.setPromptText("Proteiini");

		//Carb input
		carbInput = new TextField();
		carbInput.setPromptText("Hiilihydraatti");

		//Fat input
		fatInput = new TextField();
		fatInput.setPromptText("Rasva");

		//Lisää nappi
		Button addButton = new Button("Lisää");
		addButton.setOnAction(e -> addButtonClicked());

		// Poista nappi
		Button removeButton = new Button("Poista");
		removeButton.setOnAction(e -> removeButtonClicked());

		filterField = new TextField();
		filterField.setPromptText("Etsi ruoka listasta");

		filterField.textProperty().addListener(new ChangeListener() {
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				etsiButtonClicked((String) oldValue, (String) newValue);
			}
		});

		HBox hBox = new HBox();
		hBox.setPadding(new Insets(1, 1, 1, 1));
		hBox.setSpacing(1);
		hBox.getChildren().addAll(nameInput, calorieInput, proteinInput, carbInput, fatInput, addButton, removeButton);

		table = new TableView<>();
		table.setItems(getData());
		table.getColumns().addAll(nameColumn, calorieColumn, proteinColumn, carbColumn, fatColumn);

		HBox hboxi = new HBox();
		hboxi.setPadding(new Insets(1, 1, 1, 1));
		hboxi.setSpacing(1);
		hboxi.getChildren().addAll(filterField);

		Button lisaaButton = new Button("Lisää listaan");
		lisaaButton.setOnAction(e -> lisaaButtonClicked());

		VBox vBox = new VBox();
		vBox.getChildren().addAll(hboxi, hBox, table, lisaaButton);


		/*
		 *
		 *
		 *
		 *
		 * RUOKA-AINE -LISTA
		 *
		 *
		 *
		 *
		 */
		//Name column
		TableColumn<Ruoka, String> nameColumn2 = new TableColumn<>("Ruoka-aine");
		nameColumn2.setMinWidth(200);
		nameColumn2.setCellValueFactory(new PropertyValueFactory<>("nimi"));

		//Calorie column
		TableColumn<Ruoka, Double> syotymaara = new TableColumn<>("Määrä");
		syotymaara.setMinWidth(200);
		syotymaara.setCellValueFactory(new PropertyValueFactory<>("määrä"));

		//Calorie column
		TableColumn<Ruoka, Double> calorieColumn2 = new TableColumn<>("Energia");
		calorieColumn2.setMinWidth(200);
		calorieColumn2.setCellValueFactory(new PropertyValueFactory<>("kalori"));

		//Protein column
		TableColumn<Ruoka, Double> proteinColumn2 = new TableColumn<>("Proteiini");
		proteinColumn2.setMinWidth(200);
		proteinColumn2.setCellValueFactory(new PropertyValueFactory<>("proteiini"));

		//Carb column
		TableColumn<Ruoka, Double> carbColumn2 = new TableColumn<>("Hiilihydraatti");
		carbColumn2.setMinWidth(200);
		carbColumn2.setCellValueFactory(new PropertyValueFactory<>("hiilihydraatti"));

		//Fat column
		TableColumn<Ruoka, Double> fatColumn2 = new TableColumn<>("Rasva");
		fatColumn2.setMinWidth(200);
		fatColumn2.setCellValueFactory(new PropertyValueFactory<>("rasva"));

		//Button
		Button poistaButton = new Button("Poista listasta");
		poistaButton.setOnAction(e -> poistaButtonClicked());

		Label kalorimaara = new Label("kaloreita xxxx kcal");

		HBox hBox2 = new HBox();
		hBox2.setPadding(new Insets(1, 1, 1, 1));
		hBox2.setSpacing(1);
		hBox2.getChildren().addAll(poistaButton, kalorimaara);



		table2 = new TableView<>();
		table2.setItems(getDaily());
		table2.getColumns().addAll(nameColumn2, syotymaara, calorieColumn2, proteinColumn2, carbColumn2, fatColumn2);

		Label kuluttaa = new Label("Paino muuttuu noin xxx viikossa");

		VBox vBox2 = new VBox();
		vBox2.getChildren().addAll(table2, hBox2, kuluttaa);


		//Luodaan kysymyskentät
		GridPane paneeli = new GridPane();

		paneeli.add(new Label("Sukupuoli"),0,0);
		paneeli.add(new Label("Ikä: "), 0, 3);
		paneeli.add(new Label("v"), 3, 3);
		paneeli.add(new Label("Pituus: "), 0, 4);
		paneeli.add(new Label("cm"), 3, 4);
		paneeli.add(new Label("Paino: "), 0, 5);
		paneeli.add(new Label("kg"), 3, 5);
		paneeli.add(new Label ("Aktiivisuustaso"), 0, 6);
		paneeli.add(new Label ("Päivän liikunta (min.)"), 0, 11);
		paneeli.add(new Label ("Kevyttä liikuntaa (kävely)"), 0, 12);
		paneeli.add(new Label ("Kohtalaista liikuntaa (reipas kävely)"), 0, 13);
		paneeli.add(new Label ("Kuormittavaa liikuntaa (kuntosali)"), 0, 14);
		paneeli.add(new Label ("Raskasta liikuntaa (juoksulenkki)"), 0, 15);
		paneeli.add(new Label ("Kulutuksesi on:"), 0, 17);
		paneeli.add(new Label ("kcal"), 3, 17);

		// Sukupuolenvalinta
		ToggleGroup sukupuoliGroup = new ToggleGroup();
		RadioButton miesRadio = new RadioButton("mies");
		miesRadio.setToggleGroup(sukupuoliGroup);
		RadioButton nainenRadio = new RadioButton("nainen");
		nainenRadio.setToggleGroup(sukupuoliGroup);

		// Aktiivisuustason valinta
		ToggleGroup aktiviteettiGroup = new ToggleGroup();
		RadioButton todellakevytRadio = new RadioButton("Todella kevyt (oleilua)");
		todellakevytRadio.setToggleGroup(aktiviteettiGroup);
		RadioButton kevytRadio = new RadioButton("Kevyt (istumatyöskentelyä)");
		kevytRadio.setToggleGroup(aktiviteettiGroup);
		RadioButton kohtalainenRadio = new RadioButton("Kohtalainen (seisomista ja liikkumista jonkin verran)");
		kohtalainenRadio.setToggleGroup(aktiviteettiGroup);
		RadioButton raskasRadio = new RadioButton("Raskas (fyysisesti raskas työ)");
		raskasRadio.setToggleGroup(aktiviteettiGroup);

		// Laske kulutus
		Button btLaskeKulutus = new Button("Laske kulutus");
		btLaskeKulutus.setOnAction(e -> laskeKulutusButtonClicked(sukupuoliGroup, aktiviteettiGroup));

		paneeli.add(miesRadio, 2, 1);
		paneeli.add(nainenRadio, 2, 2);
		paneeli.add(tfIka, 2, 3);
		paneeli.add(tfPituus, 2, 4);
		paneeli.add(tfPaino, 2, 5);
		paneeli.add(todellakevytRadio, 2, 7);
		paneeli.add(kevytRadio, 2, 8);
		paneeli.add(kohtalainenRadio, 2, 9);
		paneeli.add(raskasRadio, 2, 10);
		paneeli.add(tfKevyt, 2, 12);
		tfKevyt.setText("0");
		paneeli.add(tfKohtalainen, 2, 13);
		tfKohtalainen.setText("0");
		paneeli.add(tfKuormittava, 2, 14);
		tfKuormittava.setText("0");
		paneeli.add(tfRaskas, 2, 15);
		tfRaskas.setText("0");
		paneeli.add(btLaskeKulutus, 2, 16);
		paneeli.add(tfKulutus, 2, 17);


		// Sijoittelu
		border.setLeft(vBox);
		border.setRight(paneeli);
		border.setBottom(vBox2);


		Scene scene = new Scene(border, 1400, 900);
		window.setScene(scene);
		window.show();
	}
	// Ruoka-aineiden suodatus
	public void etsiButtonClicked(String oldValue, String newValue) {
		ObservableList<Ruoka> filteredList = FXCollections.observableArrayList();
		if (filterField == null || (newValue.length() < oldValue.length()) || newValue == null) {
			table.setItems(ruoat);
		}
		else {
			newValue = newValue.toUpperCase();
			for(Ruoka ruuat : table.getItems()) {
				String filterFirstName = ruuat.getNimi();
				if(filterFirstName.toUpperCase().contains(newValue)) {
					filteredList.add(ruuat);
				}
			}
			table.setItems(filteredList);
		}
	}

	// Ruoan lisäys tietokantaan
	public void addButtonClicked() {
		try {
			conn = DBconnect.dbConnector();
			Statement myStmt = conn.createStatement();
			String query = "INSERT INTO aine(nimi, kcal, proteiini, hiilihydraatti, rasva) VALUES ('" + nameInput.getText() + "', '" + Double.parseDouble(calorieInput.getText()) + "', '" + Double.parseDouble(proteinInput.getText()) + "', '" + Double.parseDouble(carbInput.getText()) + "', '" + Double.parseDouble(fatInput.getText()) + "')"  ;

			myStmt.executeUpdate(query);

			nameInput.clear();
			calorieInput.clear();
			proteinInput.clear();
			carbInput.clear();
			fatInput.clear();
			table.setItems(getData());
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Ruoan poisto tietokannasta
	public void removeButtonClicked() {
		try {
			conn = DBconnect.dbConnector();
			Statement myStmt = conn.createStatement();
			String query = "DELETE FROM aine WHERE nimi =  '" + nameInput.getText() + "' ";

			myStmt.executeUpdate(query);

			nameInput.clear();
			
			conn.close();
			table.setItems(getData());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//Päivän ateriat
	public ObservableList<Ruoka> getDaily() {
		//lataa tiedosto
		ObservableList<Ruoka> ruokaa = FXCollections.observableArrayList();
		ruokaa.add(new Ruoka("olut", 34.2, 434, 44, 24));
		ruokaa.add(new Ruoka("pähkinä", 33.5, 124, 44, 24));
		ruokaa.add(new Ruoka("oivariini", 278, 23, 1, 34));

		return ruokaa;
	}


	//Ruoan lisäys päivän aterioihin
	public void lisaaButtonClicked() {
		ObservableList<Ruoka> productSelected;
		productSelected = table.getSelectionModel().getSelectedItems();
		table2.getItems().addAll(productSelected);

	}

	//Ruoan poisto päivän aterioista
	public void poistaButtonClicked() {

		ObservableList<Ruoka> productSelected, allProducts;
		allProducts = table2.getItems();
		productSelected = table2.getSelectionModel().getSelectedItems();

		productSelected.forEach(allProducts::remove);
	}


	// Valitsee kaikki ruoat
	public ObservableList<Ruoka> getData() {
		try {
			ruoat.clear();
			conn = DBconnect.dbConnector();
			Statement myStmt = conn.createStatement();
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM aine");
			/*
			if (filterField.getText().isEmpty()){
				myRs = myStmt.executeQuery("SELECT * FROM aine");
			}
			else {
				myRs = myStmt.executeQuery("SELECT * FROM aine WHERE nimi = '" + filterField.getText() + "' ");
			}
			*/

			while (myRs.next()) {
				ruoat.add(new Ruoka(myRs.getString("nimi"),myRs.getDouble("kcal"), myRs.getDouble("proteiini"), myRs.getDouble("hiilihydraatti"), myRs.getDouble("rasva")));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ruoat;
	}

	public void laskeKulutusButtonClicked(ToggleGroup sukupuoliGroup, ToggleGroup aktiviteettiGroup) {
		int kulutus = 0;
		RadioButton valittuSukupuoliRadio = (RadioButton) sukupuoliGroup.getSelectedToggle();
		RadioButton valittuAktiviteettiRadio = (RadioButton) aktiviteettiGroup.getSelectedToggle();
		kulutus = Kulutus.kulutus(valittuSukupuoliRadio.getText(),
				Double.parseDouble(tfIka.getText()),
				Double.parseDouble(tfPituus.getText()),
				Double.parseDouble(tfPaino.getText()),
				valittuAktiviteettiRadio.getText(),
				Integer.parseInt(tfKevyt.getText()),
				Integer.parseInt(tfKohtalainen.getText()),
				Integer.parseInt(tfKuormittava.getText()),
				Integer.parseInt(tfRaskas.getText()));

		/*
		 *
		 */

		tfKulutus.setText(String.valueOf(kulutus));
	}

	public static void main(String[] args) {
		launch(args);
	}
}
