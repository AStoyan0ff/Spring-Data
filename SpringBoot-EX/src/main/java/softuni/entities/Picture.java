package softuni.entities;

import jakarta.persistence.*;

@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String caption;
    private String path;

    public Picture() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getCaption() { return caption; }

    public void setCaption(String caption) { this.caption = caption; }

    public String getPath() { return path; }

    public void setPath(String path) { this.path = path; }
}
