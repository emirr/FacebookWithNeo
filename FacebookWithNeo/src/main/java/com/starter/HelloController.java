package com.starter;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entity.Restaurant;
import com.starter.repositories.CommentRepository;
import com.starter.repositories.PostRepository;
import com.starter.repositories.ReplyRepository;
import com.starter.repositories.RestaurantRepository;
import com.starter.repositories.UserRepository;

@Controller
// @RequestMapping("/")
public class HelloController {
	@Autowired
	RestaurantRepository restaurantRepository;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	ReplyRepository replyRepository;
	@Autowired
	UserRepository userRepository;

	private Facebook facebook;
	private ConnectionRepository connectionRepository;
	private String[] pageList = { "cibocalgary" };

	@Inject
	public HelloController(Facebook facebook, ConnectionRepository connectionRepository) {
		this.facebook = facebook;
		this.connectionRepository = connectionRepository;
		System.out.println("sdjk");
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexFacebook(Model model) {
		System.out.println("indexx");
		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String helloFacebook(Model model) {
		System.out.println("sdjk2");

		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			return "redirect:/connect/facebook";
		}

		// Sayfalar sırasıyla alınır.
		for (String pageName : pageList) {
			Page page = facebook.pageOperations().getPage(pageName);
			System.out.println("sayfa adı: " + page.getName());
			System.out.println(System.getProperty("user.country")); 
			System.out.println(System.getProperty("user.language"));
			/**
			 * 1.page bilgileri(rest. entity olarak) db'ye eklenir.
			 * 
			 **/
// -Duser.language=en  -Duser.country=US  -Dfile.encoding=UTF-8  -Duser.variant=EN
			// sayfadaki son 25 post alınır.
			List<Post> list = facebook.feedOperations().getPosts(pageName);
			Restaurant restaurant = new Restaurant();
			Long restId = Long.valueOf(page.getId()).longValue();
			System.out.println("rest id:"+restId);
			
			//restaurant.setId(new Long(1));
			restaurant.setPageName(page.getName());
			//restaurantRepository.save(restaurant);


	/*		for (Post post : list) {
				System.out.println("post id: " + post.getId() + " message: " + post.getMessage());
				com.entity.Post post1 = new com.entity.Post();
				// postRepository.save(post1);

				String s = post.getId();
				// bu regex pattern ile _'den önceki 15 hane "_" ile birlikte
				// silindi.
				s = s.replaceAll("(.*)(_)", "");
				System.out.println("" + s);

				Long postId = Long.parseLong(s.trim());
				// Long postId = Long.valueOf(s.trim());
				// post1.setId(postId);
				/** 2.Her post(post entity olarak) db'ye aktarılır. **

				// her bir post için like yapan kullanıcıların bilgisine
				// erişilir
				PagedList<Reference> usersLikedThisPost = facebook.likeOperations().getLikes(post.getId());
				System.out.println("postu likelayan kişi sayısı:" + usersLikedThisPost.size());
				for (Reference user : usersLikedThisPost) {
					System.out.println(user.getName());
					/**
					 * 3.post'u like eden user bilgisi(user entity olarak) db'ye
					 * gönderilecek.
					 /
					com.entity.User user1 = new com.entity.User();
					Long userId = Long.valueOf(user.getId()).longValue();
					// user1.setId(userId);
					user1.setUserName(user.getName());
					// if(userRepository.findByName(user1.getId()) == null)
					// userRepository.save(user1);
					if (post1.getLikeUsers() == null) {
						Set<User> likePostUsers = new HashSet<>();
						likePostUsers.add(user1);
						post1.setLikeUsers(likePostUsers);
					} else
						post1.getLikeUsers().add(user1);// set null
				}
				PagedList<Comment> comments4ThisPost = facebook.commentOperations().getComments(post.getId());
				System.out.println("postu commentleyen kişi sayısı:" + comments4ThisPost.size());
				for (Comment comment : comments4ThisPost) {
					com.entity.Comment comment1 = new com.entity.Comment();
					String s1 = comment.getId();
					s1 = s1.replaceAll("(.*)(_)", "");
					// Long commId =
					// Long.valueOf(comment.getId()).longValue();//buradan devam
					Long commId = Long.parseLong(s1.trim());
					//comment1.setId(commId);
					comment1.setMessage(comment.getMessage());
					/**
					 * 4.1 burada post'a ait commentlerin bilgisi(comment entity
					 * olarak) db'ye gönderilecek.
					 /

					/**
					 * 4.2 ek olarak comment'i gönderen user bilgisi(user entity
					 * olarak) db'ye gönderilecek.
					 /
					com.entity.User user2 = new com.entity.User();
					String s7 = comment.getFrom().getId();
					s7 = s7.replaceAll("(.*)(_)", "");

					Long userId = Long.parseLong(s7.trim());
					//user2.setId(userId);
					// user2.setId();
					user2.setUserName(comment.getFrom().getName());
					comment1.setCommentUser(user2);
					// if(userRepository.findByName(user2.getId()) == null)
					// userRepository.save(user2);
					/**
					 * 4.3 ek olarak. comment'i like eden user/like listesi
					 * db'ye eklenecek.
					 /
					PagedList<Reference> usersLikedThisComment = facebook.likeOperations().getLikes(comment.getId());
					System.out.println("commenti likelayan kişi sayısı:" + usersLikedThisComment.size());
					for (Reference user : usersLikedThisComment) {
						com.entity.User user1 = new com.entity.User();
						String s8 = user.getId();
						s8 = s8.replaceAll("(.*)(_)", "");

						Long userId2 = Long.parseLong(s8.trim());
						//user1.setId(userId2);
						// user1.setId();
						user1.setUserName(user.getName());
						// if(userRepository.findByName(user1.getId()) == null)
						// userRepository.save(user1);
						if (comment1.getLikeUsers() == null) {
							Set<User> likeUsers = new HashSet<>();
							likeUsers.add(user1);
							comment1.setLikeUsers(likeUsers);
						} else
							comment1.getLikeUsers().add(user1);
					}
					System.out.println(
							"reply içerir: " + facebook.commentOperations().getComments(comment.getId()).size());
					PagedList<Comment> replies = facebook.commentOperations().getComments(comment.getId());
					for (Comment replyComment : replies) {
						System.out.println("replies: " + replyComment.getMessage());

						/**
						 * 5.1 burada comment yapılan reply(replyComment)
						 * bilgileri(reply entity olarak) gönderilecek.
						 /
						com.entity.Reply replyComm = new com.entity.Reply();
						String s9 = replyComment.getId();
						s9 = s9.replaceAll("(.*)(_)", "");

						Long replyId = Long.parseLong(s9.trim());
						//replyComm.setId(replyId);
						replyComm.setMessage(replyComment.getMessage());

						com.entity.User user3 = new com.entity.User();
						// user1.setId(userId2);
						String s10 = replyComment.getFrom().getId();
						s10 = s10.replaceAll("(.*)(_)", "");

						Long userId2 = Long.parseLong(s10.trim());
						//user3.setId(userId2);
						user3.setUserName(replyComment.getFrom().getName());
						// if(userRepository.findByName(user3.getId()) == null)
						// userRepository.save(user3);
						replyComm.setReplyUsers(user3);

						// if(replyCommService.findReplyByName(replyId) == null)
						// replyRepository.save(replyComm);
						if (comment1.getReplies() == null) {
							Set<Reply> repliesList = new HashSet<>();
							repliesList.add(replyComm);
							comment1.setReplies(repliesList);
						} else
							comment1.getReplies().add(replyComm);
						/**
						 * 5.2 ek olarak reply yapan user bilgisi(user entity
						 * olarak) db'ye gönderilecek. getFrom() metodu
						 * sayesinde
						 /

						/**
						 * 5.3 ek olarak reply'ı like eden user/like listesi
						 * db'ye eklenecek.
						 /
						PagedList<Reference> usersLikedThisReply = facebook.likeOperations()
								.getLikes(replyComment.getId());
						System.out.println("replyı likelayan kişi sayısı:" + usersLikedThisReply.size());
						for (Reference user : usersLikedThisReply) {
							com.entity.User user4 = new com.entity.User();
							String s11 = user.getId();
							s11 = s11.replaceAll("(.*)(_)", "");

							Long userId3 = Long.parseLong(s11.trim());
							//user4.setId(userId3);
							user4.setUserName(user.getName());
							// if(userRepository.findByName(user4.getId()) ==
							// null)
							// userRepository.save(user4);
							if (replyComm.getLikeReplyUsers() == null) {
								Set<com.entity.User> likeReplyUsers = new HashSet<>();
								likeReplyUsers.add(user4);
								replyComm.setLikeReplyUsers(likeReplyUsers);
							} else
								replyComm.getLikeReplyUsers().add(user4);
						}
						// -replyRepository.save(replyComm);
					}
					// -commentRepository.save(comment1);
					if (post1.getComments() == null) {
						Set<com.entity.Comment> comments1 = new HashSet<>();
						comments1.add(comment1);
						post1.setComments(comments1);
					} else
						post1.getComments().add(comment1);
				}

				// --postRepository.save(post1);
				// sysout
				if (restaurant.getPosts() == null) {
					Set<com.entity.Post> posts = new HashSet<>();
					posts.add(post1);
					restaurant.setPosts(posts);
				} else
					restaurant.getPosts().add(post1);
				// restaurant.getPosts().add(post1);
			}*/
			register(restaurant);
//		restaurantRepository.save(restaurant);
		}

		// System.out.println("sdjk3");
		// System.out.println(facebook.userOperations().getUserProfile().getFirstName());

		// "203932023059783_991129931006651" id'li post'u like eden
		// kullanıcıların referans listesi
		// PagedList<Reference> usersLikedThisPost =
		// facebook.likeOperations().getLikes("203932023059783_991129931006651");
		// for (Reference user : usersLikedThisPost) {
		// System.out.println(user.getName());
		// }
		// System.out.println("postu likelayan kişi sayısı:" +
		// usersLikedThisPost.size());

		// 991129931006651_991142077672103 nolu comment'i reply eden kullanıcı
		// sayısını verir.
		// System.out.println(
		// "reply içerir: " +
		// facebook.commentOperations().getComments("991129931006651_991142077672103").size());
		// PagedList<Comment> replies =
		// facebook.commentOperations().getComments("991129931006651_991142077672103");
		// for (Comment commment : replies)
		// System.out.println("replies: " + commment.getMessage());
		// facebook.commentOperations().
		// facebook.pageOperations().post

		// Page page = facebook.pageOperations().getPage("cibocalgary");
		// List<Post> list = facebook.feedOperations().getPosts("cibocalgary");
		// for (Post post : list)
		// System.out.println("post id: " + post.getId() + " message: " +
		// post.getMessage());
		// PagedList usersCommentsThisPost =
		// facebook.commentOperations().getComments("203932023059783_991129931006651");
		// System.out.println("postu commentleyen kişi sayısı:" +
		// usersCommentsThisPost.size());
		// model.addAttribute("facebookProfile",
		// facebook.userOperations().getUserProfile());
		// PagedList<Post> feed = facebook.feedOperations().getFeed();
		// model.addAttribute("feed", feed);
		return "";
	}
    @Transactional
	void register(Restaurant rest){
		restaurantRepository.save(rest);
	}

}