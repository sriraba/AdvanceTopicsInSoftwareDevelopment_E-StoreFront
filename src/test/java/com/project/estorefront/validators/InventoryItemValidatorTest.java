package com.project.estorefront.validators;

import com.project.estorefront.model.IInventoryItem;
import com.project.estorefront.model.InventoryFactory;
import com.project.estorefront.model.InventoryItem;
import com.project.estorefront.model.ItemCategory;
import com.project.estorefront.model.validators.IInventoryItemValidator;
import com.project.estorefront.model.validators.InventoryItemValidationStatus;
import com.project.estorefront.model.validators.InventoryItemValidator;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.Assert.assertEquals;

public class InventoryItemValidatorTest {

    @Test
    public void validateInvalidInventoryItem() {
        IInventoryItem item = InventoryFactory.instance().makeInventoryItemWithItemID("");
        IInventoryItemValidator validator = new InventoryItemValidator();
        assertEquals(validator.validate(item), InventoryItemValidationStatus.ALL_INVALID);
    }

    @Test
    public void validateInventoryItemWithInvalidItemID() {
        IInventoryItem item = InventoryFactory.instance().makeInventoryItem("5", "Test", ItemCategory.GROCERY, 10, 10.0, "Test", "Test");

        IInventoryItemValidator validator = new InventoryItemValidator();
        assertEquals(validator.validate(item), InventoryItemValidationStatus.INVALID_INVENTORY_ITEM_ID);
    }

    @Test
    public void validateInventoryItemWithNegativeItemPrice() {
        IInventoryItem item = InventoryFactory.instance().makeInventoryItem("af638b8e-c12f-48bd-8f6a-72f716cb4b18", "Test", ItemCategory.GROCERY, 10, -10.0, "Test", "Test");

        IInventoryItemValidator validator = new InventoryItemValidator();
        assertEquals(validator.validate(item), InventoryItemValidationStatus.INVALID_INVENTORY_ITEM_PRICE);
    }

    @Test
    public void validateInventoryItemWithNegativeQuantity() {
        IInventoryItem item = InventoryFactory.instance().makeInventoryItem("af638b8e-c12f-48bd-8f6a-72f716cb4b18", "Test", ItemCategory.GROCERY, -10, 10.0, "Test", "Test");

        IInventoryItemValidator validator = new InventoryItemValidator();
        assertEquals(validator.validate(item), InventoryItemValidationStatus.INVALID_INVENTORY_ITEM_QUANTITY);
    }

    @Test
    public void validateInventoryItemWithEmptyItemName() {
        IInventoryItem item = InventoryFactory.instance().makeInventoryItem("af638b8e-c12f-48bd-8f6a-72f716cb4b18", "Test", ItemCategory.GROCERY, 10, 10.0, "", "Test");

        IInventoryItemValidator validator = new InventoryItemValidator();
        assertEquals(validator.validate(item), InventoryItemValidationStatus.INVALID_INVENTORY_ITEM_NAME);
    }

    @Test
    public void validateInventoryItemWithNullItemName() {
        IInventoryItem item = InventoryFactory.instance().makeInventoryItem("af638b8e-c12f-48bd-8f6a-72f716cb4b18", "Test", ItemCategory.GROCERY, 10, 10.0, null, "Test");

        IInventoryItemValidator validator = new InventoryItemValidator();
        assertEquals(validator.validate(item), InventoryItemValidationStatus.INVALID_INVENTORY_ITEM_NAME);
    }

    @Test
    public void validateInventoryItemWithNullItemDescription() {
        IInventoryItem item = InventoryFactory.instance().makeInventoryItem("af638b8e-c12f-48bd-8f6a-72f716cb4b18", "Test", ItemCategory.GROCERY, 10, 10.0, "Test", null);

        IInventoryItemValidator validator = new InventoryItemValidator();
        assertEquals(validator.validate(item), InventoryItemValidationStatus.INVALID_INVENTORY_ITEM_DESCRIPTION);
    }

    @Test
    public void validateInventoryItemWithEmptyItemDescription() {
        IInventoryItem item = InventoryFactory.instance().makeInventoryItem("af638b8e-c12f-48bd-8f6a-72f716cb4b18", "Test", ItemCategory.GROCERY, 10, 10.0, "Test", "");

        IInventoryItemValidator validator = new InventoryItemValidator();
        assertEquals(validator.validate(item), InventoryItemValidationStatus.INVALID_INVENTORY_ITEM_DESCRIPTION);
    }

    @Test
    public void validateInventoryItemWithInvalidUserID() {
        IInventoryItem item = InventoryFactory.instance().makeInventoryItem("af638b8e-c12f-48bd-8f6a-72f716cb4b18", "", ItemCategory.GROCERY, 10, 10.0, "Test", "");

        IInventoryItemValidator validator = new InventoryItemValidator();
        assertEquals(validator.validate(item), InventoryItemValidationStatus.INVALID_INVENTORY_ITEM_DESCRIPTION);
    }

    @Test
    public void validateInventoryItemAllValid() {
        IInventoryItem item = InventoryFactory.instance().makeInventoryItem("b0c36302-fee4-4bb9-9a09-5267e89d8f2b", "TEST", ItemCategory.GROCERY, 100, 10.0, "TEST", "TEST");

        IInventoryItemValidator validator = new InventoryItemValidator();
        assertEquals(validator.validate(item), InventoryItemValidationStatus.VALID);
    }

}
