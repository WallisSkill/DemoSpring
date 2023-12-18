package com.example.demo.Repository;

import com.example.demo.Model.Account;


import java.util.List;

public interface AccountRepo {
     List<Account> getAllAccount() ;

     int addAccount(Account account);

     int deleteAccount(int id);

     Account FindById(int id);

     Account FindByMail(String mail);
     int updateAccount(Account account);
     int updateAccountStauts(int id,boolean status);
}
