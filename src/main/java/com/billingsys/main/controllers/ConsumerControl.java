package com.billingsys.main.controllers;

import com.billingsys.main.models.Consumer;
import com.billingsys.main.models.Readings;
import com.billingsys.main.models.Slabs;
import com.billingsys.main.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/billing")
public class ConsumerControl {
    @Autowired
    private ConsumerService cs;

    @GetMapping("/")
    public ModelAndView home(){
        return new ModelAndView("index");
    }

    @GetMapping("/home2")
    public ModelAndView home2(){
        return new ModelAndView("home2");
    }

    @GetMapping("/slabs")
    public ModelAndView slabs(){

        return new ModelAndView("slabs","s",cs.getSlabs());
    }

    //registration
    @GetMapping("/register")
    public ModelAndView regform(){
        return new ModelAndView("register");
    }

    @PostMapping("/save")
    public ModelAndView save_c(@ModelAttribute Consumer c, @ModelAttribute Readings r){

        cs.saveConsumer(c,r);


        return new ModelAndView("register","sts",true);
    }
//registration end


    @GetMapping("/login")
    @ResponseBody
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @PostMapping("/userValidation")
    public ModelAndView log_veri(Consumer u , HttpServletRequest request){


        if(cs.verify(u)!=null){
            request.getSession().setAttribute("uname",(cs.verify(u)).getName());


            return new ModelAndView("redirect:/profile");

        }else {

            return new ModelAndView("redirect:/login","sts",true);
        }


    }

    @GetMapping("/profile")
    @ResponseBody
    public ModelAndView profile(HttpServletRequest request){

        if(request.getSession(false)!=null) {

            Consumer c=cs.getConsumer((String) (request.getSession().getAttribute("uname")));


            ModelAndView mv =new ModelAndView();

            mv.addObject("c",c);

            mv.setViewName("profile");

            return mv;
        }else {
            return new ModelAndView("login","sts2",true);
        }

    }

    @GetMapping("/bill/{id}")
    public ModelAndView bills(@PathVariable int id){
        List<Readings> rl=cs.getReadings(id);
        return new ModelAndView("bills","rl",rl);
    }
    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable int id) {

        Consumer c=cs.getConsumerById(id);


        return new ModelAndView("editForm","c",c);
    }

    @PostMapping("/saveUpdate")
    public ModelAndView update_c(@ModelAttribute Consumer c){


        cs.updateConsumer(c);


        return new ModelAndView("redirect:/profile");
    }

    @GetMapping("/billForm")
    public ModelAndView calc(HttpServletRequest request){

        if (request.getSession(false)==null){

            return new ModelAndView("login","sts2",true);
        }else {

            List<Slabs> sli=cs.getSlabs();
            Readings preread=cs.lastReading((String) request.getSession(false).getAttribute("uname"));

            ModelAndView mv=new ModelAndView();
            mv.addObject("r",preread);
            mv.addObject("sli",sli);
            mv.setViewName("billForm");

            return mv;
        }
    }

    @PostMapping("/calc")
    public ModelAndView bill(@RequestParam("previousReading") double previousReading,
                             @RequestParam("currentReading") double currentReading,
                             @RequestParam("month") int m){
        double bill=0.0;
        if (m==1) {

            bill = cs.cal(previousReading, currentReading);
        }else {
            bill=cs.cal2(previousReading,currentReading);
        }

        ModelAndView mv=new ModelAndView();
        mv.addObject("bill",bill);
        mv.addObject("current",currentReading);
        mv.setViewName("redirect:/billForm");
        return mv;
    }

    @PostMapping("/saveBill")
    public ModelAndView save(@RequestParam("previousReading") String previousReading,
                             @RequestParam("previousBill") String previousBill,
                             HttpServletRequest request){

        Readings r=new Readings();
        r.setPreviousBill(Double.parseDouble(previousBill));
        r.setPreviousReading(Double.parseDouble(previousReading));


        cs.updateReadings(r,(String)request.getSession(false).getAttribute("uname"));


        return new ModelAndView("redirect:/profile");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request){

        HttpSession session=request.getSession(false);
        if(session==null){
            return login();
        }else {
            session.invalidate();
            return login();
        }

    }
}
