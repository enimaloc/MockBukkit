package be.seeseemelk.mockbukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.inventory.meta.FireworkMeta;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import be.seeseemelk.mockbukkit.MockBukkit;

public class FireworkMetaMockTest
{

	@BeforeEach
	public void setUp()
	{
		MockBukkit.mock();
	}

	@AfterEach
	public void tearDown()
	{
		MockBukkit.unmock();
	}

	@Test
	public void testEffectDefaultNone()
	{
		FireworkMeta meta = new FireworkMetaMock();
		assertTrue(meta.getEffects().isEmpty());
		assertFalse(meta.hasEffects());
		assertEquals(0, meta.getEffectsSize());
	}

	@Test
	public void testAddSingleEffect()
	{
		FireworkMeta meta = new FireworkMetaMock();
		FireworkEffect effect = FireworkEffect.builder().withColor(Color.BLUE).with(Type.BALL_LARGE).build();

		assertFalse(meta.hasEffects());

		meta.addEffect(effect);

		assertTrue(meta.hasEffects());
		assertEquals(1, meta.getEffectsSize());
		assertEquals(effect, meta.getEffects().get(0));
	}

	@Test
	public void testAddEffectsArray()
	{
		FireworkMeta meta = new FireworkMetaMock();
		FireworkEffect effect = FireworkEffect.builder().withColor(Color.BLUE).with(Type.BALL_LARGE).build();
		FireworkEffect effect2 = FireworkEffect.builder().withColor(Color.RED).with(Type.CREEPER).build();

		assertFalse(meta.hasEffects());

		meta.addEffects(effect, effect2);

		assertTrue(meta.hasEffects());
		assertEquals(2, meta.getEffectsSize());

		assertEquals(effect, meta.getEffects().get(0));
		assertEquals(effect2, meta.getEffects().get(1));
	}

	@Test
	public void testAddEffectsIterable()
	{
		FireworkMeta meta = new FireworkMetaMock();
		FireworkEffect effect = FireworkEffect.builder().withColor(Color.BLUE).with(Type.BALL_LARGE).build();
		FireworkEffect effect2 = FireworkEffect.builder().withColor(Color.RED).with(Type.CREEPER).build();

		assertFalse(meta.hasEffects());

		meta.addEffects(Arrays.asList(effect, effect2));

		assertTrue(meta.hasEffects());
		assertEquals(2, meta.getEffectsSize());

		assertEquals(effect, meta.getEffects().get(0));
		assertEquals(effect2, meta.getEffects().get(1));
	}

	@Test
	public void testRemoveEffect()
	{
		FireworkMeta meta = new FireworkMetaMock();
		FireworkEffect effect = FireworkEffect.builder().withColor(Color.BLUE).with(Type.BALL_LARGE).build();
		meta.addEffect(effect);

		assertTrue(meta.hasEffects());

		meta.removeEffect(0);
		assertFalse(meta.hasEffects());
		assertEquals(0, meta.getEffectsSize());
	}

	@Test
	public void testClearEffects()
	{
		FireworkMeta meta = new FireworkMetaMock();
		FireworkEffect effect = FireworkEffect.builder().withColor(Color.BLUE).with(Type.BALL_LARGE).build();
		meta.addEffect(effect);

		assertTrue(meta.hasEffects());

		meta.clearEffects();
		assertFalse(meta.hasEffects());
		assertEquals(0, meta.getEffectsSize());
	}

	@Test
	public void testClone()
	{
		FireworkMeta meta = new FireworkMetaMock();
		FireworkMeta clone = meta.clone();
		assertEquals(meta, clone);
	}

	@Test
	public void testPower()
	{
		FireworkMeta meta = new FireworkMetaMock();
		meta.setPower(8);
		assertEquals(8, meta.getPower());
	}

	@Test
	public void testPowerTooLow()
	{
		assertThrows(IllegalArgumentException.class, () -> {
			FireworkMeta meta = new FireworkMetaMock();
			meta.setPower(-200);
		});
	}

	@Test
	public void testPowerTooHigh()
	{
		assertThrows(IllegalArgumentException.class, () -> {
			FireworkMeta meta = new FireworkMetaMock();
			meta.setPower(1024);
		});
	}
}
