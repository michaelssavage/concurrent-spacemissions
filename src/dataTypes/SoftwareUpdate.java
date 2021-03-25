package dataTypes;

public class SoftwareUpdate {

    private int updateSize;

    public SoftwareUpdate(int updateSize){
        this.updateSize = updateSize;
    }
    
    public int getUpdateSize(){
        return this.updateSize;
    }
}
