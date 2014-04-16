/**
 * This file is part of org.everit.commons.selection.
 *
 * org.everit.commons.selection is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * org.everit.commons.selection is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with org.everit.commons.selection.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.everit.commons.selection.lang;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.everit.commons.selection.math.BigDecimalRange;
import org.everit.commons.selection.math.BigIntegerRange;
import org.junit.Assert;
import org.junit.Test;

public class NumberRangeTest {

    @Test
    public void testBigIntegerRange() {
        BigIntegerRange subject = new BigIntegerRange(new BigInteger("100"), new BigInteger("105"));
        Assert.assertEquals(new BigInteger("5"), subject.getLength());
    }

    @Test
    public void testByteRange() {
        ByteRange subject = new ByteRange((byte) 100, (byte) 105);
        Assert.assertEquals((byte) 5, subject.getLength().byteValue());
    }

    @Test
    public void testDecimalRange() {
        BigDecimalRange subject = new BigDecimalRange(new BigDecimal(100), new BigDecimal(105));
        Assert.assertEquals(new BigDecimal(5), subject.getLength());
    }

    @Test
    public void testDoubleRange() {
        DoubleRange subject = new DoubleRange(100.0, 105.0);
        Assert.assertEquals(5.0, subject.getLength().doubleValue(), 0.0);
    }

    @Test
    public void testFloatRange() {
        FloatRange subject = new FloatRange(100f, 105f);
        Assert.assertEquals(5f, subject.getLength().floatValue(), 0.0f);
    }

    @Test
    public void testIntegerRange() {
        IntegerRange subject = new IntegerRange(100, 105);
        Assert.assertEquals(5, subject.getLength().intValue());
    }

    @Test
    public void testLongRange() {
        LongRange subject = new LongRange(100l, 105l);
        Assert.assertEquals(5l, subject.getLength().longValue());
    }

    @Test
    public void testShortRange() {
        ShortRange subject = new ShortRange((short) 100, (short) 105);
        Assert.assertEquals((short) 5, subject.getLength().shortValue());
    }

}
