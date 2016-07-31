package com.entity;

import java.util.Set;

//import java.sql.Date;
//import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Comment {
	@GraphId
	Long id;

	@Relationship(type = "LIKED_BY", direction = "OUTGOING")
	private Set<User> likeUsers;
	
	@Relationship(type = "COMMENTED_BY", direction = "OUTGOING")
	private User commentUser;
	
//	@Relationship(type = "REPLIED_BY", direction = "INCOMING")
//	private List<User> replyUsers;
	
	@Relationship(type = "HAS_REPLIES", direction = "OUTGOING")
	private Set<Reply> replies;
	
	public Set<User> getLikeUsers() {
		return likeUsers;
	}

	public void setLikeUsers(Set<User> likeUsers) {
		this.likeUsers = likeUsers;
	}

	public User getCommentUser() {
		return commentUser;
	}

	public void setCommentUser(User commentUser) {
		this.commentUser = commentUser;
	}

	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Comment() {

	}

	private String message;
	// commentin sahibi olan user
	// private User user;
	// commenti beğenen userların listesi
	// @Relationship(type = "LIKES")
	// List<User> users;
	// private List<User> userLikedComment;

	

	// private Date createdTime;
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

	public Set<Reply> getReplies() {
		return replies;
	}

	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}

}
