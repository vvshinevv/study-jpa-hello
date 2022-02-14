package hello.project.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Child {
    @Id
    @Column(name = "child_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    protected Child() {
    }

    public Child(String name) {
        this.name = name;
    }

    public void setParent(Parent parent) {
        if (this.parent != null) {
            this.parent.getChildren().remove(this);
        }

        this.parent = parent;
        if (!parent.getChildren().contains(this)) {
            parent.addChildren(this);
        }
    }
}
