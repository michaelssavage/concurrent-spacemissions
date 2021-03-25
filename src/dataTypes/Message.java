package dataTypes;

public class Message {
    
    String data;
    int bits;

    public Message(String data, int bits){
        this.data = data;
        this.bits = bits;
    }

    @Override
    public String toString(){
        return data;
    }
}
