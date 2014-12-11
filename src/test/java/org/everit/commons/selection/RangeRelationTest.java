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

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class RangeRelationTest {

    @Parameters
    public static Collection<Object[]> parameterLists() {
        return Arrays
                .asList(new Object[][] {
                        { new Range<Integer>(5, 10), new Range<Integer>(5, 10), RangeRelation.IDENTICAL },
                        { new Range<Integer>(5, 10), new Range<Integer>(4, 11), RangeRelation.CONTAINING },
                        { new Range<Integer>(5, 10, false, true), new Range<Integer>(5, 11),
                                RangeRelation.CONTAINING },
                        { new Range<Integer>(5, 10, true, false), new Range<Integer>(4, 10),
                                RangeRelation.CONTAINING },
                        { new Range<Integer>(4, 11), new Range<Integer>(5, 10), RangeRelation.CONTAINED },
                        { new Range<Integer>(5, 11), new Range<Integer>(5, 10, false, true),
                                RangeRelation.CONTAINED },
                        { new Range<Integer>(4, 10), new Range<Integer>(5, 10, true, false),
                                RangeRelation.CONTAINED },

                        { new Range<Integer>(5, 10), new Range<Integer>(1, 4), RangeRelation.BEFORE },
                        { new Range<Integer>(5, 10), new Range<Integer>(1, 5, true, false),
                                RangeRelation.BEFORE },
                        { new Range<Integer>(5, 10, false, true), new Range<Integer>(1, 5),
                                RangeRelation.BEFORE },
                        { new Range<Integer>(5, 10, false, true), new Range<Integer>(1, 5, true, false),
                                RangeRelation.BEFORE },

                        { new Range<Integer>(5, 10), new Range<Integer>(1, 6),
                                RangeRelation.BEFORE_OVERLAPPING },
                        { new Range<Integer>(5, 10), new Range<Integer>(1, 5),
                                RangeRelation.BEFORE_OVERLAPPING },
                        { new Range<Integer>(5, 10), new Range<Integer>(1, 10, true, false),
                                RangeRelation.BEFORE_OVERLAPPING },

                        { new Range<Integer>(1, 4), new Range<Integer>(5, 10), RangeRelation.AFTER },
                        { new Range<Integer>(1, 5, true, false), new Range<Integer>(5, 10),
                                RangeRelation.AFTER },
                        { new Range<Integer>(1, 5), new Range<Integer>(5, 10, false, true),
                                RangeRelation.AFTER },

                        { new Range<Integer>(1, 6), new Range<Integer>(5, 10),
                                RangeRelation.AFTER_OVERLAPPING },
                        { new Range<Integer>(1, 5), new Range<Integer>(5, 10),
                                RangeRelation.AFTER_OVERLAPPING },
                        { new Range<Integer>(1, 10, true, false), new Range<Integer>(5, 10),
                                RangeRelation.AFTER_OVERLAPPING },

                });
    }

    private final Range<Integer> range;

    private final Range<Integer> other;

    private final RangeRelation expectedResult;

    public RangeRelationTest(final Range<Integer> range, final Range<Integer> other,
            final RangeRelation expectedResult) {
        super();
        this.range = range;
        this.other = other;
        this.expectedResult = expectedResult;
    }

    @Test
    public void testGetRelationTo() {
        RangeRelation result = range.getRelationTo(other);
        Assert.assertNotNull("result cannot be null", result);
        Assert.assertEquals(expectedResult, result);
    }

}
