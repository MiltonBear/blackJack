/**
 * 
 */
package ca.csf.dfc.classe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author administrateur
 *
 */
public class Paquets {
	private int m_NbCartesTotal;
	private int m_NbPaquets;
	private List<Carte> m_CardListCardDeck;

	public int getNbCarte() {
		return this.m_NbCartesTotal;
	}

	public int getNbPaquets() {
		return this.m_NbPaquets;
	}

	public List<Carte> getCardListFromDeck() {
		return this.m_CardListCardDeck;
	}

	public Paquets(int p_NbPaquets) {
		// this.m_PaquetDeCarte=new ArrayList<Carte>();
		this.m_NbPaquets = p_NbPaquets;
		this.m_CardListCardDeck = new ArrayList<Carte>();
		int compteur = 0;
		while (compteur < p_NbPaquets) {
			int compteurAtout = 0;
			while (compteurAtout < Carte.CHOIX_ATOUT.length) {

				int compteurFace = 0;
				while (compteurFace < Carte.CHOIX_FACE.length) {
					int valeur = compteurFace + 1;
					if (valeur > 10) {
						valeur = 10;
					}
					Carte c = new Carte(Carte.CHOIX_FACE[compteurFace], Carte.CHOIX_ATOUT[compteurAtout], valeur);
					this.m_CardListCardDeck.add(c);
					// this.m_PaquetDeCarte[(compteur * 52 )+( compteurAtout *
					// Carte.CHOIX_FACE.length) +( compteurFace)] = c;
					compteurFace++;
				}
				compteurAtout++;

			}
			compteur++;
		}
	}

	public void brasserCartes() {

		// this.m_Deck=new ArrayList<Carte>();
		for (int i = 0; i < this.m_CardListCardDeck.size(); i++) {
			Random rand = new Random();
			int random = rand.nextInt(this.m_CardListCardDeck.size());
			Carte carteTemporaire = this.m_CardListCardDeck.get(random);
			this.m_CardListCardDeck.remove(random);
			// this.m_Deck.add(i, this.m_Deck.get(random));
			// this.m_Deck.remove(random);
			this.m_CardListCardDeck.add(i, carteTemporaire);
			// System.out.println(this.m_Deck);
		}

	}


}
