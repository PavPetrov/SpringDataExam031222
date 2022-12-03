package softuni.exam.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;

import javax.validation.Validation;
import javax.validation.Validator;


public class Utils {
    public static final Gson gson = new GsonBuilder().create();

    public static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static final ModelMapper modelMapper = new ModelMapper();

}

