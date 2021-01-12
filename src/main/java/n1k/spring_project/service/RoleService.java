package n1k.spring_project.service;

import org.springframework.stereotype.Service;
import n1k.spring_project.model.Role;
import n1k.spring_project.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    //**********************************************

    public Role getRole(String role) {
        return roleRepository.getRoleByName(role);
    }
}
