package com.project.estorefront.validators;

import com.project.estorefront.model.IInventoryItem;
import com.project.estorefront.model.InventoryItem;
import com.project.estorefront.model.validators.IInventoryItemValidator;
import com.project.estorefront.model.validators.InventoryItemValidator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InventoryItemValidatorTest {

    @Test
    public void validateInvalidInventoryItem() {
        IInventoryItem item = new InventoryItem();
        IInventoryItemValidator validator = new InventoryItemValidator();
        assertEquals(validator.validate(item), IInventoryItemValidator.InventoryItemValidationStatus.ALL_INVALID);
    }

    @Test
    public void validateInventoryItemWithInvalidItemID() {
        IInventoryItem item = new InventoryItem();
        item.setItemID("5");
        item.setItemPrice(10.0);
        item.setItemQuantity(10);
        item.setItemName("Test");
        item.setItemDescription("Test");
        item.setUserID("Test");

        IInventoryItemValidator validator = new InventoryItemValidator();
        assertEquals(validator.validate(item), IInventoryItemValidator.InventoryItemValidationStatus.INVALID_INVENTORY_ITEM_ID);
    }

    @Test
    public void validateInventoryItemWithNegativeItemPrice() {
        IInventoryItem item = new InventoryItem();
        item.setItemID("b0c36302-fee4-4bb9-9a09-5267e89d8f2b");
        item.setItemPrice((double) -10);
        item.setItemQuantity(10);
        item.setItemName("Test");
        item.setItemDescription("Test");
        item.setUserID("Test");

        IInventoryItemValidator validator = new InventoryItemValidator();
        assertEquals(validator.validate(item), IInventoryItemValidator.InventoryItemValidationStatus.INVALID_INVENTORY_ITEM_PRICE);
    }

    @Test
    public void validateInventoryItemWithNullItemPrice() {
        IInventoryItem item = new InventoryItem();
        item.setItemID("b0c36302-fee4-4bb9-9a09-5267e89d8f2b");
        item.setItemPrice(null);
        item.setItemQuantity(10);
        item.setItemName("Test");
        item.setItemDescription("Test");
        item.setUserID("Test");

        IInventoryItemValidator validator = new InventoryItemValidator();
        assertEquals(validator.validate(item), IInventoryItemValidator.InventoryItemValidationStatus.INVALID_INVENTORY_ITEM_PRICE);
    }

    @Test
    public void validateInventoryItemWithNegativeQuantity() {
        IInventoryItem item = new InventoryItem();
        item.setItemID("b0c36302-fee4-4bb9-9a09-5267e89d8f2b");
        item.setItemPrice(10.0);
        item.setItemQuantity(-10);
        item.setItemName("Test");
        item.setItemDescription("Test");
        item.setUserID("Test");

        IInventoryItemValidator validator = new InventoryItemValidator();
        assertEquals(validator.validate(item), IInventoryItemValidator.InventoryItemValidationStatus.INVALID_INVENTORY_ITEM_QUANTITY);
    }

    @Test
    public void validateInventoryItemWithEmptyItemName() {
        IInventoryItem item = new InventoryItem();
        item.setItemID("b0c36302-fee4-4bb9-9a09-5267e89d8f2b");
        item.setItemPrice(10.0);
        item.setItemQuantity(10);
        item.setItemName("");
        item.setItemDescription("Test");
        item.setUserID("Test");

        IInventoryItemValidator validator = new InventoryItemValidator();
        assertEquals(validator.validate(item), IInventoryItemValidator.InventoryItemValidationStatus.INVALID_INVENTORY_ITEM_NAME);
    }

    @Test
    public void validateInventoryItemWithNullItemName() {
        IInventoryItem item = new InventoryItem();
        item.setItemID("b0c36302-fee4-4bb9-9a09-5267e89d8f2b");
        item.setItemPrice(10.0);
        item.setItemQuantity(10);
        item.setItemName(null);
        item.setItemDescription("Test");
        item.setUserID("Test");

        IInventoryItemValidator validator = new InventoryItemValidator();
        assertEquals(validator.validate(item), IInventoryItemValidator.InventoryItemValidationStatus.INVALID_INVENTORY_ITEM_NAME);
    }

    @Test
    public void validateInventoryItemWithNullItemDescription() {
        IInventoryItem item = new InventoryItem();
        item.setItemID("b0c36302-fee4-4bb9-9a09-5267e89d8f2b");
        item.setItemPrice(10.0);
        item.setItemQuantity(10);
        item.setItemName("TEST");
        item.setItemDescription(null);
        item.setUserID("Test");

        IInventoryItemValidator validator = new InventoryItemValidator();
        assertEquals(validator.validate(item), IInventoryItemValidator.InventoryItemValidationStatus.INVALID_INVENTORY_ITEM_DESCRIPTION);
    }

    @Test
    public void validateInventoryItemWithEmptyItemDescription() {
        IInventoryItem item = new InventoryItem();
        item.setItemID("b0c36302-fee4-4bb9-9a09-5267e89d8f2b");
        item.setItemPrice(10.0);
        item.setItemQuantity(10);
        item.setItemName("TEST");
        item.setItemDescription("");
        item.setUserID("Test");

        IInventoryItemValidator validator = new InventoryItemValidator();
        assertEquals(validator.validate(item), IInventoryItemValidator.InventoryItemValidationStatus.INVALID_INVENTORY_ITEM_DESCRIPTION);
    }

    @Test
    public void validateInventoryItemWithInvalidUserID() {
        IInventoryItem item = new InventoryItem();
        item.setItemID("b0c36302-fee4-4bb9-9a09-5267e89d8f2b");
        item.setItemPrice(10.0);
        item.setItemQuantity(10);
        item.setItemName("TEST");
        item.setItemDescription("");
        item.setUserID("");

        IInventoryItemValidator validator = new InventoryItemValidator();
        assertEquals(validator.validate(item), IInventoryItemValidator.InventoryItemValidationStatus.INVALID_INVENTORY_ITEM_DESCRIPTION);
    }

    @Test
    public void validateInventoryItemAllValid() {
        IInventoryItem item = new InventoryItem();
        item.setItemID("b0c36302-fee4-4bb9-9a09-5267e89d8f2b");
        item.setItemPrice(10.0);
        item.setItemQuantity(100);
        item.setItemName("TEST");
        item.setItemDescription("TEST");
        item.setUserID("Test");

        IInventoryItemValidator validator = new InventoryItemValidator();
        assertEquals(validator.validate(item), IInventoryItemValidator.InventoryItemValidationStatus.VALID);
    }

}
