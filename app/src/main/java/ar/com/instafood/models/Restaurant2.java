package ar.com.instafood.models;

public class Restaurant2 {
    private String title;
    private String description;
    private String distance;
    private Double longitud;
    private Double latitud;
    private int image;

    public String getTitle() {
        return title;
    }

    public void setDistance(String newDistance){
        this.distance = newDistance;
    }

    public String getDescription() {
        return description;
    }

    public String getDistance() {
        return distance;
    }

    public Double getLongitud() {
        return longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public int getImage() {
        return image;
    }

}
