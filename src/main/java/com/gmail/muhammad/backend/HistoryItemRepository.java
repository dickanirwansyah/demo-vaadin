package com.gmail.muhammad.backend;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gmail.muhammad.backend.data.entity.HistoryItem;

public interface HistoryItemRepository extends JpaRepository<HistoryItem, Long> {
}
