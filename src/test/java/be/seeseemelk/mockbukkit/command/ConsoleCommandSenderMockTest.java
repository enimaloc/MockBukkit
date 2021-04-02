package be.seeseemelk.mockbukkit.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConsoleCommandSenderMockTest
{
	private ConsoleCommandSenderMock sender;

	@BeforeEach
	public void setUp() throws Exception
	{
		sender = new ConsoleCommandSenderMock();
	}

	@Test
	public void getMessage_SomeString_SameString()
	{
		sender.sendMessage("Hello");
		sender.sendMessage("Other");
		assertEquals("Hello", sender.nextMessage());
		assertEquals("Other", sender.nextMessage());
	}

	@Test
	public void getMessage_NoMessages_Null()
	{
		assertNull(sender.nextMessage());
	}

	@Test
	public void sendMessageVararg_SomeStrings_StringsInRightOrder()
	{
		sender.sendMessage(new String[] {"Hello", "world"});
		sender.assertSaid("Hello");
		sender.assertSaid("world");
	}

	@Test
	public void getName_IsConsole() {
		assertEquals("CONSOLE", sender.getName());
	}

	@Test
	public void assertIsOp() {
		assertTrue(sender.isOp());
	}

	@Test
	public void assertSaid_CorrectMessage_DoesNotAssert()
	{
		sender.sendMessage("A hello world");
		sender.assertSaid("A hello world");
	}

	@Test
	public void assertSaid_WrongMessage_Asserts()
	{
		assertThrows(AssertionError.class, () -> {
			sender.sendMessage("My message");
			sender.assertSaid("Some other message");
		});
	}

	@Test
	public void assertSaid_NoMessages_Asserts()
	{
		assertThrows(AssertionError.class, () -> sender.assertSaid("A message"));
	}

	@Test
	public void assertNoMore_NoMessages_DoesNotAssert()
	{
		sender.assertNoMoreSaid();
	}

	@Test
	public void assertNoMore_MoreMessages_Asserts()
	{
		assertThrows(AssertionError.class, () -> {
			sender.sendMessage("Some message");
			sender.assertNoMoreSaid();
		});
	}
}


















