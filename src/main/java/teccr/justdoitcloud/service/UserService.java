package teccr.justdoitcloud.service;

import org.springframework.stereotype.Service;
import teccr.justdoitcloud.data.User;
import teccr.justdoitcloud.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

/*  Service responsible for user-related business logic.
 */
@Service
public class UserService {
    /*
      Recupera un usuario a partir de su username.
     Lanza una excepción 404 si no se encuentra.
      
      @param username el nombre de usuario a buscar
      @return el User encontrado

FUNCIONALIDAD 1: busqueda de usuario por nombre de usuario (username).
por que se hace aqui? Porque la capa de servicio centraliza la regla de negocio:
valida que el texto no esté vacío y lanza un error 404 (Not Found) si el 
usuario no se encuentra registrado en la base de datos, evitando traer toda la lista.
     */
     */
    public User getUserByUserName(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.NOT_FOUND, "Username cannot be empty"
            );
        }
        return userRepository.findByUserName(username.trim())
            .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.NOT_FOUND, "User not found with username: " + username
            ));
    }
    private final UserRepository userRepository;

    // Constructor injection of UserRepository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Authenticate a user by username. For initial purposes this only looks
     * up the user by username and returns it if present.
     *
     * @param username the username to authenticate
     * @return Optional containing the User if found
     */
    public Optional<User> authenticate(String username) {
        if (username == null || username.trim().isEmpty()) {
            return Optional.empty();
        }
        return userRepository.findByUserName(username.trim());
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        if (id == null || id < 0) {
            return Optional.empty();
        }
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User updateUserFields(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    if (updatedUser.getName() != null && !updatedUser.getName().trim().isEmpty()) {
                        existingUser.setName(updatedUser.getName().trim());
                    }
                    if (updatedUser.getEmail() != null && !updatedUser.getEmail().trim().isEmpty()) {
                        existingUser.setEmail(updatedUser.getEmail().trim());
                    }
                    if (updatedUser.getType() != null) {
                        existingUser.setType(updatedUser.getType());
                    }
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
