package me.akmaljon.convertor.repository;

import me.akmaljon.convertor.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExchangeRepository extends JpaRepository<Exchange, UUID> {
}
