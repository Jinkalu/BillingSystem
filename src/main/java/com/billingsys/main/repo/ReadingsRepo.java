package com.billingsys.main.repo;

import com.billingsys.main.models.Consumer;
import com.billingsys.main.models.Readings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingsRepo extends JpaRepository<Readings,Integer> {
    @Query("select r from Readings r where r.consumer=?1")
    List<Readings> getByConsumer(Consumer c);


    @Query(value = "select * from Readings r where r.consumerid=?1 order by id desc limit 1 ",
            nativeQuery = true)
    Readings getLast(Consumer c);

}
