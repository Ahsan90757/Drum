package dev.rayyan.drums.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReactController {

    @RequestMapping(value = "/{path:[^\\.]*}") // Matches all paths without a period (e.g., .js, .css, etc.)
    public String redirect() {
        return "forward:/"; // This will forward to the static index.html
    }
}
