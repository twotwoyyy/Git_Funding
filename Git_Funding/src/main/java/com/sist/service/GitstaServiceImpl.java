package com.sist.service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GitstaServiceImpl implements GitstaService{
	@Autowired
	private GitstaDAO gDao;

	@Override
	public List<GitstaVO> gitstaMyTotalListData(String userId,int start, int end) {
		return gDao.gitstaMyTotalListData(userId,start, end);
	}

	@Override
	public int gitstaMyTotalCount(String userId) {
		return gDao.gitstaMyTotalCount(userId);
	}

	@Override
	public MemberVO gitstaInfoData(String userId) {
		return gDao.gitstaInfoData(userId);
	}

	@Override
	public void feedInsert(GitstaVO vo) {
		gDao.feedInsert(vo);
	}

	@Override
	public List<GitstaVO> gitstaTotalListData() {
		return gDao.gitstaTotalListData();
	}

	@Override
	public int gitstaTotalCount() {
		return gDao.gitstaTotalCount();
	}

	@Override
	public List<MemberVO> gitstaFollowingListData(String userId) {
		return gDao.gitstaFollowingListData(userId);
	}

	@Override
	public void insertFollow(String followerId, String followingId) {
		gDao.insertFollow(followerId, followingId);
	}

	@Override
	public void deleteFollow(String followerId, String followingId) {
		gDao.deleteFollow(followerId, followingId);
	}

	@Override
	public int getFollowingCount(String userId) {
		return gDao.getFollowingCount(userId);
	}

	@Override
	public List<MemberVO> gitstaFollowerListData(String userId) {
		return gDao.gitstaFollowerListData(userId);
	}

	@Override
	public int getFollowerCount(String userId) {
		return gDao.getFollowerCount(userId);
	}
	// 팔로우 상태 확인
	@Override
	public int followCheck(String sessionId, String userId) {
		return gDao.followCheck(sessionId, userId);
	}

	@Override
	public void setFeedDisplayDate(List<GitstaVO> list) {
		// TODO Auto-generated method stub
		LocalDateTime now = LocalDateTime.now(); // 현재 시간 가져오기

        for (GitstaVO vo : list) {
            LocalDateTime regdate = vo.getRegdate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            Duration duration = Duration.between(regdate, now);
            String dbday;

            if (duration.toDays() > 0) {
                dbday = duration.toDays() + "일 전";
            } else if (duration.toHours() > 0) {
                dbday = duration.toHours() + "시간 전";
            } else if (duration.toMinutes() > 0) {
                dbday = duration.toMinutes() + "분 전";
            } else {
                dbday = duration.getSeconds() + "초 전";
            }

            vo.setDbday(dbday); // GitstaVO에 경과 시간 설정
        }
	}

	@Override
	public GitstaVO gitstaDetailData(int no) {
		return gDao.gitstaDetailData(no);
	}



	@Override
	public GitstaVO deleteInfoData(int no) {
		return gDao.deleteInfoData(no);
	}

	@Override
	public String deletePost(int no) {
		return gDao.deletePost(no);
	}

	@Override
	public String updatePost(GitstaVO vo) {
		return gDao.updatePost(vo);
	}

	@Override
	public GitstaVO postUpdateData(int no) {
		return gDao.postUpdateData(no);
	}

	@Override
	public List<GitstaReVO> commentListData(Map<String, Object> map) {
		return gDao.commentListData(map);
	}

	@Override
	public int commentTotalPage(int rno) {
		return gDao.commentTotalPage(rno);
	}

	@Override
	public void commentInsert(GitstaReVO vo) {
		gDao.commentInsert(vo);
	}

	@Override
	public void commentReplyReplyInsert(int gno, GitstaReVO vo) {
		gDao.commentReplyReplyInsert(gno, vo);
	}



	@Override
	public void commentReplyDecrement(int gno) {
		gDao.commentReplyDecrement(gno);
	}

	@Override
	public void commentUpdate(GitstaReVO vo) {
		gDao.commentUpdate(vo);
		
	}

	@Override
	public void commentDelete(Map<String, Object> map) {
		gDao.commentDelete(map);
		
	}

	@Override
	public GitstaReVO commentDeleteInfoData(int gno) {
		return gDao.commentDeleteInfoData(gno);
	}

	@Override
	public String deletePostWithComments(int no) {
		return gDao.deletePostWithComments(no);
	}





}
