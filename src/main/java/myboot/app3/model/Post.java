package myboot.app3.model;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@Entity
@Data
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @Exclude
    @JsonManagedReference
    private List<Comment> comments = new LinkedList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @Exclude
    @JsonBackReference
    private Writer writer;

    public Post(String subject, Writer writer) {
        super();
        this.subject = subject;
        this.writer = writer;
    }

}