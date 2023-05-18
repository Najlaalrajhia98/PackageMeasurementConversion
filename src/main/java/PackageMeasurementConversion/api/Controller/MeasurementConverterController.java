package PackageMeasurementConversion.api.Controller;

import PackageMeasurementConversion.api.Service.MeasurementConverterService;
import PackageMeasurementConversion.api.Service.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The MeasurementConverterController class is responsible for handling measurement conversion requests.
 * It receives input values and converts them into desired measurements using the MeasurementConverterService.
 * The controller also performs input validation using the Validation service.
 */
@RestController
public class MeasurementConverterController {

    // Logger for logging messages
    private static final Logger LOGGER = LoggerFactory.getLogger(MeasurementConverterController.class);

    // Service for measurement conversion
    @Autowired
    private MeasurementConverterService measurementConverterService;

    // Validation service for input validation
    @Autowired
    private ValidationService validationService;

    /**
     * Converts measurements based on the input value.
     *
     * @param input the input value to be converted
     * @return ResponseEntity with the converted measurements or an error message
     */
    @GetMapping("/convert-measurements")
    public ResponseEntity<?> convertMeasurements(@RequestParam("input") String input) {
        // Validate input
        if (!validationService.isValidInput(input)) {
            String errorMessage = "Invalid input: " + input;
            LOGGER.error(errorMessage);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        try {
            // Convert measurements
            List<Integer> result = measurementConverterService.convertMeasurements(input);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // Handle exception while processing input
            String errorMessage = "Error while processing input: " + input + " 'Enter a valid Input!' ";
            LOGGER.error(errorMessage, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }
}
