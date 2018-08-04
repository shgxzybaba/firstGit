package com.shgxzyjune.personalproject.classroom;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.ZoneId;

@Embeddable
public class Status {

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Mark mark;

    public Status() {
        this.date = LocalDate.now(ZoneId.systemDefault());
        this.mark = Mark.PRESENT;
    }

    public Status(Mark mark) {
        this.date = LocalDate.now(ZoneId.systemDefault());
        this.mark = mark;
    }

    public LocalDate getDate() {
        return date;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }
}
