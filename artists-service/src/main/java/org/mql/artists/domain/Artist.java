package org.mql.artists.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author chermehdi
 */
@Entity
public class Artist {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  // we have base64 images that needs a lot of space, should be refactored
  // for demo purposes only
  @Column(length = 15024)
  private String picture;

  @Column(length = 2024)
  private String description;

  @OneToMany
  @Cascade(
      CascadeType.ALL
  )
  private List<Song> songs = new ArrayList<>();

  public Artist() {
  }

  public Artist(String name, String picture, String description) {
    this.name = name;
    this.picture = picture;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Song> getSongs() {
    return songs;
  }

  public void setSongs(List<Song> songs) {
    this.songs = songs;
  }

  @Override
  public String toString() {
    return "Artist{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", picture='" + picture + '\'' +
        ", description='" + description + '\'' +
        ", songs=" + songs +
        '}';
  }
}
