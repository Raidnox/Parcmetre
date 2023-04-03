package parcmetre;

public class Voiture {
	
	private  String plaqueImmatriculation = "";
	
	public Voiture(String plaqueImma) throws IllegalArgumentException{
		if(!isPlaqueValid(plaqueImma)) {
			throw new IllegalArgumentException("Plaque d'immatriculation non valide");
		}
		plaqueImmatriculation = plaqueImma;
	}
	
	public String getPlaqueImmatriculation() {
		return plaqueImmatriculation;
	}
	
	public boolean isPlaqueValid(String immatriculation) {
		 String regex = "^[A-Z]{2}-\\d{3}-[A-Z]{2}$";
		 return immatriculation.matches(regex);
	}

}