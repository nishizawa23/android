public class Dog_abstract {
	static abstract class Ball {
		abstract String getName();
	}
	void play(Ball b){
		System.out.println(b.getName());
	}
	public static void main(String[] args){
		Dog_abstract dog = new Dog_abstract();
		dog.play(new Ball(){
				@Override String getName() {
					return "qiu qiu";
				}
		});
	}
}

