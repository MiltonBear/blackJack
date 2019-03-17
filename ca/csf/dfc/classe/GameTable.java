/**
 * 
 */
package ca.csf.dfc.classe;

import java.util.*;

public class GameTable {
	public List<Hand> m_handListToBeDealthWith;
	public Paquets m_Deck;
	public Dealer m_Dealer;
	//public int m_NbPlayers;
	public PlayerList playerList;
	private boolean stillCanPlay = true;
	public String message = "";
	public int m_NombreDeJoueur = 2;

	public void canKeepPlaying() {

		this.stillCanPlay = true;
	}

	public boolean getStillCanPlay() {
		return this.stillCanPlay;
	}

	public void doo(String p_Action, int p_PlayerIndice) {
		switch (p_Action) {
		case "draw":
			this.m_handListToBeDealthWith.get(p_PlayerIndice).m_FiveCardList.add(this.drawCard());
			// this.playerList.m_PlayersList.get(p_PlayerIndice).getHand().m_FiveCardList.add(this.drawCard());
			break;
		case "stop":
			this.playerList.m_PlayersList.get(p_PlayerIndice).getHand().continuer = false;
			break;
		case "split":

			break;

		default:
			break;
		}
	}

	public Carte drawCard() {
		Carte c = this.m_Deck.getCardListFromDeck().get(0);
		this.m_Deck.getCardListFromDeck().remove(0);
		return c;
	}

	public GameTable(int p_NbPlayer) {
		this.m_Deck = new Paquets(1);
		this.m_Deck.brasserCartes();
		this.m_Deck.brasserCartes();
		this.m_Dealer = new Dealer();
		this.playerList = new PlayerList();
		this.m_handListToBeDealthWith = new ArrayList<Hand>();
		for (int i = 0; i < m_NombreDeJoueur; i++) {
			Player p = new Player(Player.NOM_DE_JOUEUR[i]);
			this.playerList.addPlayer(p);
		}
		for (int i = 0; i < this.m_NombreDeJoueur; i++) {
			this.m_handListToBeDealthWith.add(this.playerList.m_PlayersList.get(i).getHand());
		}
		this.m_handListToBeDealthWith.add(this.m_Dealer.getHand());

		//players bet
		for (int i = 0; i <this.m_NombreDeJoueur; i++) {
			System.out.println(this.playerList.m_PlayersList.get(i).getName());
			System.out.println("place your bet: ");
			Scanner scanBet=new Scanner(System.in);
			this.playerList.m_PlayersList.get(i).getHand().betAssociatdToHand=scanBet.nextInt();
			this.playerList.m_PlayersList.get(i).withDrawFromBankRoll(this.playerList.m_PlayersList.get(i).getHand().betAssociatdToHand);
		}
		// deal initial
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < this.m_handListToBeDealthWith.size(); j++) {
				// doo("draw", j);
				// this.playerList.m_PlayersList.get(j).getHand().m_FiveCardList.add(this.drawCard());
				this.m_handListToBeDealthWith.get(j).m_FiveCardList.add(this.drawCard());
			}
		}
		this.m_Dealer.getHand().m_FiveCardList.get(0).setInVisible();
		Scanner input = new Scanner(System.in);
		int saisie = 0;
		for (int i = 0; i < this.m_NombreDeJoueur; i++) {
			System.out.println("");
			System.out.println(this.m_Dealer.getClass().getSimpleName());
		
			System.out.println(this.m_Dealer.getHand());
			System.out.println("___________________________________________");
			System.out.println(this.playerList.m_PlayersList.get(i).getName()+ " "+this.playerList.m_PlayersList.get(i).getBankRoll()+"$");
			System.out.println(this.playerList.m_PlayersList.get(i).getHand());
			do {
				System.out.println(this.playerList.m_PlayersList.get(i).getHand().playerAction());
				System.out.println("quel actions faire voulez vous");
				saisie = input.nextInt();
				doo(this.playerList.m_PlayersList.get(i).getHand().m_Commands.get(saisie), i);
				System.out.println(this.playerList.m_PlayersList.get(i).getHand());
			} while (this.playerList.m_PlayersList.get(i).getHand().canDrawCard()
					&& this.playerList.m_PlayersList.get(i).getHand().continuer);
		}
		this.m_Dealer.getHand().m_FiveCardList.get(0).setVisible();
		System.out.println(this.m_Dealer.getHand());
	}

}
