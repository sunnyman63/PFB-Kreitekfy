package com.kreitek.kreitekfy.userSong.infraestructure.persistence;

import com.kreitek.kreitekfy.userSong.application.dto.UserSongDTO;
import com.kreitek.kreitekfy.userSong.domain.entity.UserSong;
import com.kreitek.kreitekfy.userSong.domain.persistence.UserSongPersistence;
import com.kreitek.kreitekfy.userSong.infraestructure.persistence.UserSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserSongPersistenceImpl implements UserSongPersistence {

    private final UserSongRepository userSongRepository;

    @Autowired
    public UserSongPersistenceImpl(UserSongRepository userSongRepository) {
        this.userSongRepository = userSongRepository;
    }

    @Override
    public UserSong saveUserSong(UserSong userSong) {

            return this.userSongRepository.save(userSong);
    }

    @Override
    public Boolean existUserSongBySongIdAndUserId(Long idSong, Long idUser){

        return this.userSongRepository.existsBySong_IdAndUser_Id(idSong, idUser);
    }

    @Override
    public UserSong findUserSongBySongIdAndUserId(Long idSong, Long idUser){
        return this.userSongRepository.findBySong_IdAndUser_Id(idSong, idUser);
    }

    @Override
    public UserSong updateUserSong(UserSong userSong) {

        UserSong updatedUserSong;
        if(checkIfExists(userSong)){

            updatedUserSong = this.userSongRepository.getById(userSong.getId());
            updatedUserSong.setPersonalViews(this.userSongRepository.getById(userSong.getId()).getPersonalViews()+1L);
            this.userSongRepository.save(updatedUserSong);
            return updatedUserSong;
        }else return null;
    }

    public boolean checkIfExists(UserSong userSong){


        if (this.userSongRepository.existsById(userSong.getId()) &&
                this.userSongRepository.getById(userSong.getId()).getUser().getId().equals(userSong.getUser().getId())){
            return true;
        }else return false;

    }

    @Override
    public UserSong updateUserSongRating(UserSong userSong) {

        UserSong updatedUserSong;

        if (checkIfExists(userSong)){

            updatedUserSong = this.userSongRepository.getById(userSong.getId());
            updatedUserSong.setPersonalValorations(userSong.getPersonalValorations());
            return updatedUserSong;

        }
        return null;
    }


    @Override
    public Optional<UserSong> getUserSongById(Long id) {
        return this.userSongRepository.findById(id);
    }

    @Override
    public List<UserSong> findSongByUserId(Long idUser) {

        return this.userSongRepository.getUserSongByUser_Id(idUser);
    }

    @Override
    public List<UserSong> getUserSongBySong_Id(Long id) {
        return this.userSongRepository.getUserSongBySong_Id(id);
    }
}
