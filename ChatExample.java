import nl.jketelaar.chatter.CleverbotSession;

/**
 * @author JKetelaar
 */
public class ChatExample {
    public static void main(String[] args) throws Exception {

        CleverbotSession cleverbot = new CleverbotSession("http://www.cleverbot.com/webservicemin", 35);

        String question = "How are you?";
        String answer = cleverbot.think(question);
        System.out.println("Bot replies with: " + answer);

        String question2 = "What are you doing?";
        String answer2 = cleverbot.think(question2);
        System.out.println("Bot replies with: " + answer2);
    }
}
