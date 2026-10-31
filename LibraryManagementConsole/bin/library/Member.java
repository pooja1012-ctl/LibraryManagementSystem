
package library;

public class Member extends Person {

    public Member(int memberId, String name) {
        super(memberId, name);
    }

    @Override
    public String toString() {
        return "Member ID: " + getId()
                + ", Name: " + getName();
    }
    
    public int getMemberId() {
        return getId();
    }
}
