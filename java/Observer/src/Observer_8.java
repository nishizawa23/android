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
		System.out.println("���۲����¼�����");
		this.notifyObserver();
	}
}
interface Observer {
	public void update();
}
class ConcreteObserver1 implements Observer {
	public void update() {
		System.out.println("�۲���1�յ���Ϣ�������д���");
	}
}
class ConcreteObserver2 implements Observer {
	public void update() {
		System.out.println("�۲���2�յ���Ϣ�������д���");
	}
}

public class Observer_8 {
	public static void main(String[] args){
		Subject sub = new ConcreteSubject();
		sub.addObserver(new ConcreteObserver1()); //��ӹ۲���1
		sub.addObserver(new ConcreteObserver2()); //��ӹ۲���2
		sub.doSomething();
	}
}