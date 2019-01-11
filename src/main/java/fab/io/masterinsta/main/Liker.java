package fab.io.masterinsta.main;

import java.util.Comparator;

import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

class Liker implements Comparator<Liker>{
	private String name;
	private Long likes;
	private InstagramUserSummary userSummary;
	
	public Liker() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getLikes() {
		return likes;
	}

	public void setLikes(Long likes) {
		this.likes = likes;
	}

	public InstagramUserSummary getUserSummary() {
		return userSummary;
	}

	public void setUserSummary(InstagramUserSummary userSummary) {
		this.userSummary = userSummary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Liker other = (Liker) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compare(Liker o1, Liker o2) {
		return o1.getLikes().compareTo(o2.getLikes());
	}
	
	
}