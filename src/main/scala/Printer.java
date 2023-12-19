import menuitems.Cola;
import menuitems.MenuItem;

import java.time.LocalTime;
import java.util.List;

public class Printer {

    public static void printReceipt(Bill bill) {
        String currencySymbol = Currencies.getSymbol(bill.currency());
        
        String leftFormatOneInput = "| %-51s |%n";
        String leftFormatTwoInputs = "| %-32s | %-16s |%n";

        String singleLine = "+----------------------------------+------------------+%n";
        String dottedLine = "+- - - - - - - - - - - - - - - - - + - - - - - - - - -+%n";

        System.out.format(singleLine);
        System.out.format(leftFormatOneInput, "The " + bill.theme() + " Restaurant");
        System.out.format(singleLine);
        System.out.format(leftFormatTwoInputs, "Served by: " + bill.server(), "Served at: " + bill.time());
        System.out.format(dottedLine);

        System.out.format(leftFormatTwoInputs, "Items:", "Cost (" + bill.currency() + "):");
        for (int i = 0; i < bill.items().length(); i++) {
            System.out.format(leftFormatTwoInputs, "- " + bill.items().apply(i).name(), String.format("%s %.2f", currencySymbol, bill.items().apply(i).cost().toFloat()));
        }

        System.out.format(dottedLine);

        System.out.format(leftFormatTwoInputs, "Subtotal:", String.format("%s %.2f", currencySymbol, bill.itemSum().toFloat()));

        System.out.format(singleLine);

        String starOrStars = (bill.loyalty().numOfStars() == 1) ? "star" : "stars";
        System.out.format(leftFormatTwoInputs, "Loyalty Discount - " + String.format("%d %s", bill.loyalty().numOfStars(), starOrStars),
                bill.loyalty().discount() * 100 + "%");
        System.out.format(leftFormatTwoInputs, "", String.format("-%s %.2f", currencySymbol, bill.calculateLoyaltyDiscount().toFloat()));

        if (bill.isHappyHour()) {
            System.out.format(dottedLine);
            System.out.format(leftFormatTwoInputs, "Happy Hour Savings:", String.format("-%s %.2f", currencySymbol, bill.itemSum().$minus(bill.happyHourTotal()).toFloat()));
        }

        System.out.format(dottedLine);
        System.out.format(leftFormatTwoInputs, "Service Charge:", String.format("%s %.2f", currencySymbol, bill.calculateServiceCharge().toFloat()));

        System.out.format(dottedLine);
        System.out.format(leftFormatTwoInputs, "TOTAL:", String.format("%s %.2f", currencySymbol, bill.calculateBill().toFloat()));

        System.out.format(singleLine);
        System.out.format(leftFormatOneInput, "Drinks are half price from 18:00 to 21:00 everyday!");
        System.out.format(singleLine);
        System.out.format(leftFormatOneInput, "Thank you for visiting! Please come again!");
        System.out.format(singleLine);

    }
}
