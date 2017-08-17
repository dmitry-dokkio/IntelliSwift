package net.dmitrynakhabtsev.mycode.intelliswift;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * @author Dmitry Nakhabtsev
 */
public class Elevator {

/**
 * Task
 */
    class Task {
        int weight;
        int floor;

        public Task(int weight, int floor) {
            this.weight = weight;
            this.floor = floor;
        }
    }	
	
    public int solution(int[] A, int[] B, int M, int X, int Y) {
        if (A == null || B == null || A.length == 0 || B.length == 0 || X < 1 || Y < 1) return 0;// proverochka for input
        
        int result = 0;
        Queue<Task> taskQueue = new LinkedList<>();
        for (int i = 0; i < A.length; i++) {
            if (A[i] > Y) {throw new RuntimeException("This should not happen!");}// proverochka for person weight
            taskQueue.add(new Task(A[i], B[i])); // creation queue object
        }
        while (!taskQueue.isEmpty()) {
        	result = result+stopsCounter(taskQueue, M, X, Y);
        }
        return result;
    }

/**
 * Process as much tasks as possible and then remove tasks from task queue.
 * <br>
 * 
 *
 * @param taskQueue task queue
 * @param M         floor range
 * @param X         max persons
 * @param Y         max weigth
 *
 * @return stops
 */
    int stopsCounter(Queue<Task> taskQueue, int M, int X, int Y) {
        HashSet floors = new HashSet(M);
        // taskQueue.peek(); retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
        // taskQueue.poll(); retrieves and removes the head of this queue, or returns null if this queue is empty.
        while (!taskQueue.isEmpty() && X > 0 && (Y - taskQueue.peek().weight) >= 0) {
            Task task = taskQueue.poll();
            X = X-1;
            Y = Y-task.weight;
            floors.add(task.floor);
        }
        return floors.size() + 1;
    }



    @Test
    public void test() {
        int[] A = new int[] {60, 80, 40};
        int[] B = new int[] {2, 3, 5};
        int M = 5;
        int X = 2;
        int Y = 200;
        assertThat(solution(A, B, M, X, Y), Matchers.is(5));

        A = new int[] {40, 40, 100, 80, 20};
        B = new int[] {3, 3, 2, 2, 3};
        M = 3;
        X = 5;
        Y = 200;
        assertThat(solution(A, B, M, X, Y), Matchers.is(6));

        A = new int[] {40, 40, 100, 200, 20};
        B = new int[] {3, 3, 2, 2, 3};
        M = 3;
        X = 5;
        Y = 200;
        assertThat(solution(A, B, M, X, Y), Matchers.is(7));

        A = new int[] {40, 40, 100, 200, 20};
        B = new int[] {3, 3, 2, 2, 3};
        M = 3;
        X = 1;
        Y = 200;
        assertThat(solution(A, B, M, X, Y), Matchers.is(10));
    }
}
