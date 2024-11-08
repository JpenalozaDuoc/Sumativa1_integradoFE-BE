package be_grupo15;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class SecuredController {

    @RequestMapping("greetings")
    public String greetings(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello {" + name + "}";
    }
    
}
