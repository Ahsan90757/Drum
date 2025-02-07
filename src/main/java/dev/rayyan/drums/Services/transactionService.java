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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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

//    public Map<LocalDate, List<Map<String, Object>>> getSalesByItemAndLastNTransactions(int numberOfTransactions, String itemName) {
//        // Fetch the last N transactions sorted by date (descending order)
//        List<transaction> transactions = transactionRepositoryObj.findTopNByOrderByDateDesc(numberOfTransactions);
//
//        // Group the transactions by date and map each transaction item to the desired format
//        return transactions.stream()
//                .flatMap(t -> t.getTransactionItems().stream()
//                        .filter(item -> item.getItemName().equalsIgnoreCase(itemName)) // Filter by item name
//                        .map(item -> {
//                            Map<String, Object> result = new HashMap<>();
//                            result.put("customerNumber", t.getCustomerNumber());
//                            result.put("customerName", t.getTransactionAccounts().isEmpty() ? "Unknown" :
//                                    t.getTransactionAccounts().get(0).getAccountName());
//                            result.put("quantity", item.getQuantity());
//                            result.put("transactionDate", t.getDate());
//                            return result;
//                        })
//                )
//                .collect(Collectors.groupingBy(
//                        map -> {
//                            // Convert to LocalDate if necessary
//                            Object dateObject = map.get("transactionDate");
//                            if (dateObject instanceof Date) {
//                                return ((Date) dateObject).toInstant()
//                                        .atZone(ZoneId.systemDefault())
//                                        .toLocalDate();
//                            }
//                            return (LocalDate) dateObject; // If it's already LocalDate
//                        },
//                        LinkedHashMap::new, // Maintain insertion order
//                        Collectors.toList() // Collect results into lists
//                ));
//    }

//    public Map<LocalDate, List<Map<String, Object>>> getSalesByItemAndLastNTransactions(int numberOfTransactions, String itemName) {
//        // Fetch transactions using the repository method
//        Pageable pageable = PageRequest.of(0, numberOfTransactions, Sort.by(Sort.Direction.DESC, "date"));
//        List<transaction> transactions = transactionRepositoryObj.findTopNByItemNameOrderByDateDesc(itemName, pageable);
//
//        return transactions.stream()
//                .flatMap(t -> t.getTransactionItems().stream()
//                        .filter(item -> item.getItemName().equalsIgnoreCase(itemName)) // Filter by item name
//                        .map(item -> {
//                            Map<String, Object> result = new HashMap<>();
//                            result.put("customerNumber", t.getCustomerNumber());
//                            result.put("customerName", t.getTransactionAccounts().isEmpty() ? "Unknown" :
//                                    t.getTransactionAccounts().getFirst().getAccountName());
//                            result.put("quantity", t.getTransactionType().equalsIgnoreCase("buying") ?
//                                    item.getQuantity() : -item.getQuantity()); // Positive for buying, negative for selling
//                            result.put("transactionDate", t.getDate()); // Keep Date type for now
//                            return result;
//                        })
//                )
//                .collect(Collectors.groupingBy(
//                        map -> {
//                            Date date = (Date) map.get("transactionDate");
//                            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Convert Date to LocalDate
//                        },
//                        LinkedHashMap::new, // Maintain insertion order (latest transactions first)
//                        Collectors.toList() // Group into lists
//                ));
//    }

    public Map<LocalDate, List<Map<String, Object>>> getSalesByItemAndLastNTransactions(int numberOfTransactions, String itemName) {
        // Fetch transactions sorted by _id (newest first)
        Pageable pageable = PageRequest.of(0, numberOfTransactions, Sort.by(Sort.Direction.DESC, "_id"));
        List<transaction> transactions = transactionRepositoryObj.findTopNByItemNameOrderByIdDesc(itemName, pageable);

        return transactions.stream()
                .flatMap(t -> t.getTransactionItems().stream()
                        .filter(item -> item.getItemName().equalsIgnoreCase(itemName)) // Filter by item name
                        .map(item -> {
                            Map<String, Object> result = new HashMap<>();
                            result.put("customerNumber", t.getCustomerNumber());
// Fetch customer name using customerNumber
                            Optional<customer> customerOptional = customerRepositoryObj.findByCustomerNumber(t.getCustomerNumber());
                            String customerName = customerOptional.map(customer::getCustomerName).orElse("Unknown");
                            result.put("customerName", customerName);
                            result.put("quantity", t.getTransactionType().equalsIgnoreCase("buying") ?
                                    item.getQuantity() : -item.getQuantity()); // Positive for buying, negative for selling
                            result.put("transactionDate", extractDateFromObjectId(t.getId())); // Extract date from _id
                            return result;
                        })
                )
                .collect(Collectors.groupingBy(
                        map -> {
                            Date date = (Date) map.get("transactionDate");
                            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Convert Date to LocalDate
                        },
                        LinkedHashMap::new, // Maintain insertion order (latest transactions first)
                        Collectors.toList() // Group into lists
                ));
    }
    private Date extractDateFromObjectId(ObjectId objectId) {
        return new Date(objectId.getTimestamp() * 1000L); // Convert seconds to milliseconds
    }


}