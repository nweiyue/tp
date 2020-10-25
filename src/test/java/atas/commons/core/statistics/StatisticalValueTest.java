package atas.commons.core.statistics;

import static atas.commons.core.statistics.StatisticalValues.STAT_FULL;
import static atas.commons.core.statistics.StatisticalValues.STAT_INVALID;
import static atas.commons.core.statistics.StatisticalValues.STAT_NOTFULL;
import static atas.commons.core.statistics.StatisticalValues.STAT_NULL;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class StatisticalValueTest {

    @Test
    public void getValue_negativeValues() {
        assertEquals(StatisticalValues.getValue(-1, -1), STAT_INVALID);
        assertEquals(StatisticalValues.getValue(1, -1), STAT_INVALID);
        assertEquals(StatisticalValues.getValue(-1, 1), STAT_INVALID);
    }

    @Test
    public void getValue_positiveValidValues() {
        assertEquals(StatisticalValues.getValue(0, 0), STAT_NULL);
        assertEquals(StatisticalValues.getValue(1, 2), STAT_NOTFULL);
        assertEquals(StatisticalValues.getValue(2, 2), STAT_FULL);
    }

    @Test
    public void getValue_positiveInvalidValues() {
        assertEquals(StatisticalValues.getValue(2, 1), STAT_INVALID);
    }
}
