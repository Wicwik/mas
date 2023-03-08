import java.util.*;

public class MessageQueue {
	Stack stack;

	public MessageQueue () {
		stack = new Stack();
	}

	synchronized public void pushMessage (Message message) {
		stack.push(message);
	}

	synchronized public Message popMessage () {
		Message ret = null;
		try {
			ret = (Message) stack.pop();
		} catch (EmptyStackException ee) {
			ret = null;
		}
		return ret;
	}

/*
	public static void main (String[] args) {
		Message m = new Message();
		MessageQueue q = new MessageQueue();
		System.out.println(m);
		q.pushMessage(m);
		m = q.popMessage();
		System.out.println(m);
		m = q.popMessage();
		System.out.println(m);		
	}
*/

} 
