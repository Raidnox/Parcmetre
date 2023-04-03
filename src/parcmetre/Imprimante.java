package parcmetre;
public class Imprimante {
	
	public void imprimer(Ticket ticket) {
		System.out.println("Impression du ticket \n\n" + ticket.text() + "\nBonne fin de journee.");
	}
}