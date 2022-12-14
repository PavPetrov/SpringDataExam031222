package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Car;

import java.util.List;
import java.util.Optional;
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Override
    Optional<Car> findById(Long aLong);

    Optional<Car> findByPlateNumber(String plateNumber);

}
