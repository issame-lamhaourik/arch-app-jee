package myboot.app3.web;

import myboot.app3.dao.WriterRepository;
import myboot.app3.model.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;

@RestController
@RequestMapping("/api")
public class WriterRestController {

    @Autowired
    WriterRepository repo;

    @GetMapping("/writers/{id}")
    public ResponseEntity<Writer> getWriters(@PathVariable Long id) {
        return ResponseEntity.of(repo.findById(id));
    }
}

