package ru.job4j.bank;
import java.util.*;

/**
 * Класс выполняет действия по добавлению клиента банка, добавлению нового счета
 * клиенту банка, проверку наличия клиента в списке клиентов банка, проверку наличия
 * счета в списке счетов клиента, выполняет перевод средств внутри банка от отправителя
 * к получателю.
 * @author Есипов Алексей
 */
public class BankService {
    private Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод принимает на вход нового клиента user и добавляет его в карту клиентов
     * users, если его еще там нет. Проверка производится по ключу user.
     * @param user новый клиент.
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод принимает на вход паспорт и новый счет. Если паспорт принадлежит клиенту банка,
     * и если в списке счетов клиента еще нет такого счета, то этот счет добавляется в список.
     * @param passport паспортные данные.
     * @param account номер счета.
     */
    public void addAccount(String passport, Account account) {
        Optional<User> customer = findByPassport(passport);
        if (customer.isPresent()) {
            List<Account> customerAccounts = users.get(customer.get());
            if (!customerAccounts.contains(account)) {
                customerAccounts.add(account);
            }
        }
    }

    /**
     * Метод принимает на вход паспорт и возвращает соответствующего клиента банка,
     * если этот паспорт принадлежит клиенту. Если паспорт не принадлежит никому
     * из клиентов, возвращает пустое значение {@code Optional<User>}
     * @param passport паспорт
     * @return значение {@code Optional<User>}
     */
    public Optional<User> findByPassport(String passport) {
          return users.keySet()
                .stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst();
    }

    /**
     * Метод возвращает номер счета, если номер паспорта принадлежит клиенту банка
     * и если проверяемый номер счета принадлежит этому клиенту. Если эти условия не выполняются,
     * то возвращается пустое значение {@code Optional<Account>}
     * @param passport паспорт
     * @param requisite номер счета
     * @return значение {@code Optional<Account>}
     */
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

    /**
     * Переводит средства от отправителя к получателю, предварительно проверив существование
     * источника, существование получателя, достаточность средств на счете отправителя. Возвращает
     * {@code true} если перевод успешно произведен, и {@code false} если перевод не возможен ввиду
     * отсутствия отправителя или получателя, или при недостатке средств на счете отправителя
     * на счете отправителя,
     * @param srcPassport паспорт отправителя
     * @param srcRequisite счет отправителя
     * @param destPassport паспорт получателя
     * @param destRequisite счет получателя
     * @param amount сумма перевода
     * @return {@code true} если перевод произведен, {@code false} если не произведен
     */
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