package siteParis;

import java.util.Arrays;
import java.util.List;

/**
 * @uml.dependency   supplier="siteParis.Gestionnaire"
 */
public class Competition {
	
	//Declairing necessary variables
	private String competitionNom;
	private DateFrancaise competitionDate;
	private String[] competiteursList;
	private long [] competiteursAmount ; 
	private long totalComptAmount ; 
	private String winnerCompetiteurs; 
	
	//Constructor to initialise the Competition object with the required attributes
	public Competition (String competitionNom, DateFrancaise competitionDate,String[]  competiteursList) {

		this.competitionNom = competitionNom;
		this.competitionDate = competitionDate;
		this.competiteursList = competiteursList;
		this.winnerCompetiteurs = null; 

		if (competiteursList != null) {
			this.competiteursAmount = new long [competiteursList.length];
		}


	}
	//Overriding the equal method to to compare nom 
	@Override
	public boolean equals(Object o){

		Competition c = (Competition) o ; 
		return this.competitionNom.equals(c.getNom()); 
	}
	
	//Setting the name for joueur
	public void setNom(String nom) {

		competitionNom = nom ; 
	}

	public void setWinner(String winnerCompetiteurs) {

		this.winnerCompetiteurs = winnerCompetiteurs ; 
	}

	public String getNom() {
		
		 return competitionNom ; 
	}
	
	public DateFrancaise getCompetitionDate() {
		
		 return competitionDate ; 
	}
	
	public String [] getCompetiteursList() {
		
		return competiteursList; 
		
	}
	
	public String getWinner() {
		
		 return winnerCompetiteurs ; 
	}
	
	//Method to adjust the money of each competiteurs
	public void competitorMoney(String competitiorName, long money){
	
		List<String> l = Arrays.asList(competiteursList); 
		
		  if (l.contains(competitiorName)) {
			int index = l.indexOf(competitiorName);
			competiteursAmount[index] += money ; 
		}
		
	}
	//Calculating the total competition money
	public void calcTotalComptAmount() {

		for(int i = 0 ; i < competiteursAmount.length ; i++) {

			totalComptAmount  += competiteursAmount[i];
		}

	}
	//Reset the total Amount
	public void resetCompAmount(){
		totalComptAmount  = 0 ; 
	}
	
	public long getTotalComptAmount() {
		return totalComptAmount;
		
	}
	
	public long  getIndAmount(String playerName) {
		long amount = 0;
		for(int i = 0 ; i < competiteursAmount.length ; i++) {
			if (competiteursList[i].equals(playerName)) {
				amount =  competiteursAmount[i];
			}
		
			
		}
		return amount ; 
		
	}
	
	public void solderCompetion(String vainqueur){
		setWinner(vainqueur);
	}
	

	 



	

	

}
