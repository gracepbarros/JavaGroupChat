/**
 * The GroupChatTester class is a test program to demonstrate and evaluate
 * the functionality of the GroupChat class. It creates a GroupChat instance,
 * adds users, posts messages, and reacts to messages to test various features
 * of the GroupChat class.
 * 
 * @author Dafni Paiva
 * @author Grace Barros
 */
public class GroupChatTester {
	public static void main(String[] args) {
		// Empty GroupChat Test
		GroupChat test0 = new GroupChat();
		System.out.println(test0.getMessages());

		// Main GroupChat Test
		GroupChat test1 = new GroupChat();

		// Users
		String L = "Luiz Eduardo";
		String D = "Dafni Paiva";
		String G = "Grace Barros";

		test1.addUser(L);
		test1.addUser(D);
		test1.addUser(G);
		test1.addUser(L);
		// System.out.println(test1.getParticipants());

		// Messages
		test1.postMessage(L, "Oi, arrombado!");
		test1.postMessage(D, "@Luiz Eduardo@ eh um frango!");
		test1.postMessage(G, "Vamos estudar CMNS1118 @Dafni Paiva@, nao estamos em um passeio no parque!");
		test1.postMessage(L, "@Grace Barros@ e @Dafni Paiva@ e @Luiz Eduardo@ vao ganhar 250K por ano em 2024.");

		// Reactions
		test1.reactToMessage(2, Message.Reactions.HaHaHa);
		test1.reactToMessage(0, Message.Reactions.ThumbsUp);
		test1.reactToMessage(0, Message.Reactions.EyeRoll);
		test1.reactToMessage(3, Message.Reactions.Grrrr);
		test1.reactToMessage(3, Message.Reactions.ThumbsUp);
		test1.reactToMessage(3, Message.Reactions.ThumbsUp);
		test1.reactToMessage(3, Message.Reactions.HaHaHa);

		System.out.println(test1.getMessages());

		// Test Message
		Message test2 = new Message(G,
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam pellentesque pulvinar nulla gravida porttitor. Morbi sagittis nibh ex, id ultrices risus accumsan id. Vestibulum ante ipsum primis in faucibus orci.");
		System.out.println(test2.shortString());
	}

}
