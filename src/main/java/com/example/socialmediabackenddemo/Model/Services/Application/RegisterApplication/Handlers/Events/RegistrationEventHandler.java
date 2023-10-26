package com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Handlers.Events;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.socialmediabackenddemo.Common.Handlers.BasicHandler;
import com.example.socialmediabackenddemo.Model.Business.Gender;
import com.example.socialmediabackenddemo.Model.Business.Role;
import com.example.socialmediabackenddemo.Model.Business.User;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Commands.VerificationEmailCommand;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Responses.RegisterResponse;
import com.example.socialmediabackenddemo.Model.Services.Common.RoleService;
import com.example.socialmediabackenddemo.Model.Services.Common.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
@Service
@Transactional
public class RegistrationEventHandler extends BasicHandler<VerificationEmailCommand, RegisterResponse> {
    @Value("${serverSecretKey}")
    private String SECRET_KEY;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public RegisterResponse handle(VerificationEmailCommand command) {
        RegisterResponse response = new RegisterResponse();
        Map<String, Claim> payload = testValidityTokenAndGetPayload(command,response);
        if(payload!=null){
            String name = payload.get("name").as(String.class);
            String password = payload.get("password").as(String.class);
            String email = payload.get("email").as(String.class);
            if (!userService.ExistEmail(email)) {
                String gender = command.getGender();
                String phone = command.getPhone();
                String username = command.getUsername();
                LocalDate birthDate = LocalDate.parse(command.getBirthDate());
                Role userRole = roleService.findRoleByName("user");

                User user = new User();
                user.setName(name);
                user.setUsername(username);
                user.setBirthDay(birthDate);
                user.setGender(convertStringToEnum(gender));
                user.setPhone(phone);
                user.setEmail(email);
                user.setPassword(passwordEncoder.encode(password));
                user.setBio("");
                user.setUserMedia(null);
                user.setRole(userRole);

                User user1 = userService.saveUser(user);
                if (user1 != null) {
                    response.Success("Registration successfully");
                } else {
                    response.Error("Registration field");
                }

            } else {
                response.Error("email already exist");
            }
        }
        return response;
    }


    private Map<String, Claim> testValidityTokenAndGetPayload(VerificationEmailCommand command,RegisterResponse response){
        String token = command.getToken();

        if (token == null || token.length() <= 0) {
            response.Error("token empty");
            return null;
        }

        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm).build();

        try {
            DecodedJWT decoded = verifier.verify(token);
            if(isJWTExpired(decoded)){
                response.Error("token Expired !");
                return null;
            }
            return decoded.getClaims();

        } catch (JWTVerificationException ex) {
            response.Error("verification token failed !");
            return null;
        }
    }
    boolean isJWTExpired(DecodedJWT decodedJWT) {
        Date expiresAt = decodedJWT.getExpiresAt();
        return expiresAt.before(new Date());
    }
    Gender convertStringToEnum(String gender){
        return gender.equals("FEMALE") ? Gender.FEMALE
                : gender.equals("MALE") ? Gender.MALE : null;
    }
}
