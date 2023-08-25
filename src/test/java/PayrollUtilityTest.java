import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PayrollUtilityTest {

    @Test
    public void testArrayTokenSize(){

        String testStr = "aaa;bbb;ccc";
        String[] testArr = testStr.split(";");
        System.out.println(StringUtils.defaultIfBlank(testArr[3], ""));
        assertEquals("", StringUtils.defaultIfBlank(testArr[3], ""));
    }

    @Test
    public void testArrayTokenSize_2(){

        String testStr = "aaa;bbb;ccc";
        String[] testArr = testStr.split(";");
        System.out.println(isValidIndex(testArr, 3));
        assertTrue(isValidIndex(testArr, 3));
    }

    public static boolean isValidIndex(String[] arr, int index) {
        try {
            Objects.checkIndex(index, arr.length);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }
}
