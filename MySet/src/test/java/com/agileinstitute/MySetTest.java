package com.agileinstitute;

import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MySetTest {
    private @NonNull MySet makeSetWith(String... elements) {
        final MySet sut = new MySet();
        for (String element : elements) {
            sut.add(element);
        }
        return sut;
    }

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
        final MySet sut = makeSetWith("something");
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
        final MySet sut = makeSetWith("something");
        // when
        final boolean result = sut.contains("something");
        // then
        assertThat(result).isTrue();
    }

    @Test
    void addingTwoItemsShouldContainBothOfThem() {
        // given
        final MySet sut = makeSetWith("something", "something else");
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
        MySet setA = makeSetWith("something");
        MySet setB = makeSetWith("something else");

        MySet union = MySet.union(setA, setB);

        assertThat(union.contains("something")).isTrue();
        assertThat(union.contains("something else")).isTrue();
    }

    @Test
    void theIntersectionOfTwoDistinctOneElementSetsIsEmpty() {
        // given
        MySet setA = makeSetWith("something");
        MySet setB = makeSetWith("something else");

        // when
        MySet intersection = MySet.intersect(setA, setB);

        // then
        assertThat(intersection.isEmpty()).isTrue();
    }

    @Test
    void theIntersectionOfSetWithItselfShouldReturnASetThatContainsTheElementsOfTheOriginalSet() {
        // given
        MySet setA = makeSetWith("something");

        // when
        MySet intersection = MySet.intersect(setA, setA);

        // then
        assertThat(intersection.contains("something")).isTrue();
    }

    @Test
    void theIntersectionOfSetWithAnotherSetWithTheSameElementsShouldReturnASetWithTheSameElements() {
        // given
        MySet setA = makeSetWith("something");
        MySet setB = makeSetWith("something");

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
        MySet nonemptySet = makeSetWith("something");

        assertThat(emptySet.isSupersetOf(nonemptySet)).isFalse();
    }

    @Test
    void theNonemptySetIsASupersetOfTheEmptySet() {
        MySet emptySet = new MySet();
        MySet nonemptySet = makeSetWith("something");

        assertThat(nonemptySet.isSupersetOf(emptySet)).isTrue();
    }

    @Test
    void twoDistinctNonEmptySetsAreNotASupersetsOfEachOther() {
        MySet setA = makeSetWith("something");
        MySet setB = makeSetWith("something else");

        assertThat(setA.isSupersetOf(setB)).isFalse();
        assertThat(setB.isSupersetOf(setA)).isFalse();
    }

    @Test
    void aSetContainingTheSameElementsAndSomeMoreIsASuperset() {
        MySet setA = makeSetWith("something");
        MySet setB = makeSetWith("something", "something else");

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
        MySet nonemptySet = makeSetWith("something");

        assertThat(nonemptySet.isSubsetOf(emptySet)).isFalse();
    }

    @Test
    void setWithOneElementIsSubsetOfAnotherSetWithThatElementAndAnother() {
        MySet setA = makeSetWith("something");
        MySet setB = makeSetWith("something", "something else");

        assertThat(setA.isSubsetOf(setB)).isTrue();
    }

    @Test
    void twoDisjointSetsAreNotSubsetsOfEachOther() {
        MySet setA = makeSetWith("something");
        MySet setB = makeSetWith("something else");

        assertThat(setA.isSubsetOf(setB)).isFalse();
    }

    @Test
    void twoEmptySetsAreEqual() {
        MySet emptySet1 = new MySet();
        MySet emptySet2 = new MySet();

        assertThat(emptySet1.isEqualTo(emptySet2)).isTrue();
    }

    @Test
    void aNonemptySetIsNotEqualToTheEmptySet() {
        MySet emptySet = new MySet();
        MySet nonemptySet = makeSetWith("something");

        assertThat(nonemptySet.isEqualTo(emptySet)).isFalse();
    }

    @Test
    void twoDisjointSetsAreNotEqual() {
        MySet setA = makeSetWith("something");
        MySet setB = makeSetWith("something else");

        assertThat(setA.isEqualTo(setB)).isFalse();
    }
}
