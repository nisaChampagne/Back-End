package com.buildweek4.watermyplants.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Roles", description = "Roles for the user")
@Entity
@Table(name = "roles")
public class Role extends Auditable
    {

        @ApiModelProperty(name = "roleid",
                value = "The Primary Key for the role",
                required = true,
                example = "1")
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long roleid;

        @ApiModelProperty(name = "name",
                value = "The Name of the Role",
                required = true,
                example = "Admin")
        @Column(nullable = false,
                unique = true)
        private String name;

        @OneToMany(mappedBy = "role",
                cascade = CascadeType.ALL)
        @JsonIgnoreProperties("role")
        private List<UserRoles> userRoles = new ArrayList<>();

        public Role() {
        }

        public Role(String name) {
            this.name = name;
        }

        public long getRoleid() {
            return roleid;
        }

        public void setRoleid(long roleid) {
            this.roleid = roleid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<UserRoles> getUserRoles() {
            return userRoles;
        }

        public void setUserRoles(List<UserRoles> userRoles) {
            this.userRoles = userRoles;
        }
    }