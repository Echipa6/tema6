import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Menu {
	private HBox  hbox;
	private TextField functionField;
	private ColorPicker colorPicker1;
	private Button buttonDraw;
	public HBox getHbox() {
		return hbox;
	}
	public TextField getFunctionField() {
		return functionField;
	}
	public ColorPicker getColorPicker1() {
		return colorPicker1;
	}
	public Menu()
	{
	    hbox=new HBox();
	    hbox.setPadding(new Insets(5, 12, 5, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");

	    Label labelFnct = new Label("F(x):");
	    
	    labelFnct.setStyle("-fx-text-fill: white;-fx-font-size: 11pt;-fx-font-weight: bold;");
	    functionField = new TextField ("");
	    
	    colorPicker1 = new ColorPicker();
	    
	    
	    buttonDraw = new Button("Draw");
	    buttonDraw.setPrefSize(50, 15);
	    
	    buttonDraw.setOnAction(e -> {
            System.out.println(functionField.getText());
            System.out.println(colorPicker1.getValue());
        });

	    Button buttonReset = new Button("Reset");
	    buttonReset.setPrefSize(60, 20);
	    
	    Button buttonSave = new Button("Save");
	    buttonReset.setPrefSize(60, 20);
	    
	    Button buttonLoad = new Button("Load");
	    buttonLoad.setPrefSize(60, 20);
	    
	    hbox.getChildren().addAll(labelFnct, functionField, colorPicker1, buttonDraw, buttonReset, buttonSave, buttonLoad);

	   
	}
}
