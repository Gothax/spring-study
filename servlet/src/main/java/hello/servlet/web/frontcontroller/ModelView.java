package hello.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class ModelView {

    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    private String viewName;
    private Map<String, Object> model = new HashMap<>();

}
