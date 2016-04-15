package View;

import Controller.MenuController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class TopMenu {
	
	
	MenuController myMenu;
	


	private HBox  hbox;
	private TextField functionField;
	private ColorPicker colorPicker1;
	private Button buttonDraw;
	private Button buttonReset;
	private Button buttonSave;
	private Button buttonLoad;
	
	private ComboBox<Integer> stroke;
	
	public void setMyMenu(MenuController myMenu) {
		this.myMenu = myMenu;
		buttonDraw.setOnAction(e -> {
			myMenu.drawButtonPressed(functionField.getText(),colorPicker1.getValue().toString(),stroke.getSelectionModel().getSelectedItem().intValue());
			System.out.println(functionField.getText());
			System.out.println(colorPicker1.getValue());
		});
		
		buttonReset.setOnAction(e -> { myMenu.resetButtonPressed();});
		buttonSave.setOnAction(e -> { myMenu.saveButtonPressed();});
		buttonLoad.setOnAction(e -> { myMenu.loadButtonPressed();});
		
		stroke.setOnAction(ev -> {
			myMenu.strokePressed(stroke.getSelectionModel().getSelectedItem().intValue());
		});
		stroke.getSelectionModel().selectFirst();
	}
	public ComboBox<Integer> getStroke() {
		return stroke;
	}
	public MenuController getMyMenu() {
		return myMenu;
	}
	

	
	public HBox getHbox() {
		return hbox;
	}
	public TextField getFunctionField() {
		return functionField;
	}
	public ColorPicker getColorPicker1() {
		return colorPicker1;
	}

	public TopMenu()
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

		

		buttonReset = new Button("Reset");
		buttonReset.setPrefSize(60, 20);
		

		buttonSave = new Button("Save");
		buttonSave.setPrefSize(60, 20);
		

		buttonLoad = new Button("Load");
		buttonLoad.setPrefSize(60, 20);
		
		stroke= new ComboBox<Integer>();
		stroke.getItems().addAll(1,2,3,4,5);
		stroke.setPromptText("Stroke");
		


		hbox.getChildren().addAll(labelFnct, functionField, colorPicker1,stroke ,buttonDraw, buttonReset, buttonSave, buttonLoad);
	}

}
