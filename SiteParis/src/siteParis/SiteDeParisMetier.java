package siteParis;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * 
 * @author Bernard Prou et Julien Mallet
 * <br><br>
 * La classe qui contient toutes les méthodes "Métier" de la gestion du site de paris. 
 * <br><br>
 * Dans toutes les méthodes :
 * <ul>
 * <li>un paramètre de type <code>String</code> est invalide si il n'est pas instancié.</li>
 *  <li>pour la validité d'un password de gestionnaire et d'un password de joueur :
 * <ul>
 * <li>       lettres et chiffres sont les seuls caractères autorisés </li>
 * <li>       il doit avoir une longueur d'au moins 8 caractères </li>
 * </ul></li>       
 * <li>pour la validité d'un pseudo de joueur  :
 * <ul>
 * <li>        lettres et chiffres sont les seuls caractères autorisés  </li>
 * <li>       il doit avoir une longueur d'au moins 4 caractères</li>
 * </ul></li>       
 * <li>pour la validité d'un prénom de joueur et d'un nom de joueur :
 *  <ul>
 *  <li>       lettres et tiret sont les seuls caractères autorisés  </li>
 *  <li>      il  doit avoir une longueur d'au moins 1 caractère </li>
 * </ul></li>
 * <li>pour la validité d'une compétition  :       
 *  <ul>
 *  <li>       lettres, chiffres, point, trait d'union et souligné sont les seuls caractères autorisés </li>
 *  <li>      elle  doit avoir une longueur d'au moins 4 caractères</li>
 * </ul></li>       
 * <li>pour la validité d'un compétiteur  :       
 *  <ul>
 *  <li>       lettres, chiffres, trait d'union et souligné sont les seuls caractères autorisés </li>
 *  <li>      il doit avoir une longueur d'au moins 4 caractères.</li>
 * </ul></li></ul>
 */

public class SiteDeParisMetier  {


	private LinkedList <Competition> comptList ; 
	private  LinkedList <Joueur> joueurList; 
	private String  passwordGestionnaire;


	/**
	 * constructeur de <code>SiteDeParisMetier</code>. 
	 * 
	 * @param passwordGestionnaire   le password du gestionnaire.   
	 * 
	 * @throws MetierException  levée 
	 * si le <code>passwordGestionnaire</code>  est invalide 
	 */

	public SiteDeParisMetier(String passwordGestionnaire) throws MetierException {

		/* Initialising the appropriate LinkedList to hold the objects of Competition and Joueur
		 * 
		 * */
		comptList = new LinkedList<Competition> ();
		joueurList =  new LinkedList<Joueur> ();

		//Assigning the admin password passed as parameter
		this.passwordGestionnaire = passwordGestionnaire;
		
		if(!validatePasword(passwordGestionnaire))throw new  MetierException () ; 
     
	}

	// Les méthodes du gestionnaire (avec mot de passe gestionnaire)


