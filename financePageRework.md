# Finance Month Page Rework

## Goal

Redesign the Finance feature so every item contains a date and recurring information.

The new structure should support future views without requiring another database redesign.

Future views:

- Current Month
- Specific Month
- Week
- Year
- Analytics
- Upcoming recurring payments

---

# Phase 1 — Backend

## Update FinanceItemData

Current

- Header
- Icon
- Category
- Priority
- Recurrence
- Amount

Add

- StartDate (LocalDate)

Result

FinanceItemData

- id
- header
- icon
- category
- priority
- recurrence
- amount
- startDate

---

## Update Room Entity

Add

- startDate

Update

- TypeConverter for LocalDate (if not already available)

Migration

- Handle existing saved data
- Give existing entries a default date if necessary

---

## Repository

Update

Insert

Update

Read

Delete

Queries

Prepare repository methods for

- Get all items
- Get items for month
- Get items for week
- Get items for year

Only implement month query now.

---

# Phase 2 — Finance Month Logic

Input

Displayed month/year

Repository returns

Finance items for that month

Filtering

One Time

- exact month/year

Monthly

- month/year after start date

Weekly

- occurrences inside displayed month

Yearly

- matching month after start year

Exact recurrence calculations can be expanded later.

---

# Phase 3 — UI

Top bar

Month navigation

Scrollable list

Each card displays

- Icon
- Header
- Amount
- Category
- Priority
- Recurrence
- Date

Footer

Monthly total (optional later)

---

# Future Expansion

Additional pages

- Week
- Year
- All Items
- Category Summary
- Income vs Expense
- Recurring Payments
