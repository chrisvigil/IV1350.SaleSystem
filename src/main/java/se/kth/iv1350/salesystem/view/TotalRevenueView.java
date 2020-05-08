package se.kth.iv1350.salesystem.view;

import java.util.Locale;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.model.RevenueObserver;

/**
 * Displays the total amount paid for purchases since the
 * program started
 */
public class TotalRevenueView implements RevenueObserver{
    private MonetaryValue totalRevenue;
    Locale locale;
    
    /**
     * Creates a new instance with counter set to 0
     * @param locale The locale used for formatting the revenue total
     */
    public TotalRevenueView (Locale locale){
        totalRevenue = new MonetaryValue();
        this.locale = locale;
    }
    
    @Override
    public void newSale(MonetaryValue subTotal){
        totalRevenue = totalRevenue.add(subTotal);
        printRevenue();
    }
    
    private void printRevenue(){
        StringBuilder sb = new StringBuilder();
        sb.append("-------TotalRevenueView Says:-------\n");
        sb.append("             NEW SALE!\n");
        sb.append("   Total Revenue: ").append(totalRevenue.currencyFormat(locale));
        sb.append("\n------------------------------------\n");
        System.out.println(sb);
    }
}
