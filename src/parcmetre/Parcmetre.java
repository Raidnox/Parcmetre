package parcmetre;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class Parcmetre {

	private Voiture voiture;
	private double sommePayee;
	private LocalDateTime dateEntree;
	private static final double MINUTE_PRICE_MIN = 0.00833f;
	private static final double MINUTE_PRICE_MAX = 0.05f;
	private String[] ferie = new String[] {"01/01","01/05","08/05","14/07","15/08","1/11","11/11","25/12"};

	public Parcmetre(Voiture voiture, Double sommePayee , LocalDateTime date) {
		if(!isSommeValide(sommePayee)) {
			throw new IllegalArgumentException("La somme ne doit pas dépasser 14€");
		}
		this.voiture = voiture;
		this.sommePayee = sommePayee;
		this.dateEntree = date;
	}

	public Voiture getVoiture() {
		return voiture;
	}
	
	public boolean isSommeValide(double somme) {
		return somme <= 14f;
	}

	public LocalDateTime calculerTemps() {
		LocalDateTime newDate = dateEntree.plusMinutes((int)calculNbMinute());
		int minutePourJourSuivant = minuteRestantesPourJourSuivant(dateEntree);
		if(minutePourJourSuivant >= calculNbMinute() && dateIsOk(newDate)) {
			return newDate;
		}else {
			double nbMinuteAjouter = calculNbMinute();
			if(minutePourJourSuivant < nbMinuteAjouter && minutePourJourSuivant > 0) {
				nbMinuteAjouter -= minutePourJourSuivant;
			}
			while(!dateIsOk(newDate)) {
				newDate = addDay(newDate);
			}
			newDate = newDate.plusMinutes((int)nbMinuteAjouter);
		}
		return newDate;
	}
	
	public LocalDateTime addDay(LocalDateTime date) {
	    return date.plusDays(1).withHour(9).withMinute(0).withSecond(0).withNano(0);
	}

	private boolean dateIsOk(LocalDateTime date) {
	    return !isSunday(date.toLocalDate()) && !isVaccance(date.toLocalDate()) && !isFerie(String.format("%02d", date.getDayOfMonth()) + "/" + String.format("%02d", date.getMonthValue())) && !isHeureHorsPlage(date);
	}

	public boolean isFerie(String date) {
		List<String> list = Arrays.asList(ferie);
		return list.contains(date);
	}
 
	public boolean isSunday(LocalDate date) {
		return date.getDayOfWeek() == DayOfWeek.SUNDAY;
	}

	public boolean isVaccance(LocalDate date) {
		return date.getMonth() == Month.AUGUST && date.getDayOfMonth() <= 14;
	}
	
	public boolean isHeureHorsPlage(LocalDateTime date) {
	    LocalTime heure = LocalTime.from(date);
	    return heure.isBefore(LocalTime.of(9, 0)) || heure.isAfter(LocalTime.of(19, 0));
	}

	public double calculNbMinute() {
		double nbMinute = 0.0f;
		if(sommePayee > 2) {
			nbMinute = 2 / Parcmetre.MINUTE_PRICE_MIN;
			nbMinute += (sommePayee - 2) / Parcmetre.MINUTE_PRICE_MAX;
		}else { 
			nbMinute = sommePayee / Parcmetre.MINUTE_PRICE_MIN;
		}
		return Math.round(nbMinute);
	}

	private int minuteRestantesPourJourSuivant(LocalDateTime dateTime) {
		LocalDateTime now = dateTime;
		LocalDateTime endOfDay = now.withHour(19).withMinute(0).withSecond(0).withNano(0);
		Duration duration = Duration.between(now, endOfDay);
		return (int) duration.toMinutes();
	}

	public LocalDateTime getDateEntree() {
		return dateEntree;
	}
	
	public double getSommePaye() {
		return sommePayee;
	}
}
