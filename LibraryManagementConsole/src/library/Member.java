package library;

public class Member {
	
	private int memberId;
	private String Name;
	
	public Member(int memberId, String name) {
		super();
		this.memberId = memberId;
		Name = name;
	}
	public int getMemberId() {
		return memberId;
	}
	public String getName() {
		return Name;
	}
	@Override
	public String toString() {
		return "memberId :" + memberId
				+ " Name :" + Name ;
	}
}
