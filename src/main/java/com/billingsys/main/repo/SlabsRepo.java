package com.billingsys.main.repo;

import com.billingsys.main.models.Slabs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlabsRepo extends JpaRepository<Slabs,Integer>  {
    @Query(value = "select * from Slabs s where s.low_range<=?1 and s.high_range>=?1 Limit 1",
            nativeQuery = true)
    Slabs getSlab(int usage);

    @Query("select s from Slabs s where s.highRange<=?1")
    List<Slabs> getSlabs(int highRange);
}
