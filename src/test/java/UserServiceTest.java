import org.example.User;
import org.example.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class UserServiceTest {

    private static final User USER = new User("PalmerSola", "Test123", "palmersola@gmail.com");


    @BeforeEach
    public void clear() {
        UserService.userDatabase.clear();
    }

    private void newUser() {
        UserService.userDatabase.put(USER.getUsername(), USER);
    }

    //registerUser()
    @DisplayName("registerUser() correct")
    @Test
    public void correctRegisterUserTest(){
        UserService.userDatabase.clear();
        boolean actualReturn = UserService.registerUser(USER);
        Assertions.assertTrue(actualReturn);
    }

    @DisplayName("registerUser() incorrect")
    @Test
    public void incorrectRegisterUserTest(){
        newUser();
        boolean actualReturn = UserService.registerUser(USER);
        Assertions.assertFalse(actualReturn);
    }

//    @DisplayName("registerUser() edge case")
//    @Test
//    public void edgeCaseRegisterUserTest(){
//        boolean actualReturn = UserService.registerUser();
//        System.out.println(actualReturn);
//        Assertions.assertNull(actualReturn);
//    }


    //loginUser()
    @DisplayName("loginUser() correct")
    @Test
    public void correctLoginUserTest(){
        newUser();
        User actualUser = UserService.loginUser("PalmerSola", "Test123");
        Assertions.assertEquals(USER.getUsername(), actualUser.getUsername());
        Assertions.assertEquals(USER.getPassword(), actualUser.getPassword());
        Assertions.assertEquals(USER.getEmail(), actualUser.getEmail());
    }

    @DisplayName("loginUser() incorrect")
    @Test
    public void inCorrectLoginUserTest(){
        newUser();
        User actualUser = UserService.loginUser("PalmerSola", "Test1233");
        Assertions.assertNull(actualUser);

    }

    @DisplayName("loginUser() edge case")
    @Test
    public void edgeCaseLoginUserTest(){
        newUser();
        User actualUser = UserService.loginUser(null, null);
        Assertions.assertNull(actualUser);

    }
}
