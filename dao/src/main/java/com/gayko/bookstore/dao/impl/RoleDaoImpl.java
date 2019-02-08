package com.gayko.bookstore.dao.impl;

import com.gayko.bookstore.dao.RoleDao;
import com.gayko.bookstore.dao.model.Permission;
import com.gayko.bookstore.dao.model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

    private static final Logger logger = LogManager.getLogger(RoleDaoImpl.class);

    public RoleDaoImpl() {
        super(Role.class);
    }

    @Override
    public Role getByName(String roleName){
        return(Role) getCurrentSession().createQuery("from Role where name=:rolename")
         .setParameter("rolename", roleName)
                .uniqueResult();
    }

    @Override
    public int deleteAll() {

        try {
            List<Role> rolesToDelete = getCurrentSession().createQuery("from Role").getResultList();
            for (Role r : rolesToDelete) {
                for (Permission rep : r.getPermissions()) {
                    rep.removeAllRoles();
                }
                r.removeAllPermissions();
            }
            return 0;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return -1;
        }
    }

    @Override
    public int unbind(String commonRoleName) {  // Unbind permission from it's role
        try {
            Role roleToDelete = (Role) getCurrentSession().createQuery("from Role where name =: rolename")
                    .setParameter("rolename", commonRoleName)
                    .uniqueResult();
            for (Permission rep : roleToDelete.getPermissions()) {
                rep.removeAllRoles();
            }
            roleToDelete.removeAllPermissions();
            return 0;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return -1;
        }
    }

    @Override
    public void delete(){ // delete roles without permissions
        List<Role> roles = getCurrentSession().createQuery("from Role").list();
        for (Role role : roles) {
            if(role.getPermissions().isEmpty()){
                getCurrentSession().delete(role);
            }
        }
    }

    }
