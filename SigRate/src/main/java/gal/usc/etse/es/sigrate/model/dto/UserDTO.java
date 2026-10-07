package gal.usc.etse.es.sigrate.model.dto;

import gal.usc.etse.es.sigrate.model.User;

public record UserDTO(String id, String username, String email) {
    public static UserDTO FromEntity(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail());
    }

    public static UserDTO from(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail());
    }
}