	/**
	 * inscrire un joueur. 
	 * 
	 * @param nom   le nom du joueur 
	 * @param prenom   le prénom du joueur   
	 * @param pseudo   le pseudo du joueur  
	 * @param passwordGestionnaire  le password du gestionnaire du site  
	 * 
	 * @throws MetierException   levée  
	 * si le <code>passwordGestionnaire</code> proposé est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * @throws JoueurExistantException   levée si un joueur existe avec les mêmes noms et prénoms ou le même pseudo.
	 * @throws JoueurException levée si <code>nom</code>, <code>prenom</code>, <code>pseudo</code> sont invalides.
	 * it mac=tches;;;;");
		}
	 * @return le mot de passe (déterminé par le site) du nouveau joueur inscrit.
	 */
	public String inscrireJoueur(String nom, String prenom, String pseudo, String passwordGestionnaire) throws MetierException, JoueurExistantException, JoueurException {

		//Checking for the exceptions calls passes from the test class


		
		if(!validatePasword(passwordGestionnaire))throw new  MetierException () ; 

		if(!validatejoueur(nom, prenom, pseudo) )throw new  JoueurException () ; 


		//Checking whether the jouerList has joueur objects and if there is check the joueur with nom and prenom
		if (joueurList.size() != 0)
		{

			for (Joueur j : joueurList  ) {

				if (j.getJoueurNom().equals(nom) && j.getJoueurPreNom().equals(prenom)) throw new JoueurExistantException();
				if (j.getJoueurPseudo().equals(pseudo)) throw new JoueurExistantException();				
			}
		}

		//Checking that nom should be equal to the prenom 
		if (nom.equals(prenom)) throw new JoueurExistantException();


		//Al data is valid, create in intance of joueur and add to the LinkedList joueurList
		Joueur joueur = new Joueur( nom, prenom, pseudo);
		joueurList.add(joueur); 



		return "unPasswordUnique";
	}

	
	public boolean validatePasword(String passwordGestionnaire) {
		
		boolean result = true;  
		if (passwordGestionnaire == null) result = false ; 
		else {
			if (!passwordGestionnaire.matches("[0-9A-Za-z]{8,}")) result = false ; 
	
			if (!passwordGestionnaire.equals(this.passwordGestionnaire)) result = false ; 
		}
		
		return result ; 
	}

	
	public boolean validatejoueur(String nom, String prenom, String pseudo) {
		
		boolean result = true;  
		String chars = "\\s+";
		if (nom == null || prenom == null || pseudo == null) result = false ; 
		else {
			if (prenom.matches(chars) )result = false ; 

			if (!nom.matches("[A-Za-z0-9]+")) result = false ; 

			if (!pseudo.matches("[0-9A-Za-z]{4,}")) result = false ; 

		}
		
		return result ; 
	}
	/**
	 * supprimer un joueur. 
	 * 
	 * @param nom   le nom du joueur 
	 * @param prenom   le prénom du joueur   
	 * @param pseudo   le pseudo du joueur  
	 * @param passwordGestionnaire  le password du gestionnaire du site  
	 * 
	 * @throws MetierException
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * @throws JoueurInexistantException   levée si il n'y a pas de joueur  avec le même <code>nom</code>, <code>prenom</code>  et <code>pseudo</code>.
	 * @throws JoueurException levée 
	 * si le joueur a des paris en cours,
	 * si <code>nom</code>, <code>prenom</code>, <code>pseudo</code> sont invalides.
	 * 
	 * @return le nombre de jetons à rembourser  au joueur qui vient d'être désinscrit.
	 * 
	 */
	public long desinscrireJoueur(String nom, String prenom, String pseudo, String passwordGestionnaire) throws MetierException, JoueurInexistantException, JoueurException {

		//Creating an object with the passes parameters to match in the List of Joueur objects
		Joueur otherJoueur = new Joueur(nom, prenom, pseudo);
	   
		if(!validatePasword(passwordGestionnaire))throw new  MetierException () ; 

		//Checking if the joueur list contains the object with the parameter nom and prenom
		if (!joueurList.contains(otherJoueur))throw new JoueurInexistantException(); 
	    Joueur actualJoueur =	joueurList.get(joueurList.indexOf(otherJoueur)); 
	    
	    if(actualJoueur.getBetting().size() > 0) throw new JoueurException();
		//Remove the specified joueur object
		
		long restonJetons = actualJoueur.getRestantJeton();
		joueurList.remove(otherJoueur);

		return  restonJetons;
	}



	/**
	 * ajouter une compétition.  
	 * 
	 * @param competition le nom de la compétition
	 * @param dateCloture   la date à partir de laquelle il ne sera plus possible de miser  
	 * @param competiteurs   les noms des différents compétiteurs de la compétition 
	 * @param passwordGestionnaire  le password du gestionnaire du site 
	 * 
	 * @throws MetierException levée si le tableau des
	 * compétiteurs n'est pas instancié, si le
	 * <code>passwordGestionnaire</code> est invalide, si le
	 * <code>passwordGestionnaire</code> est incorrect.
	 * @throws CompetitionExistanteException levée si une compétition existe avec le même nom. 
	 * @throws CompetitionException levée si le nom de la
	 * compétition ou des compétiteurs sont invalides, si il y a
	 * moins de 2 compétiteurs, si un des competiteurs n'est pas instancié,
	 * si deux compétiteurs ont le même nom, si la date de clôture 
	 * n'est pas instanciée ou est dépassée.
	 */

