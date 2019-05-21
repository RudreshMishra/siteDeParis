package siteParis;


public class Betting {
	

	private String competitionNom;
	private long argent;
	private String competeure;

	public Betting(String competitionNom, long argent, String competeure){
		this.competitionNom = competitionNom;
		this.argent = argent;
		this.competeure = competeure;
	}


	public long getArgent() {
		return argent;
	}

	public String getCompetitionNom() {
		return competitionNom;
	}

	public String getcompeteure() {
		return competeure;
	}



}
