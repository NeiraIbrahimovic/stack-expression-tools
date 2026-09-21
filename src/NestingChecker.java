/*
 * I attest that the code in this file is entirely my own except for the starter
 * code provided with the assignment and the following exceptions:
 * <Enter all external resources and collaborations here.>
 *
 * Note external code may reduce your score but appropriate citation is required
 * to avoid academic integrity violations. Please see the Course Syllabus as
 * well as the university code of academic integrity:
 *
 * Signed,
 * Author: Neira Ibrahimovic
 * Date: 2025-02-04
 */

import java.util.Queue;
import java.util.Stack;

public class NestingChecker {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 123674589918L;

    /**
     * TODO Implement this method!
     * Takes a nullable Queue of nullable elements and calculates its nesting status
     *
     * @param elements Nullable Queue of nullable Nestable elements
     * @return Non-null NestingReport describing the exact nesting status of the queue
     */
    public static NestingReport checkNesting(Queue<? extends Nestable> elements) {

        //First, handle edge case 1 where the input queue is null
        if (elements == null) {
        	//If input is null, return a report set with NULL_INPUT for status, null for Bad item, and empty stack
            return new NestingReport(NestingReport.Status.NULL_INPUT, null, new Stack<>());
        }
        
        //Initialize a new stack to track nesting
        Stack<Nestable> stack = new Stack<>();

        //Process each element in the queue while the queue contains remaining elements
        while (!elements.isEmpty()) {
        	//Retrieve and remove the first element (head) of the queue
            Nestable current = elements.poll(); 

            //If the retrieved element is null (edge case 4), return a report set with NULL_INPUT for status, null for Bad item, and the current stack 
            if (current == null) {
                return new NestingReport(NestingReport.Status.NULL_ITEM, null, stack);
            }

            //If the current element in the queue is an open element, push the element to the stack
            if (current.getEffect() == Nestable.NestEffect.OPEN) {
                stack.push(current); 
                
            //If the current element in the queue is a closing element, check if the top element in the stack matches
            } else if (current.getEffect() == Nestable.NestEffect.CLOSE) { 
                //If the stack is empty or the top element doesn't match the current closing element, return a report set with INVALID_CLOSE for status, the current element as the bad item, and the current stack
                if (stack.isEmpty() || !stack.peek().matches(current)) {
                    return new NestingReport(NestingReport.Status.INVALID_CLOSE, current, stack);
                }
                //If the matched element was found, remove the matched opening element from the stack
                stack.pop(); 
            }
        }

        // After going through all elements in the queue, check if there are unmatched opening elements remaining in the stack
        if (!stack.isEmpty()) {
        	//If so, return a report set with NOT_TERMINATED for status, null for Bad item, and the current stack
            return new NestingReport(NestingReport.Status.NOT_TERMINATED, null, stack);
        }

        //Return a valid nesting report set with VALID for status, null for Bad item, and an empty stack if no issues were found
        return new NestingReport(NestingReport.Status.VALID, null, new Stack<>());
    }
}
