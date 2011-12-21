public class Dog_interface {
	public interface Pet {
		public void beFriendly();
		public void play();
	}
	public static void main(String[] args){
		Pet dog = new Pet(){
			@Override public void beFriendly() {
				System.out.println("^_^");
			}
			@Override public void play() {
				System.out.println("to get ping 500^_^");
			}
		};
		dog.beFriendly();
		dog.play();
	}
}

