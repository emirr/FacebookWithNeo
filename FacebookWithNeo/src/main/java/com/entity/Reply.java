package com.entity;

import java.util.List;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Reply {
	@GraphId
	Long id;
	
	@Relationship(type = "REPLIED_BY", direction = "OUTGOING")
	private User replyUsers;
	
	@Relationship(type = "LIKED_BY", direction = "OUTGOING")
	private Set<User> likeReplyUsers;
	
	public Set<User> getLikeReplyUsers() {
		return likeReplyUsers;
	}

	public void setLikeReplyUsers(Set<User> likeReplyUsers) {
		this.likeReplyUsers = likeReplyUsers;
	}

	private String message;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getReplyUsers() {
		return replyUsers;
	}

	public void setReplyUsers(User replyUsers) {
		this.replyUsers = replyUsers;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Reply() {

	}
	
	public boolean equals(Object other) {

		if (this == other)
			return true;

		if (id == null)
			return false;

		if (!(other instanceof Comment))
			return false;

		return id.equals(((Comment) other).id);
	}

	public int hashCode() {
		return id == null ? System.identityHashCode(this) : id.hashCode();
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", message=" + message + "]";
	}
}
