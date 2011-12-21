public class Outer {
	static int x =1;
	static class Nest {
		static{
			System.out.println("Innter init");
		}
		void print(){
			System.out.println("Nest "+x);
		}
	}
	public static void main(String[] args){
		Outer.Nest nest = new Outer.Nest();
		System.out.println("--------------");
		nest.print();
	}
}

