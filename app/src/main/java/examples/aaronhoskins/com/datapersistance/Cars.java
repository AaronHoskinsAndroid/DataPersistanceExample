package examples.aaronhoskins.com.datapersistance;

public class Cars {
    private String make;
    private String model;
    private String year;
    private String engineSize;
    private String transmissionType;
    private String vinNumber;

    public Cars() {
    }

    public Cars(String make, String model, String year, String engineSize, String transmissionType, String vinNumber) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.engineSize = engineSize;
        this.transmissionType = transmissionType;
        this.vinNumber = vinNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(String engineSize) {
        this.engineSize = engineSize;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }
}
