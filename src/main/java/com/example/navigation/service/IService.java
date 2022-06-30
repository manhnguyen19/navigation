package com.example.navigation.service;

import com.example.navigation.entity.Navigate;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IService<E> {
    ResponseEntity<E> create(E e, boolean isDeleted);
    List<E> read(boolean state);
    ResponseEntity<E> update(int id);
    void  delete(int id);
    ResponseEntity<List<Navigate>> getList( boolean isDeleted);
}
