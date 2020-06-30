package se.lexicon.mariahofstam.Presence_CMS.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String groupName;


    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.LAZY,
            mappedBy = "group",     //declared in class Member, a @ManyToOne
            orphanRemoval = true
    )
    private List<Member> memberList;    //List of members for this group


    // Constructors
    public Organisation() {}

    public Organisation(String groupName) {
        this.groupName = groupName;
        this.memberList = new ArrayList<>();
    }


    //Getters and Setters

    public int getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


    //Methods for adding and removing members to the memberList
    public void addMember(Member member){

        if (!memberList.contains(member)) {
            memberList.add(member);
        }

    }

    public void removeMember(Member member) {
        if(memberList.contains(member)) {
            memberList.remove(member);
        }
    }



    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organisation)) return false;
        Organisation organisation = (Organisation) o;
        return id == organisation.id &&
                Objects.equals(groupName, organisation.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupName);
    }
}
