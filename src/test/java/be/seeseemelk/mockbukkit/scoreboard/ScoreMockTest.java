package be.seeseemelk.mockbukkit.scoreboard;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;

public class ScoreMockTest
{
	private ServerMock server;
	private ScoreboardMock scoreboard;
	private ObjectiveMock objective;
	private ScoreMock score;

	@BeforeEach
	public void setUp()
	{
		server = MockBukkit.mock();
		scoreboard = server.getScoreboardManager().getNewScoreboard();
		objective = scoreboard.registerNewObjective("Objective", "dummy");
		score = objective.getScore("Entry");
	}

	@AfterEach
	public void tearDown()
	{
		MockBukkit.unmock();
	}

	@Test
	public void getEntry_ReturnsEntry()
	{
		assertEquals("Entry", score.getEntry());
	}

	@Test
	public void getObjective_ReturnsParentObjective()
	{
		assertSame(objective, score.getObjective());
	}

	@Test
	public void getScore_ObjectiveRegisteredButNoScoreSet_ReturnsZero()
	{
		assertEquals(0, score.getScore());
	}

	@Test
	public void getScore_ObjectiveUnregistered_ThrowsError()
	{
		assertThrows(IllegalStateException.class, () -> {
			objective.unregister();
			score.getScore();
		});
	}

	@Test
	public void getScore_ObjectiveRegisteredAndScoreSet_ReturnsNumber()
	{
		score.setScore(5);
		assertEquals(5, score.getScore());
	}

	@Test
	public void getPlayer_PlayerSet_ReturnsPlayer()
	{
		PlayerMock player = server.addPlayer();
		score.setPlayer(player);
		assertSame(player, score.getPlayer());
	}

	@Test
	public void isSet_NotSet_ReturnsFalse()
	{
		assertFalse(score.isScoreSet());
	}

	@Test
	public void isSet_Set_ReturnsTrue()
	{
		score.setScore(5);
		assertTrue(score.isScoreSet());
	}

	@Test
	public void getScoreboard_ReturnsScoreboard()
	{
		assertSame(scoreboard, score.getScoreboard());
	}

}
