package com.sist.mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.*;
import com.sist.vo.*;
public interface MemberMapper {
	// 아이디 중복체크 
	@Select("SELECT COUNT(*) FROM funding_member "
			+ "WHERE userId=#{userId}")
	public int idCheck(String userId);
	
	//회원가입
	@Insert("INSERT INTO funding_member(userId, userName, userPwd, enabled, gender, email, post, addr1, addr2, phone, profile) "
			+ "VALUES(#{userId}, #{userName}, #{userPwd}, 1, #{gender}, #{email}, #{post}, #{addr1}, #{addr2}, #{phone}, '../images/profile.png')")
	public void memberInsert(MemberVO vo);
	  
	@Insert("INSERT INTO authority VALUES(#{userId}, 'ROLE_USER')")
	public void memberAuthorityInsert(String userId);
	
	//로그인
	@Select("SELECT userId, userName, userPwd, enabled, authority "
			+ "FROM funding_member fm, authority au "
			+ "WHERE fm.userId=au.userId "
			+ "AND fm.userId=#{userId}")   
	public MemberVO memberInfoData(String userId);
	
	@Select("SELECT userId, userName, userPwd, gender, email, phone, post, addr1, addr2, profile, nickname "
			+ "FROM funding_member "
			+ "WHERE userId=#{userId}")
	public MemberVO memberSessionData(String userId);
	
	//배송정보
	@Select("SELECT userId, userName, phone, post, addr1, addr2 "
			+ "FROM funding_member "
			+ "WHERE userId=#{userId}")
	public MemberVO rewardDeliveryMemberInfo(String userId);
	
	@Select("SELECT userId, userName, enabled, gender, email, post, addr1, addr2, phone, profile, "
	        + "admin, regdate, modofudate, lastlogin, nickname, msg "
	        + "FROM funding_member "
	        + "ORDER BY userName ASC")
	public List<MemberVO> memberListData(Map map);

}
