package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PartImportDTO;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.PartRepository;
import softuni.exam.service.PartService;

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
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;

    public PartServiceImpl(PartRepository partRepository) {
        this.partRepository = partRepository;
    }


    @Override
    public boolean areImported() {
        return this.partRepository.count() > 0;
    }

    @Override
    public String readPartsFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "parts.json");

        return String.join("\n", Files.readAllLines(path));
    }

    @Override
    public String importParts() throws IOException {

        PartImportDTO[] partImportDTOS = gson.fromJson(readPartsFileContent(), PartImportDTO[].class);

        return Arrays.stream(partImportDTOS)
                .map(this::importDTOS)
                .collect(Collectors.joining("\n"));

    }

    private String importDTOS(PartImportDTO dto) {
        Set<ConstraintViolation<PartImportDTO>> validate = validator.validate(dto);
        String partName = dto.getPartName();

        Optional<Part> byPartName = this.partRepository.findByPartName(partName);

        if (validate.isEmpty() && byPartName.isEmpty()) {

            Part part = modelMapper.map(dto, Part.class);

            this.partRepository.save(part);

            return String.format("Successfully imported part %s - %.2f",
                    dto.getPartName(), dto.getPrice());

        } else return "Invalid part";

    }
}
