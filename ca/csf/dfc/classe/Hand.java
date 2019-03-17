/**
 * 
 */
package ca.csf.dfc.classe;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	public List<Carte> m_FiveCardList;
	public List<String> m_Commands;
	private boolean m_FiveCardWin;
	private int m_HandValue;
	private int m_NbAs;
	public boolean continuer;
	private boolean canPlay;
	public int betAssociatdToHand;

	public List<String> playerAction() {
		this.m_Commands = new ArrayList<String>();
		this.m_Commands.add("stop");
		if (canDrawCard())
			this.m_Commands.add("draw");
		if (canSplitCard()) {
			this.m_Commands.add("split");
		}
		return this.m_Commands;
	}

	public int getNbAs() {
		for (int i = 0; i < this.m_FiveCardList.size(); i++) {
			if (this.m_FiveCardList.get(i).getFace() == "A") {
				this.m_NbAs++;
			}
		}
		return this.m_NbAs;
	}

	public Hand() {

		this.continuer = true;
		this.m_FiveCardList = new ArrayList<Carte>();
	}

	public String toString() {
		return this.m_FiveCardList.toString();
	}

	public int getHandValue() {
		this.setHandValue(0);
		for (int i = 0; i < this.m_FiveCardList.size(); i++) {
			this.m_HandValue += this.m_FiveCardList.get(i).getCarteValue();
		}
		int nbAs = this.getNbAs();
		while (nbAs != 0) {
			if (this.m_HandValue + 10 <= 21)
				this.m_HandValue += 10;
			nbAs--;
		}
		return this.m_HandValue;
	}

	public boolean isFiveCardWin() {
		return m_FiveCardWin;
	}

	public void setFiveCardWin(boolean m_FiveCardWin) {
		this.m_FiveCardWin = m_FiveCardWin;
	}

	public void setHandValue(int p_Value) {
		this.m_HandValue = p_Value;
	}

	public boolean canDrawCard() {
		if (this.getHandValue() < 21 && this.m_FiveCardList.size() < 5) {
			this.canPlay = true;
		} else {
			this.canPlay = false;
		}
		return this.canPlay;
	}

	public boolean canSplitCard() {
		if (this.m_FiveCardList.get(0).getFace() == this.m_FiveCardList.get(1).getFace()
				&& this.m_FiveCardList.size() == 2) {
			return true;
		} else
			return false;
	}

}
