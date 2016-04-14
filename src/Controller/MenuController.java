package Controller;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import View.MainWindow;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class MenuController {

	private MainWindow window;

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
        
	}
	
	public void drawButtonPressed()
	{
		System.out.println("ai apasat draw!");
	}
	
	public void resetButtonPressed()
	{
		System.out.println("ai apasat reset!");
	}
	public void loadButtonPressed()
	{
		System.out.println("ai apasat load!");
	}
	
	
	public MenuController()
	{
		
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
}
