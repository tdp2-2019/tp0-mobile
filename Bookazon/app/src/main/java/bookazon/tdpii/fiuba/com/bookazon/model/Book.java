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
    public Boolean epub;
    public Boolean pdf;
    public String downloadingLinkEPUB;
    public String downloadingLinkPDF;

    private String concatenateListOfString(List<String> list){
        String result = "";

        if (list != null) {
            for (int i = 0; i <= list.size() - 1; i++) {
                result = result.concat(list.get(i) + ", ");
            }
            return result.substring(0, result.length()-2);
        }
       return result;
    }

    public String getAuthors(){

       return concatenateListOfString(authors);

    }

    public String getLabels(){

        return concatenateListOfString(labels);
    }


}
