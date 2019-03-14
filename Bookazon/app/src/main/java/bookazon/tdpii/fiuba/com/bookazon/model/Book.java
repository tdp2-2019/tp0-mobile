package bookazon.tdpii.fiuba.com.bookazon.model;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {

    public String id;
    public String title;
    public List<String> authors;
    public List<String> categories;
    public List<String> labels;
    public String description;
    public String coverLink;
    public String downloadingLink;
    public Boolean epub;
    public Boolean pdf;

}
