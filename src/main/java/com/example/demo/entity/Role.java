package com.example.demo.entity;

public class Role {

    private String id;
    private String createDate;
    private String creatorUser;
    private String description;
    private String isDeleted;
    private String roleName;
    private String updateDate;
    private String updateUser;
    private String isAdministrators;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(String creatorUser) {
        this.creatorUser = creatorUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getIsAdministrators() {
        return isAdministrators;
    }

    public void setIsAdministrators(String isAdministrators) {
        this.isAdministrators = isAdministrators;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", createDate='" + createDate + '\'' +
                ", creatorUser='" + creatorUser + '\'' +
                ", description='" + description + '\'' +
                ", isDeleted='" + isDeleted + '\'' +
                ", roleName='" + roleName + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", isAdministrators='" + isAdministrators + '\'' +
                '}';
    }
}

