package nam.gor.machineryleasing.services;

import nam.gor.machineryleasing.dao.UserDao;
import nam.gor.machineryleasing.auth.req_res.LoginRequest;
import nam.gor.machineryleasing.auth.encoder.PasswordEncoder;
import nam.gor.machineryleasing.auth.req_res.RegisterRequest;
import nam.gor.machineryleasing.auth.token.TokenGenerator;
import nam.gor.machineryleasing.models.entities.User;
import nam.gor.machineryleasing.auth.req_res.UserResponse;
import nam.gor.machineryleasing.models.exceptions.DuplicateEmailException;
import nam.gor.machineryleasing.models.exceptions.EmailNotFoundException;
import nam.gor.machineryleasing.models.exceptions.IncorrectPasswordException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;

import javax.annotation.ManagedBean;
import java.util.UUID;

@ManagedBean
@AllArgsConstructor
public class UserService {
    private final UserDao dao;
    private final PasswordEncoder encoder;
    private final TokenGenerator tokenGen;

    public UserResponse login(LoginRequest req) {
        var user = dao
                .findByEmail(req.getEmail())
                .orElseThrow(() ->
                        new EmailNotFoundException(
                                "The email '%s' could not be found.",
                                req.getEmail()));
        if (!encoder.comparePasswordAndHash(
                req.getPassword(),
                user.getPassword())) {
            throw new IncorrectPasswordException(
                    "The provided password is incorrect.");
        }
        return createUserResponseWithToken(user);
    }

    public UserResponse register(RegisterRequest req) {
        var user = req
                .toUser(UUID.randomUUID().toString());
        user.setPassword(
                encoder.hashPassword(user.getPassword()));
        try {
            user = dao.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException(
                    "An user with the provided email " +
                            "is already registered.");
        }
        return createUserResponseWithToken(user);
    }

    private UserResponse createUserResponseWithToken(User user) {
        var token = tokenGen.generateTokenForUser(user);
        return UserResponse
                .fromUser(user)
                .withToken(token);
    }
}
