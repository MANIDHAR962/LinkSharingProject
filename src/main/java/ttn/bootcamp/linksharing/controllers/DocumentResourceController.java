package ttn.bootcamp.linksharing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ttn.bootcamp.linksharing.entities.Resource;
import ttn.bootcamp.linksharing.entities.User;
import ttn.bootcamp.linksharing.services.ResourceService;

import javax.servlet.http.HttpSession;

@Controller
public class DocumentResourceController {

    @Autowired
    ResourceService resourceService;

    @PostMapping("/createDocumentResource")
    public ModelAndView createDocument(@ModelAttribute("resource") Resource documentResource, @RequestParam("path") MultipartFile file, @RequestParam("resourcetopic") Integer topicId, HttpSession session, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");
        Resource resource = resourceService.createDocumentResource(documentResource, user, topicId, file);
        if (resource != null) {
            redirectAttributes.addFlashAttribute("postmsg", "document created");

        } else
            redirectAttributes.addFlashAttribute("postmsg", "document not created");

        modelAndView.setViewName("redirect:/dashboard");
        return modelAndView;
    }

    @PostMapping("/createDocumentResourceFromPostPage")
    public ModelAndView createDocumentFromPostPage(@ModelAttribute("resource") Resource documentResource, @RequestParam("path") MultipartFile file, @RequestParam("resourcetopic") Integer topicId, HttpSession session, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");
        Resource resource = resourceService.createDocumentResource(documentResource, user, topicId, file);

        if (resource != null) {
            redirectAttributes.addFlashAttribute("postmsg", "document created");
        } else
            redirectAttributes.addFlashAttribute("postmsg", "document not created");

        modelAndView.setViewName("redirect:/post");
        return modelAndView;
    }

    @PostMapping("/createDocumentResourceFromTopicPage/{id}")
    public ModelAndView createDocumentFromTopicPage(@PathVariable("id") Integer id, @ModelAttribute("resource") Resource documentResource, @RequestParam("path") MultipartFile file, @RequestParam("resourcetopic") Integer topicId, HttpSession session, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");
        Resource resource = resourceService.createDocumentResource(documentResource, user, topicId, file);

        if (resource != null) {
            redirectAttributes.addFlashAttribute("postmsg", "document created");

        } else
            redirectAttributes.addFlashAttribute("postmsg", "document not created");

        modelAndView.setViewName("redirect:/topicPage/" + id);
        return modelAndView;
    }

    @PostMapping("/createDocumentResourceFromAllTopicsPage")
    public ModelAndView createDocumentFromAllTopicsPage(@ModelAttribute("resource") Resource documentResource, @RequestParam("path") MultipartFile file, @RequestParam("resourcetopic") Integer topicId, HttpSession session, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");
        Resource resource = resourceService.createDocumentResource(documentResource, user, topicId, file);

        if (resource != null) {
            redirectAttributes.addFlashAttribute("postmsg", "document created");

        } else
            redirectAttributes.addFlashAttribute("postmsg", "document not created");

        modelAndView.setViewName("redirect:/alltopicsPage");
        return modelAndView;
    }
}
