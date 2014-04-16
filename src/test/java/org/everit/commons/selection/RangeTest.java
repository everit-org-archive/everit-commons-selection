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
package org.everit.commons.selection;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class RangeTest {

    private static final BigDecimal numLower1 = BigDecimal.valueOf(5);
    private static final BigDecimal numHigher1 = BigDecimal.valueOf(50);
    private static final BigDecimal numLower2 = BigDecimal.valueOf(10);
    private static final BigDecimal numHigher2 = BigDecimal.valueOf(100);
    private static final Calendar calLower1;
    private static final Calendar calHigher1;
    private static final Calendar calLower2;
    private static final Calendar calHigher2;

    static {
        calLower1 = Calendar.getInstance();
        calLower1.add(Calendar.DAY_OF_YEAR, 1);
        calLower2 = Calendar.getInstance();
        calLower2.add(Calendar.DAY_OF_YEAR, 2);

        calHigher1 = Calendar.getInstance();
        calHigher1.add(Calendar.YEAR, 1);
        calHigher2 = Calendar.getInstance();
        calHigher2.add(Calendar.YEAR, 2);
    }

    @Test
    public void testConstructor() {
        new Range<Long>(10l, 20l);
        new Range<Integer>(10, 10);
        try {
            new Range<Integer>(20, 10);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertNotNull(e.getMessage());
        }

        new Range<Long>(10l, 20l, true, true);
        new Range<Integer>(10, 10, true, true);
        try {
            new Range<Integer>(20, 10, true, true);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertNotNull(e.getMessage());
        }
    }

    public <T extends Comparable<T>> void testEquals(final T lowerBound1, final T lowerBound2, final T higherBound1,
            final T higherBound2) {
        Range<T> interval1 = new Range<T>(lowerBound1, higherBound1, true, true);
        Assert.assertFalse(interval1.equals(null));
        Assert.assertTrue(interval1.equals(interval1));

        interval1 = new Range<T>(lowerBound1, higherBound1, true, true);
        Range<T> interval2 = new Range<T>(lowerBound1, higherBound1, true, true);
        Assert.assertTrue(interval1.equals(interval2));

        interval1 = new Range<T>(lowerBound1, higherBound1, true, true);
        interval2 = new Range<T>(lowerBound1, higherBound1, false, true);
        Assert.assertFalse(interval1.equals(interval2));

        interval1 = new Range<T>(lowerBound1, higherBound1, true, true);
        interval2 = new Range<T>(lowerBound1, higherBound1, true, false);
        Assert.assertFalse(interval1.equals(interval2));

        interval1 = new Range<T>(lowerBound1, higherBound1, true, true);
        interval2 = new Range<T>(lowerBound2, higherBound1, true, true);
        Assert.assertFalse(interval1.equals(interval2));

        interval1 = new Range<T>(lowerBound1, higherBound1, true, true);
        interval2 = new Range<T>(lowerBound1, higherBound2, true, true);
        Assert.assertFalse(interval1.equals(interval2));

        interval1 = new Range<T>(null, higherBound1, true, true);
        interval2 = new Range<T>(lowerBound1, higherBound1, true, true);
        Assert.assertFalse(interval1.equals(interval2));

        interval1 = new Range<T>(lowerBound1, higherBound1, true, true);
        interval2 = new Range<T>(lowerBound1, null, true, true);
        Assert.assertFalse(interval1.equals(interval2));
    }

    @Test
    public void testEqualsWithDates() {
        testEquals(calLower1.getTime(), calLower2.getTime(), calHigher1.getTime(), calHigher2.getTime());
    }

    @Test
    public void testEqualsWithDifferentClasses() {
        Range<Date> interval1 = new Range<Date>(calLower1.getTime(), calHigher1.getTime());
        Range<BigDecimal> interval2 = new Range<BigDecimal>(numLower1, numHigher1);
        Assert.assertFalse(interval1.equals(interval2));
    }

    @Test
    public void testEqualsWithNumbers() {
        testEquals(numLower1, numLower2, numHigher1, numHigher2);
    }

    @Test
    public void testIntersectAfter() {
        Range<Integer> intv1 = new Range<Integer>(10, 20, true, false);
        Range<Integer> intv2 = new Range<Integer>(21, 26, true, false);
        Assert.assertNull(intv1.intersect(intv2));
        Assert.assertNull(intv2.intersect(intv1));
    }

    @Test
    public void testIntersectContaining() {
        Range<Integer> intv1 = new Range<Integer>(10, 20, true, false);
        Range<Integer> intv2 = new Range<Integer>(15, 16, true, false);
        Range<Integer> expected = new Range<Integer>(15, 16, true, false);
        Range<Integer> actual = intv1.intersect(intv2);
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected, intv2.intersect(intv1));
    }

    @Test
    public void testIntersectIdentical() {
        Range<Integer> intv1 = new Range<Integer>(10, 20, true, false);
        Range<Integer> intv2 = new Range<Integer>(10, 20, true, false);
        Assert.assertEquals(intv1, intv1.intersect(intv2));
        Assert.assertEquals(intv1, intv2.intersect(intv1));
    }

    @Test
    public void testIntersectOverlapping() {
        Range<Integer> intv1 = new Range<Integer>(10, 20, true, false);
        Range<Integer> intv2 = new Range<Integer>(15, 26, true, false);
        Range<Integer> expected = new Range<Integer>(15, 20, true, false);
        Range<Integer> actual = intv1.intersect(intv2);
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected, intv2.intersect(intv1));
    }

    @Test
    public void testUnion() {
        Range<Integer> intv1 = new Range<Integer>(10, 20, true, false);
        Range<Integer> intv2 = new Range<Integer>(15, 26, true, false);
        Range<Integer> union = intv1.union(intv2);
        Assert.assertEquals(intv1.getLowerBound(), union.getLowerBound());
        Assert.assertEquals(intv1.isLowerInclusive(), union.isLowerInclusive());
        Assert.assertEquals(intv2.getHigherBound(), union.getHigherBound());
        Assert.assertEquals(intv2.isHigherInclusive(), union.isHigherInclusive());

        Assert.assertEquals(union, intv2.union(intv1));
    }

}
