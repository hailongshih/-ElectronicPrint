package com.us.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.us.example.domain.Form;
import com.us.example.domain.UserInfo;
import com.us.example.repository.FormRepository;
import com.us.example.util.Constant;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {


    @Autowired
    private FormRepository formRepository;

    @RequestMapping("/")
    public String index(Model model){
        return "index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/home")
    public String home(Model model,@RequestParam(value="type",required = false) Integer type) {
        if(type==null){

            return "home";
        }else{
            model.addAttribute("type",type);
            return "home_detail";
        }
    }

    @RequestMapping(value="/forms-data")
    @ResponseBody
    public JSONObject getFormsData(@RequestParam(value="type",required = false) Integer type,@RequestParam(value="limit",required = false) Integer limit,@RequestParam(value="offset",required = false) Integer offset,@RequestParam(value="search",required = false) Integer search){
        UserInfo userInfo=(UserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Form> formList= new ArrayList<>();
        if(!userInfo.getRole().equals(Constant.ROLE_USER)){
            formList=formRepository.findAll(new Specification<Form>(){

                @Override
                public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {

                    List<Predicate> predicates = new ArrayList<>();
                    predicates.add(criteriaBuilder.notEqual(root.<Integer>get("status"),1));
                    predicates.add(criteriaBuilder.equal(root.<String>get("nearestembassy"),userInfo.getNearestembassy()));
                    if(type!=null){
                        predicates.add(criteriaBuilder.equal(root.<Integer>get("type"),type));
                    }
                    if(userInfo.getRole().equals(Constant.ROLE_ASSIANT_OFFICER)){
                        predicates.add(criteriaBuilder.equal(root.<Integer>get("type"),2));
                    }
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }
            });
        }else{
            formList = formRepository.findAllByUserid(userInfo.getUserid());
        }

        JSONObject json= new JSONObject();
        json.put("rows",formList);
        return json;

    }
}

