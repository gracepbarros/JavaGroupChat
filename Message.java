import java.util.ArrayList;

/**
 * The Message class represents a message within a chat application. It
 * includes information about the sender's username, message content, associated
 * reactions, and a message ID.
 * 
 * @author Dafni Paiva
 * @author Grace Barros
 */
public class Message {

	/**
	 * The Reactions enum defines various emotional reactions that can be associated
	 * with a message. These reactions represent the sender's emotional state or
	 * sentiment in response to a message.
	 * 
	 * The available reactions are: Neutral, Happy, Angry, and Sad.
	 */
	public enum Reactions {
		HaHaHa, EyeRoll, ThumbsUp, Grrrr
	};

	private String username, text;
	private ArrayList<Reactions> reactionsList;
	private int id;

	private static int countId = 0;

	/**
	 * Constructs a Message object with the given username and text content.
	 * Initializes an empty reactions list and assigns a unique identifier.
	 *
	 * @param username The username of the message sender.
	 * @param text     The content of the message.
	 */
	public Message(String username, String text) {
		this.username = username;
		this.text = text;
		this.reactionsList = new ArrayList<Reactions>();
		this.id = countId++;
	}

	/**
	 * Gets the username of the message sender.
	 *
	 * @return The username of the sender.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Gets the text content of the message.
	 *
	 * @return The text content of the message.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Gets the list of reactions associated with the message.
	 *
	 * @return The list of reactions.
	 */
	public ArrayList<Reactions> getReactionsList() {
		return reactionsList;
	}

	/**
	 * Gets the identifier (ID) of the message.
	 *
	 * @return The message ID.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the current count of message IDs.
	 *
	 * @return The count of message IDs.
	 */
	public static int getCountId() {
		return countId;
	}

	/**
	 * Adds a reaction to the message.
	 *
	 * @param reaction The reaction to be added.
	 */
	public void addReaction(Reactions reaction) {
		this.reactionsList.add(reaction);
	}

	/**
	 * Generates a summary String of the reactions associated with the message.
	 *
	 * @return A summary of reactions in the format: "Neutral: n, Happy: n, Angry:
	 *         n, Sad: n".
	 */
	public String getReactionSummary() {
		String result = "";
		if (this.reactionsList.isEmpty()) {
			return result;
		}

		int countHaHaHa = 0, countEyeRoll = 0, countAngry = 0, countOK = 0;
		for (int i = 0; i < reactionsList.size(); i++) {
			switch (reactionsList.get(i)) {

			case HaHaHa:
				countHaHaHa++;
				break;

			case  EyeRoll:
				countEyeRoll++;
				break;

			case  Grrrr:
				countAngry++;
				break;

			case ThumbsUp:
				countOK++;
			}
		}

		result = "Neutral: " + countHaHaHa + "\n" + "Happy: " + countEyeRoll + "\n" + "Angry: " + countAngry + "\n"
				+ "Sad: " + countOK + "\n";

		return result;
	}

	/**
	 * Checks if the message is relevant to a given user.
	 *
	 * @param user The username to check relevance for.
	 * @return true if the message is relevant to the user, false otherwise.
	 */
	public boolean relevantTo(String user) {
		if (this.username == user || this.text.contains("@" + user + "@")) {
			return true;
		}

		return false;
	}

    /**
     * Generates a string representation of the message.
     *
     * @return A string with message details, including message ID, creator, text, and reaction summary.
     */
	public String toString() {

		String result = "";

		result += "ID: " + this.id + "\n" +
				"Creator: " + this.username + "\n" +
				"Text: " + this.text + "\n"
				+ "Reaction Summary: \n" + this.getReactionSummary();

		return result;
	}

    /**
     * Generates a short string representation of the message.
     * Deletes the text content if it exceeds 50 characters.
     *
     * @return A string up to 50 characters with the creator and text.
     */
	public String shortString() {

		String result = "";

		result += "Creator: " + this.username + "\n" + "Text: " + this.text + "\n";
		if (result.length() > 50) {
			result = result.replaceAll("\n", " ");
			result = result.substring(0, 50);
		}

		return result;
	}

}
