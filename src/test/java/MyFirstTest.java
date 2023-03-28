import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MyFirstTest {
    @Test
    public void testPlus() {
        assertEquals(2, 1 + 1);//测试1+1的结果
    }

    @Test
    public void testMinus() {
        assertEquals(1, 1 - 1);//测试1-1的结果
    }
}


