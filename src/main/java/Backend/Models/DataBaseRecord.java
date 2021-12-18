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



    @Getter
    @Setter
    protected Date dateCreated;


    protected abstract int dbAdd() throws Exception;
    protected abstract int dbSave() throws Exception;
    protected abstract int dbUpdate() throws Exception;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataBaseRecord that)) return false;
        return id == that.id;
    }

}
