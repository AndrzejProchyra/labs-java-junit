package com.agileinstitute;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MySet {
    private Set<String> trackingNumbers = new HashSet<>();

    public static MySet union(MySet setA, MySet setB) {
        MySet newSet = new MySet();
        newSet.trackingNumbers.addAll(setA.trackingNumbers);
        newSet.trackingNumbers.addAll(setB.trackingNumbers);
        return newSet;
    }

    public static MySet intersect(MySet setA, MySet setB) {
        final MySet result = new MySet();
        setA.trackingNumbers.stream()
                .filter(setB.trackingNumbers::contains)
                .forEach(result::add);
        return result;
    }

    public boolean isEmpty() {
        return trackingNumbers.isEmpty();
    }

    public void add(String trackingNumber) {
        Objects.requireNonNull(trackingNumber);
        if (trackingNumber.isEmpty()) {
            throw new IllegalArgumentException();
        }
        trackingNumbers.add(trackingNumber);
    }

    public boolean contains(String trackingNumber) {
        return trackingNumbers.contains(trackingNumber);
    }

    public boolean isSupersetOf(MySet otherSet) {
        return trackingNumbers.containsAll(otherSet.trackingNumbers);
    }

    public boolean isSubsetOf(MySet otherSet) {
        return isEmpty();
    }
}
