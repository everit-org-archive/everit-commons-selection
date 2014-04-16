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
package org.everit.commons.selection.lang;

import org.everit.commons.selection.Range;

/**
 * Base class for representing number ranges. An appropriate subclass can be found in the
 * <code>org.everit.util.core.filter</code> package for each primitive wrapper class (except {@link Character} which is
 * not considered as a number).
 * 
 * Provides the abstract utility method {@link #getLength()} which returns the difference between the lower bound and
 * the higher bound for each {@link Number} subclass. Each implementation performs a substraction-like operation between
 * the lowe and higher bounds of the range.
 * 
 * @param <T>
 */
public abstract class NumberRange<T extends Number & Comparable<? super T>> extends Range<T> {

    private static final long serialVersionUID = -1450755051526926124L;

    public NumberRange(final T lowerBound, final T higherBound) {
        super(lowerBound, higherBound);
    }

    public NumberRange(final T lowerBound, final T higherBound, final boolean lowerInclusive,
            final boolean higherInclusive) {
        super(lowerBound, higherBound, lowerInclusive, higherInclusive);
    }

    /**
     * @return the length of the number range (ie. the difference between the lower bound and the higher bound).
     * 
     *         The implementations don't care if the range is open or closed on any bounds (meaning the
     *         {@code lowerInclusive} and {@code higherInclusive} properties.
     */
    public abstract T getLength();

}
