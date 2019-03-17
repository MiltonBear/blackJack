/**
 * 
 */
package ca.csf.dfc.classe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author administrateur
 *
 */
public class PlayerList {
	public List<Player> m_PlayersList;
	public PlayerList()
	{
		this.m_PlayersList=new ArrayList<Player>();
	}
	
	public void addPlayer(Player p_Player)
	{
		this.m_PlayersList.add(p_Player);
	}

}
