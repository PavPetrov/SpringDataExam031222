package softuni.exam.repository;

import com.sun.source.util.TaskListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.CarType;
import softuni.exam.models.entity.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByCarCarTypeOrderByPriceDesc(CarType carType);
}
