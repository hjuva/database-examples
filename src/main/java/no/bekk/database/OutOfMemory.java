package no.bekk.database;

import java.util.Map;

public class OutOfMemory {

	static final Map map = System.getProperties();
	
	public static void main(String[] args) {
		final OutOfMemory outOfMemory = new OutOfMemory();
		outOfMemory.start();
	}
	
	private void start() {
		for (int i = 0; i < 5000000; i++) {			
			map.put(new BadKey("key"), "value");
		}
		
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	class BadKey {
		   public final String key;
		   public BadKey(String key) { this.key = key; }
		}
}
