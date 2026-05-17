package com.soobin.board_back.entity;

import com.soobin.board_back.entity.primaryKey.FavoritePk;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "favorite")
@Table(name="favorite")
@IdClass(FavoritePk.class)
public class FavoriteEntity {
    

    @Id
    private String userEmail;

    @Id
    private int boardNumber;
     
}
