package tdt4140.gr1802.app.ui;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tdt4140.gr1802.app.core.Athlete;
import tdt4140.gr1802.app.core.Coach;
import tdt4140.gr1802.app.core.Database;

public class CoachRequestsController {
	
	private Database database = new Database();
	private Athlete athlete = database.getAthlete("Nils22");

	@FXML
	private Button btAddWorkout;
	
	@FXML
	private Button btSeeWorkouts;
	
	@FXML
	private Button btSeeCoaches;
	
	@FXML
	private Button btCoachRequests;
	
	@FXML
	private TableView<Coach> tableView;
	
	@FXML
	private TableColumn<Coach, String> usernameColumn;
	
	@FXML
	private TableColumn<Coach, String> nameColumn;
	
	private ObservableList<Coach> coaches = FXCollections.observableArrayList();
	
	// Help-method that returns an ObservableList with all the Coach-requests
	public ObservableList<Coach> getCoaches(){
		for (String uname:athlete.getQueuedCoaches()) {
			Coach coach = database.getCoach(uname);
			if (coach != null) {
				coaches.add(coach);
			}
		}
		return coaches;
	}
	
	public void initialize() {
		// Connect columns to right attribute
		nameColumn.setCellValueFactory(new PropertyValueFactory<Coach,String>("name"));
		usernameColumn.setCellValueFactory(new PropertyValueFactory<Coach,String>("username"));
		
		// Make it legal to select multiple rows
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		// Fill table with values
		tableView.setItems(getCoaches());
	}
	
	public void acceptButton(ActionEvent event) throws IOException, RuntimeException, InvocationTargetException{
		ObservableList<Coach> selectedRows;
		
		// Gives us the rows that are selected
		selectedRows = tableView.getSelectionModel().getSelectedItems();
		
		// For each coach; remove from screen and approve
		for (Coach coach : selectedRows) {
			coaches.remove(coach);
			athlete.approveCoach(coach.getUsername());
		}
		
	}

	public void declineButton(ActionEvent event) throws IOException, RuntimeException, InvocationTargetException{
		ObservableList<Coach> selectedRows;
		
		// Gives us the rows that are selected
		selectedRows = tableView.getSelectionModel().getSelectedItems();
		
		// For each coach; remove from screen and decline
		for (Coach coach: selectedRows) {
			coaches.remove(coach);
			athlete.declineCoach(coach.getUsername());
		}
	}
	
	// Side-menu buttons
	public void clickAddWorkout (ActionEvent event) throws IOException, LoadException{
		// Open new window 
		Parent root = FXMLLoader.load(getClass().getResource("AddWorkout.fxml"));
		Scene scene = new Scene(root,800,600);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(scene);
		window.show();
		
	}
	
	public void clickSeeWorkouts (ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("SeeWorkouts.fxml"));
		Scene scene = new Scene(root,800,600);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
	public void clickSeeCoaches (ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("SeeCoaches.fxml"));
		Scene scene = new Scene(root,800,600);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(scene);
		window.show();
	}
	
	public void clickCoachRequest (ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("CoachRequests.fxml"));
		Scene scene = new Scene(root,800,600);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(scene);
		window.show();
	}

}
