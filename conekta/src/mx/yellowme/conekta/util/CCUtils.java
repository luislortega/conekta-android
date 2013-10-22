/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.yellowme.conekta.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author javier
 */
public class CCUtils {

    public static final int INVALID = -1;
    public static final int VISA = 0;
    public static final int MASTERCARD = 1;
    public static final int AMERICAN_EXPRESS = 2;
    private static final String[] cardNames = {"Visa", "Mastercard", "American Express"};

    /**
     * Valid a Credit Card number
     */
    public static boolean validCC(String number) throws Exception {
        int cardID = getCardID(number);
        if (cardID != -1) {
            return validCCNumber(number);
        }
        return false;
    }

    /**
     * Get the Card type returns the credit card type INVALID = -1; VISA = 0;
     * MASTERCARD = 1; AMERICAN_EXPRESS = 2; EN_ROUTE = 3; DINERS_CLUB = 4;
     */
    public static int getCardID(String number) {
        int valid = INVALID;
        String digit1 = number.substring(0, 1);
        String digit2 = number.substring(0, 2);

        if (isNumber(number)) {
            /* ----
             ** VISA  prefix=4
             ** ----  length=13 or 16
             */
            if (digit1.equals("4")) {
                if (number.length() == 13 || number.length() == 16) {
                    valid = VISA;
                }
            } /* ----------
             ** MASTERCARD  prefix= 51 ... 55
             ** ----------  length= 16
             */ else if (digit2.compareTo("51") >= 0 && digit2.compareTo("55") <= 0) {
                if (number.length() == 16) {
                    valid = MASTERCARD;
                }
            } /* ----
             ** AMEX  prefix=34 or 37
             ** ----  length=15
              else if (digit2.equals("34") || digit2.equals("37")) {
                if (number.length() == 15) {
                    valid = AMERICAN_EXPRESS;
                }
            }*/
        }
        return valid;

    }

    private static boolean isNumber(String n) {
        try {
            double d = Double.valueOf(n).doubleValue();
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String getCardName(int id) {
        return (id > -1 && id < cardNames.length ? cardNames[id] : "");
    }

    private static boolean validCCNumber(String n) {
        try {
            /*
             ** known as the LUHN Formula (mod10)
             */
            int j = n.length();

            String[] s1 = new String[j];
            for (int i = 0; i < n.length(); i++) {
                s1[i] = "" + n.charAt(i);
            }

            int checksum = 0;

            for (int i = s1.length - 1; i >= 0; i -= 2) {
                int k = 0;

                if (i > 0) {
                    k = Integer.valueOf(s1[i - 1]).intValue() * 2;
                    if (k > 9) {
                        String s = "" + k;
                        k = Integer.valueOf(s.substring(0, 1)).intValue()
                                + Integer.valueOf(s.substring(1)).intValue();
                    }
                    checksum += Integer.valueOf(s1[i]).intValue() + k;
                } else {
                    checksum += Integer.valueOf(s1[0]).intValue();
                }
            }
            return ((checksum % 10) == 0);
        } catch (Exception e) {
            return false;
        }
    }

    /*
     ** For testing purpose
     **
     **   java CCUtils [credit card number] or java CCUtils
     **
     */
    public static void main(String args[]) throws Exception {
        String aCard = "";

        if (args.length > 0) {
            aCard = args[0];
        } else {
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Card number : ");
            aCard = input.readLine();
        }
        if (getCardID(aCard) > -1) {
            System.out.println("This card is supported.");
            System.out.println("This a " + getCardName(getCardID(aCard)));
            System.out.println("The card number " + aCard + " is "
                    + (validCC(aCard) ? " good." : " bad."));
        } else {
            System.out.println("This card is invalid or unsupported!");
        }
        
        
        getCardID("12123123123123");
    }
}
