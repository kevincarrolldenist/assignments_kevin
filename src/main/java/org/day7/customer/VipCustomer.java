package org.day7.customer;

import org.day7.bank.Bank;
public class VipCustomer extends Bank {
    public static void main(String[] args) {
        VipCustomer vip = new VipCustomer();
        System.out.println(vip.bankName);
        // System.out.println(vip.branchCode);
        // System.out.println(vip.vaultMoney);
        System.out.println(vip.headOfficeLocation);
        vip.displayBankName();
        vip.displayHeadOfficeLocation();
        // vip.displayBranchCode();
    }
}
