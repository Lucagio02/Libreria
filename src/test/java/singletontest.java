import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import service.Libreria;

public class singletontest {
    @Test
    public void testSingletonUniqueness() {
        Libreria instance1 = Libreria.getInstance();
        Libreria instance2 = Libreria.getInstance();
        assertSame(instance1, instance2, "Le due istanze devono essere la stessa");
    }
}

