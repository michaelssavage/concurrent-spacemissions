package utils;

import primaryClasses.Network;

public class Ping implements Runnable{
    
    String contents;
    Network network;

    public Ping(Network network){
        this.network = network;
    }
    
    /**
     * Start handling ping requests.
     */
    public void run() {
        // block until a ping arrives
        Object x = network.receive();

        if(x instanceof Ping){
            network.replyToPing();
        }
    }
}
