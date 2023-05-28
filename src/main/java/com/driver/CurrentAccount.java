package com.driver;

import java.util.HashMap;
import java.util.Map;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only


    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000);
        this.tradeLicenseId = tradeLicenseId;
        if(balance < 5000){
            throw new Exception("Insufficient Balance");
        }
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(!isConsecutive(tradeLicenseId)){
            String newLicense = rearrangeCharacters(tradeLicenseId);
            if(!isConsecutive(newLicense) || newLicense.length() == 0){
                throw new Exception("Valid License can not be generated");
            }
            this.tradeLicenseId = newLicense;
        }
    }

    private String rearrangeCharacters(String tradeLicenseId) {
        int n =tradeLicenseId.length();
        Map<Character, Integer> map = new HashMap<>();
        for (char ch: tradeLicenseId.toCharArray()){
            if(map.containsKey(ch)){
                map.put(ch, map.get(ch)+1);
            }
            else{
                map.put(ch, 1);
            }
            if(map.get(ch) >= (n+1)/2) return "";
        }
        char[] ans = new char[n];
        int index =0;
        for(char ch: map.keySet()){
            while (map.get(ch) > 0){
                if(index >= n){
                    index = 1;
                }
                ans[index] = ch;
                index += 2;
                map.put(ch, map.get(ch)-1);
            }
            if(index >= n){
                index = 1;
            }
        }
        return new String(ans);
    }

    public boolean isConsecutive(String str){
        for (int i=1;i<str.length();i++){
            if(str.charAt(i-1) == str.charAt(i))
                return false;
        }
        return true;
    }
}
