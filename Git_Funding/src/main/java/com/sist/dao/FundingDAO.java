package com.sist.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class FundingDAO {
	@Autowired
	private FundingMapper mapper;
	
	/*
	 //메인-위시순
	@Select("SELECT fno, title, thumb, targetprice, totalprice, rownum "
			+ "FROM (SELECT fno, title, thumb, targetprice, totalprice "
			+ "FROM funding "
			+ "WHERE WHERE startdate<SYSDATE AND enddate>SYSDATE "
			+ "ORDER BY wish DESC) "
			+ "WHERE rownum<=4")
	public List<FundingVO> mainWishListData();
	*/
	public List<FundingVO> mainWishListData(){
		return mapper.mainWishListData();
	}
	
	/*
	//메인-지지순
	@Select("SELECT fno, title, thumb, targetprice, totalprice, rownum "
			+ "FROM (SELECT fno, title, thumb, targetprice, totalprice "
			+ "FROM funding "
			+ "WHERE WHERE startdate<SYSDATE AND enddate>SYSDATE "
			+ "ORDER BY backing DESC) "
			+ "WHERE rownum<=4")
	public List<FundingVO> mainBackingListData();
	*/
	public List<FundingVO> mainBackingListData(){
		return mapper.mainBackingListData();
	}
	
	/*
	//메인-랭킹 펀딩 
	@Select("SELECT fno, title, thumb, headcount, rownum "
			+ "FROM (SELECT fno, title, thumb, headcount "
			+ "FROM funding "
			+ "WHERE startdate<SYSDATE AND enddate>SYSDATE "
			+ "ORDER BY headcount DESC) "
			+ "WHERE rownum<=5")
	public List<FundingVO> mainFundingRankListData();
	*/
	public List<FundingVO> mainFundingRankListData(){
		return mapper.mainFundingRankListData();
	}
	
	/*
	//메인-랭킹 스토어 
	@Select("SELECT fgno, title, img, hit, rownum " 
			+ "FROM (SELECT fg.fgno, fg.title, fgi.img, fg.hit " 
			+ "FROM f_goods fg LEFT JOIN (SELECT fgno, MAX(img) as img FROM f_goods_img fgi GROUP BY fgno order by fgno) fgi " 
			+ "ON fg.fgno=fgi.fgno ORDER BY hit DESC) "
            + "WHERE rownum<=5")
	public List<GoodsVO> mainStoreRankListData(); 
	 */
	public List<GoodsVO> mainStoreRankListData(){
		return mapper.mainStoreRankListData();
	}
	
	/*
	//메인-오늘오픈
	@Select("SELECT fno, title, thumb, targetprice, totalprice "
			+ "FROM funding "
			+ "WHERE WHERE startdate=SYSDATE "
			+ "ORDER BY fno DESC")
	public List<FundingVO> mainTodayListData();
	*/
	public List<FundingVO> mainTodayListData(){
		return mapper.mainTodayListData();
	}
	
	/*
	//메인-마감임박
	@Select("SELECT fno, title, thumb, targetprice, totalprice "
			+ "FROM funding "
			+ "WHERE WHERE enddate=SYSDATE "
			+ "ORDER BY fno DESC")
	public List<FundingVO> mainDeadlineListData(); 
	 */
	public List<FundingVO> mainDeadlineListData(){
		return mapper.mainDeadlineListData();
	}
	
	/*
	//메인쿠키
	Select("SELECT fno, title, thumb, targetprice, totalprice, startdate "
			+ "FROM funding "
			+ "WHERE fno=#{fno}")
	public List<FundingVO> mainCookieListData(int fno); 
	 */
	public FundingVO mainCookieListData(int fno){
		return mapper.mainCookieListData(fno);
	}
	
	/*
	//메인-광고
	@Select("SELECT * FROM funding_ad ORDER BY DBMS_RANDOM.VALUE")
	public AdVO mainAdData(); 
	 */
	public AdVO mainAdData() {
		return mapper.mainAdData();
	}
	
	/*
	@Select("SELECT fno, title, thumb, p_admin, targetprice, totalprice, TO_CHAR(startdate, 'MM\"월 \"DD\"일\"') as end, num "
			+ "FROM (SELECT fno, title, thumb, p_admin, targetprice, totalprice, startdate, rownum as num "
			+ "FROM (SELECT fno, title, thumb, p_admin, targetprice, totalprice, startdate "
			+ "FROM funding WHERE startdate>SYSDATE"
			+ "ORDER BY fno DESC))"
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FundingVO> open_list(Map map);
	*/
	public List<FundingVO> openListData(Map map){
		return mapper.openListData(map);
	}
	/*
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM funding "
			+ "WHERE startdate>SYSDATE")
	public int openTotalPage();
	*/
	public int openTotalPage() {
		return mapper.openTotalPage();
	}
	
	/*
	//오픈 카테고리 분류별 출력
	@Select("SELECT fno, title, thumb, p_admin, targetprice, totalprice, alert, TO_CHAR(startdate, 'MM\"월 \"DD\"일\"') as startday, num "
			+ "FROM (SELECT fno, title, thumb, p_admin, targetprice, totalprice, alert, startdate, rownum as num "
			+ "FROM (SELECT fno, title, thumb, p_admin, targetprice, totalprice, alert, startdate "
			+ "FROM funding "
			+ "WHERE startdate>SYSDATE AND type=#{cate} "
			+ "ORDER BY fno DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}") 
	public List<FundingVO> openCateListData(Map map);
	*/
	public List<FundingVO> openCateListData(Map map){
		return mapper.openCateListData(map);
	}
	
	/*
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM funding "
			+ "WHERE startdate>SYSDATE AND type=#{cate}")
	public int openCateTotalPage();
	*/
	public int openCateTotalPage(String cate) {
		return mapper.openCateTotalPage(cate);
	}
	
	/*
	@Select("SELECT fa.fno "
			+ "FROM funding, funding_alert fa"
			+ "WHERE userId=#{userId} AND funding.fno=fa=fno")
	public AlertVO openAlertCheck(String id);
	 */
	public List<AlertVO> openAlertCheck(String id) {
		return mapper.openAlertCheck(id);
	}
	
	/*
	@Select("SELECT fno, title, thumb, p_admin, targetprice, totalprice, TO_CHAR(enddate, 'YYYY-MM-DD') as end, num "
			+ "FROM (SELECT fno, title, thumb, p_admin, targetprice, totalprice, enddate, rownum as num "
			+ "FROM (SELECT fno, title, thumb, p_admin, targetprice, totalprice, enddate "
			+ "FROM funding WHERE startdate<SYSDATE"
			+ "ORDER BY fno DESC))"
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FundingVO> funding_list(Map map); 
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM funding "
			+ "WHERE startdate<SYSDATE")
	public int fundingTotalPage();
	 */
	public List<FundingVO> fundingListData(Map map){
		return mapper.fundingListData(map);
	}
	/*
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM funding "
			+ "WHERE startdate<SYSDATE")
	public int openTotalPage();
	*/
	public int fundingTotalPage() {
		return mapper.fundingTotalPage();
	}
	
	/*
 	@Select("SELECT fno, title, thumb, p_admin, targetprice, totalprice, TO_CHAR(headcount, 'FM999,999') as fm_headcount, TO_CHAR(enddate, 'YYYYMMDD') as endday, num "
			+ "FROM (SELECT fno, title, thumb, p_admin, targetprice, totalprice, headcount, enddate, rownum as num "
			+ "FROM (SELECT fno, title, thumb, p_admin, targetprice, totalprice, headcount, enddate "
			+ "FROM funding WHERE startdate<SYSDATE AND enddate>SYSDATE AND type=#{cate}"
			+ "ORDER BY fno DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FundingVO> fundingCateListData(Map map);
	*/
	public List<FundingVO> fundingCateListData(Map map){
		return mapper.fundingCateListData(map);
	}
	
	/*
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM funding "
			+ "WHERE startdate<SYSDATE AND enddate>SYSDATE AND type=#{cate}")
	public int fundingCateTotalPage(String cate);
	 */
	public int fundingCateTotalPage(String cate) {
		return mapper.fundingCateTotalPage(cate);
	}
	
	/*
	@Update("UPDATE funding SET "
			+ "alert=alert+1 "
			+ "WHERE fno=#{fno}")
	public void fundingAlertUpdate(int fno);
	*/
	public void fundingAlertUpdate(int fno) {
		mapper.fundingAlertUpdate(fno);
	}
	
	/*
	@Insert("INSERT INTO funding_alert(alert_ano_seq.nextval, #{userId}, #{fno}, SYSDATE)")
	public void fundingAlertInsert(AlertVO vo); 
	 */
	public void fundingAlertInsert(Map map) {
		mapper.fundingAlertInsert(map);
	}
	
	/*
	 @Update("UPDATE funding SET "
			+ "alert=alert-1 "
			+ "WHERE fno=#{fno}")
	public void fundingAlertDecr(int fno);
	*/
	public void fundingAlertDecr(int fno) {
		mapper.fundingAlertDecr(fno);
	}
	
	/*
	@Delete("DELETE FROM funding_alert WHERE fno=#{fno} AND userId=#{userId}")
	public void fundingAlertDelete(Map map); 
	 */
	public void fundingAlertDelete(Map map) {
		mapper.fundingAlertDelete(map);
	}
	
	/*
 	@Select("SELECT fno, thumb, type, title, description, p_admin, targetprice, totalprice, wish, backing, "
			+ "TO_CHAR(headcount, 'FM999,999') as fm_headcount, period, TO_CHAR(enddate, 'YYYYMMDD') as endday "
			+ "FROM funding "
			+ "WHERE fno=#{fno}")
	public FundingVO fundingDetailData(int fno);
	*/
	public FundingVO fundingDetailData(int fno) {
		return mapper.fundingDetailData(fno);
	}
	
	/*
	@Select("SELECT image FROM funding_img WHERE fno=#{fno} ORDER BY order_num ASC")
	public  List<FundingImgVO> fundingImgDetailData(int fno);
	*/
	public List<FundingImgVO> fundingImgDetailData(int fno) {
		return mapper.fundingImgDetailData(fno);
	}
	
	/*
	@Select("SELECT * FROM funding_reward WHERE fno=#{fno}")
	public List<FundingRewardVO> fundingRewardDetailData(int fno); 
	 */
	public List<FundingRewardVO> fundingRewardDetailData(int fno) {
		return mapper.fundingRewardDetailData(fno);
	}
	
	/*
 	@Insert("INSERT INTO gitsta_feed (no, fno, projectname, filename, userid, type, regdate, modifydate, content, filecount) "
          + "SELECT gf_no_seq.nextval, f.fno, f.title, f.thumb, #{userId}, 2, sysdate, sysdate, #{content}, 1 "
          + "FROM funding f "
          + "WHERE f.fno = #{fno} "
          + "AND NOT EXISTS ("
          + "    SELECT 1 "
          + "    FROM gitsta_feed gf "
          + "    WHERE gf.fno = f.fno AND gf.userid = #{userId}"
          + ")")
	public void fundingBackingInsert(Map map); 
	 */
	public void fundingBackingInsert(Map map) {
		mapper.fundingBackingInsert(map);
	}
	
	/*
	@Update("UPDATE funding SET "
			+ "backing=backing+1 "
			+ "WHERE fno=#{fno}")
	public void fundingBackingInce(int fno); 
	 */
	public void fundingBackingInce(int fno) {
		mapper.fundingBackingInce(fno);
	}
	
	/*
		// 펀딩 프로젝트 생성
		@Insert("INSERT INTO funding (fno, title, description, type, thumb, targetprice, totalprice, headcount, period, wish, backing, p_admin, link, startdate, enddate, alert) " 
		        + "VALUES(funding_fno_seq.nextval, #{title}, #{description}, #{type}, #{thumb}, #{targetprice}, #{totalprice}, #{headcount}, #{period}, #{wish}, #{backing}, #{p_admin}, #{link}, #{startdate}, #{enddate}, #{alert})")
		public void funding_insert();
	 */
	public void funding_insert() {
		mapper.funding_insert();
	}

	/*
		// 펀딩 프로젝트 생성의 이미지 등록
		@Insert("INSERT INTO funding_img (fno, image, order_num) "
	        	+ "VALUES ((SELECT MAX(fno) FROM funding), #{image}, #{orderNum})")
	 */
    public void fundingImgInsert(Map map) {
    	mapper.fundingImgInsert(map);
    }
//    public void funding_rewardInsert(Map map) {
//    	mapper.funding_rewardInsert(map);
//    }
}
