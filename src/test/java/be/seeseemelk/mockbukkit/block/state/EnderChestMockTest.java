package be.seeseemelk.mockbukkit.block.state;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.EnderChest;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.block.BlockMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnderChestMockTest
{

	@BeforeEach
	public void setUp() throws Exception
	{
		MockBukkit.mock();
	}

	@AfterEach
	public void tearDown() throws Exception
	{
		MockBukkit.unmock();
	}

	@Test
	public void testMaterialEnderChestBlockState()
	{
		Block block = new BlockMock(Material.ENDER_CHEST);
		assertTrue(block.getState() instanceof EnderChest);
	}

	@Test
	public void testMaterialEnderChestMockConstructor()
	{
		assertTrue(new EnderChestMock(Material.ENDER_CHEST) instanceof EnderChest);
	}

}
