package testParcmetre;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

import parcmetre.*;

public class TicketTest {

	private Ticket ticket;

	@Before
	public void setUp() throws Exception {
		Voiture voiture = new Voiture("AA-123-BB");
		LocalDateTime dateEntree = LocalDateTime.of(2023, Month.APRIL, 1, 12, 0);
		Parcmetre parcmetre = new Parcmetre(voiture, 2.0, dateEntree);
		ticket = new Ticket(parcmetre);
	}
	
	@Test
	public void testText() {
		String expected = "========== Ticket de parking ==========\n\n"
				+ "Date d'entree : 2023-04-01T12:00\n"
				+ "Date de sortie : 2023-04-01T16:00\n"
				+ "Montant paye : 2.0 €\n"
				+ "Temps de parking : 240.0 Minute(s)\n"
				+ "Votre vehicule : AA-123-BB\n\n"
				+ "Ticket a conserver merci ! \n"
				+ "======================================\n";
		assertEquals(expected, ticket.text());
	}

}
