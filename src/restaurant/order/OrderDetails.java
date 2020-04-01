package restaurant.order;

import restaurant.dish.Dish;

import java.math.BigDecimal;

public class OrderDetails {
    private int quantity;
    private Dish dish ;
    private String dishName = null;

    public int getQuantity() {
        return quantity;
    }

    public String getDishName() {
        return dish.getName();
    }

    public void setDishName(String dish) { this.dish.setName(dish);

    this.dishName = dish; }

    public void setDish(Dish dish) {
        this.dish = dish;
        this.dishName = dish.getName();
    }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public BigDecimal calculateSubTotal()
    {
        return dish.getPrice().multiply(new BigDecimal(quantity));
    }

    public BigDecimal calculateTax()
    {
        return calculateSubTotal().multiply(new BigDecimal(dish.taxes));
    }

    public String getOrderDetails()
    {
        return String.format("Dish: %s, Quantity: %d, Price: %s", dish.getName(), quantity, dish.getPrice());
    }

}
