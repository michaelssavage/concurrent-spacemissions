package primaryClasses;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import utils.SimulateRandomAmountOf;

public class Component implements Runnable{

    private String compID;
    private Integer sizeAmount;
    private Integer reportRate;
    private Network network;
    private Mission mission;

    public Component(String compID, Network network, Mission mission){
        this.compID = compID;
        this.network = network;
        this.mission = mission;
        // Each component has a random report rate and size 
        this.reportRate = SimulateRandomAmountOf.days();
        this.sizeAmount = SimulateRandomAmountOf.size(compID);
    }

    public void run(){
        sendProgressReport();
    }

    public String getID(){
        return compID;
    }

    public Integer getSize(){
        return sizeAmount;
    }

    public void setSize(Integer sizeAmount){
        this.sizeAmount = sizeAmount;
    }

    public Integer getReportRate(){
        return reportRate;
    }

    public void setReportRate(Integer reportRate){
        this.reportRate = reportRate;
    }

    public Network getNetwork(){
        return this.network;
    }

    public synchronized void sendProgressReport(){
        String reporter = "level";

        //"fuel", "thrusters", "powerplants", "controlSystems", "instruments"
        if(this.getID().equals("instruments")){
            List<String> instrumentDict = Arrays.asList(" of high energy particle detectors", 
            " of plasma instruments", " of dust detectors", " of magnetometers", " of spectrometers");
            int instrument = SimulateRandomAmountOf.size(4);
            reporter+= instrumentDict.get(instrument);
        } 
        else if(this.getID().equals("thrusters")){
            reporter += " performance";
        }
        else if(this.getID().equals("powerplants")){
            reporter += " heat dissipated";
        }
        else if(this.getID().equals("controlSystems")){
            reporter += " efficiency";
        }
        else {
            reporter += " remaining";
        }



        final String message = reporter;
        Runnable sendMessage = () -> {
            String componentNote = mission + " " + compID + ": " + sizeAmount + " " + message;
            System.out.println(componentNote);
        };

        final ScheduledThreadPoolExecutor scheduler = (ScheduledThreadPoolExecutor)Executors.newScheduledThreadPool(1);
        final ScheduledFuture<?> progressUpdater = scheduler.scheduleAtFixedRate(sendMessage, 2, 10, TimeUnit.SECONDS);

        Runnable progressCanceller = () -> { 
            progressUpdater.cancel(true);
            scheduler.shutdown();
        };
        scheduler.schedule(progressCanceller, 10, TimeUnit.SECONDS);
    }
}