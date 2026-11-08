package teccr.justdoitcloud.service.external.dto;

import lombok.Data;

@Data
public class DummyJsonTODODto {
    private Long id;
    private String todo;
    private Boolean completed;
    private Long userId;

    // Getters y Setters explícitos por seguridad
    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}