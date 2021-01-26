package ttn.bootcamp.linksharing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ttn.bootcamp.linksharing.entities.Resource;
import ttn.bootcamp.linksharing.entities.User;
import ttn.bootcamp.linksharing.services.ResourceService;

import javax.servlet.http.HttpSession;

@Controller
public class LinkResourceController {

    @Autowired
    ResourceService resourceService;

    @PostMapping("/createLinkResource")
    public ModelAndView createLink(@ModelAttribute("resource") Resource linkResource, @RequestParam("resourcetopic") Integer topicId, HttpSession session, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");
        Resource resource = resourceService.createLinkResource(linkResource, user, topicId);
        if (resource != null) {
            redirectAttributes.addFlashAttribute("postmsg", "link created");

        } else
            redirectAttributes.addFlashAttribute("postmsg", "link not created");

        modelAndView.setViewName("redirect:/dashboard");
        return modelAndView;
    }

    @PostMapping("/createLinkResourceFromPostPage")
    public ModelAndView createLinkFromPostPage(@ModelAttribute("resource") Resource linkResource, @RequestParam("resourcetopic") Integer topicId, HttpSession session, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");
        Resource resource = resourceService.createLinkResource(linkResource, user, topicId);
        if (resource != null) {
            redirectAttributes.addFlashAttribute("postmsg", "link created");
        } else
            redirectAttributes.addFlashAttribute("postmsg", "link not created");
        modelAndView.setViewName("redirect:/post");
        return modelAndView;
    }

    @PostMapping("/createLinkResourceFromTopicPage/{id}")
    public ModelAndView createLinkFromTopicPage(@PathVariable("id") Integer id, @ModelAttribute("resource") Resource linkResource, @RequestParam("resourcetopic") Integer topicId, HttpSession session, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");
        Resource resource = resourceService.createLinkResource(linkResource, user, topicId);

        if (resource != null) {
            redirectAttributes.addFlashAttribute("postmsg", "link created");
        } else
            redirectAttributes.addFlashAttribute("postmsg", "link not created");

        modelAndView.setViewName("redirect:/topicPage/" + id);
        return modelAndView;
    }

    @PostMapping("/createLinkResourceFromAllTopicsPage")
    public ModelAndView createLinkFromAllTopicsPage(@ModelAttribute("resource") Resource linkResource, @RequestParam("resourcetopic") Integer topicId, HttpSession session, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");
        Resource resource = resourceService.createLinkResource(linkResource, user, topicId);

        if (resource != null) {
            redirectAttributes.addFlashAttribute("postmsg", "link created");
        } else
            redirectAttributes.addFlashAttribute("postmsg", "link not created");

        modelAndView.setViewName("redirect:/alltopicsPage");
        return modelAndView;
    }


}
