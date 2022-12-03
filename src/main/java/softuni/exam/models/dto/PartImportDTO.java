package softuni.exam.models.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class PartImportDTO {

    @Size(min = 2, max = 19)
    private String partName;

    @Min(10)
    @Max(2000)
    private Double price;

    @Positive
    private int quantity;

    public PartImportDTO() {
    }

    public String getPartName() {
        return partName;
    }

    public Double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
