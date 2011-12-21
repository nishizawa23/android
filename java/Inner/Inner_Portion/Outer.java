public class Outer {
	int x =1;
	public void doSomething()
	{
		final int y=2;
		class Inner{
			int x =3;
			void print(){
				int x=4;
				System.out.println(x);
				System.out.println(this.x);
				System.out.println(Outer.this.x);
				System.out.println(y);
			}
		}
		Inner inner = new Inner();
		inner.print();
	}
	public static void main(String[] args){
		Outer outer = new Outer();
		outer.doSomething();
	}
}

