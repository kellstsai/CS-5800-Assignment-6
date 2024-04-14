import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Assert.*;

import com.example.ChatHistory;
import com.example.ChatServer;
import com.example.User;

import org.junit.*;

public class Junit {
    private ChatServer gameServer;
    private User firstUser;
    private User secondUser; 
    private User thirdUser; 

    @Before
    public void setUp() {
        gameServer = new ChatServer();
        firstUser = new User("Janet", gameServer);
        secondUser = new User("Derek", gameServer);
        thirdUser = new User("Kate", gameServer);
        gameServer.registerUser(firstUser);
        gameServer.registerUser(secondUser);
        gameServer.registerUser(thirdUser);
    }

    @Test
    public void sendMessageTest() {
        firstUser.sendMessage(Arrays.asList("Derek"), "How are you, Derek?");
        ChatHistory janetHistory = firstUser.getChatHistory(); 
        assertEquals(1, janetHistory.getSize()); 
    }

    @Test
    public void blockUserTest() {
        firstUser.blockUser("Kate");
        thirdUser.sendMessage(Arrays.asList("Janet"), "Hi Janet!");
        ChatHistory kateHistory = firstUser.getChatHistory(); 
        assertEquals(0, kateHistory.getSize());
    }

    @Test
    public void undoLastMessageSentTest(){
        firstUser.sendMessage(Arrays.asList("Kate"), "Let's meet up later.");
        firstUser.undoLastMessageSent();
        ChatHistory janetHistory = firstUser.getChatHistory();
        assertEquals(0, janetHistory.getSize()); 
    }
}
