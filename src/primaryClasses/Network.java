package primaryClasses;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import utils.SimulateRandomAmountOf;

public class Network {

    static Random random = new Random();
    private String name;
    private String availability;
    private int bandwidth;
    private long latency = 0;

    // represent the communications networks (queues) 
    private BlockingQueue<Object> inputData;
    private BlockingQueue<Object> outputData;
    private BlockingQueue<Object> responseData;

    public Network(String name) {
        this.name = name;
        this.availability = checkNetworkAvailability();
        // the network has limited bandwidth
        this.bandwidth = setbandwith(availability);
        // communications are subject to increasing delays as the mission travels further away from Earth
        this.latency = setNetworkLatency(0);
        this.inputData = new LinkedBlockingQueue<>(bandwidth);
        this.outputData = new LinkedBlockingQueue<>(bandwidth);
        this.responseData = new LinkedBlockingQueue<>(bandwidth);
    }

    // three types of deep space communications
    // network speeds are all in bits
    public enum NetworkType {
        // 2MB at 80%
        MAIN(16_000_000),
        // 2KB at 90%
        SECONDARY(16_000),
        // 20 bits at 99%
        BACKUP(20);

        private final int bandwidth;

        private NetworkType(int bandwidth){
            this.bandwidth = bandwidth;
        }

        private int showBandwidth(){
            return this.bandwidth;
        }
    }

    // There are three types of deep space communications networks 
    private static String checkNetworkAvailability(){
        // 80% availabilty, so 20 % chance of failure
        if(SimulateRandomAmountOf.chance() > 2){
            return("MAIN");
        }

        // 90% availability, so 10% chance of failure
        else if(SimulateRandomAmountOf.chance() > 1){
            return("SECONDARY"); 
        }

        // 99.9% availability, so 0.01 percent chance of failure
        else{
            return("BACKUP");
        }
    }

    private static int setbandwith(String availability) {
        NetworkType network = NetworkType.valueOf(availability);
        return network.showBandwidth();
    }

    // subject to increasing delays as the mission travels further away from Earth
    private synchronized long setNetworkLatency(int distanceTravelled){
        // adds 1 second of latency per 100 kilometers travelled
        long networkLatency = this.latency;
        long journeyCompleted = distanceTravelled/100;

        return networkLatency + journeyCompleted;
    }

    private long simulateLatency(){
        return this.getLatency() * 100;
    }

    public Object receive() {
        try{            
            return this.inputData.take();
        } catch (InterruptedException e) { 
            Thread.currentThread().interrupt();        
        } 
        return "";
    } 

    public Object receiveUpdate() {
        try{            
            return this.outputData.take();
        } catch (InterruptedException e) { 
            Thread.currentThread().interrupt();        
        } 
        return "";
    } 

    public Object receiveResponse() {
        try{                        
            return this.responseData.take();
        } catch (InterruptedException e) { 
            Thread.currentThread().interrupt();        
        } 
        return "";
    }

    public void transmit(Object data){
        try{
            Thread.sleep(simulateLatency());
            this.inputData.put(data);
        } catch (InterruptedException e) { 
            Thread.currentThread().interrupt();        
        }  
    }

    public void transmitUpdate(Object data){
        try{
            Thread.sleep(simulateLatency());
            this.outputData.put(data);
        } catch (InterruptedException e) { 
            Thread.currentThread().interrupt();        
        }  
    }

    public void transmitResponse() {
        try{            
            // simulate latency on the network
            Thread.sleep(simulateLatency());
            this.responseData.put("Response");
        } catch (InterruptedException e) { 
            Thread.currentThread().interrupt();        
        } 
    }

    public void replyToPing(){
        try{
            this.outputData.put("Success");
        } catch (InterruptedException e) { 
            Thread.currentThread().interrupt();        
        }
    }

    public String getName(){
        return this.name;
    }

    public String getAvailability(){
        return this.availability; 
    }

    public int getBandwidth(){
        return this.bandwidth; 
    }

    public long getLatency(){
        return this.latency; 
    }
}
