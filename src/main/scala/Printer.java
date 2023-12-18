import menuitems.Cola;
import menuitems.MenuItem;

import java.time.LocalTime;
import java.util.List;

public class Printer {

    public static void printReceipt(Bill bill) {
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
            System.out.format(leftFormatTwoInputs, "- " + bill.items().apply(i).name(), String.format("£ %.2f", bill.items().apply(i).cost().toFloat()));
        }

        System.out.format(dottedLine);

        System.out.format(leftFormatTwoInputs, "Subtotal:", String.format("£ %.2f", bill.calculatePreSCBill().toFloat()));

        System.out.format(dottedLine);

        String starOrStars = (bill.loyalty().numOfStars() == 1) ? "star" : "stars";
        System.out.format(leftFormatTwoInputs, "Loyalty Discount - " + String.format("%d %s", bill.loyalty().numOfStars(), starOrStars), "");
        System.out.format(leftFormatTwoInputs, bill.loyalty().discount() * 100 + "%", String.format("-£ %.2f", bill.calculateLoyaltyDiscount().toFloat()));

        if (bill.isHappyHour()) {
            System.out.format(dottedLine);
            System.out.format(leftFormatTwoInputs, "It's Happy Hour!", "");
            System.out.format(leftFormatTwoInputs, "Total Savings:", String.format("-£ %.2f", bill.calculatePreSCBill().$minus(bill.happyHourTotal()).toFloat()));
        }

        System.out.format(dottedLine);
        System.out.format(leftFormatTwoInputs, "TOTAL:", String.format("£ %.2f", bill.calculateBill().toFloat()));

        System.out.format(singleLine);
        System.out.format(leftFormatOneInput, "Drinks are half price from 18:00 to 21:00 everyday!");
        System.out.format(singleLine);
        System.out.format(leftFormatOneInput, "Thank you for visiting! Please come again!");
        System.out.format(singleLine);

    }
}
