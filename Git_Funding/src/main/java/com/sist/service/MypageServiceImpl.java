package com.sist.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.MypageDAO;
import com.sist.vo.AlertVO;
import com.sist.vo.FundingDetailCommVO;
import com.sist.vo.FundingVO;
import com.sist.vo.MemberVO;
import com.sist.vo.QnaVO;
import com.sist.vo.RewardBuyVO;
import com.sist.vo.WishVO;

@Service
public class MypageServiceImpl implements MypageService{
	@Autowired
	private MypageDAO mDao;

	@Override
	public MemberVO mypageInfoData(String userId) {
		return mDao.mypageInfoData(userId);
	}

	@Override
	public void SetNickname(Map map) {
		mDao.SetNickname(map);
	}

	@Override
	public void mypageInfoUpdate(MemberVO vo) {
		mDao.mypageInfoUpdate(vo);
		
	}

	@Override
	public List<WishVO> fundingWishList(String userId) {
		return mDao.fundingWishList(userId);
	}

	@Override
	public int fundingWishCount(String userId) {
		return mDao.fundingWishCount(userId);
	}

	@Override
	public List<AlertVO> fundingAlertList(String userId) {
		return mDao.fundingAlertList(userId);
	}

	@Override
	public int fundingAlertCount(String userId) {
		return mDao.fundingAlertCount(userId);
	}

	@Override
	public List<RewardBuyVO> getRewardBuyList(Map map) {
		return mDao.getRewardBuyList(map);
	}

	@Override
	public int getTotalRewardBuyCount(String userId) {
		return mDao.getTotalRewardBuyCount(userId);
	}

	@Override
	public RewardBuyVO getPurchaseDetail(int rbno) {
		return mDao.getPurchaseDetail(rbno);
	}

	@Override
	public int goodsBuyTotalCount(String userId) {
		return mDao.goodsBuyTotalCount(userId);
	}

	@Override
	public List<FundingVO> myFundingListData(Map map) {
		return mDao.myFundingListData(map);
	}

	@Override
	public int myFundingTotalCount(String userId) {
		return mDao.myFundingTotalCount(userId);
	}

	@Override
	public int myFundingTotalPage(String userId) {
		return mDao.myFundingTotalPage(userId);
	}

	@Override
	public List<FundingVO> myFundingTotalData(String userId) {
		return mDao.myFundingTotalData(userId);
	}

	@Override
	public List<FundingDetailCommVO> myFundingCommuList(Map map) {
		return mDao.myFundingCommuList(map);
	}

	@Override
	public int myFundingCommuTotalPage(String userId) {
		return mDao.myFundingCommuTotalPage(userId);
	}

	@Override
	public int myFundingCommuListCount(String userId) {
		return mDao.myFundingCommuListCount(userId);
	}

	@Override
	public List<QnaVO> myQnaListData(Map map) {
		return mDao.myQnaListData(map);
	}

	@Override
	public int myQnaTotalPage(String userId) {
		return mDao.myQnaTotalPage(userId);
	}

	@Override
	public int myQnaTotalCount(String userId) {
		return mDao.myQnaTotalCount(userId);
	}

}
