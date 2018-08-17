package com.shgxzyjune.personalproject.classroom;

import javax.persistence.*;
import java.util.List;

@Entity
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id = 0;



    @ElementCollection
    @Embedded
    private List<Status> statuses;


    public Register() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }


    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }
}
