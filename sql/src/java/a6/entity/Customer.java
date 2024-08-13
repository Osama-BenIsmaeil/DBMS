package cs430.a6.entity;

/**
 * This class represents the customer relation.
 */
public class Customer {
    // cust_id
    private final String customerId;
    // c_name
    private final String customerName;
    // address
    private final String address;
    // amount
    private final float amount;

    public Customer(String customerId, String customerName, String address, float amount) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.amount = amount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public float getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", address='" + address + '\'' +
                ", amount=" + amount +
                '}';
    }
}
