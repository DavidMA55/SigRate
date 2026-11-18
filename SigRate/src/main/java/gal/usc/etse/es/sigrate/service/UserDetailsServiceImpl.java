package gal.usc.etse.es.sigrate.service;

import gal.usc.etse.es.sigrate.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        gal.usc.etse.es.sigrate.model.User u = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(u.getUsername())
                .password(u.getPasswordHash())
                .roles(u.getRoles().toArray(new String[0]))
                .disabled(!u.isActive())
                .build();
    }
}
