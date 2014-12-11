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

/**
 * A {@link Limit} that defines Page boundaries.
 */
public class PageLimit extends Limit {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 3402853790896777049L;

    private static final long PAGE_INDEX_MIN_VALUE = 1;

    private static long calculateFirstResult(final long pageIndex, final long pageSize) {
        if (pageIndex < PAGE_INDEX_MIN_VALUE) {
            throw new IllegalArgumentException("pageIndex [" + pageIndex
                    + "] is less than PAGE_INDEX_MIN_VALUE [" + PAGE_INDEX_MIN_VALUE + "]");
        }
        return (pageIndex - 1) * pageSize;
    }

    /**
     * Constructor.
     * 
     * @param pageIndex
     *            The index of the page. Indexed from {@link #PAGE_INDEX_MIN_VALUE}.
     * @param pageSize
     *            The size of the page.
     */
    public PageLimit(final long pageIndex, final long pageSize) {
        super(PageLimit.calculateFirstResult(pageIndex, pageSize), pageSize);
    }

}
