package be.seeseemelk.mockbukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.bukkit.NamespacedKey;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import be.seeseemelk.mockbukkit.MockBukkit;

public class KnowledgeBookMetaMockTest
{

	private final int MAX_RECIPES = 32767;

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
	public void testRecipesDefaultFalse()
	{
		KnowledgeBookMetaMock meta = new KnowledgeBookMetaMock();
		assertFalse(meta.hasRecipes());
	}

	@Test
	public void testAddRecipe()
	{
		KnowledgeBookMetaMock meta = new KnowledgeBookMetaMock();
		NamespacedKey key = NamespacedKey.randomKey();

		assertFalse(meta.hasRecipes());
		meta.addRecipe(key);
		assertTrue(meta.hasRecipes());
	}

	@Test
	public void testAddNullRecipeAndFail()
	{
		KnowledgeBookMetaMock meta = new KnowledgeBookMetaMock();
		List<NamespacedKey> recipes = Arrays.asList(null, null, null);

		assertFalse(meta.hasRecipes());
		meta.setRecipes(recipes);
		assertFalse(meta.hasRecipes());
	}

	@Test
	public void testSetRecipes()
	{
		KnowledgeBookMetaMock meta = new KnowledgeBookMetaMock();
		List<NamespacedKey> recipes = Arrays.asList(NamespacedKey.randomKey(), NamespacedKey.randomKey());

		assertFalse(meta.hasRecipes());
		meta.setRecipes(recipes);
		assertTrue(meta.hasRecipes());
	}

	@Test
	public void testGetRecipes()
	{
		KnowledgeBookMetaMock meta = new KnowledgeBookMetaMock();
		List<NamespacedKey> recipes = Arrays.asList(NamespacedKey.randomKey(), NamespacedKey.randomKey());
		meta.setRecipes(recipes);

		assertEquals(recipes, meta.getRecipes());
	}

	@Test
	public void testTooManyRecipes()
	{
		KnowledgeBookMetaMock meta = new KnowledgeBookMetaMock();

		for (int i = 0; i < MAX_RECIPES + 50; i++)
		{
			meta.addRecipe(NamespacedKey.randomKey());
		}

		assertEquals(MAX_RECIPES, meta.getRecipes().size());
	}

	@Test
	public void testEquals()
	{
		KnowledgeBookMetaMock meta = new KnowledgeBookMetaMock();
		assertEquals(meta, meta);
		assertFalse(meta.equals(new ItemMetaMock()));

		KnowledgeBookMetaMock meta2 = new KnowledgeBookMetaMock();
		assertEquals(meta, meta2);

		NamespacedKey recipe = NamespacedKey.randomKey();

		meta.addRecipe(recipe);
		assertFalse(meta.equals(meta2));

		meta2.addRecipe(recipe);
		assertEquals(meta, meta2);
	}
}
