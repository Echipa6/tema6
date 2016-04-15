package Usefull;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Plot extends Pane {
    
	private Expression e;
	private Axes axes;
	private List<FunctionG> functions;
	public Vector<Double> xCoordinate;
    public Vector<Double> yCoordinate;
	
    public Plot(Axes axes){
		this.functions=new ArrayList<FunctionG>();
		this.axes=axes;
		getChildren().setAll(axes);
		xCoordinate= new Vector<Double>();
        yCoordinate= new Vector<Double>();
	}
	
    
	public void setFunctions(List<FunctionG> functions) {
		this.functions = functions;
		
	}
	
	public List<FunctionG> getFunctions() {
		return functions;
	}
	
	public void drawFunctions()
	{
		this.getChildren().setAll(axes);
		for(FunctionG f:functions)
		{
			drawPath(f.getFunction(),0.01,f.getColor(),Integer.valueOf(f.getStroke()).intValue());
		}
	}
	
	public void clearFunctions(){
		functions.clear();
	}
	
	public Axes getAxes() {
		return axes;
	}
	
	
	public void setAxes(Axes axes) {
		this.axes = axes;
		
		drawFunctions();
	}

	
	public void addFunction(String f,String color, int stroke)
	{
		int codRetur;
		codRetur=drawPath(f,0.01,color,stroke);
		if(codRetur==1)functions.add(new FunctionG(f,color,String.valueOf(stroke)));	
	}
	
	
	public int drawPath(String f, double xInc,String color, int stroke)
	{
		try{
		e = new ExpressionBuilder(f).variables("x").build();
		}catch(IllegalArgumentException e)
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Exceptie!");
			alert.setHeaderText(null);
			alert.setContentText("Pentru a desena graficul unei functii, aceasta nu trebuie sa fie valida. Verifica daca functia este corecta.");

			alert.showAndWait();
			return 0;
		}
			
        Path path = new Path();
        Color c=Color.web(color);
        path.setStroke(c);
        path.setStrokeWidth(stroke);
        path.setClip(new Rectangle(0, 0, axes.getPrefWidth(), axes.getPrefHeight()));

        double x = axes.getxMin();
        e.setVariable("x",x);
        double y = e.evaluate();

        path.getElements().add(new MoveTo(mapX(x, axes), mapY(y, axes)));

        x += xInc;
        while (x < axes.getxMax()) {
        	e.setVariable("x",x);
            y = e.evaluate();
            
            path.getElements().add(new LineTo(mapX(x, axes), mapY(y, axes)));

            x += xInc;
        }

        getChildren().add(path);
        return 1;
	}
	
    private double mapX(double x, Axes axes) {
        double tx = axes.getPrefWidth() / 2;
        double sx = axes.getPrefWidth() / 
           (axes.getXAxis().getUpperBound() - 
            axes.getXAxis().getLowerBound());

        return x * sx + tx;
    }

    private double mapY(double y, Axes axes) {
        double ty = axes.getPrefHeight() / 2;
        double sy = axes.getPrefHeight() / 
            (axes.getYAxis().getUpperBound() - 
             axes.getYAxis().getLowerBound());

        return -y * sy + ty;
    }
    
    public void Serealize(File file){
    	try{
    		
    		String fileName=(file.getName().toString()).substring(0,(file.getName().toString()).lastIndexOf('.'))+".xml";
			XMLEncoder encoder = new XMLEncoder( new BufferedOutputStream( new FileOutputStream(fileName)));
			encoder.writeObject(this.getFunctions());
			encoder.close();
			
		}catch(FileNotFoundException e)
		{
			System.out.println("exceptie File NotFound");
		}
		
    }
    
    public void Deserealize(File file){
    	try{
    		
    		String fileName=(file.getName().toString()).substring(0,(file.getName().toString()).lastIndexOf('.'))+".xml";
    		XMLDecoder decoder =new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));
				@SuppressWarnings("unchecked")
				List<FunctionG> readObject = (List<FunctionG>)decoder.readObject();
				this.setFunctions(readObject);
				decoder.close();
				drawFunctions();
		}catch(FileNotFoundException e)
		{
			System.out.println("Exception");
		}
    	
    }
    
}
