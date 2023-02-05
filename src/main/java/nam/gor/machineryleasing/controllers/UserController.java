package nam.gor.machineryleasing.controllers;

import nam.gor.machineryleasing.services.UserService;
import nam.gor.machineryleasing.auth.req_res.LoginRequest;
import nam.gor.machineryleasing.auth.req_res.RegisterRequest;
import nam.gor.machineryleasing.auth.req_res.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserService service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@RequestBody @Valid RegisterRequest req) {
        return service.register(req);
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody @Valid LoginRequest req) {
        return service.login(req);
    }
}
