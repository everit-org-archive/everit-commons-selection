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

public class LimitTest {

    private static final long FIRST_RESULT = 10;

    private static final long MAX_RESULTS = 5;

    @Test
    public void testRangeIntLong() {
        Limit limit = new Limit(FIRST_RESULT, MAX_RESULTS);
        Assert.assertNotNull(limit);
        Assert.assertEquals(FIRST_RESULT, limit.getFirstResult());
        Assert.assertEquals(MAX_RESULTS, limit.getMaxResults());
    }

}
