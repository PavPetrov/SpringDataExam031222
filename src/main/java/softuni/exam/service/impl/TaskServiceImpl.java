package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.models.dto.Task.TaskImportDTO;
import softuni.exam.models.dto.Task.TaskImportWrapperDTO;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.models.entity.Part;
import softuni.exam.models.entity.Task;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.repository.PartRepository;
import softuni.exam.repository.TaskRepository;
import softuni.exam.service.TaskService;

import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.models.entity.CarType.coupe;
import static softuni.exam.util.Utils.modelMapper;
import static softuni.exam.util.Utils.validator;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    private final CarRepository carRepository;

    private final MechanicRepository mechanicRepository;

    private final PartRepository partRepository;

    private final Unmarshaller unmarshal;
    private final Path path =
            Path.of("src", "main", "resources", "files", "xml", "tasks.xml");

    public TaskServiceImpl(TaskRepository taskRepository, CarRepository carRepository,
                           MechanicRepository mechanicRepository, PartRepository partRepository) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TaskImportWrapperDTO.class);
        this.taskRepository = taskRepository;
        this.carRepository = carRepository;
        this.mechanicRepository = mechanicRepository;
        this.partRepository = partRepository;
        this.unmarshal = context.createUnmarshaller();

        modelMapper.addConverter(ctx -> LocalDateTime.parse(ctx.getSource(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                String.class, LocalDateTime.class); //2021-01-28 06:43:21

    }

    @Override
    public boolean areImported() {
        return this.taskRepository.count() > 0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        return String.join("\n", Files.readAllLines(path));
    }

    @Override
    public String importTasks() throws IOException, JAXBException {
        FileReader fileReader = new FileReader(path.toFile());

        TaskImportWrapperDTO tasksImportDTO = (TaskImportWrapperDTO) this.unmarshal.unmarshal(fileReader);


        fileReader.close();

        return tasksImportDTO.getTasks()
                .stream().map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(TaskImportDTO taskImportDTO) {

        Set<ConstraintViolation<TaskImportDTO>> validate = validator.validate(taskImportDTO);
        String mechanicName = taskImportDTO.getMechanic().getFirstName();
        Optional<Mechanic> mechanic = this.mechanicRepository.getByFirstName(mechanicName);

        Long partId = taskImportDTO.getPart().getId();

        Long carId = taskImportDTO.getCar().getId();


        if (validate.isEmpty() && mechanic.isPresent()) {

            Task task = modelMapper.map(taskImportDTO, Task.class);

            Car carById = this.carRepository.findById(carId).get();
            Part partById = this.partRepository.findById(partId).get();

            task.setCar(carById);
            task.setPart(partById);
            task.setMechanic(mechanic.get());

            this.taskRepository.save(task);

            return String.format("Successfully imported task %.2f",
                    taskImportDTO.getPrice());
        } else return "Invalid task";
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {

        List<Task> tasks = this.taskRepository.findAllByCarCarTypeOrderByPriceDesc(coupe);

        return tasks.stream()
                .map(Task::toString)
                .collect(Collectors.joining("\n"));
    }
}