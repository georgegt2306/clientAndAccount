package com.george.countandmovement.mapper;

import com.george.countandmovement.model.dto.TransactionRequestDTO;
import com.george.countandmovement.model.dto.TransactionResponseDTO;
import com.george.countandmovement.model.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface ITransactionMapper {

   TransactionResponseDTO toDto(Transaction transaction);
   Transaction toEntity(TransactionRequestDTO transaction);
}
