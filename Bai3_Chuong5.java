public class Bai3 {
    public static void main(String[] args) {
        Order[] orders = {
            new Order("An", 200000, new CreditCardPayment()),
            new Order("Bình", 150000, new PayPalPayment()),
            new Order("Chi", 100000, new CashPayment()),
            new Order("Dũng", 300000, new MoMoPayment())
        };

        for (Order o : orders) {
            o.checkout();
            System.out.println();
        }
    }
}

enum PaymentType {
    DIRECT("Trực tiếp"),
    CASHLESS("Không dùng tiền mặt");

    private final String label;

    PaymentType(String label) { this.label = label; }

    public String getLabel() { return label; }
}

abstract class PaymentMethod {
    protected final PaymentType type;
    protected final String name;

    public PaymentMethod(PaymentType type, String name) {
        this.type = type;
        this.name = name;
    }

    public PaymentType getType() { return type; }
    public String getName() { return name; }

    protected abstract String describe();

    public void pay(double amount) {
        System.out.println("Thanh toán " + formatMoney(amount) + " " + describe() + ".");
    }

    protected static String formatMoney(double amount) {
        return String.format("%,.0f", amount).replace(',', '.');
    }
}

class CreditCardPayment extends PaymentMethod {
    public CreditCardPayment() { super(PaymentType.CASHLESS, "CreditCard"); }

    @Override
    protected String describe() { return "bằng thẻ tín dụng"; }
}

class PayPalPayment extends PaymentMethod {
    public PayPalPayment() { super(PaymentType.CASHLESS, "PayPal"); }

    @Override
    protected String describe() { return "qua PayPal"; }
}

class CashPayment extends PaymentMethod {
    public CashPayment() { super(PaymentType.DIRECT, "Cash"); }

    @Override
    protected String describe() { return "bằng tiền mặt"; }
}

class MoMoPayment extends PaymentMethod {
    public MoMoPayment() { super(PaymentType.CASHLESS, "MoMo"); }

    @Override
    protected String describe() { return "qua MoMo"; }
}

class Order {
    private final String customerName;
    private final double amount;
    private final PaymentMethod paymentMethod;

    public Order(String customerName, double amount, PaymentMethod paymentMethod) {
        this.customerName = customerName;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public String getCustomerName() { return customerName; }
    public double getAmount() { return amount; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }

    public void checkout() {
        System.out.println("Khách hàng: " + customerName);
        paymentMethod.pay(amount);
    }
}
