package com.agileinstitute;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MySetTest {
    @Test
    void aNewSetShouldBeEmpty() {
        // given
        final MySet sut = new MySet();
        // when
        final boolean result = sut.isEmpty();
        // then
        assertThat(result).isTrue();
    }

    @Test
    void addingAnElementToAnEmptySetMakesItNonEmpty() {
        // given
        final MySet sut = new MySet();
        // when
        sut.add("something");
        // then
        final boolean result = sut.isEmpty();
        assertThat(result).isFalse();
    }

    @Test
    void anEmptySetShouldNotContainSomething() {
        // given
        final MySet sut = new MySet();
        // when
        final boolean result = sut.contains("something");
        // then
        assertThat(result).isFalse();
    }

    @Test
    void addingSomethingToTheSetWillMakeItContainIt() {
        // given
        final MySet sut = new MySet();
        sut.add("something");
        // when
        final boolean result = sut.contains("something");
        // then
        assertThat(result).isTrue();
    }

    @Test
    void addingTwoItemsShouldContainBothOfThem() {
        // given
        final MySet sut = new MySet();
        // when
        sut.add("something");
        sut.add("something else");
        // then
        assertThat(sut.contains("something")).isTrue();
        assertThat(sut.contains("something else")).isTrue();
    }

    @Test
    void shouldNotBeAbleToAddNull() {
        // given
        final MySet sut = new MySet();

        assertThatThrownBy(() -> {
            // when
            sut.add(null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    void shouldNotBeAbleToAddEmptyString() {
        // given
        final MySet sut = new MySet();

        assertThatThrownBy(() -> {
            // when
            sut.add("");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void theUnionOfTwoOneElementSetsContainsBothElements() {
        MySet setA = new MySet();
        setA.add("something");
        MySet setB = new MySet();
        setB.add("something else");

        MySet union = MySet.union(setA, setB);

        assertThat(union.contains("something")).isTrue();
        assertThat(union.contains("something else")).isTrue();
    }

    @Test
    void theIntersectionOfTwoDistinctOneElementSetsIsEmpty() {
        // given
        MySet setA = new MySet();
        setA.add("something");
        MySet setB = new MySet();
        setB.add("something else");

        // when
        MySet intersection = MySet.intersect(setA, setB);

        // then
        assertThat(intersection.isEmpty()).isTrue();
    }

    @Test
    void theIntersectionOfSetWithItselfShouldReturnASetThatContainsTheElementsOfTheOriginalSet() {
        // given
        MySet setA = new MySet();
        setA.add("something");

        // when
        MySet intersection = MySet.intersect(setA, setA);

        // then
        assertThat(intersection.contains("something")).isTrue();
    }

    @Test
    void theIntersectionOfSetWithAnotherSetWithTheSameElementsShouldReturnASetWithTheSameElements() {
        // given
        MySet setA = new MySet();
        setA.add("something");
        MySet setB = new MySet();
        setB.add("something");

        // when
        MySet intersection = MySet.intersect(setA, setB);

        // then
        assertThat(intersection.contains("something")).isTrue();
    }

    @Test
    void theEmptySetIsASupersetOfItself() {
        MySet emptySet = new MySet();

        assertThat(emptySet.isSupersetOf(emptySet)).isTrue();
    }

    @Test
    void theEmptySetIsNotASupersetOfANonemptySet() {
        MySet emptySet = new MySet();
        MySet nonemptySet = new MySet();
        nonemptySet.add("something");

        assertThat(emptySet.isSupersetOf(nonemptySet)).isFalse();
    }

    @Test
    void theNonemptySetIsASupersetOfTheEmptySet() {
        MySet emptySet = new MySet();
        MySet nonemptySet = new MySet();
        nonemptySet.add("something");

        assertThat(nonemptySet.isSupersetOf(emptySet)).isTrue();
    }

    @Test
    void twoDistinctNonEmptySetsAreNotASupersetsOfEachOther() {
        MySet setA = new MySet();
        setA.add("something");
        MySet setB = new MySet();
        setB.add("something else");

        assertThat(setA.isSupersetOf(setB)).isFalse();
        assertThat(setB.isSupersetOf(setA)).isFalse();
    }

    @Test
    void aSetContainingTheSameElementsAndSomeMoreIsASuperset() {
        MySet setA = new MySet();
        setA.add("something");
        MySet setB = new MySet();
        setB.add("something");
        setB.add("something else");

        assertThat(setB.isSupersetOf(setA)).isTrue();
    }

    @Test
    void theEmptySetIsASubsetOfItself() {
        MySet emptySet = new MySet();

        assertThat(emptySet.isSubsetOf(emptySet)).isTrue();
    }

    @Test
    void theNonemptySetIsNotASubsetOfTheEmptySet() {
        MySet emptySet = new MySet();
        MySet nonemptySet = new MySet();
        nonemptySet.add("something");

        assertThat(nonemptySet.isSubsetOf(emptySet)).isFalse();
    }

    @Test
    void setWithOneElementIsSubsetOfAnotherSetWithThatElementAndAnother() {
        MySet setA = new MySet();
        setA.add("something");
        MySet setB = new MySet();
        setB.add("something");
        setB.add("something else");

        assertThat(setA.isSubsetOf(setB)).isTrue();
    }
}