	public void ajouterCompetition(String competition, DateFrancaise dateCloture, String [] competiteurs, String passwordGestionnaire) throws MetierException, CompetitionExistanteException, CompetitionException  {
		//Variable to hold the name of the competiteurs from the array
		String competiteursNom;

		if (!validatePasword(passwordGestionnaire) || competiteurs == null ) throw new  MetierException ();

		//Validating the competition name and checking the test exceptions
		if (competition == null|| !competition.matches("[0-9A-Za-z]{4,}")|| dateCloture == null|| competiteurs.length < 2) throw new CompetitionException();

		for (int i = 0; i < competiteurs.length; i++) {
			competiteursNom = competiteurs[i];
			if (competiteurs[i]== null) {
				throw new CompetitionException();
			}
			else if(!competiteursNom.matches("[0-9A-Za-z]{4,}")) {
				throw new CompetitionException();
			}
		}
		if (competiteurs[0] == competiteurs[1]) throw new CompetitionException();
		if (DateFrancaise.dateSimulee.compareTo(dateCloture)>0) throw new CompetitionException();


		for (Competition j : comptList) {
			if (j.getNom().equals(competition)) throw new CompetitionExistanteException();
		}

		//Creating the competion object and adding it to the compList
		Competition competitionObj = new Competition (competition, dateCloture ,competiteurs); 
		comptList.add(competitionObj);

	}






	/**
	 * solder une compétition vainqueour (compétition avec vainqueur).  
	 * 
	 * Chaque joueur ayant misé sur cette compétition
	 * en choisissant ce compétiteur est crédité d'un nombre de
	 * jetons égal à :
	 * e closing date of the competition. </ Li>
	 * </ ul>
	 * (le montant de sa mise * la somme des
	 * jetons misés pour cette compétition) / la somme des jetons
	 * misés sur ce compétiteur.o
	 *
	 * Si aucun joueur n'a trouvé le
	 * bon compétiteur, des jetons sont crédités aux joueurs ayant
	 * misé sur cette compétition (conformément au montant de
	 * leurs mises). La compétition est "supprimée" si il ne reste
	 * plus de mises suite à ce solde.
	 * 
	 * @param competition   le nom de la compétition  
	 * @param vainqueur   le nom du vainqueur de la compétition 
	 * @param passwordGestionnaire  le password du gestionnaire du site 
	 * 
	 * @throws MetierException   levée 
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * @throws CompetitionInexistanteException   levée si il n'existe pas de compétition de même nom.
	 * @throws CompetitionException levée 
	 * si le nom de la compétition ou du vainqueur est invalide, 
	 * si il n'existe pas de compétiteur du nom du vainqueur dans la compétition,
	 * si la date de clôture de la compétition est dans le futur.
	 * 
	 */	

