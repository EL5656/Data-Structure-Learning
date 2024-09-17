import org.example.Search;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;

public class SearchTest {
    private Search tc;

    @Test
    @DisplayName("testBinarySearchFound")
    public void testBinarySearchFound() {
        tc = new Search();
        int[] array = { 5, 14, 22, 30, 31, 38, 41, 44 };
        int searchElem =22;
        int expectedIndex = 2;

        try {
            assertEquals(expectedIndex, tc.binarySearch(array,searchElem));
            System.out.println("Test passed for target: " + searchElem);
        } catch (AssertionError e) {
            System.out.println("Test failed for target: " + searchElem + " with message: " + e.getMessage());
            throw e; // Re-throw to ensure the test is marked as failed
        }
    }

    @Test
    @DisplayName("testBinarySearchNotFound")
    public void testBinarySearchNotFound() {
        tc = new Search();
        int[] array = { 5, 14, 22, 30, 31, 38, 41, 44 };
        int target = 100;
        int expectedIndex = -1; // 100 is not in the array
        int result = tc.binarySearch(array, target);

        try {
            assertEquals(expectedIndex, result);
            System.out.println("Test passed for target: " + target);
        } catch (AssertionError e) {
            System.out.println("Test failed for target: " + target + " with message: " + e.getMessage());
            throw e; // Re-throw to ensure the test is marked as failed
        }
    }
}
