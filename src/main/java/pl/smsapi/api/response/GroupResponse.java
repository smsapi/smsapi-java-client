package pl.smsapi.api.response;

public class GroupResponse implements Response {

    private String name;
    private String info;
    private int numbers;

    public GroupResponse(String name, String info, int numbers) {

        this.name = name;
        this.info = info;
        this.numbers = numbers;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public int getNumbers() {
        return numbers;
    }
}
