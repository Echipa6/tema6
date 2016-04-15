package View;

import Usefull.Axes;
import Usefull.Plot;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainWindow {
	
	private VBox root;
	private StackPane layout;

	public StackPane getLayout() {
		return layout;
	}

	public VBox getRoot() {
		return root;
	}

	public MainWindow(Plot plot, HBox hbox)
	{
		layout = new StackPane(plot);
        layout.setPadding(new Insets(20));
        //layout.setStyle("-fx-background-color: rgb(195,246,244);");
        layout.setStyle("-fx-background-color: rgb(249,250,240);");
        root=new VBox();
        
        BorderPane border = new BorderPane();
        border.setTop(hbox);
        
        root.getChildren().addAll(border,layout);
	}

}
