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
package org.everit.commons.selection.util;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class SecondRangeTest {

    private static DatatypeFactory factory;

    static {
        try {
            factory = DatatypeFactory.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Calendar createCalendar(final String lexicalRepresentation) {
        return factory.newXMLGregorianCalendar(lexicalRepresentation).toGregorianCalendar();
    }

    @Parameters
    public static List<Object[]> params() {
        return Arrays
                .<Object[]> asList(
                        new Object[] { SecondRangeTest.timeInSec("08:00:00"), SecondRangeTest.timeInSec("12:10:20"),
                                "4h 10m 20s" },
                        new Object[] { SecondRangeTest.timeInSec("08:00:00"), SecondRangeTest.timeInSec("08:10:20"),
                                "10m 20s" },
                        new Object[] { SecondRangeTest.timeInSec("08:00:00"), SecondRangeTest.timeInSec("12:00:20"),
                                "4h 20s" },
                        new Object[] { SecondRangeTest.timeInSec("08:00:00"), SecondRangeTest.timeInSec("12:10:00"),
                                "4h 10m" },
                        new Object[] { SecondRangeTest.timeInSec("08:00:00"), SecondRangeTest.timeInSec("12:00:00"),
                                "4h" },
                        new Object[] { SecondRangeTest.timeInSec("08:00:00"), SecondRangeTest.timeInSec("08:10:00"),
                                "10m" },
                        new Object[] { SecondRangeTest.timeInSec("08:00:00"), SecondRangeTest.timeInSec("08:00:20"),
                                "20s" },
                        new Object[] { SecondRangeTest.timeInSec("08:00:00"), SecondRangeTest.timeInSec("08:00:00"),
                                "0s" }
                );
    }

    private static Long timeInSec(final String time) {
        return SecondRangeTest.createCalendar("2010-10-10T" + time).getTimeInMillis() / 1000l;
    }

    private final long lowerBound;

    private final long higherBound;

    private final String expectedDuration;

    public SecondRangeTest(final Long lowerBound, final Long higherBound, final String expectedDuration) {
        super();
        this.lowerBound = lowerBound;
        this.higherBound = higherBound;
        this.expectedDuration = expectedDuration;
    }

    @Test
    public void testGetAsDuration() {
        SecondRange secondRange = new SecondRange(lowerBound, higherBound);
        Assert.assertEquals(expectedDuration, secondRange.getAsDuration());
    }

}
