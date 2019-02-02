package com.example.demo.pb2UserSystem.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "caption")
    private Date caption;

    @Column(name = "path")
    private String path;

    @Column(name = "file_extension",length = 10)
    private String extension;

    @Lob
    @Size(max = 1024*1024)
    @Column(name = "picture_content",columnDefinition = "BLOB")
    private byte[] pictureContent;

    @ManyToMany(mappedBy = "pictures",targetEntity = Album.class)
    private Set<Album> albums;

    @OneToMany(mappedBy = "profilePicture")
    private Set<User> users;

    public Picture() {
        this.albums=new HashSet<>();
        this.users=new HashSet<>();
    }

    public Picture(String title, Date caption, String path, String extension, @Size(max = 1024 * 1024) byte[] pictureContent) {
        this.title = title;
        this.caption = caption;
        this.path = path;
        this.extension = extension;
        this.pictureContent = pictureContent;
        this.albums=new HashSet<>();
        this.users=new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCaption() {
        return caption;
    }

    public void setCaption(Date caption) {
        this.caption = caption;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public byte[] getPictureContent() {
        return pictureContent;
    }

    public void setPictureContent(byte[] pictureContent) {
        this.pictureContent = pictureContent;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,title,caption,path, extension);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if(!(obj instanceof Picture)){
            return false;
        }
        Picture that= (Picture) obj;
        return this.id==that.id&&this.title.equals(that.title)&&
                this.path.equals(that.path);
    }
}
