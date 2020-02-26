package com.g2.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.g2.converter.ContactConverter;
import com.g2.entity.Contact;
import com.g2.model.ContactModel;
import com.g2.repository.IContactRepository;
import com.g2.service.IContactService;

@Service("contactService")
public class ContactServiceImp implements IContactService {

    @Autowired
    @Qualifier("contactRepository")
    private IContactRepository contactRepository;

    @Autowired
    @Qualifier("contactConverter")
    private ContactConverter contactConverter;
    
    @Override
    public ContactModel addContact(ContactModel contactModel) {
        Contact contact = contactRepository.save(contactConverter.modelToEntity(contactModel));
        return contactConverter.entityToModel(contact);
    }

    @Override
    public List<ContactModel> listAllContacts() {
        List<Contact> findAll = contactRepository.findAll();
        List<ContactModel> contactsModel = new ArrayList<>();
        
        findAll.forEach(contact -> {
            contactsModel.add(contactConverter.entityToModel(contact));
        });
        
        return contactsModel;
        
    }

    @Override
    public ContactModel findContactById(int id) {
        Contact contact = contactRepository.findById(id);
        return contactConverter.entityToModel(contact);
    }

    @Override
    public void removeContact(int id) {
        // esto que esta comentariado es del curso, pero me parece que no es optimo
        // porque ya existe deleteById
//        Contact contact = contactRepository.findById(id);
//        if (contact != null) {
//            contactRepository.delete(contact);
//        }

        contactRepository.deleteById(id);
    }

}
