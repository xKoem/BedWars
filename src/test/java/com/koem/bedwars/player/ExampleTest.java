package com.koem.bedwars.player;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

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
	
}