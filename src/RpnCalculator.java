/*
 * I attest that the code in this file is entirely my own except for the starter
 * code provided with the assignment and the following exceptions:
 * <Enter all external resources and collaborations here. Note external code may
 * reduce your score but appropriate citation is required to avoid academic
 * integrity violations. Please see the Course Syllabus as well as the
 * university code of academic integrity:
 * Signed,
 * Author: Neira Ibrahimovic
 * Date: 2025-02-04
 */

import java.util.List;
import java.util.Stack;

public class RpnCalculator {
	
    public Integer evaluateExpression(List<String> expression) {
        
    	//First, check if the input list is null and return null if so (edge case 3)
        if (expression == null) {
            return null;
        }

        //Initialize a stack to perform calculations
        Stack<Integer> stack = new Stack<>();

        //Iterate through each element in the list
        for (String element : expression) {
        	
            //If there is a null element in the list, return null
            if (element == null) {
                return null;
            }

            //For each element, handle the possible inputs
            try {
                //Check if the operation is addition
                if ("+".equals(element)) {
                	//If so, push the result of adding the top two elements
                    stack.push(stack.pop() + stack.pop());
                    
                //Check if the operation is subtraction
                } else if ("-".equals(element)) { 
                	//If so, pop the top element and store it in a variable
                    int b = stack.pop();
                    //Pop the next element and store it in a variable
                    int a = stack.pop();
                    //Subtract the two elements and push the result
                    stack.push(a - b);
                    
                //Check if the operation is multiplication
                } else if ("*".equals(element)) { 
                	//If so, push the result of multiplying the top two elements
                    stack.push(stack.pop() * stack.pop());
                    
                //Check if the operation is division
                } else if ("/".equals(element)) { 
                	//If so, pop the top element and store it in a variable
                    int b = stack.pop();
                    //Since the top element will be the denominator, handle the case where the denominator is 0
                    if (b == 0) { 
                    	//If the denominator is 0, return null (edge case 5)
                        return null;
                    }
                    //Pop the next element and store it in a variable
                    int a = stack.pop();
                    //Divide the two elements and push the result
                    stack.push(a / b);
                    
                //Check if the element is a number
                } else { 
                	//If so, push the element into the stack (make sure it is converted into an integer)
                    stack.push(Integer.parseInt(element));
                }
            } catch (Exception e) {
                //Return null for any unexpected errors during processing
                return null;
            }
        }

        //Return the final result once the stack contains the final answer (only one element remaining)
        if (stack.size() == 1) {
        	return stack.pop();
        }
        else {
        	//If there are multiple elements left, this means the input is not a valid RPN expression so return null
        	return null;
        }
    }
}
