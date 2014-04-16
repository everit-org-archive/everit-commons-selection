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

/**
 * Represents a range which is bounded by two values. The type of the bounding values can be any comparable types. The
 * constructors validate data integrity (i.e. if the lower bound is lower than or equal to the higher bound value).
 * 
 * The represented range can be open or closed at both bounds.
 * <ul>
 * <li>If the {@link Range#getLowerInclusive() lowerInclusive} property is <code>true</code> then the interval should be
 * considered as being open from the lower bound.</li>
 * <li>If the {@link Range#getHigherInclusive() higherInclusive} property is <code>true</code> then the interval should
 * be considered as being open from the higher bound.</li>
 * </ul>
 * 
 * @param <T>
 *            The type of the bounding values. It is strongly recommended to use immutable types since the {@link Range}
 *            class does not ensure thread safety.
 */
public class Range<T extends Comparable<? super T>> implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -4567444380490553673L;

    protected static int alignComparisionResultBasedOnInclusive(int value, final boolean forLowerBound,
            final boolean thisInclusive, final boolean otherInclusive) {
        if (value == 0) {
            if (!thisInclusive && otherInclusive) {
                value = (forLowerBound ? 1 : -1);
            } else if (thisInclusive && !otherInclusive) {
                value = (forLowerBound ? -1 : 1);
            }
        }
        return value;
    }

    protected final T lowerBound;

    protected final T higherBound;

    protected boolean lowerInclusive = true;

    protected boolean higherInclusive = true;

    /**
     * Creates a range with the given bounding values.
     * 
     * The created range will be open at both bounds.
     * 
     * @param lowerBound
     * @param higherBound
     */
    public Range(final T lowerBound, final T higherBound) {
        super();
        if ((lowerBound != null) && (higherBound != null) && (lowerBound.compareTo(higherBound) > 0)) {
            throw new IllegalArgumentException("lowerBound [" + lowerBound + "] is not lower than higherBound ["
                    + higherBound + "]");
        }
        this.lowerBound = lowerBound;
        this.higherBound = higherBound;
    }

    public Range(final T lowerBound, final T higherBound, final boolean lowerInclusive, final boolean higherInclusive) {
        this(lowerBound, higherBound);
        this.lowerInclusive = lowerInclusive;
        this.higherInclusive = higherInclusive;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        Range<T> other = (Range<T>) obj;
        if ((higherInclusive != other.higherInclusive) || (lowerInclusive != other.lowerInclusive)) {
            return false;
        }
        if (((higherBound != null) && !higherBound.equals(other.higherBound))
                || ((lowerBound != null) && !lowerBound.equals(other.lowerBound))) {
            return false;
        }
        if (((higherBound == null) && (other.higherBound != null))
                || ((lowerBound == null) && (other.lowerBound != null))) {
            return false;
        }
        return true;
    }

    public T getHigherBound() {
        return higherBound;
    }

    public T getLowerBound() {
        return lowerBound;
    }

    /**
     * Determines the relation of this range to an other range.
     * 
     * Returns
     * <ul>
     * <li>{@link RangeRelation#BEFORE} if the <code>other</code> is before this range and they don't have common
     * element.</li>
     * <li>{@link RangeRelation#BEFORE_OVERLAPPING} if <code>other</code> starts before this range but they do have
     * common elements.</li>
     * <li>{@link RangeRelation#CONTAINING} if <code>other</code> starts before this range and ends after this interval
     * (practically <code>other</code> contains <code>this</code>).</li>
     * <li>{@link RangeRelation#IDENTICAL} if the two range are equal.</li>
     * <li>{@link RangeRelation#CONTAINED} if this range starts before <code>other</code> and ends after
     * <code>other</code> (practically <code>this</code> contains <code>other</code>).</li>
     * <li>{@link RangeRelation#AFTER_OVERLAPPING} if <code>other</code> starts before the end of this range and
     * <code>other</code> ends after the end of this range.</li>
     * <li>{@link RangeRelation#AFTER} if <code>other</code> starts after the end of this range.</li>
     * </ul>
     * 
     * @param other
     * @return
     */
    public RangeRelation getRelationTo(final Range<T> other) {
        if (this == other) {
            return RangeRelation.IDENTICAL;
        }
        int lowerToOtherLower = lowerBound.compareTo(other.getLowerBound());
        lowerToOtherLower = Range.alignComparisionResultBasedOnInclusive(lowerToOtherLower, true, lowerInclusive,
                other.isLowerInclusive());

        int higherToOtherHigher = higherBound.compareTo(other.getHigherBound());
        higherToOtherHigher = Range.alignComparisionResultBasedOnInclusive(higherToOtherHigher, false,
                higherInclusive,
                other.isHigherInclusive());

        if ((lowerToOtherLower == 0) && (higherToOtherHigher == 0)) {
            return RangeRelation.IDENTICAL;
        }

        if ((lowerToOtherLower >= 0) && (higherToOtherHigher <= 0)) {
            return RangeRelation.CONTAINING;
        }

        if ((lowerToOtherLower <= 0) && (higherToOtherHigher >= 0)) {
            return RangeRelation.CONTAINED;
        }

        if (lowerToOtherLower > 0) {
            int lowerToOtherHigher = lowerBound.compareTo(other.getHigherBound());
            if ((lowerToOtherHigher == 0) && !(other.isHigherInclusive() && lowerInclusive)) {
                lowerToOtherHigher = 1;
            }
            if (lowerToOtherHigher > 0) {
                return RangeRelation.BEFORE;
            } else {
                return RangeRelation.BEFORE_OVERLAPPING;
            }
        }

        if (higherToOtherHigher < 0) {
            int higherToOtherLower = higherBound.compareTo(other.getLowerBound());
            if ((higherToOtherLower == 0) && !(other.isLowerInclusive() && higherInclusive)) {
                higherToOtherLower = -1;
            }
            if (higherToOtherLower < 0) {
                return RangeRelation.AFTER;
            } else {
                return RangeRelation.AFTER_OVERLAPPING;
            }
        }

        throw new RangeException("failed to determine intervals' relation of this [" + toString() + "] and other ["
                + other.toString() + "]");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((higherBound == null) ? 0 : higherBound.hashCode());
        result = (prime * result) + (higherInclusive ? 1231 : 1237);
        result = (prime * result) + ((lowerBound == null) ? 0 : lowerBound.hashCode());
        result = (prime * result) + (lowerInclusive ? 1231 : 1237);
        return result;
    }

    public Range<T> intersect(final Range<T> other) {
        if (getRelationTo(other).isDistinct()) {
            return null;
        }
        T lowerBound, higherBound;
        boolean lowerInclusive, higherInclusive;
        int lowerComp = this.lowerBound.compareTo(other.lowerBound);
        if (lowerComp > 0) {
            lowerBound = this.lowerBound;
            lowerInclusive = this.lowerInclusive;
        } else if (lowerComp < 0) {
            lowerBound = other.lowerBound;
            lowerInclusive = other.lowerInclusive;
        } else {
            lowerBound = this.lowerBound;
            lowerInclusive = this.lowerInclusive || other.lowerInclusive;
        }
        int higherComp = this.higherBound.compareTo(other.higherBound);
        if (higherComp < 0) {
            higherBound = this.higherBound;
            higherInclusive = this.higherInclusive;
        } else if (higherComp > 0) {
            higherBound = other.higherBound;
            higherInclusive = other.higherInclusive;
        } else {
            higherBound = this.higherBound;
            higherInclusive = this.higherInclusive || other.higherInclusive;
        }
        return new Range<T>(lowerBound, higherBound, lowerInclusive, higherInclusive);
    }

    public boolean isHigherInclusive() {
        return higherInclusive;
    }

    public boolean isLowerInclusive() {
        return lowerInclusive;
    }

    @Override
    public String toString() {
        return "Range [lowerBound=" + lowerBound + ", lowerInclusive=" + lowerInclusive + ", higherBound="
                + higherBound + ", higherInclusive=" + higherInclusive + "]";
    }

    /**
     * Returns the union of <code>this</code> and <code>other</code>. The method does not modify neither
     * <code>this</code> or <code>other</code>.
     * 
     * @param other
     * @return
     */
    public Range<T> union(final Range<T> other) {
        T lowerBound, higherBound;
        boolean lowerInclusive, higherInclusive;
        int lowerComp = this.lowerBound.compareTo(other.lowerBound);
        if (lowerComp < 0) {
            lowerBound = this.lowerBound;
            lowerInclusive = this.lowerInclusive;
        } else if (lowerComp > 0) {
            lowerBound = other.lowerBound;
            lowerInclusive = other.lowerInclusive;
        } else {
            lowerBound = this.lowerBound;
            lowerInclusive = this.lowerInclusive || other.lowerInclusive;
        }
        int higherComp = this.higherBound.compareTo(other.higherBound);
        if (higherComp > 0) {
            higherBound = this.higherBound;
            higherInclusive = this.higherInclusive;
        } else if (higherComp < 0) {
            higherBound = other.higherBound;
            higherInclusive = other.higherInclusive;
        } else {
            higherBound = this.higherBound;
            higherInclusive = this.higherInclusive || other.higherInclusive;
        }
        return new Range<T>(lowerBound, higherBound, lowerInclusive, higherInclusive);
    }

}
