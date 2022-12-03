package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "cars")
public class Car extends BaseEntity {

    @Column(name = "car_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CarType carType;

    @Column(name = "car_make", nullable = false)
    private String carMake;

    @Column(name = "car_model", nullable = false)
    private String carModel;

    @Column(nullable = false)
    private int year;

    @Column(name = "plate_number", unique = true, nullable = false)
    private String plateNumber;

    @Column(nullable = false)
    private int kilometers;

    @Column(nullable = false)
    private Double engine;

    public Car() {
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public void setEngine(Double engine) {
        this.engine = engine;
    }

    public CarType getCarType() {
        return carType;
    }

    public String getCarMake() {
        return carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public int getYear() {
        return year;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public int getKilometers() {
        return kilometers;
    }

    public Double getEngine() {
        return engine;
    }
}
