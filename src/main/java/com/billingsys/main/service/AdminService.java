package com.billingsys.main.service;

import com.billingsys.main.models.Admin;
import com.billingsys.main.models.Consumer;
import com.billingsys.main.models.Slabs;

import java.util.List;

public interface AdminService {
    Admin verify(Admin a);

    void addSlabs(Slabs s);

    Slabs getSlab(int id);

    void updateSlab(Slabs s);

    List<Consumer> getDetailes();
}
