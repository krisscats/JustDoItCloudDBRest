package teccr.justdoitcloud.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.List;

public class User {
    private Long id;
    private String userName;
    private String name;
    private String email;
    private Type type;
    
    @JsonIgnore
    private List<Task> tasks;
    
    private LocalDateTime createdAt;

    public enum Type {
        ADMIN,
        REGULAR
    }

    // Getters y Setters explícitos
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }

    public List<Task> getTasks() { return tasks; }
    public void setTasks(List<Task> tasks) { this.tasks = tasks; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}