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

import org.junit.Assert;
import org.junit.Test;

public class PagedRangeTest {

    private static final long PAGE_INDEX_1 = Long.valueOf(3);
    private static final long PAGE_SIZE_1 = Long.valueOf(5);
    private static final long FIRST_RESULT_1 = 10;

    private static final long PAGE_INDEX_2 = Long.valueOf(2);
    private static final long PAGE_SIZE_2 = Long.valueOf(10);
    private static final long FIRST_RESULT_2 = 10;

    private static final long PAGE_INDEX_3 = Long.valueOf(1);
    private static final long PAGE_SIZE_3 = Long.valueOf(1);
    private static final long FIRST_RESULT_3 = 0;

    private void testParams(final long pageIndex, final long pageSize, final long firstResult) {
        Limit limit = new PageLimit(pageIndex, pageSize);
        Assert.assertNotNull(limit);
        Assert.assertEquals(firstResult, limit.getFirstResult());
        Assert.assertEquals(pageSize, limit.getMaxResults());
    }

    @Test
    public void testRangeIntLong() {
        testParams(PAGE_INDEX_1, PAGE_SIZE_1, FIRST_RESULT_1);
        testParams(PAGE_INDEX_2, PAGE_SIZE_2, FIRST_RESULT_2);
        testParams(PAGE_INDEX_3, PAGE_SIZE_3, FIRST_RESULT_3);
    }

}
