package Backend.Models;

import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;
import java.util.Date;

public abstract class DataBaseRecord {

    @Getter
    @Setter
    protected int id = -1;

    @Getter
    @Setter
    protected Date dateCreated;


    public abstract int dbAdd() throws SQLException;
    protected abstract int dbSave() throws SQLException;
    protected abstract int dbUpdate() throws SQLException;
}
