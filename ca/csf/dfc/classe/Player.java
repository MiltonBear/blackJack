/**
 * 
 */
package ca.csf.dfc.classe;

/**
 * @author administrateur
 *
 */
public class Player {
	public static String[] NOM_DE_JOUEUR = { "Hugo", "Yannick", "Paspy", "Pablo", "MiltonBear", "Mitaine" };
	private int m_BankRoll;

	private String m_Name;
	private Hand m_PlayersHand;

	public Hand getHand()
	{
		return this.m_PlayersHand;
	}
	public void withDrawFromBankRoll(int p_Cash) {
		this.m_BankRoll -= p_Cash;
	}
	public void addWinMoneyInBankRoll(int p_Cash)
	{
		this.m_BankRoll+=p_Cash;
	}

	public int getBankRoll() {
		return this.m_BankRoll;
	}

	public Player(String p_Name) {
		this.setName(p_Name);
		this.m_BankRoll = 500;
		this.m_PlayersHand=new Hand();

	}

	public String getName() {
		return m_Name;
	}

	public void setName(String m_Name) {
		this.m_Name = m_Name;
	}
	public String toString()
	{
	return this.getName();
	}
}
