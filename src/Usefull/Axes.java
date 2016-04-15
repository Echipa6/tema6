package Usefull;
import javafx.beans.binding.Bindings;
import javafx.geometry.Side;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Axes extends Pane {
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private double xMin;
    private double xMax;
	private double yMin;
	private double yMax;
	
	public double getxMin() {
		return xMin;
	}

	public double getxMax() {
		return xMax;
	}

	public double getyMin() {
		return yMin;
	}

	public double getyMax() {
		return yMax;
	}

	
    public Axes(int width, int height,double xTickUnit, double yTickUnit) {
    	Text origin = new Text();
    	origin.setText("O");
    	origin.setLayoutX(width/2-4);
    	origin.setLayoutY(height/2+5);
    	
    	Text yLabel= new Text();
    	yLabel.setText("Y");
    	yLabel.setLayoutX(width/2-4);
    	
    	Text xLabel= new Text();
    	xLabel.setText("X");
    	xLabel.setLayoutY(height/2+5);
    	xLabel.setLayoutX(width);
    	
    	
    	
    	xMin=-width/100;
    	xMax=width/100;
    	yMin=-height/30;
    	yMax=height/30;
    	
        setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        setPrefSize(width, height);
        setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        xAxis = new NumberAxis(xMin, xMax, xTickUnit);
        xAxis.setSide(Side.BOTTOM);
        xAxis.setMinorTickVisible(false);
        xAxis.setPrefWidth(width);
        xAxis.setLayoutY(height / 2);

        yAxis = new NumberAxis(yMin, yMax, yTickUnit);
        yAxis.setSide(Side.LEFT);
        yAxis.setMinorTickVisible(false);
        yAxis.setPrefHeight(height);
        yAxis.layoutXProperty().bind(Bindings.subtract((width / 2) + 1,yAxis.widthProperty()));

        getChildren().setAll(xAxis, yAxis,origin,xLabel,yLabel);
    }

    public NumberAxis getXAxis() {
        return xAxis;
    }

    public NumberAxis getYAxis() {
        return yAxis;
    }
}
