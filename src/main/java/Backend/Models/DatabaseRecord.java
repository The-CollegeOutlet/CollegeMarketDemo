package Backend.Models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DatabaseRecord {
    @Getter
    protected int id;
    @Getter
    @Setter
    protected String email;
    @Getter
    protected String dateTimeCreated;
    @Getter
    private LocalDateTime timeNow;

    public DatabaseRecord() {
        this.timeNow = LocalDateTime.now();
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.dateTimeCreated = this.timeNow.format(formatter);

    }


    protected String time(){
        this.timeNow = LocalDateTime.now();
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.dateTimeCreated = this.timeNow.format(formatter);
        return this.dateTimeCreated;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof DatabaseRecord)) return false;
        DatabaseRecord record = (DatabaseRecord) object;
        return id == record.id && email.equals(record.email);
    }

    @Override
    public String toString() {
        return "DatabaseRecord{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", dateTimeCreated='" + dateTimeCreated + '\'' +
                '}';
    }
}
