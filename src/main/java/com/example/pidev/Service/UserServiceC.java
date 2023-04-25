package com.example.pidev.Service;

import com.example.pidev.Repository.UserRepository;
import com.example.pidev.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceC implements  UserService {

    @Autowired
    UserRepository UserRepo ;


    @Override
    public List<User> getAllUsers() {

        return (List<User>) UserRepo.findAll();
    }


    @Override
    public User createUser(User user) {
        return UserRepo.save(user);
    }

    @Override
    public User updateUser(User user) {
        return UserRepo.save(user);
    }

    @Override
    public User getUserById(Long idUser) {
        return UserRepo.findById(idUser).get(); // .get a cause de type de retour

    }

    @Override
    public void deleteUser(Long idUser) {

        UserRepo.deleteById(idUser);
    }





}
