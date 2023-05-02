package com.example.pidev.Service;


import com.example.pidev.Repository.RoleRepository;
import com.example.pidev.Repository.UserRepository;
import com.example.pidev.entity.Gender;
import com.example.pidev.entity.Role;
import com.example.pidev.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class UserServiceImpl {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ServletContext context;


    public void initRolesAndUser(){
        Role adminRole=new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleRepository.save(adminRole);

        Role etudiantRole=new Role();
        etudiantRole.setRoleName("Project manager");
        etudiantRole.setRoleDescription("Project manager role");
        roleRepository.save(etudiantRole);

        //Ajout de l'admin dans la base
        User adminUser = new User();
        adminUser.setFileName("yahyacv.jpg");
        adminUser.setPrenom("yahya");
        adminUser.setNom("jday");
        adminUser.setEmail("mohamedyahya.jday@esprit.tn");
        adminUser.setPassword(getEncodedPassword("yahya"));
        adminUser.setUserName("yahyajday");
        adminUser.setGender(Gender.MALE);
        adminUser.setPhoneNumber(51809395);
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userRepository.save(adminUser);


    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public String registerNewUser(String user, MultipartFile file) throws JsonProcessingException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            User us = new ObjectMapper().readValue(user, User.class);

            // Ensure that the Images directory exists
            File imagesDir = new File(context.getRealPath("/Images/"));
            if (!imagesDir.exists()) {
                boolean success = imagesDir.mkdir();
                if (!success) {
                    return "Error: Unable to create Images directory";
                }
            }

            // Save the image file
            String filename = file.getOriginalFilename();
            String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
            File serverFile = new File(context.getRealPath("/Images/" + newFileName));
            FileUtils.writeByteArrayToFile(serverFile, file.getBytes());

            // Update the User object and save to the database
            us.setFileName(newFileName);
            Role role = roleRepository.findById("Etudiant").get();
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            us.setRole(userRoles);
            us.setPassword(getEncodedPassword(us.getPassword()));
            User savedUser = userRepository.save(us);

            if (savedUser != null && serverFile.exists()) {
                return "ok";
            } else {
                return "Error: Failed to save user or image";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    public byte[] getPhoto(String userName) throws Exception{
        User user   = userRepository.findById(userName).get();
        return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+user.getFileName()));
    }

    public User updateUser(User user,String userName) {
        User existingUser = userRepository.findById(userName).orElse(null);
        existingUser.setUserName(user.getUserName());
        existingUser.setNom(user.getNom());
        existingUser.setPrenom(user.getPrenom());
        existingUser.setEmail(user.getEmail());
        existingUser.setGender(user.getGender());
        existingUser.setPhoneNumber(user.getPhoneNumber());

        return userRepository.save(existingUser);
    }
    public boolean ifEmailExist(String mail){
        return userRepository.existsByEmail(mail);
    }

    public User getUserByMail(String mail){
        return userRepository.findByEmail(mail);
    }



    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    public String deleteUser(String userName) {
        userRepository.deleteById(userName);
        return "removed !! " + userName;
    }


    public User GetUserByUsername(String userName){
        return  userRepository.findById(userName).get();
    }


}

