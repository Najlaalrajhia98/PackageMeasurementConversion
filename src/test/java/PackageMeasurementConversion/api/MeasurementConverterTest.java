package PackageMeasurementConversion.api;
import PackageMeasurementConversion.api.Service.MeasurementConverterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MeasurementConverterTest {

	private MeasurementConverterService measurementConverterService;

	@BeforeEach
	public void setUp() {
		measurementConverterService = new MeasurementConverterService();
	}

	@Test
	public void testConvertMeasurements_SinglePackage() {
		// Arrange
		String input = "aa";
		List<Integer> expected = List.of(1);

		// Act
		List<Integer> result = measurementConverterService.convertMeasurements(input);

		// Assert
		assertEquals(expected, result);
	}

	@Test
	public void testConvertMeasurements_MultiplePackages() {
		// Arrange
		String input = "abbcc";
		List<Integer> expected = List.of(2, 6);

		// Act
		List<Integer> result = measurementConverterService.convertMeasurements(input);

		// Assert
		assertEquals(expected, result);
	}

	@Test
	public void testConvertMeasurements_ComplexInput() {
		// Arrange
		String input = "dz_a_aazzaaa";
		List<Integer> expected = List.of(28, 53, 1);

		// Act
		List<Integer> result = measurementConverterService.convertMeasurements(input);

		// Assert
		assertEquals(expected, result);
	}

	@Test
	public void testConvertMeasurements_UnderscoreInput() {
		// Arrange
		String input = "_";
		List<Integer> expected = List.of(0);

		// Act
		List<Integer> result = measurementConverterService.convertMeasurements(input);

		// Assert
		assertEquals(expected, result);
	}

	@Test
	public void testConvertMeasurements_UnderscoreWithCharacterInput() {
		// Arrange
		String input = "a_";
		List<Integer> expected = List.of(0);

		// Act
		List<Integer> result = measurementConverterService.convertMeasurements(input);

		// Assert
		assertEquals(expected, result);
	}

	@Test
	public void testConvertMeasurements_LongInput() {
		// Arrange
		String input = "abcdabcdab";
		List<Integer> expected = List.of(2, 7, 7);

		// Act
		List<Integer> result = measurementConverterService.convertMeasurements(input);

		// Assert
		assertEquals(expected, result);
	}

	@Test
	public void testConvertMeasurements_LongInputWithUnderscore() {
		// Arrange
		String input = "abcdabcdab_";
		List<Integer> expected = List.of(2, 7, 7,0);

		// Act
		List<Integer> result = measurementConverterService.convertMeasurements(input);

		// Assert
		assertEquals(expected, result);
	}

	@Test
	public void testConvertMeasurements_LongInputWithMultipleUnderscores() {
		// Arrange
		String input = "zdaaaaaaaabaaaaaaaabaaaaaaaabbaa";
		List<Integer> expected = List.of(34);

		// Act
		List<Integer> result = measurementConverterService.convertMeasurements(input);

		// Assert
		assertEquals(expected, result);
	}
}
