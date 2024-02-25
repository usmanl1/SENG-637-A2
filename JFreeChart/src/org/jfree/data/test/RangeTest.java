package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
	
	private Range testRange1; // Created this range to test other functionalities of the test range.
	
	// ------------- getLength() Tests ---------------------

	/**
	 * This test aims to evaluate the range's length when it is equal.
	 */
	
	@Test
	public void testGetLengthZero() {
		Range tempRange = new Range(0, 0); // creating a new Range object
		double length = tempRange.getLength(); // calling method to calculate the length of the range
		// assertion that expected value matches the actual value (0.0)
		assertEquals("The length should be 0.0 and it is " + length, 0.0, length, .000000001d);
		
	}
	
	/**
	 * This test is designed to examine the length of an very large positive range.
	 */
	@Test
	public void testGetLengthLargePositive() {
		Range tempRange = new Range(2, 300000000); // create a new Range object
		double length = tempRange.getLength(); // calling method to calculate the length of the range
		// assertion that expected value matches the actual value
		assertEquals("The length should be 299,999,998 and it is " + length, 299999998.0, length, .000000001d);
		
	}
	
	/**
	 * This test is designed to examine the length of an very large negative range.
	 */
	@Test
	public void testGetLengthLargeNegative() {
		Range tempRange = new Range(-300000000, -2); // create a new Range object
		double length = tempRange.getLength(); // calling method to calculate the length of the range
		// assertion that expected value matches the actual value
		assertEquals("The length should be 299,999,998 and it is " + length, 299999998.0, length, .000000001d);
		
	}
	
	/**
	 * This test is intended to assess the length of a range consisting of two decimal numbers (a double).
	 */
	@Test
	public void testGetLengthRangeIsDecimal() {
		Range tempRange = new Range(-200.73, 200.50); // create a new Range object
		double length = tempRange.getLength(); // calling method to calculate the length of the range
		// assertion that expected value matches the actual value
		assertEquals("The length should be 401.23 and it is " + length, 401.23, length, .000000001d);
		
	}
	
	/**
	 * This test is intended to test the length of a range composed of non decimals
	 */
	@Test
	public void testGetLengthRangeIsNotDecimal() {
		Range tempRange = new Range(-35, 35); // create a new Range object
		double length = tempRange.getLength(); // calling method to calculate the length of the range
		// assertion that expected value matches the actual value
		assertEquals("The length should be 70 and it is " + length, 70, length, .000000001d);
		
	}
	
	// ------------- shift(Range base, double delta) Tests ---------------------
	
	/**
	 * Tests the behavior when the first parameter (base range) is null for the upper bound during the shift operation.
	 * This test expects an IllegalArgumentException to be thrown as InvalidParameterException is not available in the libraries.
	 */

	
	@Test(expected = IllegalArgumentException.class)
	public void shiftNullUpperBound() {
		Range testShiftedRange = Range.shift(null, 2.0);
		assertEquals("The upper bound value should be invalid", testShiftedRange.getUpperBound());
	}
	
	/**
	 *  Tests the behavior when a range is shifted by a positive value. Upper bound of the range is only checked.
	 */
	@Test
	public void shiftByPositiveDoubleUpperBound() {
		testRange1 = new Range(2, 6);
		Range testShiftedRange = Range.shift(testRange1, 121.7);
		assertEquals("The shifted value should be ", 127.7, testShiftedRange.getUpperBound(), .000000001d);
	}

	/**
	 * Tests the behavior of what happens when a range is shifted by a
	 * positive amount. We are only checking the lower bound of the range.
	 */
	@Test
	public void shiftByPositiveDoubleLowerBound() {
		testRange1 = new Range(2, 6);
		Range testShiftedRange = Range.shift(testRange1, 2.5);
		assertEquals("The shifted value should be ", 4.5, testShiftedRange.getLowerBound(), .000000001d);
	}

	/**
	 * Tests the behavior of what happens when a range is shifted by a
	 * negative amount. We are only checking the upper bound of the range.
	 */
	@Test
	public void shiftByNegativeDoubleUpperBound() {
		testRange1 = new Range(2, 6);
		Range testShiftedRange = Range.shift(testRange1, -2.2);
		assertEquals("The shifted value should be ", 3.8, testShiftedRange.getUpperBound(), .000000001d);
	}

	/**
	 *Tests the behavior of what happens when a range is shifted by a
	 * negative amount. We are only checking the lower bound of the range.
	 */
	@Test
	public void shiftByNegativeDoubleLowerBound() {
		testRange1 = new Range(2, 6);
		Range testShiftedRange = Range.shift(testRange1, -1.2);
		assertEquals("The shifted value should be ", 0.8, testShiftedRange.getLowerBound(), .000000001d);
	}

	/**
	 * Tests the behavior of what happens when a range is shifted by a
	 * negative amount where the range will now have a negative coordinate. We are
	 * only checking the upper bound of the range.
	 */
	@Test
	public void shiftByNegativeDoubleToGetNegativeValueUpperBound() {
		testRange1 = new Range(2, 6);
		Range testShiftedRange = Range.shift(testRange1, -6.8);
		assertEquals("The value for the upper bound is incorrect.", -0.8, testShiftedRange.getUpperBound(),
				.000000001d);
	}

	/**
	 * Tests the behavior of what happens when a range is shifted by a
	 * negative amount where the range will now have a negative coordinate. We are
	 * only checking the lower bound of the range.
	 */
	@Test
	public void shiftByNegativeDoubleToGetNegativeValueLowerBound() {
		testRange1 = new Range(2, 6);
		Range testShiftedRange = Range.shift(testRange1, -2.2);
		assertEquals("The value for the lower bound is incorrect.", -0.2, testShiftedRange.getLowerBound(),
				.000000001d);
	}
	
	// ------------- combine(Range range1, Range range2) Tests ---------------------

	/**
	 * Tests the behavior of what happens when the first parameter entered
	 * is null. The second value, the other range, should be returned.
	 */
	@Test
	public void combineFirstParameterNullUpperBound() {
		Range tempRange = Range.combine(null, new Range(3, 9)); 
		double upperBound = tempRange.getUpperBound(); 
		assertEquals("The combined upper bound should be 9.0 and it is " + upperBound, 9.0, upperBound, .000000001d);
	}

	/**
	 * Tests the behavior of what happens when the first parameter entered
	 * is null. The second value (the other range) should be returned.
	 */
	@Test
	public void combineFirstParameterNullLowerBound() {
		Range tempRange = Range.combine(null, new Range(3, 9));
		double lowerBound = tempRange.getLowerBound(); 
		assertEquals("The combined lower bound should be 3.0 and it is " + lowerBound, 3.0, lowerBound, .000000001d);
	}

	/**
	 * Tests the behavior of what happens when the second parameter entered
	 * is null. The first value (the other range) should be returned.
	 */
	@Test
	public void combineSecondParameterNullUpperBound() {
		Range tempRange = Range.combine(new Range(-8, -2), null);
		double upperBound = tempRange.getUpperBound(); 
		assertEquals("The combined upper bound should be -2.0 and it is " + upperBound, -2.0, upperBound, .000000001d);
	}

	/**
	 *Tests the behavior of what happens when the second parameter entered
	 * is null. The first value (the other range) should be returned.
	 */
	@Test
	public void combineSecondParameterNullLowerBound() {
		Range tempRange = Range.combine(new Range(-8, -2), null);
		double lowerBound = tempRange.getLowerBound();
		assertEquals("The combined lower bound should be -8.0 and it is " + lowerBound, -8.0, lowerBound, .000000001d);
	}

	/**
	 * Tests the behavior of what happens when both parameters are null.
	 * The return value should be null and thus throw a null pointer exception
	 */
	@Test(expected = NullPointerException.class)
	public void combineBothParametersNullUpperBound() {
		Range tempRange = Range.combine(null, null); 
		double upperBound = tempRange.getUpperBound(); 
		assertNull("The combined upper bound should be null", upperBound);
	}

	/**
	 * This test will be used to test what happens when both parameters are null.
	 * The return value should be null and thus throw a null pointer exception
	 */
	@Test(expected = NullPointerException.class)
	public void combineBothParametersNullLowerBound() {
		Range tempRange = Range.combine(null, null); 
		double lowerBound = tempRange.getLowerBound(); 
		assertNull("The combined lower bound should be null", lowerBound);
	}

	/**
	 * This test will be used to test what happens when no parameters are null. A
	 * new range should be created from the combination of the two input ranges. 
	 * Upper Bound will be checked.
	 */
	@Test
	public void combineParametersPostiveNegativeUpperBound() {
		Range tempRange = Range.combine(new Range(1, 10), new Range(-8, -2));
		double upperBound = tempRange.getUpperBound();
		assertEquals("The combined upper bound should be 10.0 and it is " + upperBound, 10.0, upperBound, .000000001d);
	}

	/**
	 * Tests the behavior of what happens when no parameters are null. A
	 * new range should be created from the combination of the two input ranges. 
	 * Lower Bound will be checked.
	 */
	@Test
	public void combineParametersPostiveNegativeLowerBound() {
		Range tempRange = Range.combine(new Range(1, 10), new Range(-8, -2)); 
		double lowerBound = tempRange.getLowerBound(); 
		assertEquals("The combined lower bound should be -8.0 and it is " + lowerBound, -8.0, lowerBound, .000000001d);
	}
	
	/**
	 * Tests the behavior of what happens when we are close to zero. A
	 * new range should be created from the combination of the two input ranges.
	 */
	@Test
	public void combineParametersNearZeroBound() {
		Range tempRange = Range.combine(new Range(2,3), new Range(0, 1)); 
		double lowerBound = tempRange.getLowerBound();
		assertEquals("The combined upper bound should be 0.0 and it is " + lowerBound,0.0, lowerBound, .000000001d);
	}
	
    
    // ------------- getCentralValue() Tests ---------------------

	/**
	 * This method tests the behavior of getCentralValue() when the range
	 * spans across zero. The expected return value for the central value 
	 * in this case is 0.
	 */
	public void centralValueAtZero() {
	    Range tempRange = new Range(-1, 1);
	    assertEquals("The central value of -1 and 1 should be 0",
	        0, tempRange.getCentralValue(), .000000001d);
	}
	
	/**
     * This test verifies that the central value is correctly calculated
     * when the range is between positive bounds.
     */
    @Test
    public void centralValueBetweenPostiveBounds() {
        Range tempRange = new Range(1, 2);
        assertEquals("The central value of 1 and 2 should be 1.5",
            1.5, tempRange.getCentralValue(), .000000001d);
    }

    /**
     * This test verifies that the central value is correctly calculated
     * when the range is between negative bounds.
     */
    @Test
    public void centralValueBetweenNegativeBounds() {
        Range tempRange = new Range(-2, -1);
        assertEquals("The central value of -2 and -1 should be -1.5",
            -1.5, tempRange.getCentralValue(), .000000001d);
    }

    /**
     * This test verifies the central value when the range spans a large numeric range.
     */
    @Test
    public void centralValuewithLargeBounds() {
        Range tempRange = new Range(-1000000000, 2000000000);
        assertEquals("The central value of -1000000000 and 2000000000 should be 500000000",
            500000000, tempRange.getCentralValue(), .000000001d);
    }
    
    // ------------- getLowerBound() Tests ---------------------

    /**
     * Tests the behavior of getLowerBound() when the range spans from negative to 
     * positive number, -1 to 1. The expected lower bound value in this case is -1.
     */
    @Test
    public void lowerBoundShouldBeNegOne() {
        Range tempRange = new Range(-1, 1);
        double lowerBound = tempRange.getLowerBound();
        assertEquals("The lower bound value of -1 and 1 should be -1", -1, lowerBound, .000000001d);
    }

    /**
     * Tests the behavior of getLowerBound() when the range spans from small positives, 1 to 2.
     * The expected lower bound value in this case is 1.
     */
    @Test
    public void lowerBoundShouldBePostiveOne() {
        Range tempRange = new Range(1, 2);
        double lowerBound = tempRange.getLowerBound();
        assertEquals("The lower bound value of 1 and 2 should be 1", 1, lowerBound, .000000001d);
    }

    /**
     * Tests the behavior of getLowerBound() when the range spans large negatives from -1000000000 to 2000000000.
     * The expected lower bound value in this case is -1000000000.
     */
    @Test
    public void lowerBoundShouldBeBigNeg() {
        Range tempRange = new Range(-1000000000, 2000000000);
        double lowerBound = tempRange.getLowerBound();
        assertEquals("The lower bound value of -1000000000 and 2000000000 should be -1000000000",
                -1000000000, lowerBound, .000000001d);
    }

    /**
     * Tests the behavior of getLowerBound() when the range spans large positives from 1000000 to 2000000.
     * The expected lower bound value in this case is 1000000.
     */
    @Test
    public void lowerBoundShouldBeBigPositive() {
        Range tempRange = new Range(1000000, 2000000);
        double lowerBound = tempRange.getLowerBound();
        assertEquals("The lower bound value of 1000000 and 2000000 should be 1000000", 1000000, lowerBound, .000000001d);
    }

    /**
     * Tests the behavior of getLowerBound() when the range spans decimal number from 1.5 to 3.
     * The expected lower bound value in this case is 1.5.
     */
    @Test
    public void lowerBoundShouldBeDecimal() {
        Range tempRange = new Range(1.5, 3);
        double lowerBound = tempRange.getLowerBound();
        assertEquals("The lower bound value of 1.5 and 3 should be 1.5", 1.5, lowerBound, .000000001d);
    }

}
