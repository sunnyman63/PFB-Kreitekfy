package com.kreitek.kreitekfy.userSong.application.service.impl;

import com.kreitek.kreitekfy.userSong.application.dto.UserSongDTO;
import com.kreitek.kreitekfy.userSong.application.dto.UserSongSimpleDTO;
import com.kreitek.kreitekfy.userSong.application.mapper.UserSongMapper;
import com.kreitek.kreitekfy.userSong.application.service.UserSongService;
import com.kreitek.kreitekfy.userSong.domain.entity.UserSong;
import com.kreitek.kreitekfy.userSong.domain.persistence.UserSongPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserSongServiceImpl implements UserSongService {

    private final UserSongPersistence userSongPersistence;
    private final UserSongMapper userSongMapper;

    @Autowired
    public UserSongServiceImpl(UserSongPersistence userSongPersistence, UserSongMapper userSongMapper) {
        this.userSongPersistence = userSongPersistence;
        this.userSongMapper = userSongMapper;
    }

    @Override
    @Transactional
    public UserSongSimpleDTO saveUserSong(UserSongSimpleDTO userSongSimpleDTO) {
        userSongSimpleDTO.setPersonalViews(1L);
        UserSong userSong = this.userSongPersistence.saveUserSong(this.userSongMapper.toEntity(userSongSimpleDTO));
        return this.userSongMapper.toSimpleDto(userSong);
    }

    @Override
    @Transactional
    public UserSongSimpleDTO updateUserSong(UserSongSimpleDTO userSongSimpleDTO) {

        Optional<UserSong> updatedUserSong = this.userSongPersistence.getUserSongById(userSongSimpleDTO.getId());

        Long totalUserViews = updatedUserSong.get().getPersonalViews()+1;
        userSongSimpleDTO.setPersonalViews(totalUserViews);
        UserSong userSong = this.userSongPersistence.updateUserSong(this.userSongMapper.toEntity(userSongSimpleDTO));
        return this.userSongMapper.toSimpleDto(userSong);
    }

    @Override
    @Transactional
    public UserSongSimpleDTO updateUserSongRating(UserSongSimpleDTO userSongSimpleDTO) {

        UserSong userSong = this.userSongPersistence.updateUserSongRating(
                this.userSongMapper.toEntity(userSongSimpleDTO));

        return this.userSongMapper.toSimpleDto(userSong);
    }

    @Override
    public Optional<UserSongDTO> getUserSongById(Long id) {
        return this.userSongPersistence
                .getUserSongById(id)
                .map(userSongMapper::toDto);
    }

    @Override
    public Boolean existUserSongBySongIdAndUserId(Long idSong, Long idUser) {
        return this.userSongPersistence.existUserSongBySongIdAndUserId(idSong, idUser);
    }

    @Override
    public UserSongSimpleDTO findUserSongBySongIdAndUserId(Long idSong, Long idUser ) {
        UserSong userSongsimple = this.userSongPersistence.findUserSongBySongIdAndUserId(idSong, idUser);
        return this.userSongMapper.toSimpleDto(userSongsimple);
    }

    @Override
    public List<UserSongDTO> getAllUserSongBySong_Id(Long id) {
        List<UserSong> userSongDTOS = this.userSongPersistence.getUserSongBySong_Id(id);
        return this.userSongMapper.toDto(userSongDTOS);
    }
}
