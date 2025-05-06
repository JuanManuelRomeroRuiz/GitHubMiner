package aiss.PIntegracion.ProIntegracion.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="commits")
public class Commit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="title")
    private String title;

    @Column(name="message")
    private String message;

    @Column(name="author_name")
    private String author_name;

    @Column(name="author_email")
    private String author_email;

    @Column(name="authored_date")
    private LocalDateTime authored_date;

    @Column(name="url")
    private String url;

    public Commit() {
    }

    public Commit(String title, String message, String author_name, String author_email, LocalDateTime authored_date, String url) {
        this.title = title;
        this.message = message;
        this.author_name = author_name;
        this.author_email = author_email;
        this.authored_date = authored_date;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor_name() {return author_name;}

    public void setAuthor_name(String author_name) {this.author_name = author_name;}

    public String getAuthor_email() {
        return author_email;
    }

    public void setAuthor_email(String author_email) {
        this.author_email = author_email;
    }

    public LocalDateTime getAuthored_date() {
        return authored_date;
    }

    public void setAuthored_date(LocalDateTime authored_date) {
        this.authored_date = authored_date;
    }
}