package dataTypes;

public class ResponseRequest {
    
    String content;

    public ResponseRequest(){
        this.content = "Response Needed";
    }
    // Reports can be telemetry (100-10k bytes, frequent) 
    // or data (100k-100MB, periodic)

    @Override
    public String toString(){
        return content;
    }
}
