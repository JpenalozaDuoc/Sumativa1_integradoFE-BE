package grupo15;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Controller
public class HomeController {

    private TokenStore tokenStore;

    public HomeController (TokenStore tokenStore){
        super();
        this.tokenStore = tokenStore;
    }

    @GetMapping("/**")
    public String home(@RequestParam (name="name",required = false, defaultValue = "Seguridad Software") String name,Model model) {
        model.addAttribute("name",name);
        return "Home";
    }

    @GetMapping("/")
    public String root(@RequestParam (name="name",required = false, defaultValue = "Seguridad y Calidad en Desarrollo") String name,Model model) {
        model.addAttribute("name",name);
        return "Home";
    }

    @GetMapping("/greetings")
        public String greeting(@RequestParam(name="name", required=false, defaultValue="Juan González") String name, Model model) {
            
            String url = "http://localhost:8080/greetings";

            final var restTemplate = new RestTemplate();
            String token = tokenStore.getToken();

            // Crear los encabezados y añadir el token
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", token);
            
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            // Agregar parámetros a la URL
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("name", name);

            // Realizar la petición con el token en el encabezado y los parámetros en la URL
            ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

            System.out.println("Response: " + response);

            model.addAttribute("name", response.getBody());
            return "Greetings";
        }
    
}
