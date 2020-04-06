package nl.inholland.university.View;

import java.io.File;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import nl.inholland.university.Export_Reports;
import nl.inholland.university.Model.Person;

public class ExportReports {

	private ArrayList<Person> userList;
	private Person currentUser;
	private File selectedDirectory;

	public ExportReports(ArrayList<Person> userList, Person currentUser) {
		this.userList = userList;
		this.currentUser = currentUser;
		setupScene();
	}
	
	private void setupScene() {
		// TODO Auto-generated method stub
		Stage window = new Stage();
		window.setTitle("Export reports");
		window.setMinWidth(400);
		window.setMinHeight(150);
		
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(10);
		gridPane.setHgap(8);
		
		Label header = new Label("Export all reports to a directory");
		GridPane.setConstraints(header, 0, 0);
		
		DirectoryChooser directoryChooser = new DirectoryChooser();
	    directoryChooser.setInitialDirectory(new File("src"));
	
	    TextField path = new TextField();
	    path.setDisable(true);
	    GridPane.setConstraints(path, 0, 2);
	    
	    Button btnSelectDirectory = new Button("Select Directory");
	    GridPane.setConstraints(btnSelectDirectory, 1, 2);
	    
	    Button btnExport = new Button("Export");
	    GridPane.setConstraints(btnExport, 0, 4);
	    
	    Button btnGoBack = new Button("Go Back");
	    GridPane.setConstraints(btnGoBack, 1, 4);
	    
	    gridPane.getChildren().addAll(path, header, btnSelectDirectory, btnExport, btnGoBack);
	    
        Scene scene = new Scene(gridPane, 400, 150);
        window.setScene(scene);
        window.show();
	    
	    btnSelectDirectory.setOnAction(e -> {
	        selectedDirectory = directoryChooser.showDialog(window);
	        path.setText(selectedDirectory.getAbsolutePath());
	    });
	    
	    btnExport.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
			public void handle(ActionEvent event) {
	    		new Export_Reports(userList, selectedDirectory);
			}
	    });
	    
	    btnGoBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new MainWindow(userList, currentUser);
				window.close();
			}
		});
	}
}
