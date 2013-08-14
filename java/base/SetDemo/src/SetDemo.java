import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {

	public static void main(String args[]) { 
	int count[] = {34, 22,10,60,30,22};
	Set<Integer> set = new HashSet<Integer>();
	try{
       		for(int i = 0; i<5; i++){
			//Methods add()
			set.add(count[i]);
		}
		System.out.println(set);
		//Methods contains()
		System.out.println(set.contains(34));
		//Methods iterator()
		Iterator<Integer> ir=set.iterator();
		while(ir.hasNext())
		{
			System.out.println(ir.next());
		}
		//Methods remove() 
		set.remove(30);
		System.out.println(set);
		//Methods size()
		System.out.println("set size is "+set.size());
		//Methods clear()
		//set.clear();
		//Methods isEmpty()
		System.out.println("set Empty is "+set.isEmpty());
		     
		TreeSet<Integer> sortedSet = new TreeSet<Integer>(set);
		sortedSet.addAll(set);
		System.out.println("The sorted list is:");
		System.out.println(sortedSet);

		System.out.println("The First element of the set is: "+
		        (Integer)sortedSet.first());
	        System.out.println("The last element of the set is: "+
	                (Integer)sortedSet.last());
						            
	}
	catch(Exception e){}
	}

} 
