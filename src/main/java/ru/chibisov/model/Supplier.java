package ru.chibisov.model;

public class Supplier implements Identifiable<Long> {

    private static final long serialVersionUID = -7077314038055460239L;

    private Long id;
    private String orgName;
    private String nameAgent;
    private String phoneAgent;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getNameAgent() {
        return nameAgent;
    }

    public void setNameAgent(String nameAgent) {
        this.nameAgent = nameAgent;
    }

    public String getPhoneAgent() {
        return phoneAgent;
    }

    public void setPhoneAgent(String phoneAgent) {
        this.phoneAgent = phoneAgent;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", orgName='" + orgName + '\'' +
                ", nameAgent='" + nameAgent + '\'' +
                ", phoneAgent='" + phoneAgent + '\'' +
                '}';
    }
}