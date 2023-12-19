package com.example.demo.Controller;

import com.example.demo.Model.Account;
import com.example.demo.Model.ResponseAPI;
import com.example.demo.Repository.AccountRepo;
import com.example.demo.Repository.Imple.AccountRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
   private final AccountRepo accountRepo;

    public AccountController(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @GetMapping
    public ResponseAPI getAllAccount() {
        return new ResponseAPI("Success", "Get info successfully", accountRepo.getAllAccount());
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable int id) {
        Account account = accountRepo.FindById(id);
        if(account==null){
            return ResponseEntity.status(404).body(new ResponseAPI("Error", "Account not found", null));
        }
        return ResponseEntity.status(200).body(new ResponseAPI("Success", "Get info successfully", accountRepo.FindById(id)));
    }

    @GetMapping("/mail/{mail}")
    public ResponseEntity<?> getAccountByMail(@PathVariable String mail) {
        Account account = accountRepo.FindByMail(mail);
        if(account==null){
            return ResponseEntity.status(404).body(new ResponseAPI("Error", "Account not found", null));
        }
        return ResponseEntity.status(200).body(new ResponseAPI("Success", "Get info successfully", accountRepo.FindByMail(mail)));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAccount(@RequestBody Account account) {
        int result = accountRepo.addAccount(account);
        if (result== 1) {
            return ResponseEntity.status(200).body(new ResponseAPI("Success", "Add account successfully", accountRepo.FindByMail(account.getMail())));
        }
        return ResponseEntity.status(500).body(new ResponseAPI("Error",result ==0? "Add account failed":"Account existed", null));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable int id) {
        Account account = accountRepo.FindById(id);
        int result = accountRepo.deleteAccount(id);
        if (result== 1) {
            return ResponseEntity.status(200).body(new ResponseAPI("Success", "Delete account successfully", account));
        }
        return ResponseEntity.status(500).body(new ResponseAPI("Error",result ==0? "Delete account failed":"Account existed", null));

    }

    @PutMapping("/update")
    public ResponseEntity<?> UpdateAccount(@RequestBody Account account){
        ResponseAPI res = new ResponseAPI();

        try {
            if(accountRepo.updateAccount(account)==0) throw new Exception();

                res.setStatus("Success");
                res.setMessage("Update Successfully");
                res.setData(accountRepo.FindByMail(account.getMail()));

        }catch (Exception e){
           res.setStatus("Fail");
           res.setMessage("Update Fail");
            return ResponseEntity.status(404).body(res);
        }
        return ResponseEntity.status(200).body(res);
    }
    @PutMapping("/update/status")
    public ResponseEntity<?> UpdateAccountStatus(@RequestBody Account account){
        ResponseAPI res = new ResponseAPI();

        try {
            if(accountRepo.updateAccountStauts(account.getAccountID(),account.isIsActive())==0) throw new Exception();

            res.setStatus("Success");
            res.setMessage("Update Successfully");
            res.setData(accountRepo.FindById(account.getAccountID()));

        }catch (Exception e){
            res.setStatus("Fail");
            res.setMessage("Update Fail");
            return ResponseEntity.status(404).body(res);
        }
        return ResponseEntity.status(200).body(res);
    }
}
