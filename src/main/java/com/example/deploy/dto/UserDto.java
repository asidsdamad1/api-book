package com.example.deploy.dto;

import com.example.deploy.domain.Role;
import com.example.deploy.domain.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UserDto {

    private Long id;
    private String displayName;
    private String username;
    private String password;
    private String oldPassword;
    private String confirmPassword;
    private boolean isSetPassword;
    private boolean changePass;
    private Boolean active;
    private String lastName;
    private String firstName;
    private Date dob;
    private String birthPlace;
    private String email;
    private boolean hasPhoto;
    private Set<RoleDto> roles = new HashSet();

    public UserDto() {
    }

    public UserDto(User entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.displayName = "";
            this.username = entity.getUsername();
            this.password = entity.getPassword();

            this.active = entity.getActive();
            this.email = entity.getEmail();

            Iterator var3;
            if (entity.getRole() != null) {
                this.roles.clear();
                var3 = entity.getRole().iterator();

                while(var3.hasNext()) {
                    Role role = (Role)var3.next();
                    this.roles.add(new RoleDto(role));
                }
            }
        }
    }

    public User toEntity() {
        User entity = new User();
        if (this.id != null) {
            entity.setId(this.id);
        }

        entity.setUsername(this.username);
        entity.setPassword(this.password);
        entity.setActive(this.active);
        entity.setEmail(this.email);


        Iterator var3;
        if (this.roles.size() > 0) {
            var3 = this.roles.iterator();

            while(var3.hasNext()) {
                RoleDto dto = (RoleDto)var3.next();
                entity.getRole().add(dto.toEntity());
            }

        }

        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isSetPassword() {
        return isSetPassword;
    }

    public void setSetPassword(boolean setPassword) {
        isSetPassword = setPassword;
    }

    public boolean getChangePass() {
        return changePass;
    }

    public void setChangePass(boolean changePass) {
        this.changePass = changePass;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isHasPhoto() {
        return hasPhoto;
    }

    public void setHasPhoto(boolean hasPhoto) {
        this.hasPhoto = hasPhoto;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

}
