package com.example.pet1.service;

import com.example.pet1.enumiration.RoleEnum;
import com.example.pet1.model.Role;
import com.example.pet1.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements BaseService<Role> {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    private void initRoles(){
        List<Role> allRole = new ArrayList<>();
        for (RoleEnum roleEnum : RoleEnum.values()) {
            Role roleFromDb = roleRepository.findByName(roleEnum.toString());
            if (roleFromDb == null) roleFromDb = new Role(roleEnum.toString());
            allRole.add(roleFromDb);
        }
        roleRepository.saveAll(allRole);

    }
    
    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role saveOrUpdate(Role entity) {
        return roleRepository.save(entity);
    }

    @Override
    public List<Role> saveAll(List<Role> entities) {
        return roleRepository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    public Role findByName(String name){
        return roleRepository.findByName(name);
    }
}
