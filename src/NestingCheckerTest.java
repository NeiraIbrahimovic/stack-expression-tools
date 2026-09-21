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

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.*;

class NestingCheckerTest {
	
	@Test
	void testNestingChecker() {
		
		/*
		 * TEST 1: Ensure a VALID INPUT leaves an empty queue and stack
		 */
		
		//Create a queue with valid inputs
        Queue<Nestable> input1 = new LinkedList<>();
        input1.add(new NestableCharacter('['));
        input1.add(new NestableCharacter('0'));
        input1.add(new NestableCharacter('('));
        input1.add(new NestableCharacter('1'));
        input1.add(new NestableCharacter('2'));
        input1.add(new NestableCharacter('{'));
        input1.add(new NestableCharacter('3'));
        input1.add(new NestableCharacter('('));
        input1.add(new NestableCharacter('4'));
        input1.add(new NestableCharacter('5'));
        input1.add(new NestableCharacter('6'));
        input1.add(new NestableCharacter(')'));
        input1.add(new NestableCharacter('('));
        input1.add(new NestableCharacter('7'));
        input1.add(new NestableCharacter('8'));
        input1.add(new NestableCharacter(')'));
        input1.add(new NestableCharacter('}'));
        input1.add(new NestableCharacter('9'));
        input1.add(new NestableCharacter(')'));
        input1.add(new NestableCharacter(']'));

        //Pass the queue to the method
        NestingReport report1 = NestingChecker.checkNesting(input1);
        
        //Ensure that status is set to valid, badItem is set to null, and stackState set to the empty Stack
        assertEquals(NestingReport.Status.VALID, report1.getStatus(), "Status should be VALID when the input contains valid nesting");
        assertNull(report1.getBadItem(), "Bad item should be null when a queue contains valid nesting");
        assertTrue(report1.getStackState().isEmpty(), "The stack should be empty when the input contained valid nesting");
		
		
		/*
		 * TEST 2: Ensure an INPUT WITH SPECIAL CHARACTERS is still valid
		 */
        
		//Create a queue with valid inputs
        Queue<Nestable> input2 = new LinkedList<>();
        input2.add(new NestableCharacter('['));
        input2.add(new NestableCharacter('+'));
        input2.add(new NestableCharacter('('));
        input2.add(new NestableCharacter('*'));
        input2.add(new NestableCharacter(')'));
        input2.add(new NestableCharacter(']'));

        //Pass the queue to the method
        NestingReport report2 = NestingChecker.checkNesting(input2);
        
        //Ensure that status is set to valid, badItem is set to null, and stackState set to the empty Stack
        assertEquals(NestingReport.Status.VALID, report2.getStatus(), 
        		"Status should be VALID when a queue contains proper nesting and special characters, because we are only considering nesting and not other aspects of syntax");
        assertNull(report2.getBadItem(), "Bad item should be null when a queue contains valid nesting and special characters");
        assertTrue(report2.getStackState().isEmpty(), "The stack should be empty when the input contained valid nesting");
		
		/*
		 * TEST 3: Ensure a NULL INPUT queue results in a null input report
		 */
		
		//Pass a null queue into the method
		NestingReport report3 = NestingChecker.checkNesting(null);
		
		//Ensure that the status is set to NULL_INPUT, badItem is set to null, and stackState set to the empty Stack
        assertEquals(NestingReport.Status.NULL_INPUT, report3.getStatus(), 
        		"Status should be NULL_INPUT when a null queue is passed into the method");
        assertNull(report3.getBadItem(), "Bad item should be null when a null queue is passed into the method");
        assertTrue(report3.getStackState().isEmpty(), "The stack should be empty when a null queue is passed into the method");
		
		/*
		 * TEST 4: Ensure that if an ELEMENT IN THE QUEUE IS NULL, this will result in a null item report
		 */
        
        //Create a queue with a null item
        Queue<Nestable> input = new LinkedList<>();
        input.add(new NestableCharacter('['));
        input.add(null);
        input.add(new NestableCharacter(']'));

        //Pass the queue with a null item into the method
        NestingReport report4 = NestingChecker.checkNesting(input);
        
        //Ensure that the status is set to NULL_ITEM and badItem is set to null
        assertEquals(NestingReport.Status.NULL_ITEM, report4.getStatus(), "Status should be NULL_ITEM when an element in the queue is null");
        assertNull(report4.getBadItem(), "Bad item should be null when an element in the queue is null");
        
        //Ensure the stack contains only the unmatched opening '['
        assertEquals(1, report4.getStackState().size(), "Stack should contain one element because the null item should be excluded from the stack");
        assertEquals(new NestableCharacter('['), report4.getStackState().peek(), "Top of the stack should be '['");
		
		/*
		 * TEST 5: Ensure that if the queue has INVALID CLOSING ELEMENT, this will result in an INVALID_CLOSE report
		 */
        
        //Create a queue with an invalid closing element
        Queue<Nestable> input5 = new LinkedList<>();
        input5.add(new NestableCharacter('['));
        input5.add(new NestableCharacter('('));
        input5.add(new NestableCharacter(')'));
        input5.add(new NestableCharacter('}')); //invalid closing element

        //Pass the queue with the invalid closing element into the method
        NestingReport report5 = NestingChecker.checkNesting(input5);
        
        //Ensure that the status is set to INVALID_CLOSE and badItem is set to e
        assertEquals(NestingReport.Status.INVALID_CLOSE, report5.getStatus(),
        		"Status should be INVALID_CLOSE when the queue has an invalid closing element");
        assertEquals(new NestableCharacter('}'), report5.getBadItem(), "Bad item should be set to the invalid element when a queue has an invalid closing element e");
        
        //Ensure stackState is set to the current contents of the Stack (excluding e)
        assertEquals(1, report5.getStackState().size(), "Stack should contain one element because the invalid item should be excluded from the stack");
        assertEquals(new NestableCharacter('['), report5.getStackState().peek(), "Top of the stack should be '['");
		
		
		/*
		 * TEST 6: Ensure that if the queue is empty, but stack has element that was never closed, this will result in a NOT_TERMINATED report
		 */
		
        //Create a queue which isn't terminated
        Queue<Nestable> input6 = new LinkedList<>();
        input6.add(new NestableCharacter('['));
        input6.add(new NestableCharacter('{'));
        input6.add(new NestableCharacter('('));
        input6.add(new NestableCharacter(')'));

        //Pass the non-terminated queue into the method
        NestingReport report6 = NestingChecker.checkNesting(input6);
        
        //Ensure that the status is set to NOT_TERMINATED and badItem is set to null
        assertEquals(NestingReport.Status.NOT_TERMINATED, report6.getStatus(),
        		"Status should be set to NOT_TERMINATED when the queue contains an unterminated nestable character");
        assertNull(report6.getBadItem(), "Bad item should be set to null when the queue contains an unterminated nestable character");
        
        //Ensure that the stackState is set to the current contents of the Stack
        assertFalse(report6.getStackState().isEmpty(), 
        		"The stack should contain nestable characters if one or more of the nestable characters was left unterminated");
        assertEquals(2, report6.getStackState().size(), "Stack should contain two elements because two elements were left unterminated");
        assertEquals(new NestableCharacter('{'), report6.getStackState().peek(), "Top of the stack should be '{'");
        
        /*
         * TEST 7: Ensure if there are TOO MANY CLOSING BRACKETS, this will result in an INVALID_CLOSE report
         */
		
        //Create a queue with too many closing brackets
        Queue<Nestable> input7 = new LinkedList<>();
        input7.add(new NestableCharacter('['));
        input7.add(new NestableCharacter(']'));
        input7.add(new NestableCharacter(')')); //Extra closing bracket

        //Initialize a new nesting report
        NestingReport report7 = NestingChecker.checkNesting(input7);

        //Ensure that the status is set to INVALID_CLOSE and badItem is set to the extra closing bracket
        assertEquals(NestingReport.Status.INVALID_CLOSE, report7.getStatus(),
            "Status should be INVALID_CLOSE when there are too many closing brackets.");
        assertEquals(new NestableCharacter(')'), report7.getBadItem(),
            "Bad item should be the extra closing bracket.");
        
        /*
         * TEST 8: Ensure an EMPTY QUEUE will result in a valid report
         */
        
        //Initialize a new empty queue
        Queue<Nestable> input8 = new LinkedList<>(); 

        //Initialize a new nesting report
        NestingReport report = NestingChecker.checkNesting(input8);

        //Ensure that the status is set to VALID, badITem is set to null, and the stack is empty
        assertEquals(NestingReport.Status.VALID, report.getStatus(),
            "Status should be VALID when an empty queue is passed.");
        assertNull(report.getBadItem(), "Bad item should be null when the queue is empty.");
        assertTrue(report.getStackState().isEmpty(),
            "Stack should be empty when an empty queue is passed.");
	
	}
	
}
