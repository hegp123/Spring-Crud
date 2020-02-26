package com.g2.service;

import java.util.List;

import com.g2.model.ContactModel;

public interface IContactService {

    public abstract ContactModel addContact(ContactModel contactModel);
    public abstract List<ContactModel> listAllContacts();
    public abstract ContactModel findContactById(int id);
    public abstract void removeContact(int id);
}
