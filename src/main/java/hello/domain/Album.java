package hello.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@DiscriminatorValue(value = "album")
@Entity
public class Album extends Item {
    private String artist;

    protected Album() {
    }

    public Album(String name, int price, String artist) {
        super(name, price);
        this.artist = artist;
    }
}
