package be.seeseemelk.mockbukkit.scoreboard;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScoreboardMockTest
{
	private ScoreboardMock scoreboard;

	@BeforeEach
	public void setUp() throws Exception
	{
		scoreboard = new ScoreboardMock();
	}

	@Test
	public void registerObjective_DummyObjective_ObjectiveRegistered()
	{
		Objective objective = scoreboard.registerNewObjective("My objective", "dummy");
		assertNotNull(objective);
		assertSame(objective, scoreboard.getObjective("My objective"));
	}

	@Test
	public void getObjectivesByCriteria_TwoDifferentObjectives_ReturnsOne()
	{
		Objective objective = scoreboard.registerNewObjective("Correct", "dummy");
		scoreboard.registerNewObjective("Incorrect", "player_kills");

		Set<Objective> objectives = scoreboard.getObjectivesByCriteria("dummy");
		assertEquals(1, objectives.size());
		assertSame(objective, objectives.iterator().next());
	}

	@Test
	public void getObjectives_TwoObjectives_ReturnsTwo()
	{
		Objective objective1 = scoreboard.registerNewObjective("One", "dummy");
		Objective objective2 = scoreboard.registerNewObjective("Two", "dummy");

		Set<Objective> objectives = scoreboard.getObjectives();

		assertEquals(2, objectives.size(), "getObjectives() did not return 2 objectives");
		assertTrue(objectives.contains(objective1));
		assertTrue(objectives.contains(objective2));
	}

	@Test
	public void getObjective_EmptyDisplaySlot_ReturnsNull()
	{
		scoreboard.registerNewObjective("Objective", "dummy");
		assertNull(scoreboard.getObjective(DisplaySlot.BELOW_NAME));
		assertNull(scoreboard.getObjective(DisplaySlot.PLAYER_LIST));
		assertNull(scoreboard.getObjective(DisplaySlot.SIDEBAR));
	}

	@Test
	public void getObjective_ObjectiveInDisplaySlot_ReturnsObjective()
	{
		ObjectiveMock objective = scoreboard.registerNewObjective("Objective", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		assertNull(scoreboard.getObjective(DisplaySlot.BELOW_NAME));
		assertNull(scoreboard.getObjective(DisplaySlot.PLAYER_LIST));
		assertSame(objective, scoreboard.getObjective(DisplaySlot.SIDEBAR));
	}

}





















