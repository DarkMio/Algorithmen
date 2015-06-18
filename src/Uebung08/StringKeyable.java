package Uebung08;

import Uebung08.hashtables.Keyable;

public class StringKeyable implements Keyable{

    String s;

    public StringKeyable(String s) {
        this.s = s;
    }

    @Override
    public int getKey() {
        int hash = 0;
        for(int i = 0; i <= s.length(); i++) {
            hash += (int) (Math.pow(26, (s.length()-i))*s.charAt(i));
        }
        return hash;
    }

    public String getValue() {
        return s;
    }

    public boolean equals(StringKeyable other) {
        return s.equals(other.getValue());
    }
}
