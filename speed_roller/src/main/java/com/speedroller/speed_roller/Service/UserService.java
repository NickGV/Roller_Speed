package com.speedroller.speed_roller.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speedroller.speed_roller.Model.User;
import com.speedroller.speed_roller.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    // Aquí puedes agregar métodos para interactuar con la base de datos
    // Por ejemplo, para guardar un nuevo usuario:

    public User saveUser(User usuario) {
        return userRepository.save(usuario);
    } 

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
}
