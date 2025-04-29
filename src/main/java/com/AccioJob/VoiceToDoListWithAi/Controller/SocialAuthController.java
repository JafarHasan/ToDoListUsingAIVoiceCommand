package com.AccioJob.VoiceToDoListWithAi.Controller;

import com.AccioJob.VoiceToDoListWithAi.Models.SocialLoginRequest;
import com.AccioJob.VoiceToDoListWithAi.Models.Users;
import com.AccioJob.VoiceToDoListWithAi.Repository.UserRepository;
import com.AccioJob.VoiceToDoListWithAi.Service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/social-auth")
@CrossOrigin(origins = "http://localhost:5500")
public class SocialAuthController {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> socialLogin(@RequestBody SocialLoginRequest request) {
        try {
            String email = request.getUserData().getEmail();

            Users user = userRepository.findByEmail(email)
                    .orElseGet(() -> {
                        Users newUser = new Users();
                        newUser.setEmail(email);
                        newUser.setPassword("social_" + request.getUserData().getId());
                        return userRepository.save(newUser);
                    });

            Map<String, Object> response = new HashMap<>();
            response.put("user", Map.of(
                    "id", user.getId(),
                    "email", user.getEmail(),
                    "name", request.getUserData().getName(),
                    "picture", request.getUserData().getPicture()
            ));

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Failed to process social login"));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> socialSignup(@RequestBody SocialLoginRequest request) {
        try {
            String email = request.getUserData().getEmail();

            if (userRepository.existsByEmail(email)) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of("message", "User already exists with this email"));
            }

            Users newUser = new Users();
            newUser.setEmail(email);
            newUser.setPassword("social_" + request.getUserData().getId());
            Users savedUser = userRepository.save(newUser);

            Map<String, Object> response = new HashMap<>();
            response.put("user", Map.of(
                    "id", savedUser.getId(),
                    "email", savedUser.getEmail(),
                    "name", request.getUserData().getName(),
                    "picture", request.getUserData().getPicture()
            ));

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Failed to process social signup"));
        }
    }

    @GetMapping("/verify-email/{email}")
    public ResponseEntity<?> verifyEmail(@PathVariable String email) {
        boolean exists = userRepository.existsByEmail(email);
        return ResponseEntity.ok(Map.of("exists", exists));
    }
}
