package dataTypes;

public class PatchRequest {

    String data;

    public PatchRequest(String data){
        this.data = data;
    }

    @Override
    public String toString(){
        return data;
    }
    
}
