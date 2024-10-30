package barisla.example.prova.expetions;

import lombok.Getter;

@Getter
public class ResorceNotFound extends Exception {
    private String id;
    private String resourceName;

    public ResorceNotFound(String id, String resourceName) {
        super();
        this.id = id;
        this.resourceName = resourceName;
    }


}
