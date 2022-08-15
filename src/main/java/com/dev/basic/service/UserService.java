package com.dev.basic.service;
import com.dev.basic.dto.FindProfileDto;
import com.dev.basic.dto.UserDto;
import com.dev.basic.entity.User;
import com.dev.basic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserPrivacyAgreementService userPrivacyAgreementService;


    public UserDto register(UserDto userDto){
        User user = toEntity(userDto);
        if (userRepository.findUserByMailAndUserName(userDto.mail, userDto.userName) == null) {
            user.setUserPrivacyAgreement(userPrivacyAgreementService.userPrivacyAgreementRepository.save(user.getUserPrivacyAgreement()));
            userDto = toResource(userRepository.save(user));
            userDto.desc = "Ok";
            userDto.statusCode = 200;
        }
        else {
            userDto.statusCode = 400;
            userDto.desc = "user already exists";
        }
        return userDto ;
    }
    public UserDto login(UserDto userDto){
        User user = userRepository.findUserByMailAndPassword(userDto.mail, userDto.password);
        if (user != null){
            userDto = toResource(user);
            userDto.desc = "Ok";
            userDto.statusCode = 200;
            return userDto;
        }
        userDto.desc = "Not found";
        userDto.statusCode = 400;
        return userDto;
    }
    public FindProfileDto findProfile(FindProfileDto findProfileDto){
        User user = userRepository.findUserByMail(findProfileDto.mail);
        if (user == null){
            user = userRepository.findUserByUserName(findProfileDto.userName);
            return toResourceFind(user);
        }
        return toResourceFind(user);
    }

    public User toEntity(UserDto userDto){
        User user = new User();
        user.setUserName(userDto.userName);
        user.setMail(userDto.mail);
        user.setPassword(userDto.password);
        user.setUserPrivacyAgreement(userPrivacyAgreementService.toEntity(userDto.userPrivacyAgreement));
        return user;
    }
    public UserDto toResource(User user){
        UserDto userDto = new UserDto();
        userDto.userId = user.getUserId();
        userDto.userName = user.getUserName();
        userDto.mail = user.getMail();
        userDto.password = user.getPassword();
        userDto.userPrivacyAgreement = userPrivacyAgreementService.toResource(user.getUserPrivacyAgreement());
        return userDto;
    }
    public FindProfileDto toResourceFind(User user){
        FindProfileDto findProfileDto = new FindProfileDto();
        findProfileDto.userName = user.getUserName();
        findProfileDto.mail = user.getMail();
        return findProfileDto;
    }

}
