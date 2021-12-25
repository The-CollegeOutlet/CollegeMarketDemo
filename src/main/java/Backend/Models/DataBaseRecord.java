package Backend.Models;

import lombok.Getter;
import lombok.Setter;


import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

public abstract class DataBaseRecord {

    @Getter
    @Setter
    protected int id = -1;

    protected boolean GetFromDatabase = true;



    @Getter
    @Setter
    protected Date dateCreated;


    protected abstract int dbAdd() throws Exception;
    protected abstract int dbSave() throws Exception;
    protected abstract int dbUpdate() throws Exception;

    public void StopGreedy() {
        GetFromDatabase = false;
    }

    public void AllowGreedy() {
        GetFromDatabase = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataBaseRecord that)) return false;
        return id == that.id;
    }

}
