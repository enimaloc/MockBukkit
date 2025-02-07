package be.seeseemelk.mockbukkit.block.state;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.projectiles.BlockProjectileSource;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.block.BlockMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DispenserMockTest
{

	private Dispenser dispenser;

	@BeforeEach
	public void setUp() throws Exception
	{
		MockBukkit.mock();
		dispenser = new DispenserMock(Material.DISPENSER);
	}

	@AfterEach
	public void tearDown() throws Exception
	{
		MockBukkit.unmock();
	}

	@Test
	public void testMaterialDispenserBlockState()
	{
		Block block = new BlockMock(Material.DISPENSER);
		assertTrue(block.getState() instanceof Dispenser);
	}

	@Test
	public void testHasInventory()
	{
		Inventory inventory = dispenser.getInventory();
		assertNotNull(inventory);

		assertEquals(dispenser, inventory.getHolder());
		assertEquals(InventoryType.DISPENSER, inventory.getType());
	}

	@Test
	public void testLocking()
	{
		String key = "key";

		assertFalse(dispenser.isLocked());
		assertEquals("", dispenser.getLock());

		dispenser.setLock("key");
		assertTrue(dispenser.isLocked());
		assertEquals(key, dispenser.getLock());
	}

	@Test
	public void testNullLocking()
	{
		dispenser.setLock(null);
		assertFalse(dispenser.isLocked());
		assertEquals("", dispenser.getLock());
	}

	@Test
	public void testNaming()
	{
		String name = "Cool Dispenser";

		assertNull(dispenser.getCustomName());

		dispenser.setCustomName(name);
		assertEquals(name, dispenser.getCustomName());
	}

	@Test
	public void testUnplacedProjectileSource()
	{
		Dispenser dispenser = new DispenserMock(Material.DISPENSER);
		assertNull(dispenser.getBlockProjectileSource());
	}

	@Test
	public void testPlacedProjectileSource()
	{
		Block block = new BlockMock(Material.DISPENSER);
		Dispenser dispenser = (Dispenser) block.getState();
		BlockProjectileSource source = dispenser.getBlockProjectileSource();

		assertNotNull(source);
		assertEquals(block, source.getBlock());
	}
}
