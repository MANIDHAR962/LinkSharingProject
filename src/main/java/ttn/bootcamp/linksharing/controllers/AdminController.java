package ttn.bootcamp.linksharing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ttn.bootcamp.linksharing.entities.User;
import ttn.bootcamp.linksharing.services.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @RequestMapping("/userList")
    public String userListPageForAdmin(Model model, @RequestParam(defaultValue = "0") Integer page, HttpSession session) {

        if (page != 0)
            page = page - 1;
        List<User> usersList = userService.getAllUsers(session, page);
        model.addAttribute("userList", usersList);
        model.addAttribute("currentPage", page);
        return "admin";
    }

    @GetMapping("/activateUser/{id}")
    public String activateUser(@PathVariable Integer id) {
        userService.updateUserActive(true, id);
        return "redirect:/userList";
    }

    @GetMapping("/deactivateUser/{id}")
    public String deactivateUser(@PathVariable Integer id) {
        userService.updateUserActive(false, id);
        return "redirect:/userList";
    }

   /* @PostMapping("/showRecordsBySelected")
    public String getUsersBySelected(Model model, String value){
        //System.out.println(value);
        List<User> users = null;
        if(value.equals("true"))
             users = userService.getUserByActive(true);
        else if(value.equals("false"))
            users = userService.getUserByActive(false);
        else
            users = userService.getAllUser();
        //model.addAttribute("selectValue",value);
        model.addAttribute("users",users);
        //System.out.println(users);
        return "usersForAdmin";
    }

    @PostMapping("/sortIdByAscendingOrder")
    public String getUserSortIdByAscendingOrder(Model model){
        List<User> users = userService.getUsersSortedByIdAscending();
        model.addAttribute("users",users);
        return "usersForAdmin";
    }

    @PostMapping("/sortIdByDescendingOrder")
    public String getUserSortIdByDescendingOrder(Model model){
        List<User> users = userService.getUsersSortedByIdDescending();

        model.addAttribute("users",users);
        return "usersForAdmin";
    }*/
}
