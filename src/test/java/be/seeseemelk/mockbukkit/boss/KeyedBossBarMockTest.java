package be.seeseemelk.mockbukkit.boss;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;

import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.KeyedBossBar;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.google.common.collect.Iterators;

public class KeyedBossBarMockTest
{
	private ServerMock server;

	@BeforeEach
	public void setUp()
	{
		server = MockBukkit.mock();
	}

	@AfterEach
	public void tearDown()
	{
		MockBukkit.unmock();
	}

	@Test
	@SuppressWarnings("deprecation")
	public void testKeyedBossBar()
	{
		KeyedBossBar bar = server.createBossBar(new NamespacedKey("mockbukkittest", "bossbar1"), "Boss bar 1",
		                                        BarColor.WHITE, BarStyle.SEGMENTED_10);
		assertNotNull(bar);

		// Make sure it initalized correctly
		assertEquals("Boss bar 1", bar.getTitle());
		assertEquals(BarColor.WHITE, bar.getColor());
		assertEquals(BarStyle.SEGMENTED_10, bar.getStyle());
		assertEquals(1.0, bar.getProgress(), 0);

		assertEquals(1, Iterators.size(server.getBossBars()));

		assertEquals(bar, server.getBossBar(new NamespacedKey("mockbukkittest", "bossbar1")));
		assertEquals(bar, server.getBossBars().next());

		assertTrue(server.removeBossBar(new NamespacedKey("mockbukkittest", "bossbar1")));
		assertEquals(0, Iterators.size(server.getBossBars()));

		assertFalse(server.removeBossBar(new NamespacedKey("mockbukkittest", "nonexistantbossbar")));
	}

	@Test
	public void testNullKey()
	{

		assertThrows(IllegalArgumentException.class, () -> server.createBossBar(null, "Boss bar 1", BarColor.WHITE, BarStyle.SEGMENTED_10));
	}
}
