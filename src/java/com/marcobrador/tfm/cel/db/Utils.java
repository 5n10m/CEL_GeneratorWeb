package com.marcobrador.tfm.cel.db;

import java.util.Collection;

/**
 * Utils class for commont methods.
 */
public class Utils {

    private Utils() {
        // Hide default constructor
    }

    /**
     * Compares two objects that may be null.
     *
     * @param first The first object of the comparison.
     * @param second The second object of the comparison.
     *
     * @return {@code true} if both objects are equal, {@code false} otherwise.
     */
    public static boolean safeCompare(Object first, Object second) {
        if (first == null) {
            return second == null;
        } else {
            return second != null && first.equals(second);
        }
    }

    /**
     * Utility method for comparing collections. If one of the two collections is {@code null} and
     * the other one is not, the comparison will succeed if and only if the other one is empty.
     *
     * In case both are {@code null}, comparison succeeds. In case both are not {@code null}, the
     * result of {@link Collection#equals(Object)} is returned.
     *
     * @param first The first parameter of the comparison.
     * @param second The second parameter of the comparison.
     *
     * @return {@code true} if both collections are equal, {@code false} otherwise.
     */
    public static boolean lazyCollectionCompare(Collection<?> first, Collection<?> second) {
        if (first == null && second == null) {
            return true;
        } else if (first == null) {
            return second.isEmpty();
        } else if (second == null) {
            return first.isEmpty();
        } else {
            return first.equals(second);
        }
    }

    /**
     * Helper method for safely computing the hash code over a potentially null object.
     *
     * @param o The object whose hash code is to be computed.
     *
     * @return The hash code of the object, 0 if the object is null.
     */
    public static int computeNullableHashcode(Object o) {
        return o == null ? 0 : o.hashCode();
    }

    public static String nullableToStringWithCr(Object o) {
        if (o == null) {
            return "";
        } else {
            return o + "\n";
        }
    }

    public static String nullableToStringWithComma(Object o) {
        if (o == null) {
            return "";
        } else {
            return o + ", ";
        }
    }
}
