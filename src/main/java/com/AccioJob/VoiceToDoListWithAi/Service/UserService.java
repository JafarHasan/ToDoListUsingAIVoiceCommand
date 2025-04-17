//package com.AccioJob.VoiceToDoListWithAi.Service;
//
//import com.AccioJob.VoiceToDoListWithAi.Models.Users;
//import com.AccioJob.VoiceToDoListWithAi.Repository.AuthRepository;
//import com.AccioJob.VoiceToDoListWithAi.Repository.UserRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@Slf4j
//public class UserService {
//
//    @Autowired
//    JWTService jwtService;
//
//    @Autowired
//    AuthRepository authRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    public Users add(String token){
//        if(jwtService.isValidToken(token)){
//            Users user=new Users();
//            return authRepository.save(user);
//        }
//        return null;
//    }
//
//    public Users validateUser(String token){
//        if(jwtService.isValidToken(token)){
//            Long id=1l;
//            Optional<Users> optionalUsers =authRepository.findById(id);
//            if(optionalUsers.isPresent())return optionalUsers.get();
//        }
//        else{
//           throw new RuntimeException("not found in DB");
//        }
//        return null;
//    }
//
//
//    public Users findByEmail(String email){
//        if(userRepository.existsByEmail(email)){
//            Optional<Users> optionalUsers=userRepository.findByEmail(email);
//            if(optionalUsers.isPresent()){
//                Users user1=optionalUsers.get();
//            }
//
//        }
//        return null;
//    }
//    public Users saveToDB(String email,String password){
//        Users user=new Users();
//        //set attributes
//        user.setEmail(email);
//        user.setPassword(password);
//        return user;
//    }
//
//}
