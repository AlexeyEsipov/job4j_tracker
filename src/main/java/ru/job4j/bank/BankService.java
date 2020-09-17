package ru.job4j.bank;
import java.util.*;

public class BankService {
    private Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    public void addAccount(String passport, Account account) {
        Optional<User> customer = findByPassport(passport);
        if (customer.isPresent()) {
            List<Account> customerAccounts = users.get(customer.get());
            if (!customerAccounts.contains(account)) {
                customerAccounts.add(account);
            }
        }
    }

    public Optional<User> findByPassport(String passport) {
          return users.keySet()
                .stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst();
    }

    public Optional<Account> findByRequisite(String passport, String requisite) {
        Optional<Account> result = Optional.empty();
        Optional<User> customer = findByPassport(passport);
        if (customer.isPresent()) {
            result = users.get(customer.get())
                    .stream()
                    .filter(account -> account.getRequisite().equals(requisite))
                    .findFirst();
        }
        return result;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Optional<Account> srcAccount = findByRequisite(srcPassport, srcRequisite);
        Optional<Account> destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount.isEmpty()
                || srcAccount.get().getBalance() < amount
                || destAccount.isEmpty()) {
            return false;
        }
        srcAccount.get().setBalance(srcAccount.get().getBalance() - amount);
        destAccount.get().setBalance(destAccount.get().getBalance() + amount);
        return true;
    }
}