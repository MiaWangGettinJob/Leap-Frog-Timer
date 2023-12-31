package application.controller;

import application.model.Data;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

import application.model.Leaf;
import application.model.Music;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.StageStyle;
import javafx.scene.layout.BorderPane;
import DatabaseConnection.DatabaseAccessor;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

import java.util.HashMap;


public class StoreController{
	private Stage stage;
	private Scene scene;
	private Parent root;
	private int leafNumber;
	Font ubuntuFont;
	private Music music;
	HashMap<Integer, Button> buttonMap = new HashMap<Integer, Button>();
	DatabaseAccessor db;
		
	
	@FXML
	private Label leafLabel;
	
	@FXML
	private Label shopTitle;
	
	@FXML
	private AnchorPane mainPane;
	
	@FXML
	private Button buyButton1;
	
	@FXML
	private Button buyButton2;
	
	@FXML
	private Button buyButton3;

	@FXML
	private Button buyConfirm;
	
	@FXML
	private Button notBuy;
	
	
	void relateButtontoId() {
		buttonMap.put(1, buyButton1);
		buttonMap.put(2, buyButton2);
		buttonMap.put(3, buyButton3);
		return;
	}
	
	int getLeafNumber(DatabaseAccessor db) throws SQLException {
		
        Statement statement = db.getConnection().createStatement();
        ResultSet leaves = Data.excuateQuerySql("SELECT SUM(leafGot) AS [totalEarned] FROM LEAF", db, statement);
		int leavesEarned = 0;
		if (leaves.next()) {
			leavesEarned = leaves.getInt("totalEarned");
		}		
		
       
		ResultSet leavesSpent = Data.excuateQuerySql("SELECT SUM( CASE WHEN available = 'true' Then price ELSE 0 END) AS [totalCost] FROM MUSIC", db, statement);
		int result = 0;
		if (leavesSpent.next()) {
			result = leavesSpent.getInt("totalCost");
		}
		statement.close();
 		return leavesEarned - result;	
	
	}
	
	void setLabelStyle() throws SQLException{
		leafNumber = getLeafNumber(db);
	
		leafLabel.setText("        Leaf Collected:  " + leafNumber);
		leafLabel.setStyle(" -fx-font-size: 12; "
				+"-fx-font-weight: bold;"
				+ " -fx-text-fill: #7A3732;"
				+"-fx-background-color: #DAEED7;"
				+"-fx-background-radius:  30 0 0 30;"
				+"-fx-font-family: '" + ubuntuFont.getName() + "'"
				);
		
		shopTitle.setText("Froggie Shop");
		shopTitle.setStyle(" -fx-font-size: 24; "
				+"-fx-font-weight: bold;"
				+ " -fx-text-fill: #7A3732;"
				+"-fx-font-family: '" + ubuntuFont.getName() + "'"
				);
	}
	
	
	//if a music is bought show "Sold"
	void setButton() throws SQLException{
        Statement statement = db.getConnection().createStatement();
		List<Music> boughtMusic = Music.getBoughtMusic(db, statement);
		statement.close();
		boughtMusic.toString();
		for (int i = 0; i < boughtMusic.size(); i++) {
			Music current = boughtMusic.get(i);
			int index = current.getMusicId();
			Button currentB =  buttonMap.get(index);
			//change button
			System.out.print(current.getMusicName());
			currentB.setText("Sold");
			currentB.setStyle("-fx-background-color: #8D8D8D; -fx-background-radius: 30 30 30 30; ");
			
		}
		return;
	}
	
	

	
	@FXML
	private void buyMusic1(ActionEvent event) throws IOException, SQLException  {
		if (leafNumber >= 10) {
			//change music available status
				
	        Statement statement = db.getConnection().createStatement();
	        Music.updateAvailability(db, statement,1);
			statement.close();
			
			//change button
			buyButton1.setText("Sold");
			buyButton1.setStyle("-fx-background-color: #8D8D8D; -fx-background-radius: 30 30 30 30; ");
				
			//update leafNumber
			leafNumber = getLeafNumber(db);
			leafLabel.setText("        Leaf Collected:  " + leafNumber);

		}
		else {
			NotBuyAlert();
		}	
		
	}
	
	
	
	@FXML
	private void buyMusic2(ActionEvent event) throws IOException, SQLException  {
		if (leafNumber >= 10) {
			//change music available status
	        Statement statement = db.getConnection().createStatement(); 
	        Music.updateAvailability(db, statement,2);
			statement.close();			
			//change button
			buyButton2.setText("Sold");
			buyButton2.setStyle("-fx-background-color: #8D8D8D; -fx-background-radius: 30 30 30 30; ");				
			//update leafNumber
			leafNumber = getLeafNumber(db);
			leafLabel.setText("        Leaf Collected:  " + leafNumber);
		}
		else {
			NotBuyAlert();
		}	
		
	}
	
	@FXML
	private void buyMusic3(ActionEvent event) throws IOException, SQLException  {
		if (leafNumber >= 10) {
			//change music available status
	        Statement statement = db.getConnection().createStatement(); 
	        Music.updateAvailability(db, statement,3);
			statement.close();

			//change button
			buyButton3.setText("Sold");
			buyButton3.setStyle("-fx-background-color: #8D8D8D; -fx-background-radius: 30 30 30 30; ");
				
			//update leafNumber
			leafNumber = getLeafNumber(db);
			leafLabel.setText("        Leaf Collected:  " + leafNumber);
		}
		else {
			NotBuyAlert();
		}	
		
	}

	@FXML
	private void NotBuyAlert() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/ui/BuyAlert.fxml"));
		BorderPane borderPane = loader.load();
		
		
        // Create a new dialog using the custom content
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.getDialogPane().setContent(borderPane);

        // Add a button to the dialog's button pane (for demonstration purposes)
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.setStyle("-fx-background-color: #DAEED7; "
		+ "-fx-background-radius: 30 30 30 30; "
		+ "-fx-font-size: 16; "
		+"-fx-font-weight: bold");
        
        dialog.showAndWait();
	}
	


	public void initialize(DatabaseAccessor db) {	
		relateButtontoId();
		this.db = db;
		this.ubuntuFont = Font.loadFont(getClass().getResourceAsStream("/resource/fonts/Ubuntu-Regular.ttf"), 16);
		try {
			setButton();
			setLabelStyle();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



}
