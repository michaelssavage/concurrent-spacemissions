package utils;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FileLogger implements Runnable {
    String message;
    ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
    FileWriter fileWriter;
    PrintWriter printWriter;
    boolean running;

    public FileLogger(){
        this.running = true;
        try{
            fileWriter = new FileWriter("output.dat");
            printWriter = new PrintWriter(fileWriter);
            printWriter.print("The Rocket Scientist Problem \n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try{
            Thread.sleep(20);
            // Mission Component # with (Thread ID) # makes request to network # at time # for message #.
            while(running){
                if(queue.peek() != null && !queue.peek().equals("*")){

                    List<String> log = Arrays.asList(queue.poll().split(","));
                    String mission = log.get(0);
                    String threadinfo = log.get(1);
                    String note = log.get(2);
                    String now = log.get(3);

                    printWriter.print("Mission Component " + mission
                                        + " with" + threadinfo
                                        + " makes request to network at time" + now 
                                        + " for message:" + note + "\n");

                } else if(queue.peek() != null && queue.peek().equals("*")){
                    running = false;
                    close();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    } 
    // putting in the queue
    public void put(String message){
        this.message = message;
        queue.add(message);
    }

    public synchronized void close() {
        printWriter.close();
    }
}