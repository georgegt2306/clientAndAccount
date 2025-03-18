package com.george.countandmovement.mapper;

import com.george.countandmovement.model.dto.TransactionRequestDTO;
import com.george.countandmovement.model.dto.TransactionResponseDTO;
import com.george.countandmovement.model.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface ITransactionMapper {

   @Mapping(source = "account.customerId", target = "customerId")
   @Mapping(source = "account.accountId", target = "accountId")
   @Mapping(source = "account.accountType", target = "accountType")
   @Mapping(source = "account.accountNumber", target = "accountNumber")
   TransactionResponseDTO toDto(Transaction transaction);

   @Mapping(source = "account", target = "account.accountId")
   Transaction toEntity(TransactionRequestDTO transaction);
}
