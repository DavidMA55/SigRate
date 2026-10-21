package gal.usc.etse.es.sigrate.service;

import gal.usc.etse.es.sigrate.exception.DuplicatedUserException;
import gal.usc.etse.es.sigrate.model.User;
import gal.usc.etse.es.sigrate.model.dto.UserDTO;
import gal.usc.etse.es.sigrate.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loadUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserDTO create(UserDTO user) {
        if (userRepository.findByUsername(user.username()).isPresent()) {
            throw new DuplicatedUserException(user.username());
        }
        return UserDTO.from(userRepository.save(User.from(user)));
    }

    public List<UserDTO> get() {
        return userRepository.findAll().stream().map(UserDTO::from).toList();
    }

    public UserDTO getById(Long id) {
        User user = loadUserById(id);
        return user == null ? null : UserDTO.from(user);
    }
}
