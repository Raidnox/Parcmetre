package testParcmetre;
import parcmetre.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class VoitureTest {

	Voiture voiture;
	
    @Test(expected = IllegalArgumentException.class)
    public void testConstructeurPlaqueInvalide() {
        this.voiture = new Voiture("1X-123-YY");
    }

    @Test
    public void testConstructeurPlaqueValide() {
        this.voiture = new Voiture("AB-123-CD");
        assertEquals("AB-123-CD", voiture.getPlaqueImmatriculation());
    }
}