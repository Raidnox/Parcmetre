package testParcmetre;

import org.junit.Before;
import org.junit.Test;

import parcmetre.Parcmetre;
import parcmetre.Voiture;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
	
public class ParcmetreTest {
    private Parcmetre parcmetre;

    @Before
    public void setup() {
        Voiture voiture = new Voiture("AB-123-CD");
        double sommePayee = 2.0;
        LocalDateTime dateEntree = LocalDateTime.of(2023, 3, 31, 10, 0, 0);
        parcmetre = new Parcmetre(voiture, sommePayee, dateEntree);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testSommePayeeTropElevee() {
    	Voiture voiture = new Voiture("AB-123-CD");
        double sommePayee = 15.0;
        LocalDateTime dateEntree = LocalDateTime.of(2023, 3, 31, 10, 0, 0);
    	parcmetre = new Parcmetre(voiture, sommePayee, dateEntree);
    }
    
    @Test
    public void testAddDay() {
    	 LocalDateTime dateEntree = LocalDateTime.of(2023, 3, 31, 10, 0, 0);
    	 LocalDateTime dateexpected = LocalDateTime.of(2023, 4, 1, 9, 0, 0);
    	 assertEquals(dateexpected, parcmetre.addDay(dateEntree));
    }
    
    @Test
    public void testDemainIsdimanche() {
    	 Voiture voiture = new Voiture("AB-123-CD");
         double sommePayee = 3.0;
         LocalDateTime dateEntree = LocalDateTime.of(2023, 4, 1, 16, 0, 0);
         LocalDateTime dateexpected = LocalDateTime.of(2023, 4, 3, 10, 20, 0);
         parcmetre = new Parcmetre(voiture, sommePayee, dateEntree);
         assertEquals(dateexpected,parcmetre.calculerTemps());
    }
    
    @Test
    public void testGetSommePaye() {
    	double sommePayee = 2.0;
    	assertEquals( (int) sommePayee, (int) parcmetre.getSommePaye());
    }
    
    @Test
    public void testGetDateEntree() {
    	 LocalDateTime dateEntree = LocalDateTime.of(2023, 3, 31, 10, 0, 0);
    	 assertEquals(dateEntree, parcmetre.getDateEntree());
    }
    
    @Test
    public void testGetVoiture() {
    	Voiture voiture = new Voiture("AB-123-CD");
    	assertEquals(voiture.getPlaqueImmatriculation(), parcmetre.getVoiture().getPlaqueImmatriculation());
    }
    
    @Test
    public void testSommePayeeCorrect() {
    	assertTrue(parcmetre.isSommeValide(12));
    }

    @Test
    public void testCalculerTemps() {
        LocalDateTime expectedDateSortie = LocalDateTime.of(2023, 3, 31, 14, 0, 0);
        assertEquals(expectedDateSortie, parcmetre.calculerTemps());
    }

    @Test
    public void testIsFerie() {
        assertTrue(parcmetre.isFerie("01/01"));
        assertFalse(parcmetre.isFerie("31/12"));
    }

    @Test
    public void testIsSunday() {
        assertFalse(parcmetre.isSunday(LocalDateTime.of(2023, 3, 31, 10, 0, 0).toLocalDate()));
        assertTrue(parcmetre.isSunday(LocalDateTime.of(2023, 4, 2, 10, 0, 0).toLocalDate()));
    }

    @Test
    public void testIsVaccance() {
        assertTrue(parcmetre.isVaccance(LocalDateTime.of(2023, 8, 14, 10, 0, 0).toLocalDate()));
        assertFalse(parcmetre.isVaccance(LocalDateTime.of(2023, 8, 15, 10, 0, 0).toLocalDate()));
    }

    @Test
    public void testIsHeureHorsPlage() {
        assertFalse(parcmetre.isHeureHorsPlage(LocalDateTime.of(2023, 3, 31, 10, 0, 0)));
        assertTrue(parcmetre.isHeureHorsPlage(LocalDateTime.of(2023, 3, 31, 8, 0, 0)));
        assertTrue(parcmetre.isHeureHorsPlage(LocalDateTime.of(2023, 3, 31, 20, 0, 0)));
    }

    @Test
    public void testCalculNbMinute() {
        double expectedNbMinute = 240.0;
        assertEquals(expectedNbMinute, parcmetre.calculNbMinute(), 0.01);
    }
    
    @Test
    public void testCalculeNbMinuteSuperieurADeux() {
    	Voiture voiture = new Voiture("AB-123-CD");
        double sommePayee = 3.0;
        double expectedNbMinute = 260.00;
        LocalDateTime dateEntree = LocalDateTime.of(2023, 3, 31, 10, 0, 0);
        parcmetre = new Parcmetre(voiture, sommePayee, dateEntree);
        assertEquals(expectedNbMinute, parcmetre.calculNbMinute(), 0.01);
    }
}
