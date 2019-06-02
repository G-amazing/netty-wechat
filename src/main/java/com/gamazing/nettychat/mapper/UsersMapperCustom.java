package com.gamazing.nettychat.mapper;

import com.gamazing.nettychat.vo.FriendRequestVO;
import com.gamazing.nettychat.vo.MyFriendsVO;
import java.util.List;

public interface UsersMapperCustom {
	List<FriendRequestVO> queryFriendRequestList(String acceptUserId);
	List<MyFriendsVO> queryMyFriends(String userId);
	void batchUpdateMsgSigned(List<String> msgIdList);
}