package gal.usc.etse.es.sigrate.service;

import gal.usc.etse.es.sigrate.model.User;
import gal.usc.etse.es.sigrate.model.dto.UserDTO;
import gal.usc.etse.es.sigrate.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loadUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserDTO create(UserDTO user) {
        var dbUser = userRepository.findByUsername(user.username());
        if (dbUser.isPresent()) {
            //Excepcion de user duplicado
        }

        return UserDTO.from(userRepository.save(User.from(user)));

    }

    public List<UserDTO> get() {
        return userRepository.findAll().stream().map(UserDTO::from).toList();
    }

    public UserDTO getById(Long id) {
        return UserDTO.from(loadUserById(id));
    }
}
