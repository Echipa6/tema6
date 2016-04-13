import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class OurScene {
	private Scene scene;
	private Menu meniu;
	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	

	OurScene()
	{
		Axes axes = new Axes(
                600, 500,
                -8, 8, 1,
                -6, 6, 1
        );

        Plot plot = new Plot(
                "x",
                -8, 8, 0.1,
                axes
        );

        StackPane layout = new StackPane(
                plot
        );
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: rgb(195,246,244);");

        
		VBox root=new VBox();
        
        
        BorderPane border = new BorderPane();
        meniu =new Menu();
        border.setTop(meniu.getHbox());
        
        root.getChildren().addAll(border,layout);
        scene=new Scene(root, Color.rgb(35, 39, 50));
           
	}
	
	
}
