package utils;

import primaryClasses.Network;
import dataTypes.SoftwareUpdate;

public class SoftwareUpdater implements Runnable { 

	Network network;
    
	public SoftwareUpdater(Network network) 
	{ 
		this.network = network; 
	} 

	@Override 
    public void run() {
        try {
            int[] patchDetails = getPatchDetails();

            int patchSize  = patchDetails[0];
            int buildTime = patchDetails[1];
            
            System.out.println("Software Patch Requested, Beginning Build");
            System.out.println("Estimated development time: " + buildTime + " days");
            System.out.println("Estimated patch size: " + patchSize + " MB");
            
            // 33ms = 1 day
            Thread.sleep((long) buildTime * 33);
            System.out.println("Developers finished building and testing period");
            System.out.println("Patch in Network Pipeline"); 
            network.transmitUpdate(new SoftwareUpdate(patchSize)); 
        } 
        
        catch (InterruptedException e) { 
            Thread.currentThread().interrupt();
        }
    }

    // updates take a variable number of days to develop and is a variable size in MB.        
    public static synchronized int[] getPatchDetails(){
        int time = SimulateRandomAmountOf.days();
        int updateSize = SimulateRandomAmountOf.updateSize();

        return new int[] {updateSize, time};
    }

} 
