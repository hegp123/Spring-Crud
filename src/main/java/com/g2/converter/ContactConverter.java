package com.g2.converter;

import org.springframework.stereotype.Component;

import com.g2.entity.Contact;
import com.g2.model.ContactModel;

@Component("contactConverter")
public class ContactConverter {

    public Contact modelToEntity(ContactModel contactModel) {

        Contact contact = new Contact();

        contact.setId(contactModel.getId());
        contact.setFirstName(contactModel.getFirstName());
        contact.setLastName(contactModel.getLastName());
        contact.setTelephone(contactModel.getTelephone());
        contact.setCity(contactModel.getCity());

        return contact;
    }

    public ContactModel entityToModel(Contact contact) {

        ContactModel contactModel = new ContactModel();

        contactModel.setId(contact.getId());
        contactModel.setFirstName(contact.getFirstName());
        contactModel.setLastName(contact.getLastName());
        contactModel.setTelephone(contact.getTelephone());
        contactModel.setCity(contact.getCity());

        return contactModel;
    }

}