	public void solderVainqueur(String competition, String vainqueur, String passwordGestionnaire) throws MetierException,  CompetitionInexistanteException, CompetitionException  {

		Competition comp = null; 
		ArrayList<String> str = new ArrayList<String>();

		/*Checking the manager password, competition dates and the vainquer is present in the list
		 * 
		 * */
		if(!validatePasword(passwordGestionnaire))throw new  MetierException () ; 


		else {
			for (Competition c : comptList  ) {
				str.add(c.getNom());
				if (c.getNom().equals(competition)) {
					if (DateFrancaise.dateSimulee.compareTo(c.getCompetitionDate())<0) throw new CompetitionException();
					else if (!Arrays.asList(c.getCompetiteursList()).contains(vainqueur))throw new CompetitionException();
				}	
			}//end of for
			if (!str.contains(competition))throw new CompetitionInexistanteException();

		}
		//Looping the Competition list and closing the required competition
		for (int i = 0 ; i < comptList.size(); i++) {
			Competition c = comptList.get(i);
			if (c.getNom().equals(competition)) {
				c.solderCompetion(vainqueur);
				comp = c ; 
				comptList.remove(c);
			}
		}
		//Looking for the winner and setting his amount accordingly and passing the winning amount to the joueur
		calcWinningAmount(competition, vainqueur, comp);
	
		
		


	}
	public void calcWinningAmount(String competition, String vainqueur, Competition comp) {
		
		boolean winner = false ; 
		
		for (Joueur j : joueurList) {
			ArrayList <Betting> jBetting = j.getBetting();

			if (jBetting.size() != 0 ) {
				for (Betting b : jBetting) {
					long betAmount  = 0;
					if((b.getCompetitionNom().equals(competition) && (b.getcompeteure().equals(vainqueur)))) {
						winner = true ;	
						//Calculating the total competition amount
						comp.calcTotalComptAmount();

						betAmount = b.getArgent();
						//Calculating the winAmount according to the system specification 
						long winAmount = (betAmount * comp.getTotalComptAmount()/comp.getIndAmount(vainqueur));

						j.setWinningAmount(winAmount);

						//resetting the total competition amount
						comp.resetCompAmount();

					}
					//Update the engage jeton after closing the competition
					if(b.getCompetitionNom().equals(competition)  ) {
						betAmount = b.getArgent();

						j.updateEngageJeton(betAmount);

					}

				}
			}
		}//end of for loop
		//Adjusting the amount for joueur who loses the competition 
		if (winner == false) {
			for (Joueur j : joueurList) {
				ArrayList <Betting> jBetting = j.getBetting();

				if (jBetting.size() != 0 ) {
					for (Betting b : jBetting) {
						if((b.getCompetitionNom().equals(competition) )) {

							j.setRestantJeton(b.getArgent());


						}

					}
				}
			}
		}

		
	}
	/**
	 * créditer le compte en jetons d'un joueur du site de paris.
	 * 
	 * @param nom   le nom du joueur 
	 * @param prenom   le prénom du joueur   
	 * @param pseudo   le pseudo du joueur  
	 * @param sommeEnJetons   la somme en jetons à créditer  
	 * @param passwordGestionnaire  le password du gestionnaire du site  
	 * 
	 * @throws MetierException   levée 
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect,
	 * si la somme en jetons est négative.
	 * @throws JoueurException levée  
	 * si <code>nom</code>, <code>prenom</code>,  <code>pseudo</code> sont invalides.
	 * @throws JoueurInexistantException   levée si il n'y a pas de joueur  avec les mêmes nom,  prénom et pseudo.
	 */
	public void crediterJoueur(String nom, String prenom, String pseudo, long sommeEnJetons, String passwordGestionnaire) throws MetierException, JoueurException, JoueurInexistantException {
		/*Credit the amount to the joueur*/
		if(!validatePasword(passwordGestionnaire))throw new  MetierException () ;
		if(!validatejoueur(nom, prenom, pseudo) )throw new  JoueurException () ;
		if(sommeEnJetons < 0 )throw new  MetierException () ;
		
		Joueur j = joueurListDetails(nom, prenom,pseudo,1);
		if (j != null) {
			if (sommeEnJetons == 0) throw new JoueurException();
			j.setRestantJeton(sommeEnJetons);	
		}
		else throw new JoueurInexistantException();
		
	}
	//General method to loop through the objects of joueur in the ArrayList of joueur and check different 
	//validations by comparing the joueur details.
	public Joueur joueurListDetails(String nom, String prenom, String pseudo, int validationCase) {
		
		Joueur jObject = null; 
		
		for (int i = 0 ; i < joueurList.size(); i++) {
			Joueur j = joueurList.get(i);

			if (validationCase == 1 && j.getJoueurNom().equals(nom) && j.getJoueurPreNom().equals(prenom) && j.getJoueurPseudo().equals(pseudo)) {

				jObject = j; 
				
			}

			if (validationCase == 2 && j.getJoueurPseudo().equals(pseudo)) {

				jObject = j; 
			}
			
		}//end of for
		return jObject ; 
		
		
	}
	/**
	 * débiter le compte en jetons d'un joueur du site de paris.
	 * 
	 * @param nom   le nom du joueur 
	 * @param prenom   le prénom du joueur   
	 * @param pseudo   le pseudo du joueur  
	 * @param sommeEnJetons   la somme en jetons à débiter  
	 * @param passwordGestionnaire  le password du gestionnaire du site  
	 * 
	 * @throws MetierException   levée 
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect,
	 * si la somme en jetons est négative.
	 * @throws JoueurException levée  
	 * si <code>nom</code>, <code>prenom</code>,  <code>pseudo</code> sont invalides 
	 * si le compte en jetons du joueur est insuffisant (il deviendrait négatif).
	 * @throws JoueurInexistantException   levée si il n'y a pas de joueur  avec les mêmes nom,  prénom et pseudo.
	 * 
	 */

