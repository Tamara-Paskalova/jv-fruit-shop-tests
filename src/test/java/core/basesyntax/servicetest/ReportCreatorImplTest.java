package core.basesyntax.servicetest;

import core.basesyntax.model.Fruit;
import core.basesyntax.service.ReportCreator;
import core.basesyntax.service.impl.ReportCreatorImpl;
import core.basesyntax.storage.Storage;
import java.util.Map;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReportCreatorImplTest {
    private static ReportCreator reportCreator;

    @BeforeClass
    public static void beforeClass() {
        reportCreator = new ReportCreatorImpl();
    }

    @After
    public void tearDown() {
        Storage.storage.clear();
    }

    @Test
    public void createReport_validData1_Ok() {
        Storage.storage.put(new Fruit("peach"), 49);
        Storage.storage.put(new Fruit("grape"), 345);

        String expected = String.join(System.lineSeparator(), "fruit,quantity",
                "peach,49", "grape,345");
        String actual = reportCreator.createReport();
        Assert.assertEquals(String.format("\nExpected:\n%s\nbut was:\n%s", expected, actual),
                expected, actual);
    }

    @Test
    public void createReport_validData2_Ok() {
        Storage.storage.put(new Fruit("peach"), 100);
        Storage.storage.put(new Fruit("apple"), 149);
        Storage.storage.put(new Fruit("banana"), 9);
        Storage.storage.put(new Fruit("tangerine"), 33);
        Storage.storage.put(new Fruit("pear"), 0);

        StringBuilder builder = new StringBuilder().append("fruit,quantity");
        for (Map.Entry<Fruit, Integer> pair : Storage.storage.entrySet()) {
            builder.append(System.lineSeparator())
                    .append(pair.getKey().getName())
                    .append(",")
                    .append(pair.getValue());
        }
        String expected = builder.toString();
        String actual = reportCreator.createReport();
        Assert.assertEquals(String.format("\nExpected:\n%s\nbut was:\n%s", expected, actual),
                expected, actual);
    }
}