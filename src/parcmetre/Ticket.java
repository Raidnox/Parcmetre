package parcmetre;
import java.time.LocalDateTime;

public class Ticket {
	private Parcmetre parcmetre;

    public Ticket(Parcmetre parcmetre) {
        this.parcmetre = parcmetre;
    }

    public String text() {
    	Voiture voiture = parcmetre.getVoiture();
    	LocalDateTime dateSortie = parcmetre.calculerTemps();
    	StringBuilder sb = new StringBuilder();
    	sb.append("========== Ticket de parking ==========\n\n");
    	sb.append("Date d'entree : ");
    	sb.append(parcmetre.getDateEntree());
    	sb.append("\n");
    	sb.append("Date de sortie : ");
    	sb.append(dateSortie);
    	sb.append("\n");
    	sb.append("Montant paye : ");
    	sb.append(parcmetre.getSommePaye());
    	sb.append(" euros\n");
    	sb.append("Temps de parking : ");
    	sb.append(parcmetre.calculNbMinute());
    	sb.append(" Minute(s)\n");
    	sb.append("Votre vehicule : ");
    	sb.append(voiture.getPlaqueImmatriculation());
    	sb.append("\n\n");
    	sb.append("Ticket a conserver merci ! \n");
    	sb.append("======================================\n");
    	return sb.toString();
    }
}
