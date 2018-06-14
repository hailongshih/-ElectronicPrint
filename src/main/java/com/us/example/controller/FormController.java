package com.us.example.controller;

import com.us.example.domain.Form;
import com.us.example.domain.UserInfo;
import com.us.example.repository.FormRepository;
import com.us.example.service.WordService;
import com.us.example.util.Constant;
import com.us.example.util.EnumConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditorSupport;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Controller
public class FormController {

    @Autowired
    private FormRepository formRepository;
    @Autowired
    private WordService wordService;

    private DateFormat format = new SimpleDateFormat(Constant.DATE_FORMAT);
    Logger logger= Logger.getLogger(FormController.class);

    @RequestMapping(path = "/forms", method = RequestMethod.GET)
    public String getForms(Model model) {
        List<Form> forms = formRepository.findAll();
        model.addAttribute("forms", forms);
        return "forms";
    }

    @RequestMapping(value = "/forms/add/{type}",method =RequestMethod.GET)
    public String addForm(@PathVariable Integer type, Model model, HttpServletRequest request){
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
                .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        UserInfo userInfo=(UserInfo)securityContextImpl.getAuthentication().getPrincipal();
        Form form =new Form();
        form.setApplydate(new Date());
        form.setFirstname(userInfo.getFirstName());
        form.setLastname(userInfo.getLastName());
        form.setAddress(userInfo.getAddress());
        form.setTelephone(userInfo.getTelephone());
        form.setEmail(userInfo.getEmail());
        form.setUserid(userInfo.getUserid());
        form.setNearestembassy(userInfo.getNearestembassy());
        form.setConsularid(Integer.valueOf(generateRandom()));
        form.setType(type);
        form.setStatus(Constant.PENDING);
        model.addAttribute("method", "add");
        model.addAttribute("form", form);
        model.addAttribute("citizenships", EnumConstant.Citizenship.values());
        model.addAttribute("visaTypes", EnumConstant.VisaType.values());
        String page="";
        if(type==1){
            page= "visa";
            model.addAttribute("maritalstatuss", EnumConstant.MaritalStatus.values());
            model.addAttribute("appstatuss", EnumConstant.ApplicationStatus.values());
        }else if(type==2){
            page= "idCard";
        }
        return page;
    }

    @RequestMapping(value="/forms/add",method= RequestMethod.POST)
    public String addForm(Form form) throws  ParseException{
        form.setApplydate(new Date());
        Form formAdded = formRepository.saveAndFlush(form);
        return "redirect:/home";
    }

    @RequestMapping(value="/forms/submit/{type}",method= RequestMethod.POST)
    public String editForm(@PathVariable Integer type,@ModelAttribute Form form)  throws IOException{
        form.setApplydate(new Date());
        Form formEdited = formRepository.saveAndFlush(form);
        return "redirect:/home";
    }

    @RequestMapping(value="/form/save",method= RequestMethod.POST)
    public String saveForm(@ModelAttribute Form form)  throws IOException{

        Form formEdited = formRepository.saveAndFlush(form);
        return "redirect:/home";
    }

    @RequestMapping(value="/forms/view/{id}",method= RequestMethod.GET)
    public String viewForm(@PathVariable Integer id,Model model) {

        Form form = formRepository.findOne(id);
        model.addAttribute("method", "view");
        model.addAttribute("citizenships", EnumConstant.Citizenship.values());
        model.addAttribute("form", form);
        String page="";
        if(form.getType()==1){
            page= "visa";
            model.addAttribute("maritalstatuss", EnumConstant.MaritalStatus.values());
            model.addAttribute("appstatuss", EnumConstant.ApplicationStatus.values());
        }else if(form.getType()==2){
            page= "idCard";
            model.addAttribute("visaTypes", EnumConstant.VisaType.values());
        }
        return page;
    }

