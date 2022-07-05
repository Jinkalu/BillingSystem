package com.billingsys.main.controllers;

import com.billingsys.main.models.Admin;
import com.billingsys.main.models.Consumer;
import com.billingsys.main.models.Slabs;
import com.billingsys.main.service.AdminService;
import com.billingsys.main.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class AdminControl {

    @Autowired
    private AdminService as;
    @Autowired
    ConsumerService cs;

    @GetMapping("/admin")
    public ModelAndView admin(){
        return new ModelAndView("adminLogIn");
    }

    @PostMapping("/adminValidation")
    @ResponseBody
    public ModelAndView a_log(@ModelAttribute Admin a,
                              HttpServletRequest request){

        if(as.verify(a)!=null){

            request.getSession().setAttribute("aname",a.getName());

            return new ModelAndView("redirect:/dashBoard");

        }else {

            return new ModelAndView("redirect:admin","sts",true);
        }


    }

    @GetMapping("/dashBoard")
    @ResponseBody
    public ModelAndView dash(HttpServletRequest request){

        if(request.getSession(false)!=null) {

            List<Slabs> s= cs.getSlabs();

            return new ModelAndView("dashBoard","s",s);
        }else {
            return new ModelAndView("admin","sts",true);
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable int id){

        Slabs s=as.getSlab(id);

        return new ModelAndView("editForm2","s",s);
    }

    @PostMapping("/update")
    @ResponseBody
    public ModelAndView update(@ModelAttribute Slabs s,
                               HttpServletRequest request){
        if(request.getSession(false)!=null) {
            as.updateSlab(s);


            return new ModelAndView("redirect:/dashBoard");
        }else {
            return new ModelAndView("admin","sts",true);
        }
    }

    @GetMapping("/users")
    public ModelAndView users(HttpServletRequest request){

        if(request.getSession(false)!=null) {

            List<Consumer> cli= as.getDetailes();

            return new ModelAndView("users", "m", cli);
        }else {
            return new ModelAndView("admin","sts",true);
        }
    }
    //////////////////////////////////////////////////////////////
    @GetMapping("/addSlab")
    public ModelAndView addForm(){
        return new ModelAndView("addSlabs");
    }

    @PostMapping("adds")
    public ModelAndView addS(@ModelAttribute Slabs s){
        as.addSlabs(s);
        return new ModelAndView("/dashBoard");
    }
    /////////////////////////////////////////////////////////////////
    @GetMapping("/logoutA")
    public ModelAndView logout(HttpServletRequest request){

        HttpSession session=request.getSession(false);
        if(session==null){
            return admin();
        }else {
            session.invalidate();
            return admin();
        }

    }


}
