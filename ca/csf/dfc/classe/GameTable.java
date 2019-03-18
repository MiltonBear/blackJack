/**
 * 
 */
package ca.csf.dfc.classe;

import java.util.*;

public class GameTable {
	public List<Hand> m_handListToBeDealthWith;
	public Paquets m_Deck;
	public Dealer m_Dealer;
	public PlayerList playerList;
	private boolean stillCanPlay = true;
	public String message = "";
	public int m_NombreDeJoueur = 3;

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
		


		while (true) {
			// players bet
			for (int i = 0; i < this.m_NombreDeJoueur; i++) {
				System.out.println(this.playerList.m_PlayersList.get(i).getName());
				System.out.println("place your bet: ");
				Scanner scanBet = new Scanner(System.in);
				this.playerList.m_PlayersList.get(i).getHand().betAssociatdToHand = scanBet.nextInt();
				this.playerList.m_PlayersList.get(i)
						.withDrawFromBankRoll(this.playerList.m_PlayersList.get(i).getHand().betAssociatdToHand);
			}
			// deal initial
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < this.m_handListToBeDealthWith.size(); j++) {
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
				System.out.println(this.playerList.m_PlayersList.get(i).getName() + " "
						+ this.playerList.m_PlayersList.get(i).getBankRoll() + "$");
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
			System.out.println(this.m_Dealer.getClass().getSimpleName() + ": " + this.m_Dealer.getHand());

			while (this.m_Dealer.getHand().getHandValue() <= 17) {
				this.m_Dealer.getHand().m_FiveCardList.add(this.drawCard());
				System.out.println(this.m_Dealer.getClass().getSimpleName() + ": " + this.m_Dealer.getHand());
			}
			int dealersResult = this.m_Dealer.getHand().getHandValue();
			for (int i = 0; i < this.m_NombreDeJoueur; i++) {
				int playerResult = this.playerList.m_PlayersList.get(i).getHand().getHandValue();
				Player p = this.playerList.m_PlayersList.get(i);
				if (playerResult > 21) {
					System.out.println(p.getName() + "BUSTED!");
				} else {
					if (p.getHand().m_FiveCardList.size() == 2 && p.getHand().getHandValue() == 21) {
						if (this.m_Dealer.getHand().getHandValue() == 21
								&& this.m_Dealer.getHand().m_FiveCardList.size() == 2) {
							p.addWinMoneyInBankRoll(p.getHand().betAssociatdToHand * 1);
							System.out.println(p.getName() + "BLACKJACK_TIE!");
						} else {
							p.addWinMoneyInBankRoll(p.getHand().betAssociatdToHand * 3);
							System.out.println(p.getName() + "BLACKJACK!");
						}
					} else if (this.m_Dealer.getHand().getHandValue() <= 21) {
						if (p.getHand().getHandValue() > this.m_Dealer.getHand().getHandValue()) {
							System.out.println(p.getName() + "WIN!");
							p.addWinMoneyInBankRoll(p.getHand().betAssociatdToHand * 2);
						} else if (p.getHand().getHandValue() == this.m_Dealer.getHand().getHandValue()) {
							System.out.println(p.getName() + "TIE!");
							p.addWinMoneyInBankRoll(p.getHand().betAssociatdToHand * 1);
						} else {
							System.out.println(p.getName() + "LOSER");

						}

					} else if (this.m_Dealer.getHand().getHandValue() > 21) {
						System.out.println(p.getName() + "WIN!");
						p.addWinMoneyInBankRoll(p.getHand().betAssociatdToHand * 2);
					} else {
						System.out.println(p.getName() + "LOSER");

					}

				}
			}
			System.out.println(this.playerList.m_PlayersList.get(0).getBankRoll());
			System.out.println(this.playerList.m_PlayersList.get(0).getHand().getHandValue());
			System.out.println(this.playerList.m_PlayersList.get(1).getBankRoll());
			System.out.println(this.playerList.m_PlayersList.get(1).getHand().getHandValue());
			System.out.println(this.playerList.m_PlayersList.get(2).getBankRoll());
			System.out.println(this.playerList.m_PlayersList.get(2).getHand().getHandValue());
			this.m_Dealer.getHand().m_FiveCardList.clear();
			for (int i = 0; i < this.m_NombreDeJoueur; i++) {
				this.playerList.m_PlayersList.get(i).getHand().m_FiveCardList.clear();
			}
			
		}

	}

}