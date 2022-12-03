package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.models.dto.MechanicImportDTO;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.service.MechanicService;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.util.Utils.*;

@Service
public class MechanicServiceImpl implements MechanicService {

    private final MechanicRepository mechanicRepository;

    public MechanicServiceImpl(MechanicRepository repository) {
        this.mechanicRepository = repository;
    }

    @Override
    public boolean areImported() {
        return this.mechanicRepository.count() > 0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "mechanics.json");

        return String.join("\n", Files.readAllLines(path));
    }

    @Override
    public String importMechanics() throws IOException {

        MechanicImportDTO[] mechanicImportDTOS = gson.fromJson(readMechanicsFromFile(), MechanicImportDTO[].class);

        return Arrays.stream(mechanicImportDTOS)
                .map(this::importDto).collect(Collectors.joining("\n"));
    }

    private String importDto(MechanicImportDTO dto) {

        Set<ConstraintViolation<MechanicImportDTO>> validate = validator.validate(dto);

        String email = dto.getEmail();

        Optional<Mechanic> byEmail = this.mechanicRepository.findByEmail(email);


        if (validate.isEmpty() && byEmail.isEmpty()) {

            Mechanic mechanic = modelMapper.map(dto, Mechanic.class);

            this.mechanicRepository.save(mechanic);

            return String.format("Successfully imported mechanic %s %s",
                    dto.getFirstName(), dto.getLastName());
        } else return "Invalid mechanic";
    }
}
