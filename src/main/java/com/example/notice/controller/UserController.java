package com.example.notice.controller;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.notice.config.auth.PrincipalDetails;
import com.example.notice.model.Feed;
import com.example.notice.model.Follow;
import com.example.notice.repository.FeedRepository;
import com.example.notice.repository.FollowRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("user")
public class UserController {
	final private FollowRepository followRepo;
	final private FeedRepository feedRepo;
	
	// user/mypage 내가 팔로우한 선생님 목룍,내정보
	@GetMapping("mypage")
	public String mypage(Model model, 
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		String username = principalDetails.getUsername();
		System.out.println(username);
		List<Follow> follows = followRepo.findByUsername(username);
		model.addAttribute("follows", follows);
		model.addAttribute("username", username);
		return "user/mypage";
	}
	
	// user/mypage/role?role=teacher 마이페이지에서 선생님으로 활동할건지 
	// user/lesson?teacherId=티처아이디 
	/*========================
	@GetMapping("lesson")
	public String lesson(@Param(value = "teacherId") String teacherId, Model model) {
		List<Feed> feeds = feedRepo.findByUsernameOrderByCreateDate(teacherId);
		model.addAttribute("feeds", feeds);
		System.out.println(feeds.get(0));
		return "user/feed";
	} ============없애기=========================*/
	
	@GetMapping("create")
	public String create(Model model) {//새 글을 작성
		model.addAttribute("feed", new Feed());
		return "user/edit";
	}
	@GetMapping("edit")
	public String edit(@RequestParam("id") int id, Model model,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {//수정을 눌렀을때
		Feed feed = feedRepo.findById(id).get();
		if(principalDetails.getUsername().equals(feed.getUsername())) {
			model.addAttribute("feed", feed);
		}else {
			return "index";
		}
		return "user/edit";
	}
	@PostMapping("edit")
	public String create(@AuthenticationPrincipal PrincipalDetails principalDetails,
			Model model, Feed feed) {
		if(feed.getId()==0) {
			feed.setUsername(principalDetails.getUsername());
		}
		feedRepo.save(feed);
		System.out.println("feed: "+feed);
		return "index";
	}
	
	@GetMapping("feed")
	public String feed(@RequestParam("id") int id, Model model) {
		Feed feed = feedRepo.findById(id).get();
		model.addAttribute("feed",feed);
		System.out.println(feed);
		return "user/feed";
	}
	
	@GetMapping("delete")
	public String delete(@RequestParam("id") int id,@RequestParam("username") String username,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		if(username.equals(principalDetails.getUsername())) {
			feedRepo.deleteById(id);
		}
		return "index";
	}

}
