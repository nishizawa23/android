class Animal {

	public void info(){
		System.out.println("hello my animal");
	}

} 

class Dog extends Animal {

	public void info(){
		System.out.println("hello my dog");
	}

}


public class VirtualMethed{

	public static void main (String [] args)
	{
		Animal animal = new Animal();
		Animal dog = new Dog();
		System.out.println("this is a animal\n" );
		animal.info();
		System.out.println("this is a dog\n");
		dog.info();
	}
}
