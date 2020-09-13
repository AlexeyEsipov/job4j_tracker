package ru.job4j.bank;
import java.util.*;

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
        Optional<User> userResult = users.keySet()
                .stream()
                .filter(user -> user.getPassport().equals(passport))
                .findAny();
        if (userResult.isPresent()) {
            result = userResult.get();
        }
        return result;
    }

    public Account findByRequisite(String passport, String requisite) {
        Account result = null;
        User customer = findByPassport(passport);
        if (customer != null) {
            Optional<Account> accountResult = users.get(customer)
                    .stream()
                    .filter(account -> account.getRequisite().equals(requisite))
                    .findAny();
            if (accountResult.isPresent()) {
                result = accountResult.get();
            }
        }
        return result;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        if (srcAccount == null) {
            return false;
        }
        if (srcAccount.getBalance() < amount) {
            return false;
        }
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (destAccount == null) {
            return false;
        }
        srcAccount.setBalance(srcAccount.getBalance() - amount);
        destAccount.setBalance(destAccount.getBalance() + amount);
        return true;
    }
}