package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "parts")
public class Part extends BaseEntity {

    @Column(name = "part_name", unique = true, nullable = false)
    private String partName;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private int quantity;

    public Part() {
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
