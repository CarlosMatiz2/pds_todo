package co.com.poli.psd_todo.services;

import co.com.poli.psd_todo.mapper.TaskInDTOToTask;
import co.com.poli.psd_todo.persistence.entity.Task;
import co.com.poli.psd_todo.persistence.entity.TaskStatus;
import co.com.poli.psd_todo.persistence.repository.TaskRepository;
import co.com.poli.psd_todo.services.DTO.TaskInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    /*
        Inyección de independencias - TaskRepository tiene la etiqueta @Repository, por lo que se crea el Bean de TaskRepository
        en memoria y en caso de requerirlo en otra parte, se inyecta de las siguientes maneras

        @Autowired
        private TaskRepository taskRepository;

        public TaskService(TaskRepository taskRepository) {
            this.taskRepository = taskRepository;
        }

        private final TaskRepository taskRepository;

    */
    private final TaskRepository taskRepository;
    private final TaskInDTOToTask taskInDTOToTask;

    public Task createTask(TaskInDTO taskInDTO){
        return this.taskRepository.save(this.taskInDTOToTask.map(taskInDTO));
    }

    public List<Task> findAllByTaskStatus(TaskStatus taskStatus){
        return this.taskRepository.findAllByTaskStatus(taskStatus);
    }

    public List<Task> findAll(){
        return this.taskRepository.findAll();
    }

    @Transactional
    public Task markStatusFinished(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        if(task.isEmpty()){
            return null;
        }
        this.taskRepository.markStatusFinished(id);
        return task.orElse(null);
    }

    @Transactional
    public Task deleteTaskById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        if(task.isEmpty()){
            return null;
        }
        this.taskRepository.deleteById(id);
        return task.orElse(null);
    }

}
