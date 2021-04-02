package be.seeseemelk.mockbukkit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoordinateTest
{
	private Coordinate coordinate;

	@BeforeEach
	public void setUp()
	{
		coordinate = new Coordinate();
	}

	@Test
	public void constructor_NoParameters_AllZeroes()
	{
		coordinate = new Coordinate();
		assertEquals(0, coordinate.x);
		assertEquals(0, coordinate.y);
		assertEquals(0, coordinate.z);
	}

	@Test
	public void construct_Parameters_ValuesSet()
	{
		coordinate = new Coordinate(1, 2, 3);
		assertEquals(1, coordinate.x);
		assertEquals(2, coordinate.y);
		assertEquals(3, coordinate.z);
	}

	@Test
	public void hashCode_SameObject_SameHash()
	{
		assertEquals(coordinate.hashCode(), coordinate.hashCode());
	}

	@Test
	public void hashCode_DifferentObjectWithSameCoordinates_SameHash()
	{
		Coordinate c1 = new Coordinate(1, 2, 3);
		Coordinate c2 = new Coordinate(1, 2, 3);
		assertEquals(c1.hashCode(), c2.hashCode());
	}

	@Test
	public void hashCode_DifferentObjectWithDifferentCoordinates_DifferentHash()
	{
		Coordinate c1 = new Coordinate(1, 2, 3);
		Coordinate c2 = new Coordinate(4, 5, 6);
		assertNotEquals(c1.hashCode(), c2.hashCode());
	}

	@Test
	public void equals_Null_False()
	{
		assertNotEquals(coordinate, null);
	}

	@Test
	public void equals_Same_True()
	{
		assertEquals(coordinate, coordinate);
	}

	@Test
	public void equals_DifferentObjectWithSameCoordinates_True()
	{
		Coordinate c1 = new Coordinate(1, 2, 3);
		Coordinate c2 = new Coordinate(1, 2, 3);
		assertEquals(c1, c2);
	}

	@Test
	public void equals_DifferentObjectWithDifferentCoordinates_False()
	{
		Coordinate c1 = new Coordinate(1, 2, 3);
		Coordinate c2 = new Coordinate(4, 5, 6);
		assertNotEquals(c1, c2);
	}
}
