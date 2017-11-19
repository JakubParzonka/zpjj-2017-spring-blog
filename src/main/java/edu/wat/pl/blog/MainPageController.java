package edu.wat.pl.blog;

//import edu.wat.pl.blog.auth.SessionInfo;

import edu.wat.pl.blog.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class MainPageController {

//    @Autowired
//    private SessionInfo session;

    @Autowired
    private PostService postService;

    // inject via application.properties
    @Value("${welcome.message:test}")
    private String message = "Hello World";

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("message", this.message);
        model.put("posts", postService.findAllPosts());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.put("username", ((auth == null) ? "unknown" : auth.getName()));
        return "mainPage";
    }

}