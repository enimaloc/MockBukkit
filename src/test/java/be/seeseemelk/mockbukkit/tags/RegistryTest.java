package be.seeseemelk.mockbukkit.tags;

import be.seeseemelk.mockbukkit.MockBukkit;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("need to find a replacement for ParameterizedTest")
public class RegistryTest
{

	private TagRegistry registry;

	public RegistryTest(TagRegistry registry)
	{
		this.registry = registry;
	}

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
	public void testNotEmpty()
	{
		assertFalse(registry.isEmpty());

		for (TagWrapperMock tag : registry.getTags().values())
		{
			assertFalse(tag.getValues().isEmpty());
		}
	}

	@Test
	public void testForInfiniteLoops() throws TagMisconfigurationException
	{
		for (TagWrapperMock tag : registry.getTags().values())
		{
			assertNotCyclic(tag);
		}
	}

	@Test
	public void testGetValues() throws TagMisconfigurationException
	{
		for (TagWrapperMock tag : registry.getTags().values())
		{
			tag.reload();
		}

		for (TagWrapperMock tag : registry.getTags().values())
		{
			Set<Material> values = tag.getValues();
			assertFalse(values.isEmpty());

			for (Material value : tag.getValues())
			{
				// All values of our tag must be tagged
				assertTrue(tag.isTagged(value));
			}

			for (Tag<Material> sub : tag.getSubTags())
			{
				for (Material value : sub.getValues())
				{
					// All values of sub tags should be tagged by our tag too
					assertTrue(tag.isTagged(value));
				}
			}
		}
	}

	private void assertNotCyclic(@NotNull TagWrapperMock tag)
	{
		Set<TagWrapperMock> visiting = new HashSet<>();
		Set<TagWrapperMock> visited = new HashSet<>();

		if (isCyclic(visiting, visited, tag))
		{
			System.out.println("Currently visiting: " + visiting);
			System.out.println("Previously visited" + visiting);
			Assertions.fail("Tag '" + tag.getKey() + "' is cyclic!");
		}
	}

	private boolean isCyclic(Set<TagWrapperMock> visiting, Set<TagWrapperMock> visited, TagWrapperMock tag)
	{
		visiting.add(tag);

		for (TagWrapperMock sub : tag.getSubTags())
		{
			if (visiting.contains(sub))
			{
				return true;
			}
			else if (!visited.contains(sub) && isCyclic(visiting, visited, sub))
			{
				return true;
			}
		}

		visiting.remove(tag);
		visited.add(tag);
		return false;
	}

}
