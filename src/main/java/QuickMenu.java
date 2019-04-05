import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

public class QuickMenu extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    private double width = 500;

    private void execute(String text) {
        try {
            Runtime.getRuntime().exec(text);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        ApplicationPath ap = new ApplicationPath();
        ap.getFileList();
        primaryStage.setTitle("Quick Menu");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        TextField search = new TextField();

        AutoCompletionBinding acb = TextFields.bindAutoCompletion(search, ap.filesString);

        acb.setPrefWidth(width);

        acb.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<String>>() {
            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent<String> stringAutoCompletionEvent) {
                execute(search.getText());
                primaryStage.close();
            }
        });

        search.setOnKeyPressed(new EventHandler<KeyEvent>() {
           @Override
           public void handle(KeyEvent ke) {
               if(ke.getCode().equals(KeyCode.ENTER)) {
                   execute(search.getText());
                   primaryStage.close();
               }

               if(ke.getCode().equals(KeyCode.ESCAPE)) {
                   primaryStage.close();
               }
           }
        });

        VBox root = new VBox();
        root.getChildren().add(search);
        primaryStage.setScene(new Scene(root, width, 27));
        primaryStage.show();
    }
}
