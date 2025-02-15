package com.sist.mapper;
import com.sist.vo.*; 
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
public interface MypageMapper {
	// 나의 정보 확인
	@Select("SELECT userId,userName,userPwd,gender,email,post,addr1,addr2,phone,profile,nickname "
			+"FROM funding_member "
			+"WHERE userId=#{userId}") 
	public MemberVO mypageInfoData(String userId);
	 
	// 닉네임 설정
	@Update("UPDATE funding_member SET nickname=#{nickname} WHERE userId=#{userId}")
	public void SetNickname(Map map);   
	 
	// 정보 업데이트
	@Update("UPDATE funding_member SET email=#{email}, gender=#{gender}, addr1=#{addr1}, addr2=#{addr2}, post=#{post}, phone=#{phone} "
			+"WHERE userId=#{userId}")    
	public void mypageInfoUpdate(MemberVO vo);    
	    
	// 찜 목록  
	@Select("SELECT f.fno, f.title, f.type, f.thumb, w.wno, w.userId, w.cate, to_char(w.regdate, 'YYYY-MM-DD') as dbday " +
	        "FROM funding f " + 
	        "JOIN funding_wish w ON f.fno = w.pno " +
	        "WHERE w.userId = #{userId}")
	public List<WishVO> fundingWishList(String userId);
 
	@Select("SELECT COUNT(*) FROM funding_wish WHERE userId = #{userId}")
	public int fundingWishCount(String userId);
	
	// 알림 목록
	@Select("SELECT f.fno, f.title, f.type, f.thumb, a.ano, a.userId, to_char(a.regdate, 'YYYY-MM-DD') as dbday, " +
	        "to_char(f.startdate, 'YYYY-MM-DD') as startdate " +
	        "FROM funding f " +
	        "JOIN funding_alert a ON f.fno = a.fno " +
	        "WHERE a.userId = #{userId}")  
	public List<AlertVO> fundingAlertList(String userId);
	
	@Select("SELECT COUNT(*) FROM funding_alert WHERE userId = #{userId}")
	public int fundingAlertCount(String userId);
     	   
	// 펀딩 구매 내역  
	@Select("SELECT rb.rbno, rb.rno, f.fno, rb.account, rb.price, rb.delivery, rb.totalprice, rb.userId, rb.name, rb.phone, rb.post, rb.addr1, rb.addr2, rb.requestMsg, rb.regdate, f.thumb, f.title, rb.num "  
	        + "FROM (SELECT rbno, rno, fno, account, price, delivery, totalprice, userId, name, phone, post, addr1, addr2, requestMsg, regdate, rownum as num "
	        + "FROM (SELECT rbno, rno, fno, account, price, delivery, totalprice, userId, name, phone, post, addr1, addr2, requestMsg, regdate "
	        + "FROM reward_buy WHERE userId=#{userId} ORDER BY rbno DESC)) rb "
			+ "JOIN funding f ON f.fno = rb.fno "
			+ "WHERE rb.num BETWEEN #{start} AND #{end}")   
	public List<RewardBuyVO> getRewardBuyList(Map map); 
 
    @Select("SELECT CEIL(COUNT(*)/6.0) FROM reward_buy WHERE userId = #{userId}")
    public int getTotalRewardBuyCount(String userId);
 
    @Select("SELECT rb.rbno, rb.rno, rb.fno, rb.name AS buyerName, fr.name AS rewardName, fr.del_start, rb.totalprice, rb.delivery, rb.phone, rb.post, rb.addr1, rb.addr2, rb.regdate, rb.requestMsg " +
            "FROM reward_buy rb " +  
            "JOIN funding_reward fr ON rb.rno = fr.rno " +  // 리워드 정보 조인
            "WHERE rb.rbno = #{rbno}")  // 구매 번호로 구매 내역 상세 조회
    public RewardBuyVO getPurchaseDetail(int rbno);
 
    // 상품 구매 
    @Select("SELECT COUNT(*) FROM f_goods_order WHERE id = #{userId}")
    public int goodsBuyTotalCount(String userId);
    
      
    // 커뮤니티 내역 출력
    @Select("SELECT c.dcno, c.fno, c.userId, c.cate, c.content, TO_CHAR(c.regdate, 'YYYY.MM.DD HH24:MI:SS') as dbday," 
            +"f.title, f.thumb, c.num " 
            +"FROM (SELECT dcno, fno, userId, cate, content, regdate, rownum as num " 
            +"FROM (SELECT dcno, fno, userId, cate, content, regdate " 
            +"FROM funding_detail_comm WHERE userId = #{userId} ORDER BY dcno DESC) " 
            +"WHERE rownum <= #{end}) c " 
            +"JOIN funding f ON c.fno = f.fno " 
            +"WHERE c.num BETWEEN #{start} AND #{end}")
    public List<FundingDetailCommVO> myFundingCommuList(Map map);  

    @Select("SELECT CEIL(COUNT(*) / 4.0) FROM funding_detail_comm WHERE userId = #{userId}")
    public int myFundingCommuTotalPage(String userId);

    @Select("SELECT COUNT(*) FROM funding_detail_comm WHERE userId = #{userId}")
    public int myFundingCommuListCount(String userId);
    
    // 프로젝트 관리자 페이지 펀딩 내역
    @Select("SELECT fno, title, thumb, p_admin, targetprice, totalprice, TO_CHAR(headcount, 'FM999,999') as fm_headcount, TO_CHAR(enddate, 'YYYYMMDD') as endday, wish, alert, backing,  num "
			+ "FROM (SELECT fno, title, thumb, p_admin, targetprice, totalprice, headcount, enddate, wish, alert, backing, rownum as num "
			+ "FROM (SELECT fno, title, thumb, p_admin, targetprice, totalprice, headcount, enddate, wish, alert, backing "
			+ "FROM funding WHERE userId=#{userId} "
			+ "ORDER BY fno DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FundingVO> myFundingListData(Map map);
	
	@Select("SELECT COUNT(*) FROM funding WHERE userId=#{userId}")
	public int myFundingTotalCount(String userId);
	
	@Select("SELECT alert, wish, backing, totalprice FROM funding WHERE userId=#{userId}")
	public List<FundingVO> myFundingTotalData(String userId);
	
	@Select("SELECT CEIL(COUNT(*)/4.0) FROM funding "
				+ "WHERE userId=#{userId}")
	public int myFundingTotalPage(String userId);

	// 나의 문의 내역
	@Select("SELECT qno,subject,type,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,reok,id,num "
			+ "FROM (SELECT qno,subject,type,regdate,reok,id,rownum as num "
			+ "FROM (SELECT qno,subject,type,regdate,reok,id "
			+ "FROM site_qna WHERE id=#{userId} ORDER BY qno DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<QnaVO> myQnaListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM site_qna WHERE id=#{userId}")
	public int myQnaTotalPage(String userId);
	
	@Select("SELECT COUNT(*) FROM site_qna WHERE id=#{userId}")
	public int myQnaTotalCount(String userId);
	
}
        