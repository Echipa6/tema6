package Controller;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.math.analysis.polynomials.PolynomialFunctionLagrangeForm;

import Usefull.Plot;
import View.MainWindow;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

public class MenuController {

	private MainWindow window;
	private Plot plot;
	public void saveButtonPressed()
	{
		System.out.println("ai apasat save!");
		FileChooser fileChooser = new FileChooser();
  	  
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        
        //Show save file dialog
        File file = fileChooser.showSaveDialog(window.getLayout().getScene().getWindow());
        
        if(file != null){
           System.out.println(file.toString());
           snapshot(file.toString());
        }
        
        plot.Serealize(file);
        
	}
	
	public void drawButtonPressed(String f, String color, int stroke)
	{
		System.out.println("ai apasat draw!");
		System.out.println("F(x)="+f);
		System.out.println("color="+color);
		
		plot.addFunction(f, color, stroke);
	}
	
	public void resetButtonPressed()
	{
		System.out.println("ai apasat reset!");
		plot.getChildren().setAll(plot.getAxes());
		plot.clearFunctions();
	}
	public void loadButtonPressed()
	{
		System.out.println("ai apasat load!");
		FileChooser fileChooser = new FileChooser();
	  	  
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        
        //Show save file dialog
        File file = fileChooser.showOpenDialog(window.getLayout().getScene().getWindow());
        
        if(file != null){
           System.out.println(file.toString());
           //snapshot(file.toString());
        }
        
        plot.Deserealize(file);
	}
	
	
	public MenuController(MainWindow window,Plot plot)
	{
		this.window = window;
		this.plot=plot;
	}
	
	public void setWindow(MainWindow window) {
		this.window = window;
	}

	private void snapshot(String file) {  
        try {  
        	
            WritableImage image = window.getLayout().snapshot(null, null);  
  
            File outputFile = new File(file); 
  
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", outputFile);  
  
            System.out.println("Wrote: " + outputFile);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
	public void strokePressed(int strokeValue)
	{
		System.out.println("ai apasat stroke! " + strokeValue);
	}
}
