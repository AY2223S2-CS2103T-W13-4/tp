package seedu.address.model.student;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that a {@code Lesson}'s date matches the given date.
 */
public class LessonBelongsToDatePredicate implements Predicate<Lesson> {
    private final LocalDate targetDate;

    /**
     * Creates a predicate to test if a {@code Homework}'s {@code isCompleted} matches the given boolean.
     * @param targetDate The LocalDate to test against.
     */
    public LessonBelongsToDatePredicate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    /**
     * Tests if a {@code Homework}'s {@code isCompleted} matches the given boolean.
     *
     * @param lesson The homework to test.
     * @return True if the lesson's date matches the given date.
     */
    @Override
    public boolean test(Lesson lesson) {
        return lesson.getStartTime().toLocalDate().isEqual(targetDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof LessonBelongsToDatePredicate // instanceof handles nulls
            && targetDate == ((LessonBelongsToDatePredicate) other).targetDate); // date check
    }
}