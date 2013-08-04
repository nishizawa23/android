class Dog {
	int size;
	String breed;
	String name;

	void bark(){
		System.out.println("wang wang!");
	}
}

public class ClassExample {

	public static void main (String [] args)
	{
		Dog d = new Dog();
		d.size = 40;
		d.bark();

		Cat c = new Cat();
		c.size = 10;
		c.bark();
	}
}
