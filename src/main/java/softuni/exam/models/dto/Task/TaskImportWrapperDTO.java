package softuni.exam.models.dto.Task;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tasks")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskImportWrapperDTO {

    @XmlElement(name = "task")
    private List<TaskImportDTO> tasks;

    public TaskImportWrapperDTO() {
    }

    public List<TaskImportDTO> getTasks() {
        return tasks;
    }
}
