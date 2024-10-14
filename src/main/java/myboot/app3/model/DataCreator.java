package myboot.app3.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import myboot.app3.dao.WriterRepository;
import myboot.app3.dao.PostRepository;
import myboot.app3.dao.CommentRepository;

@Service
public class DataCreator {

    @Autowired
    private WriterRepository writerRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PostConstruct
    public void initializeData() {
        createDataWithLoop();
        createDataWithoutLoop();
    }

    private void createDataWithLoop() {
        Writer writer1 = new Writer("Issame", "issam@gmail.com", "Issame Description");
        writerRepository.save(writer1);

        Post post1 = new Post("Post Subject 1", writer1);
        postRepository.save(post1);

        Comment comment1 = new Comment("Comment reply 1", post1, writer1);
        commentRepository.save(comment1);

        System.out.println("Data with loop created: Writer1 -> Post1 -> Comment1 -> Writer1");
    }

    private void createDataWithoutLoop() {
        Writer writer2 = new Writer("Yasmine", "Yasmine@live.fr", "Yasmine Description");
        writerRepository.save(writer2);

        Writer writer3 = new Writer("Thomas", "Thomas@outlook.com", "Thomas Description");
        writerRepository.save(writer3);

        Post post2 = new Post("Post Subject 2", writer2);
        postRepository.save(post2);

        Comment comment2 = new Comment("Comment reply 2", post2, writer3);
        commentRepository.save(comment2);
        System.out.println("Data without loop created: Writer2 -> Post2 -> Comment2 -> Writer3");
    }
}
