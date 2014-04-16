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

public class Limit implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 3535393755786439877L;

    /**
     * Limit value for firstResult.
     */
    public static final long FIRST_RESULT_MIN_VALUE = 0;

    /**
     * Limit value for maxResults.
     */
    public static final long MAX_RESULTS_MIN_VALUE = 1;

    /**
     * The index of the first result in the whole result set. The beginning of the range. Must be greater or equal to
     * zero.
     */
    private final long firstResult;

    /**
     * The maximum number of the results that interested. Must be greater then zero or <code>null</code> for all
     * results.
     */
    private final long maxResults;

    /**
     * Constructs a new Range.
     * 
     * @param firstResult
     *            The index of the first result in the whole result set. The beginning of the range. The index of the
     *            first result in the whole result set. The beginning of the limit. Indexed from
     *            {@link #FIRST_RESULT_MIN_VALUE}. Cannot be less than {@link #FIRST_RESULT_MIN_VALUE}
     * @param maxResults
     *            The maximum number of the results that interested. Cannot be less tahn {@link #MAX_RESULTS_MIN_VALUE}.
     */
    public Limit(final long firstResult, final long maxResults) {
        if (firstResult < FIRST_RESULT_MIN_VALUE) {
            throw new IllegalArgumentException("firstResult [" + firstResult
                    + "] is less than FIRST_RESULT_MIN_VALUE [" + FIRST_RESULT_MIN_VALUE + "]");
        }
        if (maxResults < MAX_RESULTS_MIN_VALUE) {
            throw new IllegalArgumentException("maxResults [" + maxResults
                    + "] is less than MAX_RESULTS_MIN_VALUE [" + MAX_RESULTS_MIN_VALUE + "]");
        }
        this.firstResult = firstResult;
        this.maxResults = maxResults;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Limit other = (Limit) obj;
        if (firstResult != other.firstResult) {
            return false;
        }
        if (maxResults != other.maxResults) {
            return false;
        }
        return true;
    }

    public long getFirstResult() {
        return firstResult;
    }

    public long getMaxResults() {
        return maxResults;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + (int) (firstResult ^ (firstResult >>> 32));
        result = (prime * result) + (int) (maxResults ^ (maxResults >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Limit [firstResult=" + firstResult + ", maxResults=" + maxResults + "]";
    }

}
