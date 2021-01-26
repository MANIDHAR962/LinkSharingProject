package ttn.bootcamp.linksharing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ttn.bootcamp.linksharing.entities.Resource;
import ttn.bootcamp.linksharing.entities.User;
import ttn.bootcamp.linksharing.services.RatingService;
import ttn.bootcamp.linksharing.services.ResourceService;
import ttn.bootcamp.linksharing.services.TopicService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    TopicService topicService;

    @Autowired
    ResourceService resourceService;

    @Autowired
    RatingService ratingService;

    @ModelAttribute
    public void myPostModel(@RequestParam(defaultValue = "0") Integer page,HttpSession session, Model model) {

        if (page != 0)
            page = page - 1;

        User user = (User) session.getAttribute("user");
        model.addAttribute("resourcesSubscribed", resourceService.getResourcesOfsubscribedTopics(session,page,user));
        model.addAttribute("trendingTopics", topicService.getTrendingTopics());
    }

    @GetMapping("/post")
    public String getPostPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        session.setAttribute("topicSubscribed", topicService.getSubscribedTopicsByUser(user));
        Resource resource = new Resource();
        model.addAttribute("resource", resource);
        return "post";
    }

    @PostMapping("/editResource")
    public ModelAndView editResource(@RequestParam("resourceId") Integer resourceId, @RequestParam("description") String description, RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView();
        System.out.println("1233" + resourceId);
        Resource resource = resourceService.updateResourceDescription(resourceId, description);
        if (resource == null) {
            redirectAttributes.addFlashAttribute("updateDescMsg", "description updated");
        }
        else{
            redirectAttributes.addFlashAttribute("updateDescMsg", "description not updated");
        }
        modelAndView.setViewName("redirect:/post");
        return modelAndView;
    }

    @PostMapping("/resourceRating")
    @ResponseBody
    public void resourceRating(@RequestParam("rating") Integer rating, @RequestParam("resourceId") Integer resourceId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        ratingService.saveRating(user, rating, resourceId);

    }

    @GetMapping("/postRating/{id}")
    public String viewPost(@PathVariable Integer id,Model model) {

        User user = new User();
        Resource resource = resourceService.getResourceById(id);
        Integer rating = resourceService.getAvgRatingByResourceId(id);
        Integer leftRating = 0;
        List<Integer> ratingList=new ArrayList<>();
        List<Integer> leftRatingList=new ArrayList<>();
        if(rating==0){
            leftRating = 5;
        for(int i=1;i<=leftRating;i++)
            leftRatingList.add(i);
        }
        else{
            leftRating = 5-rating;
            for(int i=1;i<=rating;i++)
                ratingList.add(i);
            for(int i=1;i<=leftRating;i++)
                leftRatingList.add(i);
        }
        System.out.println(rating +"- : -"+leftRating);
        model.addAttribute("resource",resource);
        model.addAttribute("rate",ratingList);
        model.addAttribute("leftRate",leftRatingList);
        model.addAttribute("user",user);
        return "postRate";
    }

}
