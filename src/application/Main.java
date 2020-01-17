	package application;
	
	import java.io.IOException;
	
	import javafx.application.Application;
	import javafx.fxml.FXMLLoader;
	import javafx.stage.Stage;
	import javafx.stage.StageStyle;
	import javafx.scene.Parent;
	import javafx.scene.Scene;
	
	public class Main extends Application {
		private double xOffset;
		private double yOffset;
	
		@Override
		public void start(Stage stage) throws IOException 
		{
			Parent root = FXMLLoader.load(getClass().getResource("view/FXMLIndex.fxml"));
	
			stage.initStyle(StageStyle.TRANSPARENT);
	
			Scene scene = new Scene(root);
	
			/*
			 * The two following lambda expressions makes it possible to move the
			 * application without the standard StageStyle
			 */
			// Lambda mouse event handler
			scene.setOnMousePressed(event -> {
				xOffset = stage.getX() - event.getScreenX();
				yOffset = stage.getY() - event.getScreenY();
			});
			// Lambda mouse event handler
			scene.setOnMouseDragged(event -> {
				stage.setX(event.getScreenX() + xOffset);
				stage.setY(event.getScreenY() + yOffset);
			});
			
			scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
			stage.setScene(scene);
			stage.show();
	
		}
	
		public static void main(String[] args) {
			launch(args);
			
		}
		
		
	}
