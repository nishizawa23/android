class HelloWorld
{
	private native void print();
	private native void SayHello(String strName);

	public static void main(String[] args)
	{
		new HelloWorld().print();
		new HelloWorld().SayHello("helloooo world!\n");
	}

	static
	{
		try{
			System.loadLibrary("HelloWorld");
		}
		catch(UnsatisfiedLinkError e)
		{
			System.err.println("Cannot load HelloWorld library:\n" +
					e.toString());
		}
	}
}
