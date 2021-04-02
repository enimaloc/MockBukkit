package be.seeseemelk.mockbukkit;

import org.junit.platform.commons.JUnitException;

public class UnimplementedOperationException extends JUnitException
{
	private static final long serialVersionUID = 1L;

	public UnimplementedOperationException()
	{
		super("Not implemented");
	}

	public UnimplementedOperationException(String message)
	{
		super(message);
	}
}
