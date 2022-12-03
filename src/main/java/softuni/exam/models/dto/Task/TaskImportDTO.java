package softuni.exam.models.dto.Task;

import javax.validation.constraints.DecimalMin;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskImportDTO {

    @XmlElement(name = "date")
    private String dateTime;

    @XmlElement(name = "price")
    @DecimalMin(value = "0")
    private BigDecimal price;

    @XmlElement(name = "car")
    private IdDto car;

    @XmlElement(name = "mechanic")
    private NameDTO mechanic;

    @XmlElement(name = "part")
    private  IdDto part;

    public TaskImportDTO() {
    }

    public String getDateTime() {
        return dateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public IdDto getCar() {
        return car;
    }

    public NameDTO getMechanic() {
        return mechanic;
    }

    public IdDto getPart() {
        return part;
    }
}
