package laskin;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExtraTest extends AbstractParent {

    private static Laskin laskin = new Laskin();
    private final double DELTA = 0.001;

    @BeforeAll
    public static void testVirtaON() {
        System.out.println("@BeforeAll Virta ON (ennen ensimmäistä testiä)");
        laskin.virtaON();
    }

    @AfterAll
    public static void testVirtaOFF() {
        System.out.println("@AfterAll Virta OFF (kaikki testit ajettu).");
        laskin.virtaOFF();
        laskin = null;
    }

    @BeforeEach
    public void testNollaa() {
        System.out.println("  Nollaa laskin.");
        laskin.nollaa();
        assertEquals(0, laskin.annaTulos(), "Nollaus ei onnistunut");
    }


    @ParameterizedTest(name="Luvun {0} neliö on {1}")
    @CsvSource({ "2, 1.414", "25, 5", "16, 4", "4, 2"})
    public void testNeliojuuri(int luku, double tulos) {
        laskin.neliojuuri(luku);
        assertEquals(tulos, laskin.annaTulos(), DELTA, "Luvun " + luku + " neliöjuuren laskenta väärin");
    }
    
    @Test
    @DisplayName("Testaa negatiivinen neliöjuuri")
    public void testNeliojuuriNegat() {
        assertThrows(IllegalArgumentException.class, () -> laskin.neliojuuri(-1));
    }
}
