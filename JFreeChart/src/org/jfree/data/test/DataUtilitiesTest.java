package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.junit.Test;

public class DataUtilitiesTest {

	
	// ------------- calculateColumnTotal() Tests ---------------------

	
	/**
	 * Test if method returns correct sum for 2 column values
	 */
    @Test
    public void calculateColumnTotalForTwoValues() {
        // setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(2));
            oneOf(values).getValue(0, 0);
            will(returnValue(7.5));
            oneOf(values).getValue(1, 0);
            will(returnValue(2.5));
        }});

        double result = DataUtilities.calculateColumnTotal(values, 0);

        // verify
        assertEquals(10.0, result, .000000001d);

    }
    
    /**
     * Test calculateColumnTotal with valid input
     */
    @Test
    public void testCalculateColumnTotal_ValidInput() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, 0); will(returnValue(1.0));
            one(values).getValue(1, 0); will(returnValue(2.0));
            one(values).getValue(2, 0); will(returnValue(3.0));
        }});
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals("Should return the sum of the values in the column", 6.0, result, .000000001d);
    }

    /**
     * Test calculateColumnTotal with null data object
     */
    @Test(expected = InvalidParameterException.class)
    public void testCalculateColumnTotal_NullData() {
        DataUtilities.calculateColumnTotal(null, 0);
    }

    /**
     *  Test calculateColumnTotal with empty data table
     */
    @Test
    public void testCalculateColumnTotal_EmptyTable() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(0));
        }});
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals("Should return zero for an empty table", 0.0, result, .000000001d);
    }

    /**
     *  Test calculateColumnTotal with single row data table
     */
    @Test
    public void testCalculateColumnTotal_SingleRow() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(1));
            one(values).getValue(0, 0); will(returnValue(5.0));
        }});
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals("Should return the value of the single row", 5.0, result, .000000001d);
    }
    
	// ------------- calculateRowTotal() Tests ---------------------

    
    /**
     *  Test calculateRowTotal with valid input
     */
    @Test
    public void testCalculateRowTotal_ValidInput() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount(); will(returnValue(3));
            one(values).getValue(0, 0); will(returnValue(1.0));
            one(values).getValue(0, 1); will(returnValue(2.0));
            one(values).getValue(0, 2); will(returnValue(3.0));
        }});
        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals("Should return the sum of the values in the row", 6.0, result, .000000001d);
    }

    /**
     * Test calculateRowTotal with null data object
     */
    @Test(expected = InvalidParameterException.class)
    public void testCalculateRowTotal_NullData() {
        DataUtilities.calculateRowTotal(null, 0);
    }

    /**
     *  Test calculateRowTotal with empty data table
     */
    @Test
    public void testCalculateRowTotal_EmptyTable() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount(); will(returnValue(0));
        }});
        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals("Should return zero for an empty table", 0.0, result, .000000001d);
    }

    /**
     *  Test calculateRowTotal with single column data table
     */
    @Test
    public void testCalculateRowTotal_SingleColumn() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount(); will(returnValue(1));
            one(values).getValue(0, 0); will(returnValue(5.0));
        }});
        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals("Should return the value of the single column", 5.0, result, .000000001d);
    }
    
	// ------------- createNumberArray() Tests ---------------------

    
    /**
     *  Test createNumberArray with valid input
     */
    @Test
    public void testCreateNumberArray_ValidInput() {
        double[] data = new double[] {1.0, 2.0, 3.0, 4.0};
        Number[] result = DataUtilities.createNumberArray(data);
        assertNotNull("The result should not be null", result);
        assertEquals("The length of the result should be equal to the input array", data.length, result.length);
        for (int i = 0; i < data.length; i++) {
            assertEquals("Each element should match the input data", data[i], result[i].doubleValue(), .000000001d);
        }
    }

    /**
     *  Test createNumberArray with empty array
     */
    @Test
    public void testCreateNumberArray_EmptyArray() {
        double[] data = new double[] {};
        Number[] result = DataUtilities.createNumberArray(data);
        assertNotNull("The result should not be null", result);
        assertEquals("The result array should be empty", 0, result.length);
    }

    /**
     * Test createNumberArray with single element array
     */
    @Test
    public void testCreateNumberArray_SingleElement() {
        double[] data = new double[] {42.0};
        Number[] result = DataUtilities.createNumberArray(data);
        assertNotNull("The result should not be null", result);
        assertEquals("The result array should contain one element", 1, result.length);
        assertEquals("The single element should match the input data", data[0], result[0].doubleValue(), .000000001d);
    }

    /**
     *  Test createNumberArray with all zeros in input array
     */
    @Test
    public void testCreateNumberArray_AllZeros() {
        double[] data = new double[] {0.0, 0.0, 0.0};
        Number[] result = DataUtilities.createNumberArray(data);
        assertNotNull("The result should not be null", result);
        for (Number number : result) {
            assertEquals("Each element should be zero", 0.0, number.doubleValue(), .000000001d);
        }
    }

    /**
     *  Test createNumberArray with null input
     */
    @Test(expected = InvalidParameterException.class)
    public void testCreateNumberArray_NullInput() {
        DataUtilities.createNumberArray(null);
    }

    /**
     *  Test createNumberArray with negative values in input array
     */
    @Test
    public void testCreateNumberArray_NegativeValues() {
        double[] data = new double[] {-1.0, -2.0, -3.0};
        Number[] result = DataUtilities.createNumberArray(data);
        assertNotNull("The result should not be null", result);
        for (int i = 0; i < data.length; i++) {
            assertEquals("Each element should match the input data", data[i], result[i].doubleValue(), .000000001d);
        }
    }
    
	// ------------- calculateColumnTotal2D() Tests ---------------------

    
    /**
     *  Test createNumberArray2D with valid input
     */
    @Test
    public void testCreateNumberArray2D_ValidInput() {
        double[][] data = new double[][] {
            {1.0, 2.0},
            {3.0, 4.0}
        };
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertNotNull("The result should not be null", result);
        assertEquals("The number of rows should match", data.length, result.length);

        for (int i = 0; i < data.length; i++) {
            assertEquals("The number of columns in row " + i + " should match", data[i].length, result[i].length);
            for (int j = 0; j < data[i].length; j++) {
            	System.out.println(result[i][j]);
                assertEquals("The element at [" + i + "][" + j + "] should match the input data",
                             data[i][j], result[i][j].doubleValue(), .000000001d);
            }
        }
    }

    /**
     *  Test createNumberArray2D with empty array
     */
    @Test
    public void testCreateNumberArray2D_EmptyArray() {
        double[][] data = new double[][] {};
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertNotNull("The result should not be null", result);
        assertEquals("The result array should be empty", 0, result.length);
    }

    /**
     *  Test createNumberArray2D with single element array
     */
    @Test
    public void testCreateNumberArray2D_SingleElement() {
        double[][] data = new double[][] {{42.0}};
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertNotNull("The result should not be null", result);
        assertEquals("The result array should contain one row", 1, result.length);
        assertEquals("The result row should contain one element", 1, result[0].length);
        assertEquals("The single element should match the input data",
                     42.0, result[0][0].doubleValue(), .000000001d);
    }

    /**
     *  Test createNumberArray2D with null input
     */
    @Test(expected = InvalidParameterException.class)
    public void testCreateNumberArray2D_NullInput() {
        DataUtilities.createNumberArray2D(null);
    }
    
	// ------------- getCumulativePercentages() Tests ---------------------

    
	/**
	 *  Test getCumulativePercentages with valid input
	 */
	
    @Test
	public void testGetCumulativePercentages_ValidInput() {
	    Mockery mockingContext = new Mockery();
	    final KeyedValues values = mockingContext.mock(KeyedValues.class);
	    mockingContext.checking(new Expectations() {{
	        allowing(values).getItemCount(); will(returnValue(3)); 
	        allowing(values).getValue(0); will(returnValue(5)); 
	        allowing(values).getValue(1); will(returnValue(9));
	        allowing(values).getValue(2); will(returnValue(2));
	        allowing(values).getKey(0); will(returnValue(0)); 
	        allowing(values).getKey(1); will(returnValue(1));
	        allowing(values).getKey(2); will(returnValue(2));
	    }});

	    KeyedValues result = DataUtilities.getCumulativePercentages(values);
	    assertNotNull("The result should not be null", result);
	    assertEquals("Cumulative percentage of the first value should be correct",
	                 0.3125, result.getValue(0).doubleValue(), 0.000000001d);
	    assertEquals("Cumulative percentage of the second value should be correct",
	                 0.875, result.getValue(1).doubleValue(), 0.000000001d);
	    assertEquals("Cumulative percentage of the third value should be correct",
	                 1.0, result.getValue(2).doubleValue(), 0.000000001d);
	}


	/**
	 *  Test getCumulativePercentages with empty KeyedValues
	 */
	
    @Test
	public void testGetCumulativePercentages_Empty() {
	    Mockery mockingContext = new Mockery();
	    final KeyedValues values = mockingContext.mock(KeyedValues.class);
	    mockingContext.checking(new Expectations() {{
	        allowing(values).getItemCount(); will(returnValue(0)); 
	    }});

	    KeyedValues result = DataUtilities.getCumulativePercentages(values);
	    assertNotNull("The result should not be null", result);
	    assertEquals("The result should have no entries", 0, result.getItemCount());
	}


    /**
     *  Test getCumulativePercentages with null input
     */
    
    @Test(expected = InvalidParameterException.class)
    public void testGetCumulativePercentages_NullInput() {
        DataUtilities.getCumulativePercentages(null);
    }

    /**
     *  Test getCumulativePercentages with negative values
     */
    
    @Test
    public void testGetCumulativePercentages_NegativeValues() {
        Mockery mockingContext = new Mockery();
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        mockingContext.checking(new Expectations() {{
            allowing(values).getItemCount(); will(returnValue(3));
            allowing(values).getValue(0); will(returnValue(5));
            allowing(values).getValue(1); will(returnValue(9));
            allowing(values).getValue(2); will(returnValue(2));
            allowing(values).getKey(0); will(returnValue(0));
            allowing(values).getKey(1); will(returnValue(1));
            allowing(values).getKey(2); will(returnValue(2));
        }});

        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        assertNotNull("The result should not be null", result);
        
        assertEquals("Cumulative percentage of the first value should be 0.0 or handled accordingly",
                     0.0, result.getValue(0).doubleValue(), 0.000000001d);
        assertEquals("Cumulative percentage of the second value should be 0.0 or handled accordingly",
                     0.0, result.getValue(1).doubleValue(), 0.000000001d);
    }

    /**
     *  Test getCumulativePercentages with all zero values
     */
    @Test
    public void testGetCumulativePercentages_AllZeros() {
        Mockery mockingContext = new Mockery();
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        mockingContext.checking(new Expectations() {{
            allowing(values).getItemCount(); will(returnValue(3));
            allowing(values).getValue(0); will(returnValue(0));
            allowing(values).getValue(1); will(returnValue(0));
            allowing(values).getValue(2); will(returnValue(0));
            allowing(values).getKey(0); will(returnValue(0)); 
            allowing(values).getKey(1); will(returnValue(1)); 
            allowing(values).getKey(2); will(returnValue(2)); 
        }});

        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        assertNotNull("The result should not be null", result);

        for (int i = 0; i < 3; i++) {
            assertEquals("Cumulative percentage of key " + i + " should be 0.0",
                         0.0, result.getValue(i).doubleValue(), 0.000000001d);
        }
    }

}

