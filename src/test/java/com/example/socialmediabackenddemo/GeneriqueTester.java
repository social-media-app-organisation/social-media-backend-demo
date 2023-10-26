package com.example.socialmediabackenddemo;

import com.example.socialmediabackenddemo.Model.Business.Role;
import com.example.socialmediabackenddemo.Model.Business.User;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Commands.RegisterCommand;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Handlers.Events.VerificationEmailEventHandler;
import com.example.socialmediabackenddemo.Model.Services.Common.RoleService;
import com.example.socialmediabackenddemo.Model.Services.Database.RoleRepository;
import com.example.socialmediabackenddemo.Model.Services.Database.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import javax.transaction.Transactional;
import java.time.LocalDate;
import static com.example.socialmediabackenddemo.Model.Business.Gender.FEMALE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SocialMediaBackendDemoApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@Transactional
//this class should be the super class of all the test that you will create (it is already preconfigured)
public abstract class GeneriqueTester
{
    //this object will help us create the json format of the commands
    protected Gson gson =new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    @Autowired
    protected MockMvc mvc;//don't worry about this error if you find it

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    protected VerificationEmailEventHandler verificationEmailEventHandler;

    protected User user;
    protected String path = "/api/v1";


    // Setting up the testing users (they will be automatically rolled back)
    // if you need something to be done before all the tests all of your
    // tests are executed do like this example but just in your class
    @Before
    public void setUp()
    {
        user = createUser("test","test","test","2000-10-26", "0652687519","test@test.com","testBio");
    }

    protected User createUser(String name, String username, String password,  String Birthdate, String phone , String email ,String bio) {
        Role role = roleService.saveRole("user");
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setBirthDay(LocalDate.parse(Birthdate)); //
        user.setGender(FEMALE);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setBio(bio);
        user.setUserMedia(null);
        user.setRole(role);
        userRepository.save(user);
        return user;
    }

    protected String getTokenFromRegisterCommand(String name,String email,String password){
        RegisterCommand registerCommand = new RegisterCommand(name,email,password);
        return verificationEmailEventHandler.generateToken(registerCommand);
    }

}
