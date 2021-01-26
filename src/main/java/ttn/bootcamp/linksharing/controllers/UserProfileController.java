package ttn.bootcamp.linksharing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ttn.bootcamp.linksharing.entities.User;
import ttn.bootcamp.linksharing.services.ResourceService;
import ttn.bootcamp.linksharing.services.SubscriptionService;
import ttn.bootcamp.linksharing.services.TopicService;

import javax.servlet.http.HttpSession;

@Controller
public class UserProfileController {

    @Autowired
    TopicService topicService;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    ResourceService resourceService;

    @GetMapping("/userProfile")
    public String getUserProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("topicSubscribedCount", subscriptionService.getSubscriptionCountOfUser(user));
        model.addAttribute("topicsCreatedCount", topicService.getTopicsCreatedCountOfUser(user));
        model.addAttribute("topicSubscribed", topicService.getSubscribedTopicsByUser(user));
        model.addAttribute("createdTopics", topicService.getTopicsCreatedByUser(user));
        model.addAttribute("resourcesCreated",resourceService.getResourcesCreatedByUser(user));

        return "userprofile";
    }


}
