package com.g2.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.g2.constant.ViewContant;
import com.g2.model.ContactModel;
import com.g2.service.IContactService;

//@PreAuthorize("permitAll()")
@Controller
@RequestMapping("/contact")
public class ContactController {
    private static final Log LOGGER = LogFactory.getLog(ContactController.class);

    @Autowired
    @Qualifier("contactService")
    private IContactService contactService;

    // preauthorize: permite escribir expresiones de spring
    // estos se pueden colocar tambien a nivel de clase
    // incluso tambien se pueden colocar en los services a nivel de metodo y de
    // clase
//    @PreAuthorize("permitAll()")
//    @PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_USER')")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_USER')")
//    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/contactform")
    private String redirectContactForm(@RequestParam(name = "id", required = false) int id, Model model) {
        ContactModel contactModel = new ContactModel();
        if (id > 0) {
            contactModel = contactService.findContactById(id);
        }
        model.addAttribute("contactModel", contactModel);
        return ViewContant.CONTACT_FORM;
    }

    @GetMapping("/cancel")
    public String cancel() {
//        return ViewContant.CONTACTS;
        return "redirect:/contact/showcontacts";
    }

    @PostMapping("/addcontact")
    public String addContact(@ModelAttribute(name = "contactModel") ContactModel contactModel, Model model) {
        LOGGER.info("METHOD: addContact -- PARAMS " + contactModel.toString());

        if (null != contactService.addContact(contactModel)) {
            model.addAttribute("result", 1);
        } else {
            model.addAttribute("result", 0);
        }

//        return ViewContant.CONTACTS;
        return "redirect:/contact/showcontacts";
    }

    @GetMapping("/showcontacts")
    public ModelAndView showcontacts() {
        ModelAndView mav = new ModelAndView(ViewContant.CONTACTS);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        mav.addObject("userName", user.getUsername()+ "...");
        mav.addObject("contacts", contactService.listAllContacts());

        return mav;
    }

//    @GetMapping("/removecontact")
//    public ModelAndView removeContact (@RequestParam(name="id", required=true) int id) {
//        contactService.removeContact(id);
//        return showcontacts();
//    }

//El curso hizo el metodo anterior, pero me parce que no es optimo porque despues de eliminar queda una url mostrando el id que se elimino
    @GetMapping("/removecontact")
    public String removeContact(@RequestParam(name = "id", required = true) int id) {
        contactService.removeContact(id);
        return "redirect:/contact/showcontacts";
    }

}
