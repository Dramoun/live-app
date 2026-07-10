# Calendar Month Page

## Data Model

Each calendar cell contains:

* `date: LocalDate`
* `eventCount: Int`
* `isCurrentMonth: Boolean`
* `isToday: Boolean`

The cell itself is dumb and only displays the provided data.

---

## Calendar Generation

**Input**

* Displayed month/year

**Process**

1. Get the first day of the displayed month.
2. Determine which weekday it falls on.
3. Calculate the first visible date (Monday of the first calendar row) by subtracting the required number of days.
4. Generate 42 consecutive dates (`6 × 7`) using `LocalDate.plusDays(1)`.
5. For each generated date:

   * determine `isCurrentMonth`
   * determine `isToday`
   * fetch `eventCount`
   * create `CalendarCellData`

**Output**

* `List<CalendarCellData>` (42 items)

---

## UI Layout

```
Top Bar
Month Navigation
Weekday Header

6 rows × 7 columns
```

Each cell displays:

* Large day number
* Small event count
* Current month styling
* Today highlight

---

## Navigation

Previous month

* Regenerate calendar

Next month

* Regenerate calendar

Tap cell

* Open Calendar Day page
* Pass `LocalDate`

---

## Helper Functions

* Get first day of displayed month
* Get weekday of first day
* Calculate first visible date
* Generate 42 consecutive dates
* Check if date belongs to displayed month
* Check if date is today
* Get event count for date
* Build `CalendarCellData`

---

## Notes

* Always generate **42 cells** (6 × 7).
* Do **not** special-case previous or next months; rely on `LocalDate.plusDays()` and `minusDays()`.
* The UI should never calculate dates—it only renders the generated data.
* Pass `LocalDate` between screens instead of day numbers to avoid reconstructing dates later.

