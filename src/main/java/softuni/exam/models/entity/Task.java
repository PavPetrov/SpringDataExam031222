package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity(name = "tasks")
public class Task extends BaseEntity {

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne
    private Part part;

    @ManyToOne
    private Mechanic mechanic;

    @ManyToOne
    private Car car;

    public Task() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Part getPart() {
        return part;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public Car getCar() {
        return car;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void String(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {

        return String.format("Car %s %s with %dkm%n" +
                        "\t-Mechanic: %s %s - task №%d:%n" +
                        "\t--Engine: %f%n" +
                        "\t---Price: %.2f$",
                getCar().getCarMake(), getCar().getCarModel(), getCar().getKilometers(),
                getMechanic().getFirstName(), getMechanic().getLastName(), getId(),
                getCar().getEngine(), getPrice());

    }
}