	public void debiterJoueur(String nom, String prenom, String pseudo, long sommeEnJetons, String passwordGestionnaire) throws  MetierException, JoueurInexistantException, JoueurException {

		//Checking for valid password and debiting thje amount from the joueur
		if(!validatePasword(passwordGestionnaire)) throw new MetierException ();
		if(!validatejoueur(nom, prenom, pseudo) )throw new  JoueurException () ;
		if(sommeEnJetons < 0) throw new MetierException ();
		Joueur j = joueurListDetails(nom, prenom,pseudo,1);
		if (j != null) {
			//Calling method on joueur object to debit the amount passed as parameter
			if (j.getRestantJeton() <  sommeEnJetons) throw new JoueurException () ;
			
			j.setdebitJetons(sommeEnJetons);

			if(j.getdebitJetons()<=-1) throw new JoueurException();

		}
		else throw new JoueurInexistantException();
	}



	/** 
	 * consulter les  joueurs.
	 * 
	 * @param passwordGestionnaire  le password du gestionnaire du site de paris 

	 * @throws MetierException   levée  
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * 
	 * @return une liste de liste dont les éléments (de type <code>String</code>) représentent un joueur avec dans l'ordre : 
	 *  <ul>
	 *  <li>       le nom du joueur  </li>
	 *  <li>       le prénom du joueur </li>
	 *  <li>       le pseudo du joueur  </li>
	 *  <li>       son compte en jetons restant disponibles </li>
	 *  <li>       le total de jetons engagés dans ses mises en cours. </li>
	 *  </ul>
	 */
	public LinkedList <LinkedList <String>> consulterJoueurs(String passwordGestionnaire) throws MetierException {

		if(!validatePasword(passwordGestionnaire)) throw new MetierException ();

		LinkedList <LinkedList <String>> tempList = new LinkedList <LinkedList <String>>() ; 
		//Creating a linkedlist of joueur details as String objects and passed to the test call in the test class; 
		for (Joueur joueur: joueurList) { 

			LinkedList <String> elements = new LinkedList<String>();

			elements.add(joueur.getJoueurNom());
			elements.add(joueur.getJoueurPreNom());
			elements.add(joueur.getJoueurPseudo());
			elements.add(String.valueOf(joueur.getRestantJeton()));
			elements.add(String.valueOf(joueur.getEngagesJeton()));

			tempList.add(elements);

		}

		return tempList ;
	}
	// Les méthodes avec mot de passe utilisateur
	/**
	 * miserVainqueur  (parier sur une compétition, en désignant un vainqueur).
	 * Le compte du joueur est débité du nombre de jetons misés.
	 * 
	 * @param pseudo   le pseudo du joueur  
	 * @param passwordJoueur  le password du joueur  
	 * @param miseEnJetons   la somme en jetons à miser  
	 * @param competition   le nom de la compétition  relative au pari effectué
	 * @param vainqueurEnvisage   le nom du compétiteur  sur lequel le joueur mise comme étant le  vainqueur de la compétition  
	 * 
	 * @throws MetierException levée si la somme en jetons est négative.
	 * @throws JoueurInexistantException levée si il n'y a pas de
	 * joueur avec les mêmes pseudos et password.
	 * @throws

 CompetitionInexistanteException   levée si il n'existe pas de compétition de même nom. 
	 * @throws CompetitionException levée 
	 * si <code>competition</code> ou <code>vainqueurEnvisage</code> sont invalides,
	 * si il n'existe pas un compétiteur de  nom <code>vainqueurEnvisage</code> dans la compétition,
	 * si la compétition n'est plus ouverte (la date de clôture est dans le passé).
	 * @throws JoueurException   levée 
	 * si <code>pseudo</code> ou <code>password</code> sont invalides, 
	 * si le <code>compteEnJetons</code> du joueur est insuffisant (il deviendrait négatif).
	 */
	public void miserVainqueur(String pseudo, String passwordJoueur, long miseEnJetons, String competition, String vainqueurEnvisage) throws MetierException, JoueurInexistantException, CompetitionInexistanteException, CompetitionException, JoueurException  {

		if(pseudo == null )throw new  JoueurException () ;
		
		if (!pseudo.matches("[0-9A-Za-z]{4,}")) throw new  JoueurException () ;
		
		
		Joueur j = joueurListDetails("","",pseudo,2);
		
		if (j == null)throw new JoueurInexistantException(); 
		
		if (passwordJoueur == null) throw new JoueurException(); 
		
		if (!passwordJoueur.equals("unPasswordUnique")) throw new JoueurException(); 
		//Checking for the user password and pseudo and then placing the bet
		
		if (j.getRestantJeton() < miseEnJetons) throw new JoueurException(); 
		
		Competition comp = new Competition (competition, null, null ); 
		
		
		if (!comptList.contains(comp)) throw  new CompetitionInexistanteException(); 
		
		Competition compObject = comptList.get(comptList.indexOf(comp));
			
		if (DateFrancaise.dateSimulee.compareTo(compObject.getCompetitionDate())>0) throw new CompetitionException();
	
		j.placebet(competition, miseEnJetons, vainqueurEnvisage);
	
		
		
		
		
		//Adjusting the competituer money account to the competition for e.g Lyon 40, Paris 80
		for (Competition c : comptList) {
			if (c.getNom().equals(competition)) {
				List<String> list = Arrays.asList(c.getCompetiteursList());
				if (!list.contains(vainqueurEnvisage)) throw new CompetitionException(); // check for the competitor exist in the competition;
				else  c.competitorMoney(vainqueurEnvisage,miseEnJetons);
			}

		}
	}

