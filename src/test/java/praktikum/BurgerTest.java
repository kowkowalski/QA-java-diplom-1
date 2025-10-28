package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BurgerTest {

    @Test
    public void emptyBurger_shouldHaveZeroIngredients() {
        Burger burger = new Burger();
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void setBuns_shouldAssignBun() {
        Bun bun = new Bun("black bun", 100.0f);
        Burger burger = new Burger();

        burger.setBuns(bun);

        assertEquals("black bun", burger.bun.getName());
        assertEquals(100.0f, burger.bun.getPrice(), 0.001f);
    }

    @Test
    public void addIngredient_shouldAddToList() {
        Burger burger = new Burger();
        burger.setBuns(new Bun("default", 50.0f));

        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "chili", 15.0f);
        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
        assertEquals("chili", burger.ingredients.get(0).getName());
    }

    @Test
    public void removeIngredient_shouldRemoveFromList() {
        Burger burger = new Burger();
        burger.setBuns(new Bun("default", 50.0f));
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cheese", 40.0f);
        burger.addIngredient(ingredient);

        burger.removeIngredient(0);

        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveIngredient_shouldChangeOrder() {
        Burger burger = new Burger();
        burger.setBuns(new Bun("default", 50.0f));

        Ingredient first = new Ingredient(IngredientType.SAUCE, "bbq", 10.0f);
        Ingredient second = new Ingredient(IngredientType.FILLING, "beef", 80.0f);
        burger.addIngredient(first);
        burger.addIngredient(second);

        burger.moveIngredient(0, 1);

        assertEquals("bbq", burger.ingredients.get(1).getName());
        assertEquals("beef", burger.ingredients.get(0).getName());
    }

    @Test
    public void getPrice_shouldSumBunsAndIngredients() {
        Burger burger = new Burger();
        burger.setBuns(new Bun("white bun", 60.0f));
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "garlic", 20.0f));
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "cutlet", 100.0f));

        float expected = 2 * 60.0f + 20.0f + 100.0f;
        assertEquals(expected, burger.getPrice(), 0.001f);
    }

    @Test
    public void getPrice_withoutIngredients_shouldBeDoubleBunPrice() {
        Burger burger = new Burger();
        burger.setBuns(new Bun("black bun", 100.0f));

        float expected = 2 * 100.0f;
        assertEquals(expected, burger.getPrice(), 0.001f);
    }

    @Test
    public void getReceipt_shouldContainBunsIngredientsAndPrice() {
        Burger burger = new Burger();
        burger.setBuns(new Bun("black bun", 100.0f));
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "spicy", 30.0f));
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "cheese", 40.0f));

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("(==== black bun ====)"));
        assertTrue(receipt.contains("= sauce spicy ="));
        assertTrue(receipt.contains("= filling cheese ="));
        assertTrue(receipt.contains("(==== black bun ====)"));

        String expectedPriceLine = String.format("Price: %f", burger.getPrice());
        assertTrue(receipt.contains(expectedPriceLine));
    }
}