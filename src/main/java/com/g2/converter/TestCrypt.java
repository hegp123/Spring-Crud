package com.g2.converter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestCrypt {

    public static void main(String[] args) {
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        System.out.println(pe.encode("user"));

    }

//    INSERT INTO `mydb`.`user` (`user_name`, `enabled`, `password`) 
//    VALUES ('user', true, '$2a$10$eKSTCNwiAVqwHAlFUk5klOFmmEWAXIrYRRRqFs2sMXNOIoWIUbUby');
    
//    INSERT INTO `mydb`.`user_role` (`role`, `user_name`) VALUES ('ROLE_USER', 'user');
    
}
