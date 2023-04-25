package java.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="username")
public class Seller extends Benutzer
{
    private double rating;
    public Seller(String username, String password, String location) {
        super(username, password, location);
        rating = 5.0d;
    }

    public Seller() {

    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }
}
