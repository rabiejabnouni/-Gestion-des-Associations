package print.print.Auth.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import print.print.Auth.DTO.LoginRequest;
import print.print.Auth.Model.ConfirmationToken;
import print.print.Auth.Service.GoogleTokenValidator;
import print.print.Auth.Service.LoginService;


@RestController
@RequestMapping("/api/users")
public class LoginController {
    @Autowired
    private LoginService loginService;


    @Autowired
    private GoogleTokenValidator googleTokenValidator;

    @PostMapping("/google")
    public ResponseEntity<ConfirmationToken> loginWithGoogle(@RequestBody String email) {
        System.out.println(email);
        ConfirmationToken newToken = googleTokenValidator.loginWithApiGoogle(email);

        return ResponseEntity.ok(newToken);
    }
    @PostMapping("/login")
    public ConfirmationToken login(@RequestBody LoginRequest dto){
        return loginService.login(dto);
    }

}
