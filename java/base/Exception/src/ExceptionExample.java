class DogExceptionOne extends Exception{
    private static final long serialVersionUID = 1L;   

    DogExceptionOne(){
	    System.out.println("DogException One!!");
    }

    DogExceptionOne(String	smg){
	    super(smg);
    }

    void DogDug(){
	    this.printStackTrace();
    }
}

class DogExceptionTwo extends Exception{
    private static final long serialVersionUID = 1L;   

    DogExceptionTwo(){
	    System.out.println("DogException Two!!");
    }

    DogExceptionTwo(String smg){
	    super(smg);
    }

    void DogDug(){
	    this.printStackTrace();
    }
}

class Dog{
	boolean dogdug;
	int size;
	String name;

	void brark() throws DogExceptionOne,DogExceptionTwo{
		System.out.println("wang wang!");
		if(dogdug)
	        	throw new DogExceptionOne("this is DogExceptionOne");  
		else
	        	throw new DogExceptionTwo();  
	}
} 

public class ExceptionExample{   

	public static void main(String[] args) {   

		Dog d = new Dog();
		d.dogdug = true;

	 	try{
	 		d.brark();
	 	}catch(DogExceptionOne e){

			System.out.println("miao!");
			System.out.println("getMessage : " + e.getMessage());
			System.out.println("toString : " + e.toString());
			e.DogDug();

		}catch(DogExceptionTwo e){
			
			System.out.println("miao!miao!");
			e.DogDug();
		}
		finally{

			System.out.println("wangwang!");
	 	} 
	} 
}
