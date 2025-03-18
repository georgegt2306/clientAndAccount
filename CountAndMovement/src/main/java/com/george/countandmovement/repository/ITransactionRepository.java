package com.george.countandmovement.repository;
import com.george.countandmovement.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccount_AccountIdAndDateBetween(Integer accountId, LocalDateTime startDate, LocalDateTime endDate);
    List<Transaction> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

}
