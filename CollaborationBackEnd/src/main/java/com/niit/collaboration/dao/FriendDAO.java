package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.Friend;

public interface FriendDAO {
	
	public List<Friend> list();

	public List<Friend> getByUser(int userid);

	public List<Friend> getByName(String username);

	public List<Friend> getByFriendName(String username);

	public void save(Friend friend);

	public Friend saveOrUpdate(Friend friend);

	public List<Friend> getByFriendAccepted(String friendname);

    public List<Friend> list(int userid);

    public void delete(int friendid);

    public Friend getByFriendId(int friendid);
    
    public List<Friend> getByFriendAccepted1(String name);
}
