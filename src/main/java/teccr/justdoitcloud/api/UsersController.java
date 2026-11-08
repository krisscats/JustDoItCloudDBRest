package teccr.justdoitcloud.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teccr.justdoitcloud.data.User;
import teccr.justdoitcloud.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }



    /* FUNCIONALIDAD 1: Endpoint REST para el login optimizado por username.
      se hace modificacion aqui porque el controlador actúa como la interfaz HTTP 
      que expone la ruta exacta solicitada (GET /api/v1/users/byusername/{username}),
      permitiendo que el Frontend consulte únicamente al usuario digitado en lugar 
     de descargar la lista completa de usuarios.
     */
    @GetMapping("/byusername/{username}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String username) {
        User user = userService.getUserByUserName(username);
        return ResponseEntity.ok(user);
    }

        @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOpt = userService.getUserById(id);
        return userOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PatchMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUserFields(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
