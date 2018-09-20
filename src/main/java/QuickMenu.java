import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.textfield.TextFields;

public class QuickMenu extends Application {


    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        ApplicationPath ap = new ApplicationPath();
        ap.getFileList();
        primaryStage.setTitle("Quick Menu");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        TextField search = new TextField();
        TextFields.bindAutoCompletion(search, ap.filesString);

        search.textProperty().addListener((observable, oldValue, newValue) -> {

            //System.out.println(newValue);
            //System.out.println(observable);
            //primaryStage.close();
        });

        search.setOnKeyPressed(new EventHandler<KeyEvent>() {
           @Override
           public void handle(KeyEvent ke) {
               if(ke.getCode().equals(KeyCode.ENTER)) {
                   try {
                       System.out.println(search.getText());
                       Runtime.getRuntime().exec(search.getText());
                   } catch (Exception e) {
                       System.out.println(e.getMessage());
                   }
                   primaryStage.close();
               }
           }
        });

        VBox root = new VBox();
        root.getChildren().add(search);
        primaryStage.setScene(new Scene(root, 500, 50));
        primaryStage.show();
    }
}
