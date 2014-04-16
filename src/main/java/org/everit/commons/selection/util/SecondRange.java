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
package org.everit.commons.selection.util;

import org.everit.commons.selection.lang.LongRange;

/**
 * {@code TimeInterval} is the interval of two timestamps represented in seconds using {@code Long} values.
 */
public class SecondRange extends LongRange {

    private static final long serialVersionUID = 1459251233031508309L;

    public SecondRange(final Long lowerBound, final Long higherBound) {
        super(lowerBound, higherBound);
    }

    public SecondRange(final Long lowerBound, final Long higherBound, final boolean lowerInclusive,
            final boolean higherInclusive) {
        super(lowerBound, higherBound, lowerInclusive, higherInclusive);
    }

    /**
     * Returns the interval duration described in human-readable format.
     * 
     * The result will be in "((\d+)h)? ?((\d+)m)? ?((\d+)s)?" format.
     * 
     * Examples:
     * <table style="width:400px">
     * <thead>
     * <tr>
     * <th>Interval</th>
     * <th>formatted duration</th>
     * </tr>
     * </thead> <tbody>
     * <tr>
     * <td>2010-10-10 08:00:00 - 2010-10-10 12:10:20</td>
     * <td>4h 10m 20s</td>
     * </tr>
     * <tr>
     * <td>2010-10-10 08:00:00 - 2010-10-10 08:10:20</td>
     * <td>10m 20s</td>
     * </tr>
     * <tr>
     * <td>2010-10-10 08:00:00 - 2010-10-10 12:00:20</td>
     * <td>4h 20s</td>
     * </tr>
     * <tr>
     * <td>2010-10-10 08:00:00 - 2010-10-10 12:10:00</td>
     * <td>4h 10m</td>
     * </tr>
     * <tr>
     * <td>2010-10-10 08:00:00 - 2010-10-10 12:00:00</td>
     * <td>4h</td>
     * </tr>
     * <tr>
     * <td>2010-10-10 08:00:00 - 2010-10-10 08:10:00</td>
     * <td>10m</td>
     * </tr>
     * <tr>
     * <td>2010-10-10 08:00:00 - 2010-10-10 08:00:20</td>
     * <td>20s</td>
     * </tr>
     * <tr>
     * <td>2010-10-10 08:00:00 - 2010-10-10 08:00:00</td>
     * <td>0s</td>
     * </tr>
     * </tbody>
     * </table>
     * 
     * Useful for debugging and displaying purposes.
     */
    public String getAsDuration() {
        if (lowerBound.longValue() == higherBound.longValue()) {
            return "0s";
        }
        long duration = getLength().longValue();
        long remaining = duration;
        long seconds = remaining % 60;
        remaining /= 60;
        long minutes = remaining % 60;
        remaining /= 60;
        long hours = remaining % 60;
        StringBuilder sb = new StringBuilder();
        boolean leadingSpaceNeeded;
        if (hours != 0) {
            sb.append(Math.abs(hours)).append("h");
            leadingSpaceNeeded = true;
        } else {
            leadingSpaceNeeded = false;
        }
        if (minutes != 0) {
            if (leadingSpaceNeeded) {
                sb.append(' ');
            }
            sb.append(Math.abs(minutes)).append("m");
            leadingSpaceNeeded = true;
        }
        if ((seconds != 0) || ((minutes == 0L) && (hours == 0L))) {
            if (leadingSpaceNeeded) {
                sb.append(' ');
            }
            sb.append(Math.abs(seconds)).append("s");
        }
        return sb.toString();
    }

}
