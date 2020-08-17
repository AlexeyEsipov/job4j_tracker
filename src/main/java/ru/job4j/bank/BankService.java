package ru.job4j.bank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    public void addAccount(String passport, Account account) {
        User customer = findByPassport(passport);
        if (customer != null) {
            List<Account> customerAccounts = users.get(customer);
            if (!customerAccounts.contains(account)) {
                customerAccounts.add(account);
            }
        }
    }

    public User findByPassport(String passport) {
        User result = null;
        for (User customer: users.keySet()) {
            if (customer.getPassport().equals(passport)) {
                result = customer;
                break;
            }
        }
        return result;
    }

    public Account findByRequisite(String passport, String requisite) {
        Account result = null;
        User customer = findByPassport(passport);
        if (customer != null) {
            List<Account> customerAccounts = users.get(customer);
            for (Account account: customerAccounts) {
                if (account.getRequisite().equals(requisite)) {
                    result = account;
                    break;
                }
            }
        }
        return result;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        if (srcAccount.getBalance() < amount) {
            return false;
        }
        Account destAccount = findByRequisite(destPassport, destRequisite);
        srcAccount.setBalance(srcAccount.getBalance() - amount);
        destAccount.setBalance(destAccount.getBalance() + amount);
        return true;
    }
}