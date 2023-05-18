package PackageMeasurementConversion.api.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class, MeasurementConverterService, is a Spring Service component that provides a method, convertMeasurements,
 * for converting measurements.
 * The method takes an input string as a parameter and converts it into a list of integers representing
 * the converted measurements. The input string is processed character by character, and each character
 * is converted into a numeric value using
 * the getNumericValue method. The converted values are then stored in the convertedValues list.
 * The method handles special cases where the character 'a' corresponds to 1, 'b' corresponds to 2, and so on,
 * up to 'z' corresponding to 26. The value 0 is used for the underscore character '_'.
 * The converted values are accumulated to form packages of values. Each package represents a converted measurement.
 * The packages are added to the result list.
 */
@Service
public class MeasurementConverterService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MeasurementConverterService.class);

    public List<Integer> convertMeasurements(String input) {

        List<Integer> result = new ArrayList<>(); // to store the package values
        List<Integer> convertedValues = new ArrayList<>(); // to store the converted numeric values

        try {
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                int numericValue = getNumericValue(c);
                convertedValues.add(numericValue);
            }
        } catch (Exception e) {
            String errorMessage = "Unexpected error while processing input: " + input;
            LOGGER.error(errorMessage, e);
        }
        LOGGER.info("Processed the Values and Converted to Numeric Values successfully ");

        if (input.isEmpty()) {
            return result;
        }
        if (input.equals("_")) {
            result.add(0);
            return result;
        }

        int packageValue = 0; // to accumulate the values of each package
        int counter = 0; // to keep track of the remaining values needed to complete the current package
        int i = 0; // index of the current value in the convertedValues list

        // handle special case when the first value is 26
        if (convertedValues.get(i) == 26) {
            while (i < convertedValues.size() && convertedValues.get(i) == 26) {
                counter += 26;
                i++;
            }
            counter += convertedValues.get(i);
        } else {
            counter = convertedValues.get(i);
        }

        i++;

        // iterate through the convertedValues list and accumulate the package values
        while (i < convertedValues.size()) {
            int value = convertedValues.get(i);

            // handle special case when the current value is 26
            if (value == 26) {
                int sum = 26;

                while (i + 1 < convertedValues.size() && convertedValues.get(i + 1) == 26) {
                    sum += 26;
                    i++;
                }

                sum += convertedValues.get(++i);
                value = sum;
            }

            packageValue += value;
            counter--;

            // if the current package is complete, add it to the result list and reset the packageValue and counter
            if (counter == 0) {
                result.add(packageValue);
                packageValue = 0;

                // if there are remaining values, update the counter based on the next value(s)
                if (i + 1 < convertedValues.size()) {
                    if (convertedValues.get(i + 1) == 26) {
                        while (i + 1 < convertedValues.size() && convertedValues.get(i + 1) == 26) {
                            counter += 26;
                            i++;
                        }
                        counter += convertedValues.get(i + 1);
                    } else {
                        counter = convertedValues.get(i + 1);
                        if (counter == 0) {
                            result.add(0);
                            return result;
                        }
                    }
                    i++;

                }

            }
            i++;
        }
        LOGGER.info("All Packages added to the Result List successfully  ");
        return result;

    }

    // Convert Chars to Integers
    private int getNumericValue(char c) {
        return (c == '_') ? 0 : Character.toLowerCase(c) - 'a' + 1;
    }
}