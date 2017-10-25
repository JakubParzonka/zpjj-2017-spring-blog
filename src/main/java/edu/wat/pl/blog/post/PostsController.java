package edu.wat.pl.blog.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Controller
public class PostsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Autowired
//    PostService postService;

    @RequestMapping(value = "/add_post", method = RequestMethod.GET)
    public String admin(Model model) {
        model.addAttribute("post", new Post());
        return "addPost";
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public String addPost(Post post) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        post.setCreationDate(dtf.format(LocalDate.now()));
        //TODO handle tags
        //       post.setTags(Arrays.asList(post.getWrongTags().split(" ")));
        logger.info("Post object has been captured: " + post.toString());

        return "redirect:/";
    }

/*

    @RequestMapping("/X")
    public String x(HttpServletRequest request, @ModelAttribute("postDto") PostDTO postDTO, BindingResult result, Model model) {
//        System.out.println(request.getMethod() + "    /     " + result.getFieldError().toString());
        if (request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()) {
            Post post = new Post();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            post.setCreationDate(dtf.format(LocalDate.now()));
            post.setTitle(postDTO.getTitle());
            post.setContents(postDTO.getContents());
            System.out.println(post.toString());
            model.addAttribute("postDto", postDTO);
            //TODO handle database insert
            System.out.println("Hello!!!!!!!!!!!!!!!!!!!!!!!!");
            return "redirect:/hello";
        }
        return "addPost";
    }
*/
}
