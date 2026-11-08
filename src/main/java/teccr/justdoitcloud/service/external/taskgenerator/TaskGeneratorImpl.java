package teccr.justdoitcloud.service.external.taskgenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import teccr.justdoitcloud.data.Task;
import teccr.justdoitcloud.service.external.dto.DummyJsonTODODto;

@Service
public class TaskGeneratorImpl implements TaskGenerator {

    private static final Logger log = LoggerFactory.getLogger(TaskGeneratorImpl.class);
    private static final String RANDOM_TODO_URL = "https://dummyjson.com/todos/random";

    private final RestTemplate restTemplate;

    // Constructor para inyectar RestTemplate como bean
    public TaskGeneratorImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Task generateTask() {
        DummyJsonTODODto resp = null;
        try {
            resp = restTemplate.getForObject(RANDOM_TODO_URL, DummyJsonTODODto.class);
        } catch (RestClientException e) {
            log.error("Error invoking " + RANDOM_TODO_URL, e);
            return null;
        }

        String todo = null;
        boolean completed = false;

        if (resp != null) {
            todo = resp.getTodo();
            completed = Boolean.TRUE.equals(resp.getCompleted());
        }

        Task task = new Task();
        task.setDescription(todo);
        task.setStatus(completed ? Task.Status.DONE : Task.Status.INPROGRESS);

        return task;
    }
}