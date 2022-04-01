package pcd.assignment01.concurrent.controller;

/**
 * Implementation of Chronometer
 */
public class ChronometerImpl implements Chronometer {

	private boolean running;
	private long startTime;

	public ChronometerImpl(){
		running = false;
		startTime = 0;
	}
	
	@Override
	public void start(){
		running = true;
		startTime = System.currentTimeMillis();
	}
	
	@Override
	public void stop(){
		startTime = getTime();
		running = false;
	}
	
	@Override
	public long getTime(){
		if (running){
			return 	System.currentTimeMillis() - startTime;
		} else {
			return startTime;
		}
	}
}
