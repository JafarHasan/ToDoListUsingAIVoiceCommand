//package com.AccioJob.VoiceToDoListWithAi.Controller;
//
//import com.AccioJob.VoiceToDoListWithAi.Service.JWTService;
//import com.AccioJob.VoiceToDoListWithAi.Service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//    @Autowired
//    UserService userService;
//
//   @Autowired
//   JWTService jwtService;
//    //LOGIN
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestHeader("Authorization")String token){
//        if(token!=null && token.startsWith("Bearer ")){
//            //validate token
//            if(jwtService.isValidToken(token)) {
//                //get email and pass
//                String email= jwtService.getEmailFromClaims(token);
//                String password= jwtService.getPasswordFromClaims(token);
//
//                //store into DB
//            }
//        }
//        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> signup(@RequestHeader("Authorization") String token){
//        if(token!=null && token.startsWith("Bearer ")){
//            if(jwtService.validateToken(token)){
//                //get email and pass
//                String email= jwtService.getEmailFromClaims(token);
//                String password= jwtService.getPasswordFromClaims(token);
//
//                //store into DB
//            }
//        }
//        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//    }
//}
//
