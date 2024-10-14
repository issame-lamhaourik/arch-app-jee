package myboot.app3.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @Exclude
    @JsonBackReference
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @Exclude
    @JsonBackReference
    private Writer writer;

    public Comment(String reply, Post post, Writer writer) {
        super();
        this.reply = reply;
        this.post = post;
        this.writer = writer;
    }

}