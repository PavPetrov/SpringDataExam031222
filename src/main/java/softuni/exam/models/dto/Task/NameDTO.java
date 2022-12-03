package softuni.exam.models.dto.Task;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class NameDTO {

    @XmlElement
    private String firstName;

    public NameDTO() {
    }

    public String getFirstName() {
        return firstName;
    }
}
