package com.tslcompany.controllers;

import com.tslcompany.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class ManagementController {

    private final UserService userService;

    public ManagementController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/management")
    public String managementForm() {
        return "management-panel";
    }
    @GetMapping("/forwarder-list")
    public String forwarderManagementForm(Model model){
        List<String> allForwarders = userService.findAllForwarderEmail();
        model.addAttribute("forwardersEmail", allForwarders);
        return "forwarders";
    }

    @GetMapping("/delete-forwarders")
    public String deleteUser(@RequestParam String email){
        userService.deleteUserByEmail(email);
        return "redirect:/forwarders";
    }

    @GetMapping("/assign-task")
    public String assignNewTask(){
        return "assign-task-employees";
    }

    @PostMapping("/assign-role")
    public String assignRoleToEmployee(@RequestParam Long userId, @RequestParam String roleName){
        userService.setNewRole(userId,roleName);
        return "redirect:/assign-task-employees";
    }


}
