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

public final class LimitUtil {

    /**
     * Shifts the given limit if necessary. If the given number of all elements is greater than the
     * {@link Limit#getFirstResult()} then the given {@link Limit} will be returned. Otherwise a new {@link Limit} will
     * be created with a modified {@link Limit#getFirstResult()} value (the range will be within 0 and the number of all
     * elements).
     * 
     * @param limit
     *            the {@link Limit} to check, cannot be null
     * @param numberOfAllElements
     *            the reference that will be checked against the {@link #firstResult}
     * @return The valid range that corresponds to the number of all elements.
     */
    public static Limit shiftIfNecessary(final Limit limit, final long numberOfAllElements) {
        if (limit == null) {
            throw new NullPointerException("limit cannot be null");
        }
        if (numberOfAllElements < LimitedResult.NUMBER_OF_ALL_ELEMENTS_MIN_VALUE) {
            throw new IllegalArgumentException("numberOfAllElements [" + numberOfAllElements
                    + "] is less than NUMBER_OF_ALL_ELEMENTS_MIN_VALUE ["
                    + LimitedResult.NUMBER_OF_ALL_ELEMENTS_MIN_VALUE + "]");
        }
        long maxResults = limit.getMaxResults();
        if (numberOfAllElements == 0) {
            return new Limit(0, maxResults);
        }
        long firstResult = limit.getFirstResult();
        if (firstResult < numberOfAllElements) {
            return limit;
        }
        long newFirstResult = (numberOfAllElements / maxResults) * maxResults;
        if (newFirstResult == numberOfAllElements) {
            newFirstResult = newFirstResult - maxResults;
        }
        return new Limit(newFirstResult, maxResults);
    }

    private LimitUtil() {
    }

}
