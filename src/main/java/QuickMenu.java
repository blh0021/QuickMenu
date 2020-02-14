import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

        ListView listView = new ListView();
        listView.setItems(FXCollections.observableList(ap.filterFiles("")));

        search.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                String txt = search.getText();
                listView.setItems(FXCollections.observableList(ap.filterFiles(txt)));
                listView.getSelectionModel().select(0);
            }
        });

        search.setOnKeyPressed(new EventHandler<KeyEvent>() {
           @Override
           public void handle(KeyEvent ke) {
               if(ke.getCode().equals(KeyCode.ENTER)) {
                   String txt = (String) listView.getSelectionModel().getSelectedItems().get(0);
                   execute(txt);
                   primaryStage.close();
               }

               if(ke.getCode().equals(KeyCode.ESCAPE)) {
                   primaryStage.close();
               }

               if (ke.getCode().isArrowKey()) {
                   listView.fireEvent(ke);
               }
           }
        });

        listView.setOnMouseClicked(event -> {
                String txt = (String) listView.getSelectionModel().getSelectedItems().get(0);
                execute(txt);
                primaryStage.close();
        });

        primaryStage.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean onHidden, Boolean onShown)
            {
                if(!onShown) {
                    primaryStage.close();
                }
            }
        });

        VBox root = new VBox();
        root.getChildren().add(search);
        root.getChildren().add(listView);
        primaryStage.setScene(new Scene(root, width, 150));
        primaryStage.show();
    }
}
