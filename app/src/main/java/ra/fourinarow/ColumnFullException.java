package ra.fourinarow;

public class ColumnFullException extends Exception
{
	String msg;

	public ColumnFullException(int col)
	{
		msg = "Column " + col + " is already full.";
	}

	public String toString()
	{
		return "ColumnFullException: " + msg;
	}
}
