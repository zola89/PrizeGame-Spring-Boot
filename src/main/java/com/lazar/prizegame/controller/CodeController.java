package com.lazar.prizegame.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lazar.prizegame.model.Code;
import com.lazar.prizegame.service.CodeService;
import com.lazar.prizegame.service.UserService;

@Controller
public class CodeController {
    @Autowired
    private CodeService codeService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/code")
    public String index(Model model) {
        model.addAttribute("codes", codeService.findAll());
        return "code_list";
    }

    @GetMapping("/code/create")
    public String create(Model model) {
        model.addAttribute("code", new Code());
        return "code_form";
    }

    @GetMapping("/code/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("code", codeService.findOne(id));
        return "code_form";
        
    }

    @PostMapping("/code/save")
    public String save(@Valid Code code, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "code_form";
        }
        codeService.save(code);
        redirect.addFlashAttribute("success", "Saved code successfully!");
        return "redirect:/code";
    }

    @GetMapping("/code/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        codeService.delete(id);
        redirect.addFlashAttribute("success", "Deleted code successfully!");
        return "code_list";
    }
    
    @GetMapping("/code/{user_id}")
    public String getCodeByUserId(@PathVariable int user_id, Model model) {
    	model.addAttribute("codes", codeService.findByUserId(user_id));
    	model.addAttribute("user", userService.findOne(user_id));
        return "code_list";
    }
    
    @GetMapping(value= "/code/{id}/add")
    public String getCodeByPrizeCode(@PathVariable("id") int id, @RequestParam("code") String code, Model model) {
    	model.addAttribute("code", codeService.findByPrizeCode(code, id));
    	model.addAttribute("user", userService.findOne(id));
        return "code_list";
    }

}
