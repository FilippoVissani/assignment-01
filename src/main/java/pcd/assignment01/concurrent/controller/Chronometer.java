package pcd.assignment01.concurrent.controller;

public class Chronometer {

	private boolean running;
	private long startTime;

	public Chronometer(){
		running = false;
		startTime = 0;
	}
	
	public void start(){
		running = true;
		startTime = System.currentTimeMillis();
	}
	
	public void stop(){
		startTime = getTime();
		running = false;
	}
	
	public long getTime(){
		if (running){
			return 	System.currentTimeMillis() - startTime;
		} else {
			return startTime;
		}
	}
}
