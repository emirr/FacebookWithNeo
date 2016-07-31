package com.entity;

import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Post {
	@GraphId 
	Long id;	
	
	@Relationship(type = "HAS_COMMENT",direction = "OUTGOING")
	private Set<Comment> comments;
	@Relationship(type = "LIKED_BY", direction = "OUTGOING")
	private Set<User> likeUsers;
	public Post(){
		
	}
	
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Post other = (Post) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + "]";
	}

	public Set<User> getLikeUsers() {
		return likeUsers;
	}

	public void setLikeUsers(Set<User> likeUsers) {
		this.likeUsers = likeUsers;
	}

}
