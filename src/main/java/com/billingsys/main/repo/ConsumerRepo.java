package com.billingsys.main.repo;

import com.billingsys.main.models.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepo extends JpaRepository<Consumer,Integer> {

    @Query("select c from Consumer c where c.name=?1 and c.password=?2")
    Consumer verifyConsumer(String name, String password);

    @Query("select c from Consumer c where c.name=?1")
    Consumer getByName(String name);
}
