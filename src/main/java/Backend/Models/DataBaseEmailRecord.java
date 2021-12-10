package Backend.Models;

import lombok.Getter;
import lombok.Setter;

public abstract class DataBaseEmailRecord extends DataBaseRecord{


    @Getter
    @Setter
    protected String email;


}
