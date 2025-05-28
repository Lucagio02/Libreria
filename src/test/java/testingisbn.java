import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utility.isbnvalidator;
public class testingisbn{

    @Test
    public void testValidIsbn13() {
        assertTrue(isbnvalidator.isvalid("9781234567897"));
    }

    @Test
    public void testInvalidIsbnLength() {
        assertFalse(isbnvalidator.isvalid("12345"));
    }

    @Test
    public void testInvalidIsbnCharacters() {
        assertFalse(isbnvalidator.isvalid("9781234abcxyz"));
    }

    @Test
    public void testNullIsbn() {
        assertFalse(isbnvalidator.isvalid(null));
    }
}