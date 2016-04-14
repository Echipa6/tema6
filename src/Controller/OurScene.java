package Controller;
import Usefull.Axes;
import Usefull.Plot;
import View.MainWindow;
import View.TopMenu;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class OurScene {
	private Scene scene;
	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	

	public OurScene()
	{
		TopMenu topMenu= new TopMenu();
		
		
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
        
        MainWindow mainWindow= new MainWindow(plot,topMenu.getHbox());
        MenuController menuController= new MenuController(mainWindow);
        topMenu.setMyMenu(menuController);
        
        scene=new Scene(mainWindow.getRoot(), Color.rgb(35, 39, 50));
        
       

           
	}
	
	
}
