package teccr.justdoitcloud.data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class Task {
    private Long id;
    
    @Size(min = 3, message = "Descripcion debe tener al menos 3 caracteres")
    private String description;
    
    private LocalDateTime createdAt;
    private LocalDate deadline;
    
    @NotNull
    private Status status;

    private Long userId;

    public enum Status {
        PENDING,
        INPROGRESS,
        DONE
    }

    // Getters y Setters explícitos
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}