package com.example.pidev.Controller;


import com.example.pidev.Repository.UserRepository;
import com.example.pidev.Service.UserServiceImpl;
import com.example.pidev.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public class UserController implements UserDetails {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserRepository userRepository;


    @PostConstruct //lors de l'execution
    public void initRoleAndUser() {
        userService.initRolesAndUser();
    }


    @GetMapping(path = "/ImgUsers/{userName}")
    public byte[] getPhoto(@PathVariable("userName") String userName) throws Exception {
        return userService.getPhoto(userName);
    }


    @GetMapping("/users") // afficher tout les utilisateurs
    public List<User> retrieveAllUsers() {
        return userService.retrieveAllUsers();
    }

    @DeleteMapping("/delete/{userName}")
    public String deleteUser(@PathVariable String userName) {
        return userService.deleteUser(userName);
    }

 /*   @GetMapping("/list")//affichage+pagination
    public Page<User> showPage(@RequestParam(defaultValue = "0") int page) {
        return userRepository.findAll(PageRequest.of(page, 4));
    }*/


    @PostMapping({"/registerNewUser"})
    public String registerNewUser(@RequestParam("user") String user, @RequestParam("file") MultipartFile file) throws JsonProcessingException {
        return userService.registerNewUser(user, file);
    }




    @PutMapping("/updateUser/{userName}")
    public User updateUser(@RequestBody User user, @PathVariable("userName") String userName) {
        return userService.updateUser(user, userName);
    }

    @GetMapping(path = "/getUser/{userName}")
    public User getUserByUsername(@PathVariable("userName") String userName) throws Exception {
        return userService.GetUserByUsername(userName);
    }






    @GetMapping(path= "/test" )
    public Map<String,Object> currentUser(OAuth2AuthenticationToken oAuth2AuthenticationToken)
    {
        return oAuth2AuthenticationToken.getPrincipal().getAttributes();
    }













    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}



