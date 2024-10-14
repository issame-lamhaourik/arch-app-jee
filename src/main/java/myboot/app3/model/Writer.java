package myboot.app3.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@Entity
@Data
@NoArgsConstructor
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @Basic(fetch = FetchType.LAZY)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "writer")
    @Exclude
    @JsonManagedReference
    private List<Post> posts = Arrays.asList();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "writer")
    //@OrderColumn(name = "position")
    @Exclude
    @JsonManagedReference
    private List<Comment> comments = new LinkedList<>();

    public Writer(String name, String email, String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }

}