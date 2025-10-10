package core.basesyntax.model;

import core.basesyntax.exceptions.InvalidDataException;
import java.util.Objects;

public class FruitTransaction {
    private String actionCode;
    private int quantity;
    private String productName;

    public FruitTransaction(String actionCode, int quantity, String productName) {
        this.actionCode = actionCode;
        this.quantity = quantity;
        this.productName = productName;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new InvalidDataException("Quantity must not be negative: "
                    + quantity);
        }
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FruitTransaction that = (FruitTransaction) o;
        return quantity == that.quantity && Objects.equals(actionCode, that.actionCode)
                && Objects.equals(productName, that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionCode, quantity, productName);
    }

    @Override
    public String toString() {
        return "FruitTransaction{"
                + "actionCode='" + actionCode + '\''
                + ", quantity=" + quantity
                + ", productName='" + productName + '\''
                + '}';
    }
}
