package com.billingsys.main.repo;

import com.billingsys.main.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepo extends JpaRepository<Admin,Integer> {

    @Query("select a from Admin a where a.name=?1 and a.password=?2")
    Admin verify(String name, String password);

}
