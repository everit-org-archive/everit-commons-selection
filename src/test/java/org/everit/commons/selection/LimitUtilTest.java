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

public class LimitUtilTest {

    @Test
    public void testShiftIfNecessary() {
        Limit limit = new Limit(15, 6L);
        Assert.assertEquals(limit, LimitUtil.shiftIfNecessary(limit, 19));
        Assert.assertEquals(new Limit(12, 6L), LimitUtil.shiftIfNecessary(limit, 15));
        limit = new Limit(20, 5L);
        Assert.assertEquals(new Limit(5, 5L), LimitUtil.shiftIfNecessary(limit, 10));
        Assert.assertEquals(new Limit(0, 5L), LimitUtil.shiftIfNecessary(limit, 0));
    }

}
