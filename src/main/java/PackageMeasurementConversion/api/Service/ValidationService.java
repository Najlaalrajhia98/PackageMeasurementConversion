package PackageMeasurementConversion.api.Service;
import org.springframework.stereotype.Service;
/**
This class, Validation, is a Spring Service component that provides a method,
isValidInput, for validating input strings.
 */
@Service
public class ValidationService {

    public boolean isValidInput(String input) {
        // Check for numbers
        if (input.matches(".*\\d.*")) {
            return false;
        }

        return true;
    }
}
