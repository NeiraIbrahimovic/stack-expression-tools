/*
 ***** Important!  Please Read! *****
 *
 *  - Do NOT remove any of the existing import statements
 *  - Do NOT import additional junit packages 
 *  - You MAY add in other non-junit packages as needed
 * 
 *  - Do NOT remove any of the existing test methods or change their name
 *  - You MAY add additional test methods.  If you do, they should all pass
 * 
 *  - ALL of your assert test cases within each test method MUST pass, otherwise the 
 *        autograder will fail that test method
 *  - You MUST write the require number of assert test cases in each test method, 
 *        otherwise the autograder will fail that test method
 *  - You MAY write more than the required number of assert test cases as long as they all pass
 * 
 *  - All of your assert test cases within a method must be related to the method they are meant to test
 *  - All of your assert test cases within a method must be distinct and non-trivial
 *  - Your test cases should reflect the method requirements in the homework instruction specification
 * 
 *  - Your assert test cases will be reviewed by the course instructors and they may take off
 *        points if your assert test cases to do not meet the requirements
 */
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.*;

class RpnCalculatorTest {
	
	@Test
	void testRpnCalculator() {

		/*
		 * TEST 1: Ensure a VALID INPUT returns the correct answer
		 */
		
		//Initialize a new RPN calculator
        RpnCalculator calculator = new RpnCalculator();
        
        //Create a string of inputs
        List<String> input1 = List.of("1", "1", "+");
        
        //Ensure the calculator returns the correct answer
        assertEquals(2, calculator.evaluateExpression(input1), "The RPN calcualtor should return the correct answer when valid inputs are passed");
        
        //Create a string of inputs
        List<String> input2 = List.of("1", "-5", "+");
        
        //Ensure the calculator returns the correct answer
        assertEquals(-4, calculator.evaluateExpression(input2), "The RPN calcualtor should return the correct answer when valid inputs are passed");
		
        //Create a string of inputs
        List<String> input3 = List.of("2", "1", "3", "+", "*");
        
        //Ensure the calculator returns the correct answer
        assertEquals(8, calculator.evaluateExpression(input3), "The RPN calcualtor should return the correct answer when valid inputs are passed");
        
        //Create a string of inputs
        List<String> input4 = List.of("2", "1", "+", "3", "*");
        
        //Ensure the calculator returns the correct answer
        assertEquals(9, calculator.evaluateExpression(input4), "The RPN calcualtor should return the correct answer when valid inputs are passed");
        
        //Create a string of inputs
        List<String> input5 = List.of("4", "13", "5", "/", "+");
        
        //Ensure the calculator returns the correct answer
        assertEquals(6, calculator.evaluateExpression(input5), "The RPN calcualtor should return the correct answer when valid inputs are passed");
        
        //Create a string of inputs
        List<String> input6 = List.of("3", "2", "*", "11", "-");
        
        //Ensure the calculator returns the correct answer
        assertEquals(-5, calculator.evaluateExpression(input6), "The RPN calcualtor should return the correct answer when valid inputs are passed");
        
        //Create a string of inputs
        List<String> input7 = List.of("2", "5", "*", "4", "+", "3", "2", "*", "1", "+", "/");
        
        //Ensure the calculator returns the correct answer
        assertEquals(2, calculator.evaluateExpression(input7), "The RPN calcualtor should return the correct answer when valid inputs are passed");
        
		/*
		 * TEST 2: Ensure a NULL INPUT returns null
		 */
        
        //Initialize an RPN calculator
        RpnCalculator calculator2 = new RpnCalculator();
        
        //Ensure the calculator returns null if a input is used
        assertNull(calculator2.evaluateExpression(null), "The calculator should return null if a null input is used"); 
        
		/*
		 * TEST 3: Ensure if an ELEMENT IN THE INPUT LIST IS NULL, the calculator returns null
		 */
        
        //Initialize an RPN calculator
        RpnCalculator calculator3 = new RpnCalculator();
        
        //Create a string of inputs including a null element
        List<String> input9 = Arrays.asList("2", null, "+");
        
        //Ensure the calculator returns null if a null element is included in the string of inputs
        assertNull(calculator3.evaluateExpression(input9), "The RPN calculator should return null if the input contains a null element");
		
		/*
		 * TEST 4: Ensure a DIVIDE BY ZERO calculation returns null
		 */
        
        //Initialize an RPN calculator
        RpnCalculator calculator4 = new RpnCalculator();
        
        //Create a string of inputs
        List<String> input10 = List.of("8", "0", "/");
        
        //Ensure dividing by zero returns null
        assertNull(calculator4.evaluateExpression(input10), "Dividing by 0 should return null");
        
		/*
		 * TEST 5: Ensure all calculations are completed using INTEGERS ONLY
		 */
        
        //Initialize an RPN calculator
        RpnCalculator calculator5 = new RpnCalculator();
        
        //Create a string of inputs
        List<String> input11 = List.of("3", "2", "/");
        
        //Ensure dividing should result in integers only
        assertEquals(1, calculator5.evaluateExpression(input11), "All calulations should result in integer answers");
        
		
		/*
		 * TEST 6: Ensure calculations with a SINGLE OPERAND return that operand
		 */
        
        //Initialize an RPN calculator
        RpnCalculator calculator6 = new RpnCalculator();
        
        //Create a string of one single input
        List<String> input12 = List.of("5");
        
        //Ensure that the input is returned as is
        assertEquals(5, calculator6.evaluateExpression(input12), "A single operand should be returned as is");
	}

}
