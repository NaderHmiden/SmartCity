/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttractionModels;

/**
 *
 * @author manso
 */

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Opening hours record for a given day.
 */
public record OpeningHours(DayOfWeek day, LocalTime open, LocalTime close) {
    public OpeningHours {
        Objects.requireNonNull(day, "day");
        Objects.requireNonNull(open, "open");
        Objects.requireNonNull(close, "close");
        if (open.isAfter(close)) {
            throw new IllegalArgumentException("open must be before close");
        }
    }

    @Override
    public String toString() {
        return day + ": " + open + " - " + close;
    }
}

