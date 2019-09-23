package com.buildweek4.watermyplants.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// User is considered the parent entity
@ApiModel(value = "User", description = "The User Model")
@Entity
@Table(name = "users")
public class User extends Auditable
    {
        @ApiModelProperty(name = "userid",
                value = "Primary Key for User",
                required = true,
                example = "1")
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long userid;

        @ApiModelProperty(name = "username",
                value = "Username",
                required = true,
                example = "th!skewlusername")
        @Column(nullable = false,
                unique = true)
        private String username;

        @ApiModelProperty(name = "password",
                value = "Password for your account",
                required = true,
                example = "pa$$w0rds")
        @Column(nullable = false)
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String password;

        @ApiModelProperty(name = "phonenumber",
                value = "Your Phone Number used to sign up",
                required = true,
                example = "111-867-5309")
        @Column(nullable = false)
        private String phonenumber;

        @OneToMany(mappedBy = "user",
                cascade = CascadeType.ALL)
        @JsonIgnoreProperties("user")
        private List<UserRoles> userRoles = new ArrayList<>();

        @OneToMany(mappedBy = "user",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
        @JsonIgnoreProperties("user")
        private List<Plant> plants = new ArrayList<>();

        public User() {
        }

        public User(String username)
        {
            this.username = username;
        }

        public User(String username, String password, String phonenumber, List<UserRoles> userRoles) {
            setUsername(username);
            setPassword(password);
            setPhonenumber(phonenumber);
            for (UserRoles ur : userRoles) {
                ur.setUser(this);
            }
            this.userRoles = userRoles;
        }


        public long getUserid() {
            return userid;
        }

        public void setUserid(long userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            this.password = passwordEncoder.encode(password);
        }

        public void setPasswordNoEncrypt(String password) {
            this.password = password;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public List<UserRoles> getUserRoles() {
            return userRoles;
        }

        public void setUserRoles(List<UserRoles> userRoles) {
            this.userRoles = userRoles;
        }

        public List<Plant> getPlants() {
            return plants;
        }

        public void setPlants(List<Plant> plants) {
            this.plants = plants;
        }

        public List<SimpleGrantedAuthority> getAuthority() {
            List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

            for (UserRoles r : this.userRoles) {
                String myRole = "ROLE_" + r.getRole().getName().toUpperCase();
                rtnList.add(new SimpleGrantedAuthority(myRole));
            }

            return rtnList;
        }
    }
