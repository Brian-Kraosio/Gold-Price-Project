package id.putraprima.mygoldtracker.api;

public class TokopediaEnvelope<T> {
    private String code;
    private String latency;
    T data;

    public TokopediaEnvelope() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLatency() {
        return latency;
    }

    public void setLatency(String latency) {
        this.latency = latency;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
