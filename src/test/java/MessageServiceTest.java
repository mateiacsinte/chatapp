import com.chat.chatapp.dto.message.MessageDTO;
import com.chat.chatapp.dto.message.SendMessageDTO;
import com.chat.chatapp.entities.ChatUser;
import com.chat.chatapp.entities.Message;
import com.chat.chatapp.exceptions.InvalidBodyException;
import com.chat.chatapp.repository.MessageRepository;
import com.chat.chatapp.repository.UserRepository;
import com.chat.chatapp.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MessageServiceTest {

    @Mock
    MessageRepository messageRepository;

    @Mock
    UserRepository userRepository;

    MessageService messageService;

    @Test
    public void shouldGetMessagesIfGetMessageIsTriggered(){

        //create
        messageService = new MessageService(messageRepository, userRepository);
        when(messageRepository.findAll()).thenReturn(Collections.singletonList(getMessageEntity()));

        //execute
        List<MessageDTO> response = messageService.getMessages();

        assertEquals(response.size(), 1);
        assertEquals(response.get(0).getContent(), "Hello!");
        assertEquals(response.get(0).getSentAt(), new Date(2323223232L));
    }

    @Test
    public void shouldSendMessageIfSendMessageIsTriggered() throws InvalidBodyException {

        //create
        messageService = new MessageService(messageRepository, userRepository);
        SendMessageDTO messageDTO = getSendMessageDTOWithValidData();
        when(userRepository.findAll()).thenReturn(getValidUsers());

        //execute
        MessageDTO response = messageService.sendMessage(messageDTO);

        //verify
        assertEquals(response.getSenderId(), 0);
        assertEquals(response.getReceiverId(), 1);
        assertEquals(response.getContent(), "Hello!");
    }

    @Test
    public void shouldThrowInvalidBodyExceptionIfSenderIsNull() throws InvalidBodyException {

        //create
        messageService = new MessageService(messageRepository, userRepository);
        SendMessageDTO messageDTO = getSendMessageDTOWithNullSender();

        //execute
        InvalidBodyException thrown = assertThrows(
                InvalidBodyException.class,
                () -> messageService.sendMessage(messageDTO),
                "Sender Id Not Found"
        );

        //verify
        assertTrue(thrown.getMessage().contains("Sender Id Not Found"));
    }

    private Message getMessageEntity(){
        Message message =  new Message();

        ChatUser user1 = new ChatUser();
        user1.setUsername("test_u1");
        user1.setPassword("pass_u1");

        ChatUser user2 = new ChatUser();
        user2.setUsername("test_u2");
        user2.setPassword("pass_u2");

        message.setSender(user1);
        message.setReceiver(user2);
        message.setContent("Hello!");
        message.setSendAt( new Date(2323223232L));

        return message;
    }

    private SendMessageDTO getSendMessageDTOWithNullSender(){
        return   new SendMessageDTO(null, 1, "Hello!");
    }

    private SendMessageDTO getSendMessageDTOWithValidData(){
        return   new SendMessageDTO(0, 1, "Hello!");
    }

    private List<ChatUser> getValidUsers(){
        ChatUser sender = new ChatUser();
        sender.setId(0);
        sender.setUsername("u1");

        ChatUser receiver = new ChatUser();
        receiver.setId(1);
        receiver.setUsername("u2");

        return Arrays.asList(sender, receiver);
    }
}
