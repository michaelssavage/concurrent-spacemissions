package dataTypes;

public class Report {
    
    String content;
    int size;

    public Report(int size){
        this.content = "Report";
        this.size = size;
    }
    // Reports can be telemetry (100-10k bytes, frequent) 
    // or data (100k-100MB, periodic)

    @Override
    public String toString(){
        return content;
    }
}
