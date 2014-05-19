package pl.smsapi.api.response;

public class NumberResponse implements Response {

    private String id;
    private String number;
    private int mcc;
    private int mnc;
    private String info;
    private String status;
    private int date;
    private int ported;
    private int portedFrom;
    private double points;

    public NumberResponse(String id, String number, int mcc, int mnc, String info, String status, int date, int ported, int portedFrom, String points) {
        this.id = id;
        this.number = number;
        this.mcc = mcc;
        this.mnc = mnc;
        this.info = info;
        this.status = status;
        this.date = date;
        this.ported = ported;
        this.portedFrom = portedFrom;
        this.points = (points != null && !points.isEmpty() ? Double.parseDouble(points) : 0);
    }

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public int getMcc() {
        return mcc;
    }

    public int getMnc() {
        return mnc;
    }

    public String getInfo() {
        return info;
    }

    public String getStatus() {
        return status;
    }

    public int getDate() {
        return date;
    }

    public int getPorted() {
        return ported;
    }

    public int getPortedFrom() {
        return portedFrom;
    }

    public double getPoints() {
        return points;
    }
}
