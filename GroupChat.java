import java.util.ArrayList;

/**
 * The GroupChat class represents a group chat within a messaging application.
 * It allows users to participate, exchange messages, and react to messages.
 * 
 * @author Dafni Paiva
 * @author Grace Barros
 */
public class GroupChat {
	private ArrayList<String> participants;
	private ArrayList<Message> messageList;

	/**
	 * Constructs a new GroupChat instance with empty participant and message lists.
	 */
	public GroupChat() {
		this.participants = new ArrayList<String>();
		this.messageList = new ArrayList<Message>();
	}

	/**
	 * Gets the list of participants in the group chat.
	 *
	 * @return An ArrayList containing the usernames of participants.
	 */
	public ArrayList<String> getParticipants() {
		ArrayList<String> copy = new ArrayList<String>(this.participants);
		return copy;
	}

	/**
	 * Adds a user to the group conversation by their username.
	 *
	 * @param username The username of the user to be added.
	 */
	public void addUser(String username) {
		if (username.length() > 0 && username.length() < 21) {
			if (getParticipants().contains(username)) {
				return;
			} else {
				this.participants.add(username);
			}
		}
	}

	/**
	 * Posts a message in the group chat and add the message in the messageList.
	 *
	 * @param creator The username of the individual creating the message.
	 * @param text    The content of the message.
	 * @throws IllegalArgumentException If the creator's username is not found in
	 *                                  the list of participants.
	 */
	public void postMessage(String creator, String text) {
		if (getParticipants().contains(creator)) {
			Message newMessage = new Message(creator, text);
			this.messageList.add(newMessage);
		} else {
			throw new IllegalArgumentException("Error: Creator does not exist!");
		}
	}

	/**
	 * Adds a reaction to a specific message within the group conversation.
	 *
	 * @param id       The unique ID of the message to react to.
	 * @param reaction The type of reaction to add to the message.
	 * @throws IllegalArgumentException If the provided message ID is not found.
	 */
	public void reactToMessage(int id, Message.Reactions reaction) {

		for (Message value : this.messageList) {
			if (value.getId() == id) {
				value.addReaction(reaction);
				return;
			}
		}
		throw new IllegalArgumentException("Error: ID not found!");
	}

	/**
	 * Retrieves a list of messages in string format from the group chat.
	 *
	 * @return An ArrayList containing string representations of messages in the
	 *         group chat.
	 */
	public ArrayList<String> getMessages() {
		ArrayList<String> stringList = new ArrayList<String>(this.messageList.size());
		for (int i = 0; i < this.messageList.size(); i++) {
			stringList.add(i, this.messageList.get(i).toString());
		}
		return stringList;
	}

	/**
	 * Retrieves a list of relevant messages for a specified user.
	 *
	 * @param important The username of the user for whom relevant messages are
	 *                  retrieved.
	 * @return An ArrayList containing string representations of relevant messages.
	 */
	public ArrayList<String> getRelevantMessages(String important) {
		ArrayList<String> stringList = new ArrayList<String>();
		for (Message sms : this.messageList) {
			if (sms.relevantTo(important)) {
				stringList.add(sms.toString());
			}
		}
		return stringList;
	}
	
    /**
     * Posts a reply to a specific message in the group chat.
     *
     * @param creator The username of the individual creating the reply.
     * @param text    The content of the reply.
     * @param id      The unique ID of the message to which the reply is posted.
     * @throws IllegalArgumentException If the provided message ID is not valid.
     */
	public void postReply(String creator, String text, int id) {
		for(Message msg: this.messageList) {
			if(msg.getId() == id) {
				Reply newReply = new Reply(creator, text, msg);
				this.messageList.add(newReply);
				return;
			}
		}
		throw new IllegalArgumentException("Message ID for the reference not valid.");
	}
	
    /**
     * Deletes a message created by a user in the group chat.
     *
     * @param user The username of the user attempting to delete the message.
     * @param id   The unique ID of the message to be deleted.
     * @throws IllegalArgumentException If the provided message ID or username is invalid.
     */
	public void deleteMessage(String user, int id) {
		for(Message msg: this.messageList) {
			if(msg.getId() == id && msg.getUsername() == user) {
				msg.deleteMessage();
				this.messageList.remove(msg);
				return;
			}
		}
		throw new IllegalArgumentException("Message ID or user invalid.");
	}
}
