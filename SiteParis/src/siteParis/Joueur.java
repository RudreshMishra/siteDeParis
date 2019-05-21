package siteParis;

import java.util.ArrayList;

public class Joueur  {
	
	private String joueurNom;
	private String joueurPreNom;
	private String joueurPseudo;
	private long restantJeton;
	private long engagesJeton; 
	private long holdDebitedAmount;
	private ArrayList<Betting> bettings;
	private long winningAmount ; 

	
	public Joueur(String joueurNom, String joueurPreNom, String joueurPseudo ) {
		this.joueurNom = joueurNom;
		this.joueurPreNom = joueurPreNom;
		this.joueurPseudo = joueurPseudo;
		this.bettings = new ArrayList<Betting>(); 
		this.winningAmount = 0; 
	}
	
	
	public long getRestantJeton() {
		return restantJeton;
	}

	public void setRestantJeton(long restantJeton) {

		this.restantJeton += restantJeton;
	}
	
	@Override
	public boolean equals(Object o) {

		Joueur j = (Joueur) o ; 
		return this.joueurNom.equals(j.joueurNom) && this.joueurPreNom.equals(j.joueurPreNom);
		
	}

    public void setWinningAmount(long winningAmount) {
    	
    	this.restantJeton += winningAmount ; 
    	
    }
    
	public void setEngagesJeton(long engagesJeton) {
		this.engagesJeton += engagesJeton;
		restantJeton -= engagesJeton;
		
	}
	
	public void updateEngageJeton(long amount) {
		
		this.engagesJeton -= amount;
		
	}
	
	public long getEngagesJeton() {
		return engagesJeton;
	}
	
	public void setdebitJetons(long sommeEnJetons) {
		long holdInitialAmount = restantJeton;
		holdInitialAmount -= sommeEnJetons;
		holdDebitedAmount = holdInitialAmount;
		
	}
	public long getdebitJetons() {
		return holdDebitedAmount;
	}


	public String getJoueurNom() {
		return joueurNom;
	}

	public void setJoueurNom(String joueurNom) {
		this.joueurNom = joueurNom;
	}

	public String getJoueurPreNom() {
		return joueurPreNom;
	}

	public void setJoueurPreNom(String joueurPreNom) {
		this.joueurPreNom = joueurPreNom;
	}

	public String getJoueurPseudo() {
		return joueurPseudo;
	}

	public void setJoueurPseudo(String joueurPseudo) {
		this.joueurPseudo = joueurPseudo;
	}


	public void setBettings(Betting bettings) {
		this.bettings.add(bettings);
	}


	/**
	 * @uml.property  name="betting"
	 * @uml.associationEnd  multiplicity="(0 -1)" dimension="1" ordering="true" inverse="joueur:siteParis.Betting"
	 * @uml.association  name="bets on"
	 */
	
	
	public void  placebet(String competitionNom, long argent, String competeure)
	{	
		
		bettings.add(new Betting(competitionNom, argent, competeure));
		setEngagesJeton(argent);
	}

	/**
	 * Getter of the property <tt>betting</tt>
	 * @return  Returns the bettings.
	 * @uml.property  name="betting"
	 */
	public ArrayList<Betting> getBetting() {
		return bettings;
	}




	
	

}