	// Les méthodes sans mot de passe


	/**
	 * connaître les compétitions en cours.
	 * 
	 * @return une liste de liste dont les éléments (de type <code>String</code>) représentent une compétition avec dans l'ordre : 
	 *  <ul>
	 *  <li>       le nom de la compétition,  </li>
	 *  <li>       la date de clôture de la compétition. </li>
	 *  </ul>
	 */
	public LinkedList <LinkedList <String>> consulterCompetitions(){

		LinkedList <LinkedList <String>> tempList = new LinkedList <LinkedList <String>>() ; 
		//Returing the list of Competition attributes as String objects in the LinkedList
		for (Competition comp: comptList) {
			LinkedList <String> elements = new LinkedList<String>();
			elements.add(comp.getNom());
			elements.add(String.valueOf(comp.getCompetitionDate()));
			tempList.add(elements);
		}

		return tempList ;
	} 



	/**
	 * connaître  la liste des noms des compétiteurs d'une compétition.  
	 * 
	 * @param competition   le nom de la compétition  
	 * 
	 * @throws CompetitionException   levée  
	 * si le nom de la compétition est invalide.
	 * @throws CompetitionInexistanteException   levée si il n'existe pas de compétition de même nom. 
	 * 
	 * @return la liste des compétiteurs de la  compétition.
	 */
	public LinkedList <String> consulterCompetiteurs(String competition) throws CompetitionException, CompetitionInexistanteException{

		//Checking for the exceptions from the test cases in the test class
		if (competition == null) throw  new CompetitionException(); 

		if (competition.length() < 4) throw  new CompetitionException(); 
		Competition c = new Competition (competition, null, null ); 

		if (!comptList.contains(c)) throw  new CompetitionInexistanteException(); 

		//Getting the specific Competition
		Competition c1 = comptList.get(comptList.indexOf(c));

		return new LinkedList<String>(Arrays.asList(c1.getCompetiteursList()));
	}

	/**
	 * vérifier la validité du password du gestionnaire.
	 * 
	 * @param passwordGestionnaire   le password du gestionnaire à vérifier
	 * 
	 * @throws MetierException   levée 
	 * si le <code>passwordGestionnaire</code> est invalide.  
	 */
	protected void validitePasswordGestionnaire(String passwordGestionnaire) throws MetierException {
		if(!validatePasword(passwordGestionnaire)) throw new MetierException ();
	}



}


