package net.alex.web.parser.controller;


import net.alex.app.parser.facade.PdfFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.Map;

@Controller
public class MyController {

    WebApplicationContext webApplicationContext;

    PdfFacade pdfFacade;

    @RequestMapping(value = "/")
    public ModelAndView  index(Map<String, Object> model){
        //redirection/ its return ModelAnDView object? not a String
        return new ModelAndView("redirect:" + "hello");
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello(Map<String, Object> model){

        model.put("name", "ALEX\"");
        return "hello2";
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile mpfile, ModelMap modelMap) {

        //todo// its fake document
        ServletContext servletContext = webApplicationContext.getServletContext();
        modelMap.addAttribute("dickWords", pdfFacade.countWord(new File(servletContext.getRealPath("/WEB-INF/files/temp.pdf"))));

        return "result";
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(80000000);
        return multipartResolver;
    }

    @Autowired
    public void setPdfFacade(PdfFacade pdfFacade) {
        this.pdfFacade = pdfFacade;
    }

    @Autowired
    public void setWebApplicationContext(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }
}
