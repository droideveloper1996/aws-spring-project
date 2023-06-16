package com.example.aws.awsstarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AwsstarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsstarterApplication.class, args);
    }

    @Autowired
    private BookRepository mBookRepository;

    @GetMapping("/root")
    public java.lang.String root() {
        return "Hello";
    }

    @GetMapping("/getBooks")
    public java.util.List<Book> findBooks() {
        return mBookRepository.findAll();
    }

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.end-point.uri}")
    private String endPoint;

    @GetMapping("/put/{msg}")
    public void putMessageToQueue(@PathVariable("msg") String message) {
        queueMessagingTemplate.send(endPoint, MessageBuilder.withPayload(message).build());
    }

    @GetMapping("/addBook")
    public Book saveBook(){
        Book book=new Book();
        book.setName("Amazon Kindle");
        book.setPrice(24.00);

        return mBookRepository.save(book);
    }


}
