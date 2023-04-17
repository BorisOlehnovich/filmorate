package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {
    private final UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage){
        this.userStorage = userStorage;
    }

    public User createUser (User user){
       return userStorage.createUser(user);
    }

    public User updateUser (User user){
        return userStorage.updateUser(user);
    }

    public Collection<User> findAll(){
        return userStorage.findAll();
    }

    public User getUserById(int id){
        return userStorage.getUserById(id);
    }

    public User deleteUserById(int id){
        return userStorage.deleteUserById(id);
    }

    public void addFriends(int userId, int friendId){
        User user = userStorage.getUserById(userId);
        User friend = userStorage.getUserById(friendId);

        user.getFriendsIds().add(friend.getId());
        friend.getFriendsIds().add(user.getId());

        userStorage.updateUser(user);
        userStorage.updateUser(friend);
    }

    public void deleteFriends (int userId, int friendId){
        User user = userStorage.getUserById(userId);
        User friend = userStorage.getUserById(friendId);

        user.getFriendsIds().remove(friend.getId());
        friend.getFriendsIds().remove(user.getId());

        userStorage.updateUser(user);
        userStorage.updateUser(friend);
    }

    public List<User> getUserFriends (int id){
        User user = userStorage.getUserById(id);
        List<User> friends = new ArrayList<>();
        for (int friendId : user.getFriendsIds()){
            friends.add(userStorage.getUserById(friendId));
        }
        return friends;
    }

    public List<User> getCommonFriends(int firstUserId, int secondUserId){
        User user1 = userStorage.getUserById(firstUserId);
        User user2 = userStorage.getUserById(secondUserId);

        List<User> result = new ArrayList<>();
        for (int id : user1.getFriendsIds()){
            if (user2.getFriendsIds().contains(id)){
                result.add(userStorage.getUserById(id));
            }
        }
        return result;
    }




}
