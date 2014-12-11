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
import java.util.Collections;
import java.util.List;

public class LimitedResult<T extends Serializable> implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 6257187399825248108L;

    public static final long NUMBER_OF_ALL_ELEMENTS_MIN_VALUE = 0;

    /**
     * The elements of the result.
     */
    private final List<T> elements;

    /**
     * The number of all elements.
     */
    private final long numberOfAllElements;

    /**
     * The limit of the {@link #elements}.
     */
    private final Limit limit;

    public LimitedResult(final List<T> elements, final long numberOfAllElements, final Limit limit) {
        if (elements == null) {
            throw new NullPointerException("elements cannot be null");
        }
        if (numberOfAllElements < NUMBER_OF_ALL_ELEMENTS_MIN_VALUE) {
            throw new IllegalArgumentException("numberOfAllElements [" + numberOfAllElements
                    + "] is less than NUMBER_OF_ALL_ELEMENTS_MIN_VALUE [" + NUMBER_OF_ALL_ELEMENTS_MIN_VALUE + "]");
        }
        if (limit == null) {
            throw new NullPointerException("limit cannot be null");
        }
        if (elements.size() > limit.getMaxResults()) {
            throw new IllegalArgumentException(
                    "the size of the elements is greather than the max results of the limit");
        }
        if (elements.size() > numberOfAllElements) {
            throw new IllegalArgumentException(
                    "the size of the elements is greather than the number of all elements");
        }
        this.elements = Collections.unmodifiableList(elements);
        this.numberOfAllElements = numberOfAllElements;
        this.limit = limit;
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
        LimitedResult other = (LimitedResult) obj;
        if (elements == null) {
            if (other.elements != null) {
                return false;
            }
        } else if (!elements.equals(other.elements)) {
            return false;
        }
        if (limit == null) {
            if (other.limit != null) {
                return false;
            }
        } else if (!limit.equals(other.limit)) {
            return false;
        }
        if (numberOfAllElements != other.numberOfAllElements) {
            return false;
        }
        return true;
    }

    public List<T> getElements() {
        return elements;
    }

    public Limit getLimit() {
        return limit;
    }

    public long getNumberOfAllElements() {
        return numberOfAllElements;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((elements == null) ? 0 : elements.hashCode());
        result = (prime * result) + ((limit == null) ? 0 : limit.hashCode());
        result = (prime * result) + (int) (numberOfAllElements ^ (numberOfAllElements >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "LimitedResult [elements=" + elements + ", numberOfAllElements=" + numberOfAllElements + ", limit="
                + limit + "]";
    }

}