    @RequestMapping(value="/forms/edit/{id}",method= RequestMethod.GET)
    public String addForm(@PathVariable Integer id,Model model) {

        Form form = formRepository.findOne(id);
        model.addAttribute("method", "edit");
        model.addAttribute("citizenships", EnumConstant.Citizenship.values());
        model.addAttribute("form", form);
        String page="";
        if(form.getType()==1){
            page= "visa";
            model.addAttribute("maritalstatuss", EnumConstant.MaritalStatus.values());
            model.addAttribute("appstatuss", EnumConstant.ApplicationStatus.values());
        }else if(form.getType()==2){
            page= "idCard";
            model.addAttribute("visaTypes", EnumConstant.VisaType.values());
        }
        return page;
    }

    @RequestMapping(value="/forms/delete/{id}",method= RequestMethod.GET)
    public String deleteForm(@PathVariable Integer id) {

        formRepository.delete(id);
        return "redirect:/forms";
    }

    @RequestMapping(value="/forms/printdoc/{id}",method= RequestMethod.GET)
    public String printForm(@PathVariable Integer id, RedirectAttributes ra,@RequestParam(value="printtype", required=false) String printtype){
        Form form = formRepository.findOne(id);
        int type=form.getType();
        if(type==1){
            if(StringUtils.isNotBlank(printtype)&&printtype.equals("sticker")){
                try {
                    logger.info("invoke genVisaStickerWordFile method");
                    wordService.genVisaStickerWordFile(form);
                }catch (Exception e) {
                    e.printStackTrace();
                }finally {

                }
                /*return "redirect:https://view.officeapps.live.com/op/view.aspx?src=www.cocoshi.tech%3A9090%2Fprintdoc%2Fvisasticker%2Fvisasticker_"+form.getId()+".doc";*/
                return "redirect:https://view.officeapps.live.com/op/view.aspx?src=http%3A%2F%2Fembassyforms.com%2Fvisasticker_35.doc";
            }else{
                try {
                    logger.info("invoke genVisaWordFile method");
                    wordService.genVisaWordFile(form);
                }catch (Exception e) {
                    e.printStackTrace();
                }finally {

                }
                /*return "redirect:https://view.officeapps.live.com/op/view.aspx?src=www.cocoshi.tech%3A9090%2Fprintdoc%2Fvisa%2Fvisa_"+form.getId()+".doc";*/
                return "redirect:https://view.officeapps.live.com/op/view.aspx?src=http%3A%2F%2Fembassyforms.com%2Fvisa_35.doc";
            }
        }else if(type==2){
            try {
                logger.info("invoke genIDCardWordFile method");
                wordService.genIDCardWordFile(form);
            }catch (Exception e) {
                e.printStackTrace();
            }finally {

            }
            /*return "redirect:https://view.officeapps.live.com/op/view.aspx?src=www.cocoshi.tech%3A9090%2Fprintdoc%2Fidcard%2Fidcard_"+form.getId()+".doc";*/
            return "redirect:https://view.officeapps.live.com/op/view.aspx?src=http%3A%2F%2Fembassyforms.com%2Fidcard_22.doc";
        }
        return null;
    }

    @RequestMapping(value="/getimage/{id}",method= RequestMethod.GET)
    public void getImages(@PathVariable Integer id,HttpServletResponse response) throws  ParseException{
        ServletOutputStream out = null;
        Form form =  formRepository.findOne(id);
        InputStream is = new ByteArrayInputStream(form.getPhoto());
        response.setContentType("image/*");
        try{
            out = response.getOutputStream();
            int len=0;
            byte[]buf=new byte[1024];
            while((len=is.read(buf,0,1024))!=-1){
                out.write(buf, 0, len);
            }
            out.flush();
            out.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                try {
                    setValue(new SimpleDateFormat(Constant.DATE_FORMAT).parse(value));
                } catch(ParseException e) {
                    setValue(null);
                }
            }
            public String getAsText() {
                if(getValue()==null){
                    return "";
                }else{
                    return new SimpleDateFormat(Constant.DATE_FORMAT).format((Date) getValue());
                }
            }

        });

    }
    public String generateRandom(){
        Random random = new Random();
        String result="";
        for (int i=0;i<6;i++)
        {
            if(i==0){
                int j;
                if((j=random.nextInt(10))!=0){
                    result+=j;
                }else{
                    result+=j+1;
                }
            }else{
                result+=random.nextInt(10);
            }
        }
        return result;
    }
}
