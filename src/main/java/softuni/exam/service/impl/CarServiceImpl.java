package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CarImportDTO;
import softuni.exam.models.dto.CarImportWrapperDTO;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;

import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.util.Utils.modelMapper;
import static softuni.exam.util.Utils.validator;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final Unmarshaller unmarshal;
    private final Path path =
            Path.of("src", "main", "resources", "files", "xml", "cars.xml");


    public CarServiceImpl(CarRepository carRepository) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(CarImportWrapperDTO.class);
        this.carRepository = carRepository;
        this.unmarshal = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
        return String.join("\n", Files.readAllLines(path));
    }

    @Override
    public String importCars() throws IOException, JAXBException {

        FileReader fileReader = new FileReader(path.toFile());
        CarImportWrapperDTO carImportWrapperDTO = (CarImportWrapperDTO) this.unmarshal.unmarshal(fileReader);


        return carImportWrapperDTO.getCar()
                .stream().map(this::importDTO)
                .collect(Collectors.joining("\n"));

    }

    private String importDTO(CarImportDTO carImportDTO) {

        Set<ConstraintViolation<CarImportDTO>> validate = validator.validate(carImportDTO);

        String plateNumber = carImportDTO.getPlateNumber();
        Optional<Car> carByPlateNumber = this.carRepository.findByPlateNumber(plateNumber);

        if (validate.isEmpty() && carByPlateNumber.isEmpty()) {
            Car car = modelMapper.map(carImportDTO, Car.class);

            this.carRepository.save(car);

            return String.format("Successfully imported car %s - %s",
                    carImportDTO.getCarMake(), carImportDTO.getCarModel());

        } else return "Invalid car";

    }
}
