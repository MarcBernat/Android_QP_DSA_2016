package marc.edu.upc.eetac.dsa.beeter_app.Beeter.entity;

import java.net.URI;
import java.util.List;

/**
 * Created by Marc on 8/05/2016.
 */
public class Link {
    private URI uri;
    private String rel;
    private List<String> rels;
    private String title;
    private String type;

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public List<String> getRels() {
        return rels;
    }

    public void setRels(List<String> rels) {
        this.rels = rels;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

