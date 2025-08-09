package com.expenseTracker.respository;

import com.expenseTracker.model.Expense;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExpenseRepository {
    private final JdbcTemplate jdbcTemplate;

    public Expense save(Expense expense) {
        String sql = "INSERT INTO expenses (amount, description, date, type) VALUES (?, ?, ?, ?) RETURNING id";
        Long id = jdbcTemplate.queryForObject(
                sql,
                new Object[] {
                        expense.getAmount(),
                        expense.getDescription(),
                        expense.getDate(),
                        expense.getType()
                },
                Long.class
        );
        expense.setId(id);
        return expense;
    }

    public List<Expense> findAll() {
        String sql = "SELECT * FROM expenses";
        return jdbcTemplate.query(sql, this::mapRowToExpense);
    }

    public Expense findById(long id) {
        String sql = "SELECT * FROM expenses WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, this::mapRowToExpense);
    }

    public Expense update(Expense expense) {
        String sql = "UPDATE expenses SET amount = ?, description = ?, date = ?, type = ? WHERE id = ?";
        jdbcTemplate.update(sql, expense.getAmount(), expense.getDescription(), expense.getDate(), expense.getType(), expense.getId());
        return expense;
    }

    public int deleteById(long id) {
        String sql = "DELETE FROM expenses WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public List<Expense> findByType(String type) {
        String sql = "SELECT * FROM expenses WHERE type = ?";
        return jdbcTemplate.query(sql, new Object[]{type}, this::mapRowToExpense);
    }

    public List<Expense> findByDateRange(java.time.LocalDate startDate, java.time.LocalDate endDate) {
        String sql = "SELECT * FROM expenses WHERE date BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new Object[]{startDate, endDate}, this::mapRowToExpense);
    }

    private Expense mapRowToExpense(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        Expense expense = new Expense();
        expense.setId(rs.getLong("id"));
        expense.setDescription(rs.getString("description"));
        expense.setAmount(rs.getDouble("amount"));
        expense.setType(rs.getString("type"));
        expense.setDate(rs.getDate("date").toLocalDate());
        return expense;
    }
}