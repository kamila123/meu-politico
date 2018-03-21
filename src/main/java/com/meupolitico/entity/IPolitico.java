package com.meupolitico.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IPolitico extends CrudRepository<Politico, Long> {

}
