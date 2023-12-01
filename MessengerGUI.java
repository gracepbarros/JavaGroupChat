import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

import java.util.ArrayList;

/**
 * @author Grace Barros
 * @author Dafni Paiva
 */
public class MessengerGUI extends Application {
    //Program parameters
	private GroupChat groupChat = new GroupChat();
    private String currentUser;

	// Main Layout
	private VBox root = new VBox();
	private ListView<String> listMessages = new ListView<>();
	private CheckBox relevantCheck = new CheckBox("Show My Relevant Messages Only");
	private Text currentAction = new Text("Select A User");
	private TabPane tabPane = new TabPane();

	// Choose User Tab
	private HBox userBox = new HBox(10);
	private Tab userTab = new Tab("Choose User", userBox);
    private TextField userField = new TextField();
    private Button userSelect = new Button("Select");

	// Act On Messages Tab
	private HBox actonBox = new HBox(10);
	private Tab actonTab = new Tab("Act On Messages", actonBox);
	private RadioButton thumbs = new RadioButton("Thumbs Up");
	private RadioButton hahaha = new RadioButton("Ha Ha Ha");
	private RadioButton eyeroll = new RadioButton("Eye Roll");
	private ToggleGroup reactions = new ToggleGroup(); // setToggleGroup
	private Button actReact = new Button("React To Message");
	private Button actDelete = new Button("Delete Message");

	// Post Message Tab
	private VBox postBox = new VBox();
	private Tab postTab = new Tab("Post Message", postBox);
	private TextArea postArea = new TextArea();
	private Button postReply = new Button("Reply To Message");
	private Button postPost = new Button("Post Message");

	@Override
    public void start(Stage primaryStage) {
        //Init Group Chat message and users
        groupChat.addUser("Grace");
        groupChat.addUser("Dafni");
        groupChat.addUser("Luiz");
        groupChat.addUser("Lucas");

        groupChat.postMessage("Lucas", "O @Luiz@ eh um frango arrombado");
        groupChat.postMessage("Luiz", "Sou mesmo");
        groupChat.postReply("Dafni", "Voce tambem eh arrombado",0);
        groupChat.addReaction(1, Message.Reaction.HaHaHa);
        groupChat.addReaction(2, Message.Reaction.HaHaHa);
        groupChat.postMessage("Grace", "Voces so sabem arrumar confusao");
        groupChat.addReaction(3, Message.Reaction.EyeRoll);
        groupChat.addReaction(3, Message.Reaction.EyeRoll);

        updateListView(groupChat.getMessages());

        //Relevant CheckBox
        relevantCheck.setDisable(true);
        relevantCheck.setOnAction(new relevantMessagesEventHandler());

        //User Tab
        Text enter = new Text("Enter Username:");
        userBox.getChildren().addAll(enter, userField, userSelect);
        userTab.setClosable(false);
        //User Tab Event handler
        userSelect.setOnMouseClicked(new selectUserEventHandler());

        //Act Tab
        thumbs.setToggleGroup(reactions);
        hahaha.setToggleGroup(reactions);
        eyeroll.setToggleGroup(reactions);
        VBox radioBox = new VBox(5, thumbs, hahaha, eyeroll);
        actonBox.getChildren().addAll(radioBox, actReact, actDelete);
        actonTab.setClosable(false);
        actonTab.setDisable(true);

        //Post Tab
        HBox postButtons = new HBox(5, postReply, postPost);
        postArea.setFont(Font.font(java.awt.Font.MONOSPACED));
        postBox.getChildren().addAll(postArea,postButtons);
        postTab.setClosable(false);
        postTab.setDisable(true);

        tabPane.getTabs().addAll(userTab,actonTab,postTab);
        currentAction.setStyle("-fx-font-weight: bold;");
        root.getChildren().addAll(listMessages, relevantCheck, currentAction, tabPane);

        // Set padding, margin, and alignment
        tabPane.setPadding(new Insets(5));
        userBox.setAlignment(Pos.CENTER);
        relevantCheck.setPadding(new Insets(5));
        actonBox.setAlignment(Pos.CENTER);
        actonBox.setPadding(new Insets (20));
        postButtons.setAlignment(Pos.BOTTOM_RIGHT);
        
        // Set heights/widths
        listMessages.setPrefHeight(300);
        postArea.setPrefHeight(100);
        
        Scene scene = new Scene(root, 500, 450);
        primaryStage.setTitle("GroupChat GUI");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

	public static void main(String[] args) {
		Application.launch(args);
	}

    private class selectUserEventHandler implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent mouseEvent) {
            String user = userField.getText();
            if(groupChat.getUserList().contains(user)){
                currentUser = user;
                currentAction.setText("Current user: " + currentUser);
                relevantCheck.setDisable(false);
                actonTab.setDisable(false);
                postTab.setDisable(false);
            }
        }
    }

    private class relevantMessagesEventHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent action){
            if(relevantCheck.isSelected()) {
                updateListView(groupChat.getRelevantMessages(currentUser));
            } else {
                updateListView(groupChat.getMessages());
            }
        }
    }

    private void updateListView(ArrayList<String> messages){
        listMessages.getItems().clear();
        for(String msg: messages){
            listMessages.getItems().add(msg);
        }
    }

    private class actOnMessagesEventHandler implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent e){
            if (e.getSource() == actReact){

            } else{

            }
        }
    }
}
