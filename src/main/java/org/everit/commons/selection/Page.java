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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page<T extends Serializable> extends LimitedResult<T> {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -7857550936374542552L;

    /**
     * The index of this page calculated from the {@link Range} parameter.
     */
    private final long pageIndex;

    /**
     * The index of the last available page calculated from the 'numberOfAllElements' and the 'maxResults' of the
     * 'limit' parameter.
     */
    private final long lastAvailablePageIndex;

    public Page(final List<T> elements, final long numberOfAllElements, final Limit limit) {
        super(elements, numberOfAllElements, limit);
        long firstResult = limit.getFirstResult();
        long pageSize = limit.getMaxResults();
        if ((firstResult != 1) && (pageSize != 1) && (((firstResult + (1 % pageSize))) == 0)) {
            pageIndex = firstResult / pageSize;
        } else {
            pageIndex = (firstResult / pageSize) + 1;
        }
        if ((numberOfAllElements % pageSize) == 0) {
            lastAvailablePageIndex = numberOfAllElements / pageSize;
        } else {
            lastAvailablePageIndex = (numberOfAllElements / pageSize) + 1;
        }
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Page other = (Page) obj;
        if (lastAvailablePageIndex != other.lastAvailablePageIndex) {
            return false;
        }
        if (pageIndex != other.pageIndex) {
            return false;
        }
        return true;
    }

    /**
     * Returns the list of page indexes close to the current page index.
     * 
     * @param maxNumberOfClickablePageIndexes
     *            The maximum number of the returned list.
     * @return The list of page indexes close to the current page index.
     */
    public List<Long> getClickablePageIndexes(final int maxNumberOfClickablePageIndexes) {
        List<Long> clickablePageIndexes = new ArrayList<Long>(maxNumberOfClickablePageIndexes);
        if (maxNumberOfClickablePageIndexes == 1) {
            clickablePageIndexes.add(pageIndex);
        } else if ((maxNumberOfClickablePageIndexes > 1)) {

            int before = (maxNumberOfClickablePageIndexes - 1) / 2;

            long firstAvailablePageIndex = 1;
            long firstClickablePageIndex = pageIndex - before;
            if (firstClickablePageIndex < firstAvailablePageIndex) {
                firstClickablePageIndex = firstAvailablePageIndex;
            }
            long lastClickablePageIndex = (firstClickablePageIndex + maxNumberOfClickablePageIndexes) - 1;
            if (lastClickablePageIndex > lastAvailablePageIndex) {
                long offset = lastClickablePageIndex - lastAvailablePageIndex;
                lastClickablePageIndex = lastAvailablePageIndex;
                firstClickablePageIndex = firstClickablePageIndex - offset;
                if (firstClickablePageIndex < firstAvailablePageIndex) {
                    firstClickablePageIndex = firstAvailablePageIndex;
                }
            }

            for (long i = firstClickablePageIndex; i <= lastClickablePageIndex; i++) {
                clickablePageIndexes.add(i);
            }
        }
        return clickablePageIndexes;
    }

    public long getFirstResult() {
        return getLimit().getFirstResult();
    }

    public long getLastAvailablePageIndex() {
        return lastAvailablePageIndex;
    }

    public long getPageIndex() {
        return pageIndex;
    }

    public long getPageSize() {
        return getLimit().getMaxResults();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = (prime * result) + (int) (lastAvailablePageIndex ^ (lastAvailablePageIndex >>> 32));
        result = (prime * result) + (int) (pageIndex ^ (pageIndex >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Page [pageIndex=" + pageIndex + ", lastAvailablePageIndex=" + lastAvailablePageIndex + "]";
    }

}
