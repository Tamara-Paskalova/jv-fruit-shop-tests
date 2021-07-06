package core.basesyntax.strategy.impl;

import core.basesyntax.dto.Transaction;
import core.basesyntax.model.Fruit;
import core.basesyntax.storage.Storage;
import core.basesyntax.strategy.OperationHandler;

public class SubtractionOperatorHandler implements OperationHandler {
    @Override
    public int apply(Transaction transaction) {
        Fruit fruit = new Fruit(transaction.getName());
        int quantity = Storage.storage.get(fruit) - transaction.getQuantity();
        if (quantity < 0) {
            throw new RuntimeException("Quantity is not enough " + fruit.getName());
        }
        Storage.storage.put(fruit, quantity);
        return quantity;
    }
}