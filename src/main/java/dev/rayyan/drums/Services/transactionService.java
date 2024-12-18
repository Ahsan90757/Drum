package dev.rayyan.drums.Services;
import dev.rayyan.drums.Repositories.itemRepository;
import dev.rayyan.drums.Repositories.transactionRepository;
import dev.rayyan.drums.Repositories.customerRepository;
import dev.rayyan.drums.Models.transaction;
import dev.rayyan.drums.Models.transactionItem;
import dev.rayyan.drums.Models.item;
import dev.rayyan.drums.Models.customer;
import dev.rayyan.drums.Models.account;
import dev.rayyan.drums.Repositories.accountRepository;
import dev.rayyan.drums.Models.transactionAccount;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class transactionService {
    @Autowired
    itemRepository itemRepositoryObj;
    @Autowired
    transactionRepository transactionRepositoryObj;
    @Autowired
    customerRepository customerRepositoryObj;
    @Autowired
    accountRepository accountRepositoryObj;


    public List<transaction> allTransactions(){
        return transactionRepositoryObj.findAll();
    }

    public Optional<transaction> transactionById(ObjectId id){
        return transactionRepositoryObj.findById(id);
    }

    public transaction createTransaction(transaction transaction) {

        //int balance = ;
        float totalAmount= transaction.getTotalAmount();
        float amountReceived = transaction.getAmountReceived();
        // Find the corresponding customer


        Optional<customer> optionalCustomer = customerRepositoryObj.findByCustomerNumber(transaction.getCustomerNumber());
        if (optionalCustomer.isPresent()) {
            customer existingCustomer = optionalCustomer.get();
            // Update customer's lastTransaction date and balance
            existingCustomer.setLastTransaction(new Date());
            existingCustomer.setReminder(transaction.getReminder());

           if(transaction.getTransactionType().equals("selling")){
            existingCustomer.setBalance(existingCustomer.getBalance() + totalAmount);
            existingCustomer.setBalance(existingCustomer.getBalance() - amountReceived);
            }
            else{
                existingCustomer.setBalance(existingCustomer.getBalance() - totalAmount);
                existingCustomer.setBalance(existingCustomer.getBalance() + amountReceived);
            }


            // Save updated customer
            customerRepositoryObj.save(existingCustomer);
        }


        for (transactionAccount transactionAccount : transaction.getTransactionAccounts()) {
            String accountName = transactionAccount.getAccountName();
            Optional<account> optionalAccount = accountRepositoryObj.findByAccountName(accountName);
            if (optionalAccount.isPresent()) {
                account existingAccount = optionalAccount.get();
                float balance  = existingAccount.getBalance();
                if(transaction.getTransactionType().equals("selling")){
                    existingAccount.setBalance(existingAccount.getBalance() + transactionAccount.getAmount());
                }
                else{
                    existingAccount.setBalance(existingAccount.getBalance() - transactionAccount.getAmount());
                }
                accountRepositoryObj.save(existingAccount);
            }
        }

        Optional<transaction> latestTransaction = transactionRepositoryObj.findTopByOrderByTransactionNumberDesc();
        if(latestTransaction.isPresent()){
            transaction existingTransaction = latestTransaction.get();
            int newTransactionNumber = existingTransaction.getTransactionNumber() + 1;
            transaction.setTransactionNumber(newTransactionNumber);
        }


        for (transactionItem transactionItem : transaction.getTransactionItems()) {
            String itemName = transactionItem.getItemName();
            float quantity = transactionItem.getQuantity();
            Optional<item> optionalItem = itemRepositoryObj.findByName(itemName);

            if (optionalItem.isPresent()) {
                item existingItem = optionalItem.get();
                float remainingQuantity = existingItem.getRemainingQuantity();
                if ("buying".equalsIgnoreCase(transaction.getTransactionType())) {
                    existingItem.setRemainingQuantity(remainingQuantity + quantity);
                } else {
                    if (remainingQuantity >= quantity) {
                        existingItem.setRemainingQuantity(remainingQuantity - quantity);
                    }
                }
                itemRepositoryObj.save(existingItem);
            }
        }
        return transactionRepositoryObj.save(transaction);
    }

    public List<transaction> transactionsByCustomerNumber(String customerNumber) {
        return transactionRepositoryObj.findByCustomerNumber(customerNumber);
    }
    public Optional<transaction> getTopTransaction (){
        return transactionRepositoryObj.findTopByOrderByTransactionNumberDesc();
    }
}
