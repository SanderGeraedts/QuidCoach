package nl.codepanda.quidcoach.models;

public class Player {
    private String id;
    private String position;
    private float latitude;
    private float longitude;

    public Player(String id, String position, float latitude, float longitude) {
        this.id = id;
        this.position = position;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
