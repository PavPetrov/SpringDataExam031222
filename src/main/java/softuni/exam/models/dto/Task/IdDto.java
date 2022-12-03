package softuni.exam.models.dto.Task;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class IdDto {

    @XmlElement
    private Long id;

    public IdDto() {
    }

    public Long getId() {
        return id;
    }
}
