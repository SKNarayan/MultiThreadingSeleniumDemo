package generic;

import com.aventstack.extentreports.markuputils.Markup;

public class CreateLink implements Markup {

    private String filePath;
    private String linkName;

    public CreateLink(String path, String name){
        this.filePath = path;
        this.linkName = name;
    }

    @Override
    public String getMarkup() {
        final String htmlTag = "<a href='"+filePath+"' target='_new'style = 'color:blue;'><u>"+linkName+"</u></a>";
        return htmlTag;
    }
}
