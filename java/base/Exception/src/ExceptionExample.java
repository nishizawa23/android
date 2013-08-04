class DogException extends Exception{
    private static final long serialVersionUID = 1L;   

    DogException(){
	    System.out.println("ahhah");
	//  this.printStackTrace();
    }

    DogException(String	smg){
	    super(smg);
    }
}

class Dog{

	int size;
	String name;

	void brark() throws DogException{
		System.out.println("wang wang!");
	        throw new DogException();  
	}
} 

public class ExceptionExample{   

	public static void main(String[] args) {   

		Dog d = new Dog();
	 	try{
	 		d.brark();
	 	}catch(DogException e){
			System.out.println("wangwangwangwang!");
	 	}finally{
			System.out.println("miaomiao");
	 	} 
	} 
}
