package ru.stqa.pft.addressbook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by IrinaIv on 6/27/2017.
 */

@Entity
@Table(name = "address_in_groups")
public class ContactGroupData {

  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "group_id")
  private int group_id;


  public int getGroup_id() {
    return group_id;
  }

  public int getId() {
    return id;
  }
}

