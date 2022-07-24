import com.chat.chatapp.dto.user.UserDTO;
import com.chat.chatapp.dto.user.UserRegisterDTO;
import com.chat.chatapp.entities.ChatUser;
import com.chat.chatapp.exceptions.InvalidBodyException;
import com.chat.chatapp.exceptions.UserNotFoundException;
import com.chat.chatapp.repository.UserRepository;
import com.chat.chatapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {

    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void shouldCreateANewUserIfCreateUserIsTriggered() throws Exception{
        //create
        userService = new UserService(userRepository);
        UserRegisterDTO userRegisterDTO= new UserRegisterDTO("testUser", "testPass");
        when(userRepository.save(any(ChatUser.class))).thenReturn(new ChatUser());

        //execute
        UserDTO userDTO = userService.addUser(userRegisterDTO);

        //verify
        assertEquals(userDTO.getUsername(), "testUser");
    }

    @Test
    public void shouldThrowExceptionIfCreateUserHasNullUsername() throws Exception{
        //create
        userService = new UserService(userRepository);
        UserRegisterDTO userRegisterDTO= new UserRegisterDTO(null, "testPass");
        when(userRepository.save(any(ChatUser.class))).thenReturn(new ChatUser());

        //execute
        InvalidBodyException thrown = assertThrows(
                InvalidBodyException.class,
                () -> userService.addUser(userRegisterDTO),
                "Invalid request body"
        );

        //verify
        assertTrue(thrown.getMessage().contains("Invalid request body"));
    }

    @Test
    public void shouldUpdateUserIfUpdateIsTriggered() throws UserNotFoundException, InvalidBodyException {
        //create
        userService = new UserService(userRepository);
        UserDTO user = getSuccessfulUser();
        ChatUser userEntity = new ChatUser();
        userEntity.setUsername(user.getUsername());
        userEntity.setId(user.getId());
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(userEntity));

        UserDTO update = new UserDTO(user.getId(), "update");

        //execute
        UserDTO userResponse = userService.updateUser(update);

        //verify
        assertEquals(userResponse.getUsername(), "update");
        assertEquals(userResponse.getId(), user.getId());
    }

    @Test
    public void shouldDeleteUserIfDeleteIsTriggered() throws UserNotFoundException, InvalidBodyException {
        //create
        userService = new UserService(userRepository);
        UserDTO user = getSuccessfulUser();

        ChatUser userEntity = new ChatUser();
        userEntity.setUsername(user.getUsername());
        userEntity.setId(user.getId());

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(userEntity));

        //execute
        UserDTO userResponse = userService.deleteUser(user);

        //verify
        assertEquals(userResponse.getUsername(), user.getUsername());
        assertEquals(userResponse.getId(), user.getId());
    }

    private UserDTO getSuccessfulUser(){
        return new UserDTO(1,"testUser");
    }
}
