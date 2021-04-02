package be.seeseemelk.mockbukkit.block.state;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.ShulkerBox;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.block.BlockMock;

public class ShulkerBoxMockTest
{

	private ShulkerBoxMock shulkerBox;

	@BeforeEach
	public void setUp() throws Exception
	{
		MockBukkit.mock();
		shulkerBox = new ShulkerBoxMock(Material.SHULKER_BOX);
	}

	@AfterEach
	public void tearDown() throws Exception
	{
		MockBukkit.unmock();
	}

	@Test
	public void testMaterialShulkerBoxBlockState()
	{
		Block block = new BlockMock(Material.SHULKER_BOX);
		assertTrue(block.getState() instanceof ShulkerBox);
	}

	@Test
	public void testShulkerBoxStateMaterialValidation()
	{
		assertThrows(IllegalArgumentException.class, () -> new ShulkerBoxMock(Material.BREAD));
	}

	@Test
	public void testHasInventory()
	{
		Inventory inventory = shulkerBox.getInventory();
		assertNotNull(inventory);

		assertEquals(shulkerBox, inventory.getHolder());
		assertEquals(InventoryType.SHULKER_BOX, inventory.getType());
	}

	@Test
	public void testLocking()
	{
		String key = "key";

		assertFalse(shulkerBox.isLocked());
		assertEquals("", shulkerBox.getLock());

		shulkerBox.setLock("key");
		assertTrue(shulkerBox.isLocked());
		assertEquals(key, shulkerBox.getLock());
	}

	@Test
	public void testNullLocking()
	{
		shulkerBox.setLock(null);
		assertFalse(shulkerBox.isLocked());
		assertEquals("", shulkerBox.getLock());
	}

	@Test
	public void testNaming()
	{
		String name = "Cool Shulker";

		assertNull(shulkerBox.getCustomName());

		shulkerBox.setCustomName(name);
		assertEquals(name, shulkerBox.getCustomName());
	}

	@Test
	public void testNullPointerUndyed()
	{
		assertThrows(NullPointerException.class, () -> shulkerBox.getColor());
	}

	@Test
	public void testShulkerColors()
	{
		for (DyeColor color : DyeColor.values())
		{
			assertDyed(Material.valueOf(color.name() + "_SHULKER_BOX"), color);
		}
	}

	private void assertDyed(Material shulkerBox, DyeColor color)
	{
		Block block = new BlockMock(shulkerBox);
		assertTrue(block.getState() instanceof ShulkerBox);
		assertEquals(color, ((ShulkerBox) block.getState()).getColor());
	}
}
