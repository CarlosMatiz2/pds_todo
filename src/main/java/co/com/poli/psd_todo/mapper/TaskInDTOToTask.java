package co.com.poli.psd_todo.mapper;

import co.com.poli.psd_todo.persistence.entity.Task;
import co.com.poli.psd_todo.persistence.entity.TaskStatus;
import co.com.poli.psd_todo.services.DTO.TaskInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskInDTOToTask implements IMapper<TaskInDTO, Task>{

    @Override
    public Task map(TaskInDTO in) {
        Task task = new Task();
        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setCreateDate(LocalDateTime.now());
        task.setFinished(false);
        task.setTaskStatus(TaskStatus.ON_TIME);
        return task;
    }
}
