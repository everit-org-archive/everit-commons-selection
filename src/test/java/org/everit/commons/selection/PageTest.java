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

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PageTest {

    private static final long FIRST_RESULT = 5;

    private static final long PAGE_SIZE = Long.valueOf(10);

    private static final List<String> ELEMENTS = new ArrayList<String>();

    private static final long NUMBER_OF_ALL_ELEMENTS = 100;

    private static final long PAGE_INDEX = 1;

    private static final int NUM_OF_ALL = 47;

    private static final long PS = 5;

    static {
        ELEMENTS.add("TIBI");
        ELEMENTS.add("JOZSI");
    }

    private List<Long> getClickablePageIndexes(final List<Long> elements, final long numberOfAllElements,
            final Limit limit, final int maxNumberOfClickablePageIndexes) {
        Page<Long> page = new Page<Long>(elements, numberOfAllElements, limit);
        return page.getClickablePageIndexes(maxNumberOfClickablePageIndexes);
    }

    @Test
    public void testGetClickablePageIndexes() {
        List<Long> elements = new ArrayList<Long>((int) PS);
        List<Long> clickablePageIndexes = null;

        clickablePageIndexes = getClickablePageIndexes(elements, NUM_OF_ALL, new PageLimit(1, PS), 0);
        Assert.assertEquals(0, clickablePageIndexes.size());

        clickablePageIndexes = getClickablePageIndexes(elements, NUM_OF_ALL, new PageLimit(1, PS), 1);
        Assert.assertEquals(1, clickablePageIndexes.size());
        Assert.assertEquals(Long.valueOf(1), clickablePageIndexes.get(0));

        clickablePageIndexes = getClickablePageIndexes(elements, NUM_OF_ALL, new PageLimit(5, PS), 1);
        Assert.assertEquals(1, clickablePageIndexes.size());
        Assert.assertEquals(Long.valueOf(5), clickablePageIndexes.get(0));

        clickablePageIndexes = getClickablePageIndexes(elements, NUM_OF_ALL, new PageLimit(1, PS), 2);
        Assert.assertEquals(2, clickablePageIndexes.size());
        Assert.assertEquals(Long.valueOf(1), clickablePageIndexes.get(0));
        Assert.assertEquals(Long.valueOf(2), clickablePageIndexes.get(1));

        clickablePageIndexes = getClickablePageIndexes(elements, NUM_OF_ALL, new PageLimit(5, PS), 2);
        Assert.assertEquals(2, clickablePageIndexes.size());
        Assert.assertEquals(Long.valueOf(5), clickablePageIndexes.get(0));
        Assert.assertEquals(Long.valueOf(6), clickablePageIndexes.get(1));

        clickablePageIndexes = getClickablePageIndexes(elements, NUM_OF_ALL, new PageLimit(1, PS), 3);
        Assert.assertEquals(3, clickablePageIndexes.size());
        Assert.assertEquals(Long.valueOf(1), clickablePageIndexes.get(0));
        Assert.assertEquals(Long.valueOf(2), clickablePageIndexes.get(1));
        Assert.assertEquals(Long.valueOf(3), clickablePageIndexes.get(2));

        clickablePageIndexes = getClickablePageIndexes(elements, NUM_OF_ALL, new PageLimit(2, PS), 3);
        Assert.assertEquals(3, clickablePageIndexes.size());
        Assert.assertEquals(Long.valueOf(1), clickablePageIndexes.get(0));
        Assert.assertEquals(Long.valueOf(2), clickablePageIndexes.get(1));
        Assert.assertEquals(Long.valueOf(3), clickablePageIndexes.get(2));

        clickablePageIndexes = getClickablePageIndexes(elements, NUM_OF_ALL, new PageLimit(3, PS), 3);
        Assert.assertEquals(3, clickablePageIndexes.size());
        Assert.assertEquals(Long.valueOf(2), clickablePageIndexes.get(0));
        Assert.assertEquals(Long.valueOf(3), clickablePageIndexes.get(1));
        Assert.assertEquals(Long.valueOf(4), clickablePageIndexes.get(2));

        clickablePageIndexes = getClickablePageIndexes(elements, NUM_OF_ALL, new PageLimit(10, PS), 3);
        Assert.assertEquals(3, clickablePageIndexes.size());
        Assert.assertEquals(Long.valueOf(8), clickablePageIndexes.get(0));
        Assert.assertEquals(Long.valueOf(9), clickablePageIndexes.get(1));
        Assert.assertEquals(Long.valueOf(10), clickablePageIndexes.get(2));

        clickablePageIndexes = getClickablePageIndexes(elements, NUM_OF_ALL, new PageLimit(9, PS), 3);
        Assert.assertEquals(3, clickablePageIndexes.size());
        Assert.assertEquals(Long.valueOf(8), clickablePageIndexes.get(0));
        Assert.assertEquals(Long.valueOf(9), clickablePageIndexes.get(1));
        Assert.assertEquals(Long.valueOf(10), clickablePageIndexes.get(2));

        clickablePageIndexes = getClickablePageIndexes(elements, NUM_OF_ALL, new PageLimit(8, PS), 3);
        Assert.assertEquals(3, clickablePageIndexes.size());
        Assert.assertEquals(Long.valueOf(7), clickablePageIndexes.get(0));
        Assert.assertEquals(Long.valueOf(8), clickablePageIndexes.get(1));
        Assert.assertEquals(Long.valueOf(9), clickablePageIndexes.get(2));

        clickablePageIndexes = getClickablePageIndexes(elements, NUM_OF_ALL, new PageLimit(5, PS), 3);
        Assert.assertEquals(3, clickablePageIndexes.size());
        Assert.assertEquals(Long.valueOf(4), clickablePageIndexes.get(0));
        Assert.assertEquals(Long.valueOf(5), clickablePageIndexes.get(1));
        Assert.assertEquals(Long.valueOf(6), clickablePageIndexes.get(2));

        clickablePageIndexes = getClickablePageIndexes(elements, 8, new PageLimit(1, PS), 3);
        Assert.assertEquals(2, clickablePageIndexes.size());
        Assert.assertEquals(Long.valueOf(1), clickablePageIndexes.get(0));
        Assert.assertEquals(Long.valueOf(2), clickablePageIndexes.get(1));

        clickablePageIndexes = getClickablePageIndexes(elements, 8, new PageLimit(2, PS), 3);
        Assert.assertEquals(2, clickablePageIndexes.size());
        Assert.assertEquals(Long.valueOf(1), clickablePageIndexes.get(0));
        Assert.assertEquals(Long.valueOf(2), clickablePageIndexes.get(1));

    }

    @Test
    public void testPageListLongRange() {
        Page<String> page = new Page<String>(ELEMENTS, NUMBER_OF_ALL_ELEMENTS, new Limit(FIRST_RESULT, PAGE_SIZE));
        Assert.assertNotNull(page);
        Assert.assertEquals(ELEMENTS, page.getElements());
        Assert.assertEquals(FIRST_RESULT, page.getFirstResult());
        Assert.assertEquals(PAGE_SIZE, page.getPageSize());
        Assert.assertEquals(NUMBER_OF_ALL_ELEMENTS, page.getNumberOfAllElements());
        Assert.assertEquals(PAGE_INDEX, page.getPageIndex());
        Assert.assertEquals(10, page.getLastAvailablePageIndex());
        page = new Page<String>(ELEMENTS, NUMBER_OF_ALL_ELEMENTS + 1, new Limit(FIRST_RESULT, PAGE_SIZE));
        Assert.assertEquals(11, page.getLastAvailablePageIndex());
    }

    @Test
    public void testWithElementsNull() {
        try {
            new Page<String>(null, 0, null);
            Assert.fail();
        } catch (NullPointerException e) {
            Assert.assertNotNull(e.getMessage());
        }
    }

    @Test
    public void testWithNumberOfAllElements() {
        Limit limit = new Limit(0, 100);
        try {
            new Page<String>(ELEMENTS, -1, limit);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertNotNull(e.getMessage());
        }
        ArrayList<String> oneElementList = new ArrayList<String>(1);
        oneElementList.add("element1");
        try {
            new Page<String>(oneElementList, 0, limit);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertNotNull(e.getMessage());
        }
        try {
            new Page<String>(ELEMENTS, 0, limit);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertNotNull(e.getMessage());
        }
    }

    @Test
    public void testWithPageHasMoreResultsThanTheExpectedFromTheInputRange() {
        ArrayList<String> twoElementsList = new ArrayList<String>(2);
        twoElementsList.add("element1");
        twoElementsList.add("element2");
        Limit smallLimit = new Limit(0, 1);
        try {
            new Page<String>(twoElementsList, 2, smallLimit);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertNotNull(e.getMessage());
        }
    }

    @Test
    public void testWithRangeNull() {
        try {
            new Page<String>(ELEMENTS, NUMBER_OF_ALL_ELEMENTS, null);
            Assert.fail();
        } catch (NullPointerException e) {
            Assert.assertNotNull(e.getMessage());
        }
    }

}
