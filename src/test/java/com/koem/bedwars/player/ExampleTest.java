package com.koem.bedwars.player;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

import com.koem.bedwars.BedWars;
import com.koem.bedwars.player.TeamManager.TEAM;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.*;

class ExampleTest {
	
	@Test
	@DisplayName("An example on how to use JUnit testing framework.")
	void duplicateTest() {
	    PlayerManager manager = new PlayerManager(null);
	    Player player = mock(Player.class);
	    manager.createPlayer(player);
	    assertFalse(manager.getBWPlayers().isEmpty());
	    manager.createPlayer(player);
	    manager.removePlayer(player);
	    assertTrue(manager.getBWPlayers().isEmpty());
	}

	/* Zajme sie tym pozniej
	@Test
	@DisplayName("This test is supposed to fail.")
	void multipleTeamsTest() {
	    BedWars plugin = mock(BedWars.class);
	    plugin.onEnable();
	    Player player = mock(Player.class);
	    plugin.getPlayerManager().createPlayer(player);
	    plugin.getPlayerManager().getBWPlayer(player).setTeam(TEAM.RED);
	    assertTrue(plugin.getTeamManager().getTeam(TEAM.RED).getTeamPlayers() == 1);
	    assertTrue(plugin.getTeamManager().getTeam(TEAM.GREEN).getTeamPlayers() == 0);
	    plugin.getPlayerManager().getBWPlayer(player).setTeam(TEAM.GREEN);
		assertTrue(plugin.getTeamManager().getTeam(TEAM.RED).getTeamPlayers() == 0);
		assertTrue(plugin.getTeamManager().getTeam(TEAM.GREEN).getTeamPlayers() == 1);		
	}
	*/
}