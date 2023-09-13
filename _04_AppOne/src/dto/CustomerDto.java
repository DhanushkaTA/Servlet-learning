package dto;

public class CustomerDto {
    private String cus_id;
    private String cus_name;

    public CustomerDto() {
    }

    public CustomerDto(String cus_id, String cus_name) {
        this.cus_id = cus_id;
        this.cus_name = cus_name;
    }

    public String getCus_id() {
        return cus_id;
    }

    public void setCus_id(String cus_id) {
        this.cus_id = cus_id;
    }

    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "cus_id='" + cus_id + '\'' +
                ", cus_name='" + cus_name + '\'' +
                '}';
    }
}
