/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package kz.ayan;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
public class TestApplicationTest {

    @Test
    public void testAppHasAGreeting() {
        TestApplication classUnderTest = new TestApplication();
        assertNotNull("app should have a greeting", classUnderTest);
    }
}
