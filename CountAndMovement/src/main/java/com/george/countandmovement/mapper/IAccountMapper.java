package com.george.countandmovement.mapper;

import com.george.countandmovement.model.dto.AccountRequestDTO;
import com.george.countandmovement.model.dto.AccountResponseDTO;
import com.george.countandmovement.model.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAccountMapper {
    AccountResponseDTO toDto(Account account);
    Account toEntity(AccountRequestDTO account);
}
