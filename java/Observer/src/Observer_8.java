import java.util.Vector;

abstract class Subject {
	private Vector<Observer> obs = new Vector<Observer>();
	
	public void addObserver(Observer obs){
		this.obs.add(obs);
	}
	public void delObserver(Observer obs){
		this.obs.remove(obs);
	}
	protected void notifyObserver(){
		for(Observer o: obs){
			o.update();
		}
	}
	public abstract void doSomething();
}

class ConcreteSubject extends Subject {
	public void doSomething(){
		System.out.println("被观察者事件反生");
		this.notifyObserver();
	}
}
interface Observer {
	public void update();
}
class ConcreteObserver1 implements Observer {
	public void update() {
		System.out.println("观察者1收到信息，并进行处理。");
	}
}
class ConcreteObserver2 implements Observer {
	public void update() {
		System.out.println("观察者2收到信息，并进行处理。");
	}
}

public class Observer_8 {
	public static void main(String[] args){
		Subject sub = new ConcreteSubject();
		sub.addObserver(new ConcreteObserver1()); //添加观察者1
		sub.addObserver(new ConcreteObserver2()); //添加观察者2
		sub.doSomething();
	}
}