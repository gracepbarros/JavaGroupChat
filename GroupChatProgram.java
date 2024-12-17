import java.util.Scanner;

/**
 * The GroupChatProgram class represents a simple group chat program that allows
 * users to interact with a group chat using a console interface. Users can view
 * and post messages, reply to messages, react to messages, delete messages, and
 * switch active users within the chat. This class initializes the chat with
 * sample participants and messages and provides a menu-driven interface for
 * users to interact with the group chat.
 * 
 * @author Dafni Paiva
 * @author Grace Barros
 */
public class GroupChatProgram {
	public static void main(String[] args) {
		GroupChatProgram prog = new GroupChatProgram();
		prog.execute();
	}

	// Data Members
	public Scanner scanner;
	public GroupChat msn;
	public String activeUser;

	/**
	 * Initializes the group chat program. It sets active user to an empty string,
	 * initializes the scanner, and creates a new group chat instance.
	 * 
	 * Sample users and messages are added for testing purposes.
	 */
	public GroupChatProgram() {
		this.activeUser = "";
		this.scanner = new Scanner(System.in);
		this.msn = new GroupChat();

		// Initializing users
		this.msn.addUser("Lucas");
		this.msn.addUser("Camila");
		this.msn.addUser("Gustavo");
		this.msn.addUser("Henrique");

		// Adding some messages
		this.msn.postMessage("Lucas", "Hello, I am taking a break");
		this.msn.postReply("Camila", "Oh, I am always taking a break", 0);
		this.msn.postReply("Gustavo", "I am so jealous! I want a break!", 1);
		this.msn.postMessage("Henrique", "Luna, come here!");
		this.msn.reactToMessage(3, Message.Reactions.HaHaHa);
		this.msn.reactToMessage(1, Message.Reactions.ThumbsUp);
	}

	/**
	 * Executes the group chat program. It prompts the user to choose an active user
	 * and then presents a menu for interacting with the group chat. The user can
	 * choose to view messages, post messages, reply to messages, react to messages,
	 * delete messages, or switch active users.
	 */
	public void execute() {
		int indexUser = chooseUser();
		this.activeUser = this.msn.getParticipants().get(indexUser);
		int option = this.mainMenu();

		//keep calling the mainmenu if user does not chose exit option
		while (option != 8) {
			switch (option) {
			case 1:
				this.seeAllMessages();
				break;

			case 2:
				this.seeMyMessages();
				break;

			case 3:
				this.postMessage();
				break;

			case 4:
				this.replyToMessage();
				break;

			case 5:
				this.reactToMessage();
				break;

			case 6:
				this.deleteMessage();
				break;

			case 7:
				this.switchActiveUser();
			}
			option = this.mainMenu();
		}
		this.exitProgram();
	}

	/**
	 * Allows the user to choose an active user from the available participants in
	 * the group chat.
	 * 
	 * @return The index of the selected active user.
	 */
	public int chooseUser() {
		int number;
		int len = this.msn.getParticipants().size();
		do {
			System.out.println("Choose an active user: ");
			for (int i = 0; i < len; i++) {
				System.out.println((i + 1) + ": " + this.msn.getParticipants().get(i));
			}
			System.out.print("Enter a number from the choices above:\t");
			number = this.scanner.nextInt();
		} while (number <= 0 || number > len);

		this.seeAllMessages();
		return number - 1;
	}

	/**
	 * Displays the main menu for the group chat program and prompts the user to
	 * select an option.
	 * 
	 * @return The selected menu option.
	 */
	public int mainMenu() {
		System.out.println("1: See All Messages");
		System.out.println("2: See My Messages");
		System.out.println("3: Post Message");
		System.out.println("4: Reply to Message");
		System.out.println("5: React to Message");
		System.out.println("6: Delete Message");
		System.out.println("7: Switch Active User");
		System.out.println("8: Exit");
		System.out.println("Enter a number from the choices above: \n");
		int number = this.scanner.nextInt();
		if (number <= 0 || number > 8) {
			mainMenu();
		}
		return number;

	}

	/**
	 * Retrieves multiline text input from the user.
	 * 
	 * @return The entered text as a single string.
	 */
	public String getText() {
		// Consume the newline character left in the buffer
		scanner.nextLine();

		String text = "";
		System.out.println("Enter your message: ");
		String post = this.scanner.nextLine();
		while (!post.isEmpty()) {
			text += post + "\n";
			post = this.scanner.nextLine();
		}
		return text;
	}

	/**
	 * Displays all messages in the group chat.
	 */
	public void seeAllMessages() {
		System.out.println("Starting group chat... \n");
		if (this.msn.getMessages().isEmpty()) {
			System.out.println("\n=== No messages to display ===\n");
		}
		for (String message : this.msn.getMessages()) {
			System.out.println(message);
		}
	}

	/**
	 * Displays messages in the group chat that are relevant to the active user.
	 */
	public void seeMyMessages() {
		System.out.println("User chose: see my messages.\n");
		if (this.msn.getRelevantMessages(this.activeUser).isEmpty()) {
			System.out.println("\n=== No messages to display ===\n");
		}
		for (String message : this.msn.getRelevantMessages(this.activeUser)) {
			System.out.println(message);
		}
	}

	/**
	 * Allows the active user to post a new message in the group chat.
	 */
	public void postMessage() {
		System.out.println("User chose: post message.");
		this.msn.postMessage(this.activeUser, this.getText());
		System.out.println("Message posted. \n");
	}

	/**
	 * Allows the active user to post a reply to a specific message in the group
	 * chat.
	 */
	public void replyToMessage() {
		System.out.println("User chose: reply to message.\n");
		System.out.println("What is the ID of the message?: ");
		int id = this.scanner.nextInt();
		this.msn.postReply(this.activeUser, this.getText(), id);
		System.out.println("Reply posted.");
	}

	/**
	 * Allows the active user to react to a specific message in the group chat by
	 * selecting a reaction from a list of predefined reactions.
	 */
	public void reactToMessage() {
		System.out.println("User chose: react to message.\n");
		System.out.println("What is the ID of the message?: ");
		int id = this.scanner.nextInt();
		int index;
		Message.Reactions[] reactions = Message.Reactions.values();
		do {
			for (int i = 0; i < reactions.length; i++) {
				System.out.println((i + 1) + ": " + reactions[i]);
			}
			System.out.println("Enter a number from the choices above: \n");
			index = this.scanner.nextInt();
		} while (index < 1 || index > 5);
		this.msn.reactToMessage(id, reactions[index - 1]);
		System.out.println("Reaction added.");
	}

	/**
	 * Allows the active user to delete a message they previously posted in the
	 * group chat.
	 */
	public void deleteMessage() {
		System.out.println("User chose: delete message.");
		System.out.println("What is the ID of the message?: ");
		int id = this.scanner.nextInt();
		this.msn.deleteMessage(this.activeUser, id);
		System.out.println("Message deleted.");
	}

	/**
	 * Allows the user to switch to a different active user from the available
	 * participants in the group chat.
	 */
	public void switchActiveUser() {
		System.out.println("User chose: switch active user.");
		int indexUser = chooseUser();
		this.activeUser = this.msn.getParticipants().get(indexUser);
		System.out.println("User chose: " + this.activeUser);
	}

	/**
	 * Exits the Group Chat Program.
	 */
	public void exitProgram() {
		System.out.println("User chose: Exit the Group Chat Program.");
	}

}
