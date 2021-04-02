package be.seeseemelk.mockbukkit.metadata;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.MockPlugin;
import be.seeseemelk.mockbukkit.TestPlugin;
import be.seeseemelk.mockbukkit.WorldMock;
import be.seeseemelk.mockbukkit.block.BlockMock;
import be.seeseemelk.mockbukkit.block.state.BlockStateMock;
import org.bukkit.Material;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("need to find a replacement for ParameterizedTest")
public class MetadataTest<T extends Metadatable>
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


	private static Stream<Arguments> data()
	{
		return Stream.of(
				Arguments.of(new MetadataTable()),
				Arguments.of(new BlockMock()),
				Arguments.of(new BlockStateMock(Material.DIAMOND_BLOCK)),
				Arguments.of(new WorldMock())
		);
	}

	private final T testSubject;

	public MetadataTest(T subject)
	{
		this.testSubject = subject;
	}

	@Test
	public void testRemoveMetadataMultipleOwners()
	{
		MockPlugin plugin1 = MockBukkit.createMockPlugin();
		TestPlugin plugin2 = MockBukkit.load(TestPlugin.class);

		testSubject.setMetadata("MyMetadata", new FixedMetadataValue(plugin1, "wee"));
		testSubject.setMetadata("MyMetadata", new FixedMetadataValue(plugin2, "woo"));
		testSubject.removeMetadata("MyMetadata", plugin1);

		assertTrue(testSubject.hasMetadata("MyMetadata"));
		List<MetadataValue> metadata = testSubject.getMetadata("MyMetadata");
		assertEquals(1, metadata.size());

		MetadataValue value = metadata.get(0);
		assertEquals(plugin2, value.getOwningPlugin());
	}

	@Test
	public void testSetMetadata()
	{
		MockPlugin plugin = MockBukkit.createMockPlugin();
		assertFalse(testSubject.hasMetadata("MyMetadata2"));
		testSubject.setMetadata("MyMetadata2", new FixedMetadataValue(plugin, "wee"));
		assertTrue(testSubject.hasMetadata("MyMetadata2"));
	}

	@Test
	public void testRemoveMetadataNotSet()
	{
		MockPlugin plugin = MockBukkit.createMockPlugin();
		assertFalse(testSubject.hasMetadata("MyMetadata3"));
		testSubject.removeMetadata("MyMetadata3", plugin);
		assertFalse(testSubject.hasMetadata("MyMetadata3"));
	}

}
